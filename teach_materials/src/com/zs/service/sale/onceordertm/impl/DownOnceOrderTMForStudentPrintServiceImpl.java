package com.zs.service.sale.onceordertm.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.domain.basic.Semester;
import com.zs.service.sale.onceordertm.DownOnceOrderTMForStudentPrintService;
import com.zs.service.sale.onceordertm.FindOnceOrderTMForStudentPrintService;
import com.zs.service.sale.studentbookordertm.DownStudentBookOrderTMForStudentPrintService;
import com.zs.service.sale.studentbookordertm.FindStudentBookOrderTMForStudentPrintService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Allen on 2015/8/13.
 */
@Service("downOnceOrderTMForStudentPrintService")
public class DownOnceOrderTMForStudentPrintServiceImpl extends EntityServiceImpl implements DownOnceOrderTMForStudentPrintService {

    @Resource
    private FindOnceOrderTMForStudentPrintService findOnceOrderTMForStudentPrintService;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    public String down(long semesterId, int state, String operateTime, String fileName) throws Exception {
        Semester semester = findNowSemesterDAO.get(semesterId);
        JSONObject result = findOnceOrderTMForStudentPrintService.print(semesterId, state, 0, 0, operateTime, null);
        String title = semester.getSemester()+"  教材发书清单";
        return this.createExcel(result, title, fileName);
    }


    private String createExcel(JSONObject data, String title, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;

        ExcelRow runRow = new ExcelRow(index);
        ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "打印序号",false,"",30);
        ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "学号",false,"",30);
        ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "姓名",false,"",30);
        ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "邮寄地址",false,"",30);
        ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "联系电话",false,"",30);
        ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "邮编",false,"",30);
        runRow.addExcelCell(runCell_1);
        runRow.addExcelCell(runCell_2);
        runRow.addExcelCell(runCell_3);
        runRow.addExcelCell(runCell_4);
        runRow.addExcelCell(runCell_5);
        runRow.addExcelCell(runCell_6);
        sheet.addExcelRow(runRow);
        index ++;
        if(null != data && data.size() > 0){
            Iterator it = data.keys();
            int i = 0;
            while (it.hasNext()){
                String key = it.next().toString();
                JSONObject orderJSON = (JSONObject)data.get(key);
                if(null == orderJSON){
                    orderJSON = new JSONObject();
                }

                String address = null == orderJSON.get("sendAddress") ? "" : orderJSON.get("sendAddress").toString();
                address = StringUtils.isEmpty(address) ? "" : address;
                address = address.replace("|", "");

                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_8 = new ExcelCell(index,(byte)0, (i+1)+"",false,"", 9);
                ExcelCell runCell_9 = new ExcelCell(index,(byte)1, orderJSON.get("code").toString(),false,"",9);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)2, orderJSON.get("name").toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)3, address,false,"",9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)4, orderJSON.get("sendPhone").toString(),false,"",9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)5, orderJSON.get("sendZipCode").toString(),false,"",9);

                runRow_2.addExcelCell(runCell_8);
                runRow_2.addExcelCell(runCell_9);
                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);
                runRow_2.addExcelCell(runCell_12);
                runRow_2.addExcelCell(runCell_13);

                sheet.addExcelRow(runRow_2);
                index ++;
                i++;
            }
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)1500);
        columnWidthMap.put((short)1, (short)11000);
        columnWidthMap.put((short)2, (short)7000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)5000);
        columnWidthMap.put((short)5, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}
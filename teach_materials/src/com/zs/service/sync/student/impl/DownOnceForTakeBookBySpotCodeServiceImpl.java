package com.zs.service.sync.student.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.domain.sale.StudentBookOnceOrder;
import com.zs.domain.sync.Spot;
import com.zs.service.sync.spot.FindSpotByCodeService;
import com.zs.service.sync.student.DownOnceForTakeBookBySpotCodeService;
import com.zs.tools.DateTools;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/9/10.
 */
@Service("downOnceForTakeBookBySpotCodeService")
public class DownOnceForTakeBookBySpotCodeServiceImpl extends EntityServiceImpl implements DownOnceForTakeBookBySpotCodeService {

    @Resource
    private FindListByWhereDAO findOnceForTakeBookBySpotCodeDAO;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    @Transactional
    public String down(String spotCode, String fileName) throws Exception {

        Spot spot = findSpotByCodeService.getSpotByCode(spotCode);
        if(null == spot){
            throw new BusinessException("没有找到"+spot+"学习中心");
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("semesterId", findNowSemesterDAO.getNowSemester().getId()+"");
        params.put("spotCode", spotCode);
        params.put("state", StudentBookOnceOrder.STATE_SEND+"");
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.code", true);
        List<Object[]> studentList = findOnceForTakeBookBySpotCodeDAO.findListByWhere(params, sortMap);

        return this.createExcel(studentList, "缴费领书学生信息表", spot.getName(), spotCode, fileName);
    }


    private String createExcel(List<Object[]> data, String title, String spotName, String spotCode, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;

        ExcelRow row_1 = new ExcelRow(index);
        sheet.addExcelRow(row_1);
        ExcelCell cell_1 = new ExcelCell(index, (byte)0,0,(byte)6,title,false,"",30);
        cell_1.setIfleft(false);
        row_1.addExcelCell(cell_1);
        index ++;

        ExcelRow row_2 = new ExcelRow(index);
        ExcelCell cell_2 = new ExcelCell(index, (byte)0, 0, (byte)2,"学校名称：",false,"",30);
        ExcelCell cell_3 = new ExcelCell(index, (byte)3, 0, (byte)1,spotName,false,"",30);
        ExcelCell cell_4 = new ExcelCell(index, (byte)5, 0, (byte)1, DateTools.getFormattedString(DateTools.getShortNowTime(), "yyyy-MM-dd"),false,"",30);
        row_2.addExcelCell(cell_2);
        row_2.addExcelCell(cell_3);
        row_2.addExcelCell(cell_4);
        sheet.addExcelRow(row_2);
        index ++;

        if(null != data && data.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "序号",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "层次",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "专业",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "学号",false,"",30);
            ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "姓名",false,"",30);
            ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "入学期",false,"",30);
            ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "联系电话",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            runRow.addExcelCell(runCell_6);
            runRow.addExcelCell(runCell_7);
            sheet.addExcelRow(runRow);
            index ++;
            for(int i = 0; i < data.size(); i++){
                Object[] objs = data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);

                ExcelCell runCell_8 = new ExcelCell(index,(byte)0, (i+1)+"",false,"", 9);
                ExcelCell runCell_9 = new ExcelCell(index,(byte)1, objs[0].toString(),false,"",9);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)2, objs[1].toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)3, objs[2].toString(),false,"",9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)4, objs[3].toString(),false,"",9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)5, objs[4].toString()+objs[5].toString()+"季",false,"",9);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)6, (null == objs[7] ? "" : objs[7].toString())+"  "+(null == objs[6] ? "" : objs[6].toString()),false,"",9);

                runRow_2.addExcelCell(runCell_8);
                runRow_2.addExcelCell(runCell_9);
                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);
                runRow_2.addExcelCell(runCell_12);
                runRow_2.addExcelCell(runCell_13);
                runRow_2.addExcelCell(runCell_14);

                sheet.addExcelRow(runRow_2);

                index ++;
            }
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)2000);
        columnWidthMap.put((short)1, (short)3000);
        columnWidthMap.put((short)2, (short)7000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)5000);
        columnWidthMap.put((short)5, (short)5000);
        columnWidthMap.put((short)6, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

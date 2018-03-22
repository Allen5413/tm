package com.zs.service.finance.studentexpensebuy.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.sync.student.StudentDAO;
import com.zs.service.finance.studentexpensebuy.DownStuEBService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/10/14.
 */
@Service("downStuEBService")
public class DownStuEBServiceImpl extends EntityServiceImpl implements DownStuEBService {

    @Resource
    private StudentDAO studentDAO;

    @Override
    @Transactional
    public String down(String code, String fileName) throws Exception {
        //查询学生的购书明细
        List<Object[]> list = studentDAO.findOrderTmByStudentCode(code);
        return this.createExcel(list, fileName);
    }


    private String createExcel(List<Object[]> list, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        if(null != list && 0 < list.size()){
            for(int j = 0; j < list.size(); j++){
                Object[] objs = list.get(j);
                String studentName = objs[0].toString();
                String tmName = objs[1].toString();
                String count = objs[2].toString();
                String price = objs[3].toString();
                String totalPrice = objs[4].toString();

                ExcelRow runRow = new ExcelRow(index);
                ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "1111",false,"",9);
                ExcelCell runCell_2 = new ExcelCell(index,(byte)1, studentName,false,"",9);
                ExcelCell runCell_3 = new ExcelCell(index,(byte)2, tmName,false,"", 9);
                ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "本",false,"", 9);
                ExcelCell runCell_5 = new ExcelCell(index,(byte)4, count,false,"", 9);
                ExcelCell runCell_6 = new ExcelCell(index,(byte)5, new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),false,"", 9);
                ExcelCell runCell_7 = new ExcelCell(index,(byte)6, new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),false,"", 9);
                ExcelCell runCell_8 = new ExcelCell(index,(byte)7, "免税",false,"", 9);
                ExcelCell runCell_9 = new ExcelCell(index,(byte)8, "",false,"", 9);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)9, "",false,"", 9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)10, "",false,"", 9);


                runRow.addExcelCell(runCell_1);
                runRow.addExcelCell(runCell_2);
                runRow.addExcelCell(runCell_3);
                runRow.addExcelCell(runCell_4);
                runRow.addExcelCell(runCell_5);
                runRow.addExcelCell(runCell_6);
                runRow.addExcelCell(runCell_7);
                runRow.addExcelCell(runCell_8);
                runRow.addExcelCell(runCell_9);
                runRow.addExcelCell(runCell_10);
                runRow.addExcelCell(runCell_11);
                sheet.addExcelRow(runRow);
                index ++;
            }
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)5000);
        columnWidthMap.put((short)1, (short)5000);
        columnWidthMap.put((short)2, (short)12000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)5000);
        columnWidthMap.put((short)5, (short)5000);
        columnWidthMap.put((short)6, (short)5000);
        columnWidthMap.put((short)7, (short)5000);
        columnWidthMap.put((short)8, (short)5000);
        columnWidthMap.put((short)9, (short)5000);
        columnWidthMap.put((short)10, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

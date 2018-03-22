package com.zs.service.statis.impl;

import com.zs.dao.FindListByWhereDAO;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.domain.basic.Semester;
import com.zs.service.statis.DownSendTeachMaterialService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/12/2.
 */
@Service("downSendTeachMaterialService")
public class DownSendTeachMaterialServiceImpl implements DownSendTeachMaterialService {

    @Resource
    private FindListByWhereDAO findSendTeachMaterialDAO;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    @Transactional
    public String down(String semesterId, String name, String beginDate, String endDate, String tmTypeId, String fileName) throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("semesterId", semesterId);
        paramMap.put("name", name);
        paramMap.put("beginDate", beginDate);
        paramMap.put("endDate", endDate);
        paramMap.put("tmTypeId", tmTypeId);
        List<Object[]> resultList = findSendTeachMaterialDAO.findListByWhere(paramMap, null);

        Semester semester = findNowSemesterDAO.get(Long.parseLong(semesterId));
        String title = semester.getSemester();
        if(!StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)){
            title += beginDate+" - "+endDate+"的发书情况";
        }
        if(StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)){
            title += endDate+"之前的发书情况";
        }
        if(!StringUtils.isEmpty(beginDate) && StringUtils.isEmpty(endDate)){
            title += beginDate+"之后的发书情况";
        }
        if(StringUtils.isEmpty(beginDate) && StringUtils.isEmpty(endDate)){
            title += "的发书情况";
        }
        return this.createExcel(resultList, title, fileName);
    }

    private String createExcel(List<Object[]> data, String title, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        ExcelRow row_1 = new ExcelRow(index);
        sheet.addExcelRow(row_1);
        ExcelCell cell_1 = new ExcelCell(index, (byte)0, 0, (byte)4, title, false, "", 30);
        row_1.addExcelCell(cell_1);
        index ++;
        if(null != data && data.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "教材名称",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "出版社",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "作者",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "发书时的价格",false,"",30);
            ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "数量",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            sheet.addExcelRow(runRow);
            index ++;
            for(int i = 0; i < data.size(); i++){
                Object[] objs = data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_7 = new ExcelCell(index,(byte)0, objs[0].toString(),false,"",9);
                ExcelCell runCell_8 = new ExcelCell(index,(byte)1, objs[1].toString(),false,"",9);
                ExcelCell runCell_9 = new ExcelCell(index,(byte)2, objs[2].toString(),false,"", 9);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)3, objs[3].toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)4, objs[4].toString(),false,"",9);

                runRow_2.addExcelCell(runCell_7);
                runRow_2.addExcelCell(runCell_8);
                runRow_2.addExcelCell(runCell_9);
                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);

                sheet.addExcelRow(runRow_2);

                index ++;
            }
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)11000);
        columnWidthMap.put((short)1, (short)8000);
        columnWidthMap.put((short)2, (short)11000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

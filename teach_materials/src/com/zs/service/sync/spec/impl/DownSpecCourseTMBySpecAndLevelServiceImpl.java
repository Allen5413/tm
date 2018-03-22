package com.zs.service.sync.spec.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.sync.spec.SpecCourseDAO;
import com.zs.domain.sync.SpecCourse;
import com.zs.service.sync.spec.DownSpecCourseTMBySpecAndLevelService;
import com.zs.service.sync.spec.FindSpecCourseTMBySpecAndLevelService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Allen on 2016/10/21.
 */
@Service("downSpecCourseTMBySpecAndLevelService")
public class DownSpecCourseTMBySpecAndLevelServiceImpl extends EntityServiceImpl
        implements DownSpecCourseTMBySpecAndLevelService {

    @Resource
    private FindSpecCourseTMBySpecAndLevelService findSpecCourseTMBySpecAndLevelService;
    @Resource
    private SpecCourseDAO specCourseDAO;

    @Override
    @Transactional
    public String down(String specCode, String levelCode, String fileName) throws Exception {
        JSONObject json = findSpecCourseTMBySpecAndLevelService.find(specCode, levelCode);

        return this.createExcel(json, "课程教材对照表", fileName);
    }

    @Override
    @Transactional
    public File[] downZip(String fileName)throws Exception{
        //查询得到所有专业对应层次
        List<SpecCourse> specCourseList = specCourseDAO.findSpecLevel();
        List<String> fileNames = new ArrayList();// 用于存放生成的文件名称
        for(SpecCourse specCourse : specCourseList){
            String xlsName = fileName + specCourse.getSpecCode() + SpecEnum.getDescn(specCourse.getSpecCode()) + LevelEnum.getDescn(specCourse.getLevelCode())+".xls";
            this.down(specCourse.getSpecCode(), specCourse.getLevelCode(), xlsName);
            fileNames.add(xlsName);
        }
        File[] srcfile = new File[fileNames.size()];
        for (int i = 0, n1 = fileNames.size(); i < n1; i++) {
            srcfile[i] = new File(fileNames.get(i));
        }
        return srcfile;
    }


    private String createExcel(JSONObject json, String title, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        ExcelRow row_1 = new ExcelRow(0);
        sheet.addExcelRow(row_1);
        ExcelCell cell_1 = new ExcelCell(0, (byte)0,0,(byte)8,title,false,"",30);
        row_1.addExcelCell(cell_1);
        index ++;
        if(null != json && json.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "课程编号",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "课程名称",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "教材名称",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "作者",false,"",30);
            ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "出版社",false,"",30);
            ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "ISBN",false,"",30);
            ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "价格",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            runRow.addExcelCell(runCell_6);
            runRow.addExcelCell(runCell_7);
            sheet.addExcelRow(runRow);
            index ++;
            Set keys = json.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                String courseCode = key.substring(0, key.indexOf("_"));
                String courseName = key.substring(key.indexOf("_")+1, key.length());
                JSONArray jsonArray = (JSONArray)json.get(key);
                if(null == jsonArray || 1 > jsonArray.size()){
                    ExcelRow runRow_2 = new ExcelRow(index);
                    ExcelCell runCell_10 = new ExcelCell(index,(byte)0, courseCode,false,"",9);
                    ExcelCell runCell_11 = new ExcelCell(index,(byte)1, courseName,false,"", 9);
                    ExcelCell runCell_12 = new ExcelCell(index,(byte)2, "",false,"",9);
                    ExcelCell runCell_13 = new ExcelCell(index,(byte)3, "",false,"",9);
                    ExcelCell runCell_14 = new ExcelCell(index,(byte)4, "",false,"",9);
                    ExcelCell runCell_15 = new ExcelCell(index,(byte)5, "",false,"",9);
                    ExcelCell runCell_16 = new ExcelCell(index,(byte)6, "",false,"",9);

                    runRow_2.addExcelCell(runCell_10);
                    runRow_2.addExcelCell(runCell_11);
                    runRow_2.addExcelCell(runCell_12);
                    runRow_2.addExcelCell(runCell_13);
                    runRow_2.addExcelCell(runCell_14);
                    runRow_2.addExcelCell(runCell_15);
                    runRow_2.addExcelCell(runCell_16);

                    sheet.addExcelRow(runRow_2);

                    index ++;
                }else{
                    for(int i=0; i<jsonArray.size(); i++){
                        JSONObject tmJSON = (JSONObject) jsonArray.get(i);
                        ExcelRow runRow_2 = new ExcelRow(index);
                        ExcelCell runCell_10 = new ExcelCell(index,(byte)0, courseCode,false,"",9);
                        ExcelCell runCell_11 = new ExcelCell(index,(byte)1, courseName,false,"", 9);
                        ExcelCell runCell_12 = new ExcelCell(index,(byte)2, tmJSON.get("tmName").toString(),false,"",9);
                        ExcelCell runCell_13 = new ExcelCell(index,(byte)3, tmJSON.get("author").toString(),false,"",9);
                        ExcelCell runCell_14 = new ExcelCell(index,(byte)4, tmJSON.get("pName").toString(),false,"",9);
                        ExcelCell runCell_15 = new ExcelCell(index,(byte)5, tmJSON.get("isbn").toString(),false,"",9);
                        ExcelCell runCell_16 = new ExcelCell(index,(byte)6, tmJSON.get("price").toString(),false,"",9);

                        runRow_2.addExcelCell(runCell_10);
                        runRow_2.addExcelCell(runCell_11);
                        runRow_2.addExcelCell(runCell_12);
                        runRow_2.addExcelCell(runCell_13);
                        runRow_2.addExcelCell(runCell_14);
                        runRow_2.addExcelCell(runCell_15);
                        runRow_2.addExcelCell(runCell_16);

                        sheet.addExcelRow(runRow_2);

                        index ++;
                    }
                }

            }

            index ++;
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)3000);
        columnWidthMap.put((short)1, (short)11000);
        columnWidthMap.put((short)2, (short)7000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)10000);
        columnWidthMap.put((short)5, (short)3000);
        columnWidthMap.put((short)6, (short)3000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

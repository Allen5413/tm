package com.zs.service.basic.courseteachmaterial.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.service.basic.courseteachmaterial.DownCourseTeachMaterialService;
import com.zs.service.basic.courseteachmaterial.FindPageByWhereService;
import com.zs.service.basic.semester.FindNowSemesterService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/12/16.
 */
@Service("downCourseTeachMaterialService")
public class DownCourseTeachMaterialServiceImpl extends EntityServiceImpl implements DownCourseTeachMaterialService{

    @Resource
    private FindPageByWhereDAO findCourseTeachMaterialPageByWhereDAO;

    @Override
    @Transactional
    public String down(PageInfo pageInfo, Map<String, String> paramsMap, Map<String, Boolean> sortMap, String fileNmame) throws Exception {
        pageInfo.setCountOfCurrentPage(9999999);
        pageInfo = findCourseTeachMaterialPageByWhereDAO.findPageByWhere(pageInfo, paramsMap, sortMap);
        List<JSONObject> resultList = new ArrayList<JSONObject>();
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            for(Object[] objs : list){
                String courseCode = objs[0].toString();
                String courseName = objs[1].toString();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("courseCode", courseCode);
                jsonObject.put("courseName", courseName);
                jsonObject.put("tmName", null == objs[2] ? "" : objs[2]);
                jsonObject.put("author", null == objs[3] ? "" : objs[3]);
                jsonObject.put("price", null == objs[4] ? "" : objs[4]);
                jsonObject.put("pName", null == objs[5] ? "" : objs[5]);
                resultList.add(jsonObject);
            }
        }
        return this.createExcel(resultList, fileNmame);
    }

    private String createExcel(List<JSONObject> resultList, String fileNmame)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);

        int index = 0;
        if(null != resultList && resultList.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "课程编号",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "课程名称",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "教材名称",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "出版社",false,"",30);
            ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "作者",false,"",30);
            ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "单价",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            runRow.addExcelCell(runCell_6);
            sheet.addExcelRow(runRow);
            index ++;
            for(int i = 0; i < resultList.size(); i++){
                JSONObject jsonObject = resultList.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);

                ExcelCell runCell_10 = new ExcelCell(index,(byte)0, jsonObject.get("courseCode").toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)1, jsonObject.get("courseName").toString(),false,"",9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)2, jsonObject.get("tmName").toString(),false,"",9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)3, jsonObject.get("pName").toString(),false,"",9);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)4, jsonObject.get("author").toString(),false,"",9);
                ExcelCell runCell_15 = new ExcelCell(index,(byte)5, jsonObject.get("price").toString(),false,"",9);


                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);
                runRow_2.addExcelCell(runCell_12);
                runRow_2.addExcelCell(runCell_13);
                runRow_2.addExcelCell(runCell_14);
                runRow_2.addExcelCell(runCell_15);

                sheet.addExcelRow(runRow_2);

                index ++;
            }
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)8000);
        columnWidthMap.put((short)1, (short)8000);
        columnWidthMap.put((short)2, (short)10000);
        columnWidthMap.put((short)3, (short)10000);
        columnWidthMap.put((short)4, (short)10000);
        columnWidthMap.put((short)5, (short)5000);
        return ExcelTools.writeExcelFile(exo,columnWidthMap, fileNmame);
    }
}

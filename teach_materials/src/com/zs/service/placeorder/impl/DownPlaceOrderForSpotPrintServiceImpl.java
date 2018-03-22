package com.zs.service.placeorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.domain.basic.Semester;
import com.zs.service.placeorder.DownPlaceOrderForSpotPrintService;
import com.zs.service.placeorder.FindPlaceOrderForSpotPrintService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2015/8/18.
 */
@Service("downPlaceOrderForSpotPrintService")
public class DownPlaceOrderForSpotPrintServiceImpl extends EntityServiceImpl implements DownPlaceOrderForSpotPrintService {

    @Resource
    private FindPlaceOrderForSpotPrintService findPlaceOrderForSpotPrintService;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;

    @Override
    public String down(String spotCode, String semesterId, String state, String fileName) throws Exception {

        Semester semester = findNowSemesterDAO.get(Long.parseLong(semesterId));

        Map<String, String> params = new HashMap<String, String>();
        params.put("semesterId", semesterId);
        params.put("spotCode", spotCode);
        params.put("state", state);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("deptName", true);
        JSONArray jsonArray = findPlaceOrderForSpotPrintService.findPlaceOrderForSpotPrint(params, sortMap);

        String title="";
        if(null != jsonArray && 0 < jsonArray.size()){
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            title += jsonObject.get("spotCode").toString() + jsonObject.get("spotName").toString();
        }
        title += "  "+semester.getSemester()+"  发书单";

        return this.createExcel(jsonArray, title, fileName);
    }


    private String createExcel(JSONArray data, String title, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        ExcelRow row_1 = new ExcelRow(0);
        sheet.addExcelRow(row_1);
        ExcelCell cell_1 = new ExcelCell(0, (byte)0,0,(byte)5,title,false,"",30);
        row_1.addExcelCell(cell_1);
        index ++;
        if(null != data && data.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "学院",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "教材名称",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "作者",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "数量",false,"",30);
            ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "单价",false,"",30);
            ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "总价",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            runRow.addExcelCell(runCell_6);
            sheet.addExcelRow(runRow);
            index ++;
            for(int i = 0; i < data.size(); i++){
                JSONObject jsonObject = (JSONObject)data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_7 = new ExcelCell(index,(byte)0, jsonObject.get("deptName").toString(),false,"",9);
                ExcelCell runCell_8 = new ExcelCell(index,(byte)1, jsonObject.get("tmName").toString(),false,"", 9);
                ExcelCell runCell_9 = new ExcelCell(index,(byte)2, jsonObject.get("author").toString(),false,"",9);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)3, jsonObject.get("count").toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)4, jsonObject.get("price").toString(),false,"",9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)5, jsonObject.get("totalPrice").toString(),false,"",9);

                runRow_2.addExcelCell(runCell_7);
                runRow_2.addExcelCell(runCell_8);
                runRow_2.addExcelCell(runCell_9);
                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);
                runRow_2.addExcelCell(runCell_12);

                sheet.addExcelRow(runRow_2);

                index ++;
            }
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)7000);
        columnWidthMap.put((short)1, (short)11000);
        columnWidthMap.put((short)2, (short)7000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)5000);
        columnWidthMap.put((short)5, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

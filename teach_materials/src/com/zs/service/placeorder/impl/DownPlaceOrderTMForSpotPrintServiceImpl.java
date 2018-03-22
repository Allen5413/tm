package com.zs.service.placeorder.impl;

import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.semester.FindNowSemesterDAO;
import com.zs.domain.basic.Semester;
import com.zs.domain.sync.Spot;
import com.zs.service.placeorder.DownPlaceOrderTMForSpotPrintService;
import com.zs.service.placeorder.FindPlaceOrderTMForSpotPrintService;
import com.zs.service.sync.spot.FindSpotByCodeService;
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
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Allen on 2015/8/18.
 */
@Service("downPlaceOrderTMForSpotPrintService")
public class DownPlaceOrderTMForSpotPrintServiceImpl extends EntityServiceImpl implements DownPlaceOrderTMForSpotPrintService {

    @Resource
    private FindPlaceOrderTMForSpotPrintService findPlaceOrderTMForSpotPrintService;
    @Resource
    private FindNowSemesterDAO findNowSemesterDAO;
    @Resource
    private FindSpotByCodeService findSpotByCodeService;

    @Override
    public String down(String spotCode, String semesterId, String state, String fileName) throws Exception {

        Semester semester = findNowSemesterDAO.get(Long.parseLong(semesterId));
        Spot spot = findSpotByCodeService.getSpotByCode(spotCode);

        Map<String, String> params = new HashMap<String, String>();
        params.put("semesterId", semesterId);
        params.put("spotCode", spotCode);
        params.put("state", state);
        JSONObject result = findPlaceOrderTMForSpotPrintService.findPlaceOrderTMForSpotPrint(null, params, null);
        String title = semester.getSemester()+"  教材发书清单";
        String spotStr = "学习中心：("+spot.getCode()+")"+spot.getName();
        return this.createExcel(result, title, spotStr, fileName);
    }


    private String createExcel(JSONObject data, String title, String spotStr, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        if(null != data && data.size() > 0){
            Iterator it = data.keys();
            while (it.hasNext()){
                String key = it.next().toString();
                JSONObject orderJSON = (JSONObject)data.get(key);
                if(null == orderJSON){
                    orderJSON = new JSONObject();
                }

                ExcelRow row_1 = new ExcelRow(index);
                sheet.addExcelRow(row_1);
                ExcelCell cell_1 = new ExcelCell(index, (byte)0, 0, (byte)6, title, false, "", 30);
                row_1.addExcelCell(cell_1);
                index ++;

                ExcelRow row_2 = new ExcelRow(index);
                sheet.addExcelRow(row_2);
                ExcelCell cell_2 = new ExcelCell(index, (byte)0, 0, (byte)6, spotStr, false, "", 30);
                row_2.addExcelCell(cell_2);
                index ++;

                String specStr = "专业："+orderJSON.get("specName");
                ExcelRow row_3 = new ExcelRow(index);
                sheet.addExcelRow(row_3);
                ExcelCell cell_3 = new ExcelCell(index, (byte)0,0,(byte)6,specStr,false,"",30);
                row_3.addExcelCell(cell_3);
                index ++;

                String nameCodeLevel = "入学年："+orderJSON.get("enYear")+"    入学季："+("0".equals(orderJSON.get("enQuarter").toString()) ? "春" : "秋")+"    层次："+orderJSON.get("levelName");
                ExcelRow row_4 = new ExcelRow(index);
                sheet.addExcelRow(row_4);
                ExcelCell cell_4 = new ExcelCell(index, (byte)0,0,(byte)6,nameCodeLevel,false,"",30);
                row_4.addExcelCell(cell_4);
                index ++;

                ExcelRow runRow = new ExcelRow(index);
                ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "序号",false,"",30);
                ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "课程名称",false,"",30);
                ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "教材名称",false,"",30);
                ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "作者",false,"",30);
                ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "单价",false,"",30);
                ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "数量",false,"",30);
                ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "总价",false,"",30);
                runRow.addExcelCell(runCell_1);
                runRow.addExcelCell(runCell_2);
                runRow.addExcelCell(runCell_3);
                runRow.addExcelCell(runCell_4);
                runRow.addExcelCell(runCell_5);
                runRow.addExcelCell(runCell_6);
                runRow.addExcelCell(runCell_7);
                sheet.addExcelRow(runRow);
                index ++;
                for(int i = 0; i < ((JSONArray)orderJSON.get("orderTM")).size(); i++){
                    JSONObject jsonObject = (JSONObject)((JSONArray)orderJSON.get("orderTM")).get(i);
                    ExcelRow runRow_2 = new ExcelRow(index);
                    ExcelCell runCell_8 = new ExcelCell(index,(byte)0, (i+1)+"",false,"", 9);
                    ExcelCell runCell_9 = new ExcelCell(index,(byte)1, "【"+jsonObject.get("courseCode")+"】"+jsonObject.get("courseName"),false,"",9);
                    ExcelCell runCell_10 = new ExcelCell(index,(byte)2, jsonObject.get("tmName").toString(),false,"",9);
                    ExcelCell runCell_11 = new ExcelCell(index,(byte)3, jsonObject.get("author").toString(),false,"",9);
                    ExcelCell runCell_12 = new ExcelCell(index,(byte)4, jsonObject.get("price").toString(),false,"",9);
                    ExcelCell runCell_13 = new ExcelCell(index,(byte)5, jsonObject.get("count").toString(),false,"",9);
                    ExcelCell runCell_14 = new ExcelCell(index,(byte)6, (Double)jsonObject.get("price")*(Integer)jsonObject.get("count")+"",false,"",9);

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

                index ++;
                index ++;
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
        columnWidthMap.put((short)6, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

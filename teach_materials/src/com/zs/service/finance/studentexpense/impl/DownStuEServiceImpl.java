package com.zs.service.finance.studentexpense.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.config.LevelEnum;
import com.zs.config.SpecEnum;
import com.zs.dao.FindPageByWhereDAO;
import com.zs.service.finance.studentexpense.DownStuEService;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/10/14.
 */
@Service("downStuEService")
public class DownStuEServiceImpl extends EntityServiceImpl implements DownStuEService {

    @Resource
    private FindPageByWhereDAO findStuEPageByWhereDao;

    @Override
    @Transactional
    public String down(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap, String fileName) throws Exception {
        double totalPayPrice = 0;
        double totalBuyPrice = 0;
        double totalAccPrice = 0;

        JSONArray jsonArray = new JSONArray();
        pageInfo = findStuEPageByWhereDao.findPageByWhere(pageInfo, map, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Object[]> list = pageInfo.getPageResults();
            for(Object[] objs : list){
                //JSON日期转换必须要传bean，所以这里先把日期format后在设置值
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("provCode", objs[0]);
                jsonObject.put("provName", objs[1]);
                jsonObject.put("spotCode", objs[2]);
                jsonObject.put("spotName", objs[3]);
                jsonObject.put("code", objs[4]);
                jsonObject.put("name", objs[5]);
                jsonObject.put("specName", SpecEnum.getDescn(objs[6]+""));
                jsonObject.put("levelName", LevelEnum.getDescn(objs[7]+""));
                jsonObject.put("pay", objs[8]);
                jsonObject.put("buy", objs[9]);
                jsonObject.put("acc", new BigDecimal((Double)objs[8]).subtract(new BigDecimal((Double)objs[9])).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
                jsonArray.add(jsonObject);
                totalPayPrice = new BigDecimal(totalPayPrice).add(new BigDecimal(objs[8].toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                totalBuyPrice = new BigDecimal(totalBuyPrice).add(new BigDecimal(objs[9].toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                totalAccPrice = new BigDecimal(totalAccPrice).add(new BigDecimal(objs[8].toString()).subtract(new BigDecimal(objs[9].toString()))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        return this.createExcel(jsonArray, "学生费用明细", totalPayPrice, totalBuyPrice, totalAccPrice, fileName);
    }


    private String createExcel(JSONArray data, String title, double totalPayPrice, double totalBuyPrice, double totalAccPrice, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        ExcelRow row_1 = new ExcelRow(0);
        sheet.addExcelRow(row_1);
        ExcelCell cell_1 = new ExcelCell(0, (byte)0,0,(byte)8,title,false,"",30);
        row_1.addExcelCell(cell_1);
        index ++;
        if(null != data && data.size() > 0){
            ExcelRow runRow = new ExcelRow(index);
            ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "学习中心编号",false,"",30);
            ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "学习中心名称",false,"",30);
            ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "学号",false,"",30);
            ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "姓名",false,"",30);
            ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "专业",false,"",30);
            ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "层次",false,"",30);
            ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "已缴费金额",false,"",30);
            ExcelCell runCell_8 = new ExcelCell(index,(byte)7, "消费金额",false,"",30);
            ExcelCell runCell_9 = new ExcelCell(index,(byte)8, "余额",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            runRow.addExcelCell(runCell_6);
            runRow.addExcelCell(runCell_7);
            runRow.addExcelCell(runCell_8);
            runRow.addExcelCell(runCell_9);
            sheet.addExcelRow(runRow);
            index ++;
            for(int i = 0; i < data.size(); i++){
                JSONObject jsonObject = (JSONObject)data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)0, jsonObject.get("spotCode").toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)1, jsonObject.get("spotName").toString(),false,"", 9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)2, jsonObject.get("code").toString(),false,"",9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)3, jsonObject.get("name").toString(),false,"",9);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)4, jsonObject.get("specName").toString(),false,"",9);
                ExcelCell runCell_15 = new ExcelCell(index,(byte)5, jsonObject.get("levelName").toString(),false,"",9);
                ExcelCell runCell_16 = new ExcelCell(index,(byte)6, jsonObject.get("pay").toString(),false,"",9);
                ExcelCell runCell_17 = new ExcelCell(index,(byte)7, jsonObject.get("buy").toString(),false,"",9);
                ExcelCell runCell_18 = new ExcelCell(index,(byte)8, jsonObject.get("acc").toString(),false,"",9);

                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);
                runRow_2.addExcelCell(runCell_12);
                runRow_2.addExcelCell(runCell_13);
                runRow_2.addExcelCell(runCell_14);
                runRow_2.addExcelCell(runCell_15);
                runRow_2.addExcelCell(runCell_16);
                runRow_2.addExcelCell(runCell_17);
                runRow_2.addExcelCell(runCell_18);

                sheet.addExcelRow(runRow_2);

                index ++;
            }

            ExcelRow runRow_3 = new ExcelRow(index);
            ExcelCell runCell_15 = new ExcelCell(index,(byte)5, "合计",false,"",9);
            ExcelCell runCell_16 = new ExcelCell(index,(byte)6, totalPayPrice+"",false,"", 9);
            ExcelCell runCell_17 = new ExcelCell(index,(byte)7, totalBuyPrice+"",false,"",9);
            ExcelCell runCell_18 = new ExcelCell(index,(byte)8, totalAccPrice+"",false,"",9);

            runRow_3.addExcelCell(runCell_15);
            runRow_3.addExcelCell(runCell_16);
            runRow_3.addExcelCell(runCell_17);
            runRow_3.addExcelCell(runCell_18);

            sheet.addExcelRow(runRow_3);

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
        columnWidthMap.put((short)7, (short)3000);
        columnWidthMap.put((short)8, (short)3000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

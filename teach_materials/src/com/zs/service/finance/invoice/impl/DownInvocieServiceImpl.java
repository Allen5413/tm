package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.service.finance.invoice.DownInvocieService;
import com.zs.service.finance.invoice.FindInvoiceByWhereService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/5/5.
 */
@Service("downInvocieService")
public class DownInvocieServiceImpl extends EntityServiceImpl implements DownInvocieService {

    @Resource
    private FindInvoiceByWhereService findInvoiceByWhereService;

    @Override
    @Transactional
    public String down(PageInfo pageInfo, Map<String, String> map, String fileName) throws Exception {
        try {
            JSONObject jsonObject = findInvoiceByWhereService.findPageByWhere(pageInfo, map);
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("pageInfo");
            List<JSONObject> dataList = (List<JSONObject>) jsonObject1.get("pageResults");
            return this.createExcel(dataList, fileName);
        }catch(Throwable t){
            t.printStackTrace();
        }
        return null;

    }

    private String createExcel(List<JSONObject> data, String fileName)throws Throwable{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        if(null != data && data.size() > 0){
            for(int i = 0; i < data.size(); i++){
                JSONObject json = data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_7 = new ExcelCell(index,(byte)0, (1111+index)+"",false,"",9);
                ExcelCell runCell_8 = new ExcelCell(index,(byte)1, json.get("name").toString(),false,"",9);
                ExcelCell runCell_9 = new ExcelCell(index,(byte)2, "书（教材）",false,"", 9);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)3, "批",false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)4, "1",false,"",9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)5, json.get("money").toString(),false,"",9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)6, json.get("money").toString(),false,"",9);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)7, "免税",false,"",9);
                ExcelCell runCell_15 = new ExcelCell(index,(byte)8, null == json.get("studentCode") ? json.get("spotCode").toString() : json.get("studentCode").toString(),false,"",9);
                ExcelCell runCell_16 = new ExcelCell(index,(byte)9, json.get("id").toString(),false,"",9);

                runRow_2.addExcelCell(runCell_7);
                runRow_2.addExcelCell(runCell_8);
                runRow_2.addExcelCell(runCell_9);
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
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)11000);
        columnWidthMap.put((short)1, (short)8000);
        columnWidthMap.put((short)2, (short)11000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)5000);
        columnWidthMap.put((short)5, (short)5000);
        columnWidthMap.put((short)6, (short)5000);
        columnWidthMap.put((short)7, (short)5000);
        columnWidthMap.put((short)8, (short)5000);
        columnWidthMap.put((short)9, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

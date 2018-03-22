package com.zs.service.finance.studentexpensepay.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.service.finance.invoice.DownInvocieService;
import com.zs.service.finance.invoice.FindInvoiceByWhereService;
import com.zs.service.finance.studentexpensepay.DownStuEPService;
import com.zs.service.finance.studentexpensepay.FindStuEPPageByWhereService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/5/5.
 */
@Service("downStuEPService")
public class DownStuEPServiceImpl extends EntityServiceImpl implements DownStuEPService {

    @Resource
    private FindStuEPPageByWhereService findStuEPPageByWhereService;

    @Override
    @Transactional
    public String down(PageInfo pageInfo, Map<String, String> map, String fileName) throws Exception {
        try {
            JSONObject jsonObject = findStuEPPageByWhereService.findPage(pageInfo, map, null);
            JSONObject json = (JSONObject) jsonObject.get("pageInfo");
            List<JSONObject> dataList = (List<JSONObject>) json.get("pageResults");
            return this.createExcel(dataList, jsonObject.get("totalPayPrice").toString(), fileName);
        }catch(Throwable t){
            t.printStackTrace();
        }
        return null;

    }

    private String createExcel(List<JSONObject> data, String totalMoney, String fileName)throws Throwable{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;

        ExcelRow runRow = new ExcelRow(index);
        ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "学号",false,"",30);
        ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "姓名",false,"",30);
        ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "专业",false,"",30);
        ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "层次",false,"",30);
        ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "交费金额",false,"",30);
        ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "交费类型",false,"",30);
        ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "创建时间",false,"",30);
        ExcelCell runCell_8 = new ExcelCell(index,(byte)7, "到账时间",false,"",30);
        ExcelCell runCell_9 = new ExcelCell(index,(byte)8, "备注",false,"",30);
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
        if(null != data && data.size() > 0){
            for(int i = 0; i < data.size(); i++){
                JSONObject json = data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)0, json.get("code").toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)1, json.get("name").toString(),false,"",9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)2, json.get("spec").toString(),false,"", 9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)3, json.get("level").toString(),false,"",9);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)4, json.get("money").toString(),false,"",9);
                ExcelCell runCell_15 = new ExcelCell(index,(byte)5, json.get("payType").toString(),false,"",9);
                ExcelCell runCell_16 = new ExcelCell(index,(byte)6, json.get("createTime").toString(),false,"",9);
                ExcelCell runCell_17 = new ExcelCell(index,(byte)7, json.get("arrivalTime").toString(),false,"",9);
                ExcelCell runCell_18 = new ExcelCell(index,(byte)8, null == json.get("remark") ? "" : json.get("remark").toString(),false,"",9);

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
            ExcelCell runCell_19 = new ExcelCell(index,(byte)3, "合计：",false,"",9);
            ExcelCell runCell_20 = new ExcelCell(index,(byte)4, totalMoney,false,"",9);

            runRow_3.addExcelCell(runCell_19);
            runRow_3.addExcelCell(runCell_20);

            sheet.addExcelRow(runRow_3);
            index ++;
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
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}

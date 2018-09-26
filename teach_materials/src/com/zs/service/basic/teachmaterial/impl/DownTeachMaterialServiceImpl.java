package com.zs.service.basic.teachmaterial.impl;

import com.feinno.framework.common.dao.support.PageInfo;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.service.basic.teachmaterial.DownTeachMaterialService;
import com.zs.service.basic.teachmaterial.FindTeachMaterialPageByWhereService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2015/8/13.
 */
@Service("downTeachMaterialService")
public class DownTeachMaterialServiceImpl extends EntityServiceImpl
        implements DownTeachMaterialService {

    @Resource
    private FindTeachMaterialPageByWhereService findTeachMaterialPageByWhereService;

    @Override
    public String down(PageInfo pageInfo, Map<String, String> map, Map<String, Boolean> sortMap, String fileName) throws Exception {
        pageInfo.setCountOfCurrentPage(999999);
        pageInfo = findTeachMaterialPageByWhereService.findPageByWhere(pageInfo, map, sortMap);
        return this.createExcel(pageInfo.getPageResults(), fileName);
    }


    private String createExcel(List data, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;

        ExcelRow runRow = new ExcelRow(index);
        ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "ISBN",false,"",30);
        ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "教材名称",false,"",30);
        ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "教材类型",false,"",30);
        ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "出版社",false,"",30);
        ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "作者",false,"",30);
        ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "版次",false,"",30);
        ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "单价",false,"",30);
        ExcelCell runCell_8 = new ExcelCell(index,(byte)7, "状态",false,"",30);
        ExcelCell runCell_9 = new ExcelCell(index,(byte)8, "是否套教材",false,"",30);
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
            for(int i=0; i<data.size(); i++){
                JSONObject jsonObject = (JSONObject) data.get(i);

                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)0, null == jsonObject.get("isbn") ? "" : jsonObject.get("isbn").toString(),false,"", 9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)1, null == jsonObject.get("name") ? "" : jsonObject.get("name").toString(),false,"",9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)2, null == jsonObject.get("tmTypeName") ? "" : jsonObject.get("tmTypeName").toString(),false,"",9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)3, null == jsonObject.get("pressName") ? "" : jsonObject.get("pressName").toString(),false,"",9);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)4, null == jsonObject.get("author") ? "" : jsonObject.get("author").toString(),false,"",9);
                ExcelCell runCell_15 = new ExcelCell(index,(byte)5, null == jsonObject.get("revision") ? "" : jsonObject.get("revision").toString(),false,"",9);
                ExcelCell runCell_16 = new ExcelCell(index,(byte)6, null == jsonObject.get("price") ? "" : jsonObject.get("price").toString(),false,"",9);
                ExcelCell runCell_17 = new ExcelCell(index,(byte)7, Integer.parseInt(jsonObject.get("state").toString()) == 0 ? "启用":"停用",false,"",9);
                ExcelCell runCell_18 = new ExcelCell(index,(byte)8, Integer.parseInt(jsonObject.get("isSet").toString()) == 0 ? "否":"是",false,"",9);

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
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Short, Short> columnWidthMap = new HashMap<Short, Short>();
        columnWidthMap.put((short)0, (short)5000);
        columnWidthMap.put((short)1, (short)11000);
        columnWidthMap.put((short)2, (short)11000);
        columnWidthMap.put((short)3, (short)5000);
        columnWidthMap.put((short)4, (short)8000);
        columnWidthMap.put((short)5, (short)5000);
        columnWidthMap.put((short)6, (short)5000);
        columnWidthMap.put((short)7, (short)5000);
        columnWidthMap.put((short)8, (short)5000);
        return ExcelTools.writeExcelFile(exo, columnWidthMap, fileName);
    }
}
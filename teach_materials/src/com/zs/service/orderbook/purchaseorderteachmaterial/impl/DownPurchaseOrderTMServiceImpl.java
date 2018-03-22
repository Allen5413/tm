package com.zs.service.orderbook.purchaseorderteachmaterial.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.orderbook.purchaseorderteachmaterial.DownPurchaseOrderTMService;
import com.zs.service.orderbook.purchaseorderteachmaterial.FindPurchaseOrderTeachMaterialListByOrderCodeService;
import com.zs.tools.ExcelTools;
import com.zs.tools.bean.ExcelCell;
import com.zs.tools.bean.ExcelObject;
import com.zs.tools.bean.ExcelRow;
import com.zs.tools.bean.ExcelSheet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by Allen on 2015/6/3.
 */
@Service("downPurchaseOrderTMService")
public class DownPurchaseOrderTMServiceImpl extends EntityServiceImpl implements DownPurchaseOrderTMService {

    @Resource
    private FindPurchaseOrderTeachMaterialListByOrderCodeService findPurchaseOrderTeachMaterialListByOrderCodeService;
    @Resource
    private FindIssueChannelService findIssueChannelService;

    @Override
    public String downPurchaseOrderTM(String orderCode, Long semesterId, Long channelId, String fileName) throws Exception {
        if(StringUtils.isEmpty(orderCode)){
            throw new BusinessException("订单号为空");
        }
        if(null == semesterId || 0 >= semesterId){
            throw new BusinessException("学期id为空");
        }
        //得到数据
        JSONArray result = findPurchaseOrderTeachMaterialListByOrderCodeService.
                getPurchaseOrderTeachMaterialListByOrderCode(orderCode, semesterId);
        //得到发行渠道
        IssueChannel issueChannel = findIssueChannelService.get(channelId);
        return this.createExcel(result, orderCode, null == issueChannel ? "" : issueChannel.getName(), fileName);
    }

    public String createExcel(JSONArray data, String orderCode, String issueChannelName, String fileName){
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
        if(null != data){
            ExcelRow row_1 = new ExcelRow(0);
            sheet.addExcelRow(row_1);
            ExcelCell cell_1 = new ExcelCell(0, (byte)0,0,(byte)5,"订单号:" + orderCode + "     发行渠道:" + issueChannelName,false,"",30);
            row_1.addExcelCell(cell_1);
            index ++;
            if(null != data && data.size() > 0){
                ExcelRow runRow = new ExcelRow(index);
                ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "学院",false,"",30);
                ExcelCell runCell_4 = new ExcelCell(index,(byte)1, "教材名称",false,"",30);
                ExcelCell runCell_5 = new ExcelCell(index,(byte)2, "作者",false,"",30);
                ExcelCell runCell_2 = new ExcelCell(index,(byte)3, "出版社",false,"",30);
                ExcelCell runCell_3 = new ExcelCell(index,(byte)4, "ISBN",false,"",30);
                ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "价格",false,"",30);
                ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "数量",false,"",30);
                runRow.addExcelCell(runCell_1);
                runRow.addExcelCell(runCell_2);
                runRow.addExcelCell(runCell_3);
                runRow.addExcelCell(runCell_4);
                runRow.addExcelCell(runCell_5);
                runRow.addExcelCell(runCell_6);
                runRow.addExcelCell(runCell_7);
                sheet.addExcelRow(runRow);
                index ++;
                for(int i = 0; i < data.size(); i++){
                    JSONObject jsonObject = (JSONObject)data.get(i);
                    ExcelRow runRow_2 = new ExcelRow(index);
                    ExcelCell runCell_8 = new ExcelCell(index,(byte)0, jsonObject.get("deptName").toString(),false,"",9);
                    ExcelCell runCell_11 = new ExcelCell(index,(byte)1, jsonObject.get("tmName").toString(),false,"", 9);
                    ExcelCell runCell_12 = new ExcelCell(index,(byte)2, jsonObject.get("author").toString(),false,"",9);
                    ExcelCell runCell_9 = new ExcelCell(index,(byte)3, jsonObject.get("pressName").toString(),false,"",9);
                    ExcelCell runCell_10 = new ExcelCell(index,(byte)4, jsonObject.get("isbn").toString(),false,"",9);
                    ExcelCell runCell_13 = new ExcelCell(index,(byte)5, jsonObject.get("price").toString(),false,"",9);
                    ExcelCell runCell_14 = new ExcelCell(index,(byte)6, jsonObject.get("tmCount").toString(),false,"",9);

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
            }
            ExcelRow endTitleRow = new ExcelRow(index);
            sheet.addExcelRow(endTitleRow);
        }
        return ExcelTools.writeExcelFile(exo, null, fileName);
    }
}

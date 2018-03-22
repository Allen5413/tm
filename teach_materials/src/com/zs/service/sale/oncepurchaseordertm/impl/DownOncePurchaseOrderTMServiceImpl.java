package com.zs.service.sale.oncepurchaseordertm.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.domain.basic.IssueChannel;
import com.zs.service.basic.issuechannel.FindIssueChannelService;
import com.zs.service.orderbook.purchaseorderteachmaterial.DownPurchaseOrderTMService;
import com.zs.service.orderbook.purchaseorderteachmaterial.FindPurchaseOrderTeachMaterialListByOrderCodeService;
import com.zs.service.sale.oncepurchaseordertm.DownOncePurchaseOrderTMService;
import com.zs.service.sale.oncepurchaseordertm.FindOncePurchaseOrderTMListByOrderCodeService;
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
import java.io.ByteArrayOutputStream;

/**
 * Created by Allen on 2015/6/3.
 */
@Service("downOncePurchaseOrderTMService")
public class DownOncePurchaseOrderTMServiceImpl extends EntityServiceImpl implements DownOncePurchaseOrderTMService {

    @Resource
    private FindOncePurchaseOrderTMListByOrderCodeService findOncePurchaseOrderTMListByOrderCodeService;
    @Resource
    private FindIssueChannelService findIssueChannelService;

    @Override
    public String down(String orderCode, Long semesterId, Long channelId, String fileName) throws Exception {
        if(StringUtils.isEmpty(orderCode)){
            throw new BusinessException("订单号为空");
        }
        if(null == semesterId || 0 >= semesterId){
            throw new BusinessException("学期id为空");
        }
        //得到数据
        JSONArray result = findOncePurchaseOrderTMListByOrderCodeService.find(orderCode, semesterId);
        //得到发行渠道
        IssueChannel issueChannel = findIssueChannelService.get(channelId);
        return this.createExcel(result, orderCode, null == issueChannel ? "" : issueChannel.getName(), fileName);
    }


    private String createExcel(JSONArray data, String orderCode, String issueChannelName, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;
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
            ExcelCell runCell_15 = new ExcelCell(index,(byte)7, "学生订单所需数量",false,"",30);
            ExcelCell runCell_16 = new ExcelCell(index,(byte)8, "预订单所需数量",false,"",30);
            ExcelCell runCell_17 = new ExcelCell(index,(byte)9, "现有库存",false,"",30);
            ExcelCell runCell_18 = new ExcelCell(index,(byte)10, "库存差",false,"",30);
            runRow.addExcelCell(runCell_1);
            runRow.addExcelCell(runCell_2);
            runRow.addExcelCell(runCell_3);
            runRow.addExcelCell(runCell_4);
            runRow.addExcelCell(runCell_5);
            runRow.addExcelCell(runCell_6);
            runRow.addExcelCell(runCell_7);
            runRow.addExcelCell(runCell_15);
            runRow.addExcelCell(runCell_16);
            runRow.addExcelCell(runCell_17);
            runRow.addExcelCell(runCell_18);
            sheet.addExcelRow(runRow);
            index ++;
            for(int i = 0; i < data.size(); i++){
                JSONObject jsonObject = (JSONObject)data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);
                int tmCount = Integer.parseInt(jsonObject.get("tmCount").toString());
                int sCount = Integer.parseInt(jsonObject.get("sCount").toString());
                int pCount = Integer.parseInt(jsonObject.get("pCount").toString());
                int stock = Integer.parseInt(jsonObject.get("stock").toString());
                int temp = stock - pCount - sCount - tmCount;
                ExcelCell runCell_8 = new ExcelCell(index,(byte)0, jsonObject.get("deptName").toString(),false,"",9);
                ExcelCell runCell_11 = new ExcelCell(index,(byte)1, jsonObject.get("tmName").toString(),false,"", 9);
                ExcelCell runCell_12 = new ExcelCell(index,(byte)2, jsonObject.get("author").toString(),false,"",9);
                ExcelCell runCell_9 = new ExcelCell(index,(byte)3, jsonObject.get("pressName").toString(),false,"",9);
                ExcelCell runCell_10 = new ExcelCell(index,(byte)4, jsonObject.get("isbn").toString(),false,"",9);
                ExcelCell runCell_13 = new ExcelCell(index,(byte)5, jsonObject.get("price").toString(),false,"",9);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)6, tmCount+"",false,"",9);
                ExcelCell runCell_19 = new ExcelCell(index,(byte)7, sCount+"",false,"",9);
                ExcelCell runCell_20 = new ExcelCell(index,(byte)8, pCount+"",false,"",9);
                ExcelCell runCell_21 = new ExcelCell(index,(byte)9, stock+"",false,"",9);
                ExcelCell runCell_22 = new ExcelCell(index,(byte)10, temp+"",false,"",9);

                runRow_2.addExcelCell(runCell_8);
                runRow_2.addExcelCell(runCell_9);
                runRow_2.addExcelCell(runCell_10);
                runRow_2.addExcelCell(runCell_11);
                runRow_2.addExcelCell(runCell_12);
                runRow_2.addExcelCell(runCell_13);
                runRow_2.addExcelCell(runCell_14);
                runRow_2.addExcelCell(runCell_19);
                runRow_2.addExcelCell(runCell_20);
                runRow_2.addExcelCell(runCell_21);
                runRow_2.addExcelCell(runCell_22);

                sheet.addExcelRow(runRow_2);

                index ++;
            }
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        return ExcelTools.writeExcelFile(exo, null, fileName);
    }
}

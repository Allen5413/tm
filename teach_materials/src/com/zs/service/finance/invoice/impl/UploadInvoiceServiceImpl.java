package com.zs.service.finance.invoice.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.finance.invoice.InvoiceDAO;
import com.zs.domain.finance.Invoice;
import com.zs.service.finance.invoice.UploadInvoiceService;
import com.zs.tools.DateTools;
import com.zs.tools.UserTools;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2016/5/6.
 */
@Service("uploadInvoiceService")
public class UploadInvoiceServiceImpl extends EntityServiceImpl<Invoice, InvoiceDAO> implements UploadInvoiceService{

    @Override
    @Transactional
    public void upload(HttpServletRequest request) throws Exception {
        MultipartRequest mulReu = (MultipartRequest)request;
        if(null == mulReu){
            throw new BusinessException("没有上传文件");
        }
        List<MultipartFile> fileList = mulReu.getFiles("file");
        if(null == fileList){
            throw new BusinessException("没有解析到上传文件");
        }
        for(MultipartFile multipartFile : fileList){
            String fileName = multipartFile.getOriginalFilename();
            String fileExtention = StringUtils.substringAfterLast(fileName, ".").toLowerCase();
            if (!fileExtention.matches("xls|xlsx")) {
                throw new BusinessException("只能上传excel文件");
            }
            InputStream inputStream = multipartFile.getInputStream();

            List<Invoice> invoiceList = readXls(inputStream);
            for (Invoice invoice : invoiceList) {
                Invoice oldInvoice = super.get(invoice.getId());
                if(oldInvoice != null){
                    oldInvoice.setCode(invoice.getCode());
                    oldInvoice.setState(Invoice.STATE_OPEN);
                    oldInvoice.setOpenTime(invoice.getOpenTime());
                    oldInvoice.setOperator(UserTools.getLoginUserForName(request));
                    oldInvoice.setOperateTime(DateTools.getLongNowTime());
                    super.update(oldInvoice);
                }
            }

        }
    }

    protected List<Invoice> readXls(InputStream inputStream)throws Exception{
        List<Invoice> invoiceList = new ArrayList<Invoice>();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Invoice invoice = new Invoice();
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
                    HSSFCell hssfCell = hssfRow.getCell((short)i);
                    if (i == 8) {
                        invoice.setStudentCode(hssfCell.toString());
                    } else if (i == 9) {
                        invoice.setId(Long.parseLong(hssfCell.toString()));
                    } else if (i == 10) {
                        invoice.setCode(hssfCell.toString());
                    } else if (i == 11) {
                        invoice.setOpenTime(DateTools.getNowNewTime(hssfCell.toString(), "yyyy-MM-dd"));
                    }
                }
                invoiceList.add(invoice);
            }
        }
        return invoiceList;
    }
}

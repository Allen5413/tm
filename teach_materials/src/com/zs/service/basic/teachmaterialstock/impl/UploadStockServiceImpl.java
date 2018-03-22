package com.zs.service.basic.teachmaterialstock.impl;

import com.feinno.framework.common.exception.BusinessException;
import com.feinno.framework.common.service.EntityServiceImpl;
import com.zs.dao.basic.teachmaterial.FindTMByNameAuthorIsbnPricePressDAO;
import com.zs.dao.basic.teachmaterialstock.FindTeachMaterialStockBytmIdAndChannelIdDAO;
import com.zs.dao.basic.teachmaterialstock.TeachMaterialStockDAO;
import com.zs.domain.basic.TeachMaterial;
import com.zs.domain.basic.TeachMaterialStock;
import com.zs.service.basic.teachmaterialstock.UploadStockService;
import com.zs.tools.DateTools;
import com.zs.tools.UserTools;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2016/5/6.
 */
@Service("uploadStockService")
public class UploadStockServiceImpl extends EntityServiceImpl<TeachMaterialStock, TeachMaterialStockDAO>
        implements UploadStockService{

    @Resource
    private FindTMByNameAuthorIsbnPricePressDAO findTMByNameAuthorIsbnPricePressDAO;
    @Resource
    private FindTeachMaterialStockBytmIdAndChannelIdDAO findTeachMaterialStockBytmIdAndChannelIdDAO;

    @Override
    @Transactional
    public String upload(HttpServletRequest request) throws Exception {
        String str = "";
        String type = request.getParameter("types");
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

            List<JSONObject> list = readXls(inputStream);
            for (JSONObject json : list) {
                String tmName = json.get("tmName").toString();
                String author = json.get("author").toString();
                String press = json.get("press").toString();
                String isbn = json.get("isbn").toString();
                String price = json.get("price").toString();

                //查询是否存在这个教材
                List<TeachMaterial> teachMaterialList = findTMByNameAuthorIsbnPricePressDAO.find(tmName, author, Double.parseDouble(price), isbn, press);
                if(null == teachMaterialList || 1 > teachMaterialList.size()){
                    str += "教材名："+tmName+"，作者："+author+"，出版社："+press+"，ISBN："+isbn+"，价格："+price+"。没有找到相匹配的教材\r\n\n";
                }
                if(1 < teachMaterialList.size()){
                    str += "教材名："+tmName+"，作者："+author+"，出版社："+press+"，ISBN："+isbn+"，价格："+price+"。有"+teachMaterialList.size()+"条相同的教材\r\n\n ";
                }
            }
            if(StringUtils.isEmpty(str)){
                for (JSONObject json : list) {
                    String tmName = json.get("tmName").toString();
                    String author = json.get("author").toString();
                    String press = json.get("press").toString();
                    String isbn = json.get("isbn").toString();
                    String price = json.get("price").toString();
                    long count = Long.parseLong(json.get("count").toString());

                    //查询是否存在这个教材
                    List<TeachMaterial> teachMaterialList = findTMByNameAuthorIsbnPricePressDAO.find(tmName, author, Double.parseDouble(price), isbn, press);
                    if(null != teachMaterialList && 1 == teachMaterialList.size()) {
                        TeachMaterial teachMaterial = teachMaterialList.get(0);
                        //查询库存
                        TeachMaterialStock teachMaterialStock = findTeachMaterialStockBytmIdAndChannelIdDAO.getTeachMaterialStockBytmIdAndChannelId(teachMaterial.getId(), 1l);
                        if(null == teachMaterialStock){
                            teachMaterialStock = new TeachMaterialStock();
                            teachMaterialStock.setStock(count);
                            teachMaterialStock.setIssueChannelId(1l);
                            teachMaterialStock.setTeachMaterialId(teachMaterial.getId());
                            teachMaterialStock.setOperator(UserTools.getLoginUserForName(request));
                            findTeachMaterialStockBytmIdAndChannelIdDAO.save(teachMaterialStock);
                        }else{
                            //累加库存
                            if ("0".equals(type)) {
                                if(0 > teachMaterialStock.getStock()){
                                    teachMaterialStock.setStock(count);
                                }else{
                                    teachMaterialStock.setStock(teachMaterialStock.getStock() + count);
                                }
                            }
                            //替换库存
                            if ("1".equals(type)) {
                                teachMaterialStock.setStock(count);
                            }
                            teachMaterialStock.setOperator(UserTools.getLoginUserForName(request));
                            teachMaterialStock.setOperateTime(DateTools.getLongNowTime());
                            findTeachMaterialStockBytmIdAndChannelIdDAO.update(teachMaterialStock);
                        }
                    }
                }
            }
        }
        return str;
    }

    protected List<JSONObject> readXls(InputStream inputStream)throws Exception{
        List<JSONObject> list = new ArrayList<JSONObject>();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                JSONObject json = new JSONObject();
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
                    HSSFCell hssfCell = hssfRow.getCell((short)i);
                    if (i == 0) {
                        json.put("tmName", hssfCell.toString());
                    }
                    if (i == 1) {
                        json.put("author", hssfCell.toString());
                    }
                    if (i == 2) {
                        json.put("press", hssfCell.toString());
                    }
                    if (i == 3) {
                        json.put("isbn", null == hssfCell ? "" : new DecimalFormat("#").format(hssfCell.getNumericCellValue()));
                    }
                    if (i == 4) {
                        json.put("price", hssfCell.toString());
                    }
                    if (i == 5) {
                        json.put("count", (int)(Double.parseDouble(hssfCell.toString())));
                    }
                }
                list.add(json);
            }
        }
        return list;
    }
}

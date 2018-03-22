package com.zs.web.controller.sync.speccourse;

import com.zs.service.sync.spec.DownSpecCourseTMBySpecAndLevelService;
import com.zs.web.controller.LoggerController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Allen on 2015/10/14.
 */
@Controller
@RequestMapping(value = "/downSpecourseTMBySpecAndLevel")
public class DownSpecourseTMBySpecAndLevelController extends LoggerController {
    private static Logger log = Logger.getLogger(DownSpecourseTMBySpecAndLevelController.class);

    @Resource
    private DownSpecCourseTMBySpecAndLevelService downSpecCourseTMBySpecAndLevelService;

    @RequestMapping(value = "down")
    @ResponseBody
    public String down(@RequestParam(value="specCode") String specCode,
                       @RequestParam(value="levelCode") String levelCode,
                       HttpServletRequest request){
        try{
            String downUrl = "/excelDown/kcdzb.xls";
            downSpecCourseTMBySpecAndLevelService.down(specCode, levelCode, request.getRealPath("") + downUrl);
            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载课程对照表明细");
            return "error";
        }
    }

    @RequestMapping(value = "downZip")
    @ResponseBody
    public String downZip(HttpServletRequest request){
        try{
            String downUrl = request.getRealPath("") + "/excelDown/";
            File[] xlss = downSpecCourseTMBySpecAndLevelService.downZip(downUrl);

            byte[] buf = new byte[8192];
            int len;
            try {
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(downUrl + "kcdzb.zip"));
                for (int i = 0; i < xlss.length; i++) {
                    out.putNextEntry(new ZipEntry(xlss[i].getName()));
                    BufferedInputStream in = new BufferedInputStream( new FileInputStream( xlss[i] ) );

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.closeEntry();
                    in.close();
                }
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return downUrl;
        }
        catch(Exception e){
            super.outputException(request, e, log, "下载课程对照表明细");
            return "error";
        }
    }
}

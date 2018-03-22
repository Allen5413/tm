package com.zs.tools;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 发起Http请求
 * Created by Allen on 2015/6/25.
 */
public class HttpRequestTools {

    //Http请求url
    private static String url;
    //快递100请求url
    private static String kuaidiReqUrl;
    //青书url
    private static String qsUrl;
    //青书key
    private static String qsKey;
    //青书学校标志
    private static String collegeUrl;

    static{
        try {
            PropertiesTools propertiesTools = new PropertiesTools("resource/commons.properties");
            url = propertiesTools.getProperty("eduwest.url");
            kuaidiReqUrl = propertiesTools.getProperty("kuaidi100.req.url");
            qsUrl = propertiesTools.getProperty("qingshu.url");
            qsKey = propertiesTools.getProperty("qingshu.key");
            collegeUrl = propertiesTools.getProperty("qingshu.collegeUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录验证
     * @return
     * @throws Exception
     */
    public static boolean vaildEduLogin(String pin, String pwd)throws Exception{
        StringBuilder urlStr = new StringBuilder(url);
        urlStr.append("?pin="+pin);
        urlStr.append("&pwd="+pwd);
        String result = sendGet(urlStr.toString());
        if("Success".equals(result)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 快递100请求
     * @param nu
     * @return
     * @throws Exception
     */
    public static int reqKuaidi(String nu)throws Exception{
        StringBuilder urlStr = new StringBuilder(kuaidiReqUrl);
        urlStr.append("?com=ems");
        urlStr.append("&nu="+nu);
        String result = sendGet(urlStr.toString());
        JSONObject json = new JSONObject();
        json = json.fromObject(result);
        if(null != json && null != json.get("state")){
            return Integer.parseInt(json.get("state").toString());
        }else{
            return -1;
        }
    }

    /**
     * 请求青书
     * @param nu
     * @return
     * @throws Exception
     */
    public static JSONObject reqQingShu(String studentId, String studentName, String spotName, int idCardType, String idCardNumber, String phone,
                                 String buyerPayAmount)throws Exception{
        String sign = "BuyerPayAmount="+buyerPayAmount+"&CollegeUrl="+collegeUrl+"&StudentCenterName="+spotName+"&StudentId="+studentId+"&StudentIdCardNumber="+idCardNumber+
                "&StudentIdCardType="+idCardType+"&StudentName="+studentName+"&StudentPhone="+phone;
        sign = MD5Tools.qingShuMD5(sign+qsKey).toUpperCase();
        JSONObject paramJSON = new JSONObject();
        paramJSON.put("CollegeUrl", collegeUrl);
        paramJSON.put("StudentId", studentId);
        paramJSON.put("StudentName", studentName);
        paramJSON.put("StudentCenterName", spotName);
        paramJSON.put("StudentIdCardType", idCardType);
        paramJSON.put("StudentIdCardNumber", idCardNumber);
        paramJSON.put("StudentPhone", phone);
        paramJSON.put("BuyerPayAmount", buyerPayAmount);
        paramJSON.put("Sign", sign);
        String result = sendJSONPost(qsUrl, paramJSON);
        JSONObject json = new JSONObject();
        return json.fromObject(result);
    }

    public static void main(String[] args){
        try {
            String pwd = "spot01";
            vaildEduLogin("spot01", CryptTools.Encrypt(pwd));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url
     * @return
     */
    public static String sendGet(String url)throws Exception{
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("charset", "utf-8");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.trim();
    }

    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("charset", "utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendJSONPost(String url, JSONObject json) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        //post.addHeader("charset", "utf-8");
        String result = "";

        try {
            StringEntity s = new StringEntity(json.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(s);

            //发送请求
            HttpResponse httpResponse = client.execute(post);

            //获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                strber.append(line + "\n");
            }
            inStream.close();
            result = strber.toString();
        } catch (Exception e) {
             e.printStackTrace();
         }
        return result;
    }

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getIp(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}

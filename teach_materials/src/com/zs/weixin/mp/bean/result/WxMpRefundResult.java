package com.zs.weixin.mp.bean.result;

import java.io.Serializable;

/**
 * <pre>
 * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"返回的结果
 *
 * 统一下单(详见http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
 *
 * </pre>
 *
 * @author chanjarster
 */
public class WxMpRefundResult implements Serializable {
    private String return_code;
    private String return_msg;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String result_code;
    private String err_code;
    private String err_code_des;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
    
    public String getErr_code() {
      return err_code;
    }

    public void setErr_code(String err_code) {
      this.err_code = err_code;
    }

    public String getErr_code_des() {
      return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
      this.err_code_des = err_code_des;
    }
}

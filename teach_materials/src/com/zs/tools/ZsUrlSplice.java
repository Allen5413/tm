package com.zs.tools;

public class ZsUrlSplice
{
  public static String splieceGetUserUrl(String zz)
  {
    return "?funcId=1&mac=" + MD5Tools.MD5(String.format("funcId=%d&zz=%s&key=%s", new Object[] { Integer.valueOf(1), zz, "0123456789_attop_9876543210#" })) + "&zz=" + zz;
  }

  public static String splieceAddCredenceUrl(String zz)
  {
    return "?funcId=2&mac=" + MD5Tools.MD5(String.format("funcId=%d&zz=%s&key=%s", new Object[] { Integer.valueOf(2), zz, "0123456789_attop_9876543210#" })) + "&zz=" + zz;
  }

  public static String splieceAddCashUrl(String zz, int cash)
  {
    return "?funcId=3&mac=" + MD5Tools.MD5(String.format("funcId=%d&zz=%s&key=%s", new Object[] { Integer.valueOf(3), zz, "0123456789_attop_9876543210#" })) + "&zz=" + zz + "&cash=" + cash;
  }

  public static String splieceAddMsg(String zz, String msg)
  {
    return "?funcId=4&mac=" + MD5Tools.MD5(String.format("funcId=%d&zz=%s&key=%s", new Object[] { Integer.valueOf(4), zz, "0123456789_attop_9876543210#" })) + "&zz=" + zz + "&msg=" + msg;
  }
  
  public static String splieceDeductCashUrl(String zz,int cash){
	  return "?funcId=5&mac=" + MD5Tools.MD5(String.format("funcId=%d&zz=%s&key=%s",5,zz,"0123456789_attop_9876543210#")) + "&zz=" + zz + "&cash=" + cash;
  }
}

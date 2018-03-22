package com.zs.config;

/**
 * Created by Allen on 2015/11/5.
 */
public enum LevelEnum {
    LEVEL_KCXX("0", "课程学习"),
    LEVEL_ZSB("3", "专升本"),
    LEVEL_GQB("4", "高起本"),
    LEVEL_DEXL("5", "第二学历"),
    LEVEL_GQZ("6", "高起专"),
    LEVEL_JCCZSB("7", "降层次专升本");

    LevelEnum(String value,String descn){
        this.value = value;
        this.descn = descn;
    }
    private final String value;
    private final String descn;

    public String getValue() {
        return value;
    }
    public String getDescn(){
        return descn ;
    }

    public static String getDescn(String value){
        for(LevelEnum levelEnum : LevelEnum.values()){
            if(levelEnum.getValue().equals(value)){
                return levelEnum.getDescn();
            }
        }
        return "";
    }
}

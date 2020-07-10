package com.linkwisdom.mylibrary.http;

import com.linkwisdom.mylibrary.R;

/**
 * @ProjectName: NBN
 * @ClassName: Constant
 * @Description: 常量
 * @Author: RAMON
 * @CreateDate: 2020/6/16 17:05
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version:
 */
public class Constant {
    //    服务器常量
    /**
     * 正式服
     */
    public static final int RELESESERVER = 0;
    /**
     * 测试服
     */
    public static final int DEBUG_SERVER = 1;
    /**
     * HK测试服
     */
    public static final int HK_DEBUG_SERVER = 2;

    //release
    public static String BASE_URL = "https://www.5wave.io";
    //验证TOKEN
    public static String checkToken = "/nbnback/third/validLogin";


    /**
     * 设置服务器地址，0 正式服，1 内地正式服，2 HK服务器
     *
     * @param ser
     */
    public static void setService(int ser) {
        switch (ser) {

            case 0:
                BASE_URL = "https://www.5wave.io";
                break;
            case 1:
                BASE_URL = "http://47.108.137.168:80";
                break;
            case 2:
                BASE_URL = "http://www.nbwave.xyz";
                break;
        }
    }

}

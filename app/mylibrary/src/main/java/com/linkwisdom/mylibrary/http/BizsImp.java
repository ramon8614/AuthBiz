package com.linkwisdom.mylibrary.http;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;

/**
 * @ProjectName: NBN
 * @ClassName: Bizs
 * @Description: 业务类，并不多，并未细分
 * @Author: RAMON
 * @CreateDate: 2020/6/16 17:19
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version:
 */
public class BizsImp extends BaseBiz {

    /**
     * 验证token
     *
     * @param token
     * @param type
     */
    public void checkToken(String type, String token, String userAddress, Callback callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("loginType", type);
        postJsonWithToken(Constant.checkToken, map, token, userAddress,callBack);
    }

}

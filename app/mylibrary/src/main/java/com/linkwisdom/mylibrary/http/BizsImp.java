package com.linkwisdom.mylibrary.http;

import java.util.HashMap;
import java.util.Map;

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
    public void checkToken(String token, String type, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("loginType", "1");
        postJsonWithToken(Constant.checkToken, map, token, callBack);
    }

}

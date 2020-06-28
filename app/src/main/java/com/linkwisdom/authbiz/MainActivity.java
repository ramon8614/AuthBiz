package com.linkwisdom.authbiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.linkwisdom.mylibrary.AuthUtils;
import com.linkwisdom.mylibrary.http.BizsImp;
import com.linkwisdom.mylibrary.http.MyCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    AuthUtils au;

    String address;
    String userName;
    String token = "66d26512fa77cc5ff934201903dd7482";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        au = new AuthUtils(this);

        Button bt1 = findViewById(R.id.authBt);
        Button bt2 = findViewById(R.id.rachargeBt);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                sendIntent.putExtra("key", "66d26512fa77cc5ff934201903dd7482");//测试环境
//        sendIntent.putExtra("key", "a6492c181a133355fcee1de3a8d12f15");//生产环境

                au.authApply("mytestapp", "66d26512fa77cc5ff934201903dd7482", "v0.0");

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                sendIntent.putExtra("key", "66d26512fa77cc5ff934201903dd7482");//测试环境
//        sendIntent.putExtra("key", "a6492c181a133355fcee1de3a8d12f15");//生产环境

                au.rechargeApply(token, 500, "王者农药充值");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == AuthUtils.AUTH_APPLY) {

                address = data.getStringExtra("address");
                userName = data.getStringExtra("userName");
                token = data.getStringExtra("token");

                BizsImp biz = new BizsImp();

                biz.checkToken("1", token, address, new MyCallBack(MainActivity.this) {

                    @Override
                    public void onSuccess(JsonElement datastr) {
                        Log.e("ramon", "suucess");
                        Toast.makeText(MainActivity.this, "token 验证通过", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String messgae) {
                        Log.e("ramon", messgae);
//                        Toast.makeText(MainActivity.this, "token 已失效", Toast.LENGTH_SHORT).show();
                    }


                });
            }

            if (requestCode == AuthUtils.RECHARGE_APPLY) {
                boolean isSuccess = data.getBooleanExtra("result", false);
                String order = data.getStringExtra("consumptionCode");
                double fee = data.getDoubleExtra("fee", 0d);
                double pay = data.getDoubleExtra("pay", 0d);
                Log.e("ramon", isSuccess + "order:  " + order + " fee: " + fee + " pay: " + pay);
            }

        }

    }

}
package com.linkwisdom.authbiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.linkwisdom.mylibrary.AuthUtils;
import com.linkwisdom.mylibrary.http.BizsImp;
import com.linkwisdom.mylibrary.http.Constant;
import com.linkwisdom.mylibrary.http.MyCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AuthUtils au;

    String userName;
    String token = "66d26512fa77cc5ff934201903dd7482";
    String address = "1H1g2mUsqbRSp5MJuRDoyZmLYs1JiwbBfR";
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        au = new AuthUtils(this, Constant.DEBUG_SERVER);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);

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
            if (requestCode == AuthUtils.WITHDRAW_APPLY) {
                boolean isSuccess = data.getBooleanExtra("result", false);
                String order = data.getStringExtra("consumptionCode");
                double withdraw = data.getDoubleExtra("withdraw", 0d);
                double fee = data.getDoubleExtra("fee", 0d);
                Log.e("ramon", isSuccess + "order:  " + order + " withdraw: " + withdraw + " fee: " + fee);
            }

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                au.authApply("mytestapp", "66d26512fa77cc5ff934201903dd7482", "v0.0");
                break;
            case R.id.bt2:
                au.rechargeApply(token, 500, "王者农药充值", "");
                break;
            case R.id.bt3:
                au.withdrawApply(token, address, "http://www.baidu.com");
                break;
            case R.id.bt4:
                au.is5Wave();
                break;
            default:
                break;
        }
    }
}
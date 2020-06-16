# AuthBiz
授权申请sdk

发起授权请求
new AuthUtils().authApply(MainActivity.this, "myAppyoyo");
参数列表
1.Activity 发起授权activity
2.String 请求授权应用名称

接收授权响应回调
重写 activity或者fragment onActivityResult方法 
requestCode为 AuthUtils.AUTH_APPLY 或者 901。
授权回调信息字段：
String address;
String userName;

Demo 
@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AuthUtils.AUTH_APPLY) {
                Log.e("demo", data.getStringExtra("address"));
                Log.e("demo", data.getStringExtra("userName"));
            }
        }
    }

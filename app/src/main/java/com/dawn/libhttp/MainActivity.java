package com.dawn.libhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dawn.http.HttpUtil;
import com.dawn.http.http.entity.HttpConfig;
import com.dawn.http.http.net.HTTPCaller;
import com.dawn.http.http.net.Header;
import com.dawn.http.http.net.RequestDataCallback;
import com.dawn.http.http.util.Util;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHttp();
    }

    public void getMachineMsg(View view){
        getMsg();
    }

    private void initHttp(){
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setAgent(true);//有代理的情况能不能访问
        httpConfig.setDebug(true);//是否debug模式 如果是debug模式打印log
        httpConfig.setTagName("dawn");//打印log的tagname

        //可以添加一些公共字段 每个接口都会带上
        httpConfig.addCommonField("pf", "android");
        httpConfig.addCommonField("version_code", "1.0.1");

        HTTPCaller.getInstance().setHttpConfig(httpConfig);
    }

    private void getMsg(){
        String url = "http://jargee.cn/photomachines/photomachines/EA5C80FD363900000000";
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", Util.getAuthorization("", ""));
        HttpUtil.getInstance().get(MachineMsgBean.class, url, new RequestDataCallback<MachineMsgBean>() {
            @Override
            public void dataCallback(MachineMsgBean obj) {
                Log.e("dawn", "dataCallback: " + (obj == null ? "null" : obj.toString() ));
            }
        });
    }
}
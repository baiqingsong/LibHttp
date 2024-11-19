# LibHttp
 Http请求引用

#### HTTPCall
HTTP操作类

* getInstance 获取实例
* setHttpConfig 设置Http配置

#### HttpConfig
Http配置类
```
    HttpConfig httpConfig = new HttpConfig();
    httpConfig.setAgent(true);//有代理的情况能不能访问
    httpConfig.setDebug(true);//是否debug模式 如果是debug模式打印log
    httpConfig.setTagName("tag");//打印log的tagname
    httpConfig.setAUTH_UM("tag");
    httpConfig.setAUTH_PW("tag");
    //可以添加一些公共字段 每个接口都会带上
    httpConfig.addCommonField("pf", "android");
    httpConfig.addCommonField("version_code", String.valueOf(SystemUtil.getVersionCode(getApplicationContext())));
```


1.1.1版本后去掉头部参数统一配置，需要每个接口单独配置

如果header中需要传单独的参数
```
    Map<String, String> headersMap = new HashMap<>();
    headersMap.put("Authorization", Util.getAuthorization("", ""));
```

get请求
```agsl
    HttpUtil.getInstance().get(Object.class, url, new RequestDataCallback<Object>() {
        @Override
        public void dataCallback(Object obj) {
            super.dataCallback(obj);
        }
    });
```

post 请求
```agsl
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.put("self_test_num", "2");
    HttpUtil.getInstance().post(Object.class, url, paramsMap, new RequestDataCallback<Object>() {
        @Override
        public void dataCallback(Object obj) {
            super.dataCallback(obj);
        }
    });
```
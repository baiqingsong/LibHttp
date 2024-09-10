package com.dawn.http;

import android.util.Log;

import com.dawn.http.http.entity.HttpConfig;
import com.dawn.http.http.net.HTTPCaller;
import com.dawn.http.http.net.Header;
import com.dawn.http.http.net.NameValuePair;
import com.dawn.http.http.net.RequestDataCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static HttpUtil _instance = null;
    public static HttpUtil getInstance() {
        if (_instance == null) {
            _instance = new HttpUtil();
        }
        return _instance;
    }
    private HttpUtil() {
    }
    public <T> void get(Class<T> clazz, String url, RequestDataCallback<T> callback){
        get(clazz, url, null, callback);
    }

    public <T> void post(Class<T> clazz, String url, Map<String, String> paramsMap, RequestDataCallback<T> callback){
        post(clazz, url, null, paramsMap, callback);
    }

    public <T> void get(Class<T> clazz, String url , Map<String, String> headersMap, RequestDataCallback<T> callback){
        Header[] headers = null;
        if(headersMap != null && headersMap.size() > 0){
            headers = new Header[headersMap.size()];
            int index = 0;
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                headers[index++] = new Header(entry.getKey(), entry.getValue());
            }
        }
        HTTPCaller.getInstance().get(clazz, url, headers, callback);
    }

    public <T> void post(Class<T> clazz, String url, Map<String, String> headersMap, Map<String, String> paramsMap, RequestDataCallback<T> callback){
        Header[] headers = null;
        if(headersMap != null && headersMap.size() > 0){
            headers = new Header[headersMap.size()];
            int index = 0;
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                headers[index++] = new Header(entry.getKey(), entry.getValue());
            }
        }
        List<NameValuePair> params = null;
        if(paramsMap != null && paramsMap.size() > 0){
            params = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                params.add(new NameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        HTTPCaller.getInstance().post(clazz, url, headers, params, callback, true);
    }

}

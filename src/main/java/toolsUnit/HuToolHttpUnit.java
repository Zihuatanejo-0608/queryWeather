package toolsUnit;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * Created by andy on 2019/7/4.
 *
 *
 * 2020.1.10 升级hutool框架到5.1.0,parameter不支持直接JSONObject类型,需要切换到string类型
 *
 */
public class HuToolHttpUnit {

    public static JSONObject post(String url, String headerName, String token, JSONObject parameter){
        try{
            HttpRequest httpRequest = HttpUtil.createPost(url).timeout(10000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            if (parameter != null){
                httpRequest.body(JSONUtil.toJsonStr(parameter));
                System.out.println("请求对象:" + parameter);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                if (response.body().equals("")){
                    System.out.println("请求成功,返回对象为null");
                    return null;
                }else {
                    System.out.println("请求结果:" + response.body());
                    return new JSONObject(response.body());
                }
            }
            System.out.println("post请求失败!" + response.getStatus() + response.body());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject get(String url, String headerName, String token){
        try{
            HttpRequest httpRequest = HttpUtil.createGet(url).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                System.out.println("请求url:" + url);
                System.out.println("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            System.out.println("get请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject delete(String url, String headerName, String token){
        try{
            HttpRequest httpRequest = HttpUtil.createRequest(Method.DELETE, url).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                System.out.println("请求url:" + url);
                System.out.println("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            System.out.println("delete请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject put(String url, String headerName, String token, JSONObject parameter){
        try {
            HttpRequest httpRequest = HttpUtil.createRequest(Method.PUT, url).body(JSONUtil.toJsonStr(parameter)).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            System.out.println("请求对象:" + parameter);
            if (response.isOk()){
                System.out.println("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            System.out.println("put请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

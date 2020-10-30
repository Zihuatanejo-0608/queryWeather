package toolsUnit;

import cn.hutool.core.lang.Console;
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
 * 2020.10.30 增加方法重载,增加日志打印输出方式
 *
 */
public class HuToolHttpUnit {

    public JSONObject post(String url, String headerName, String token, JSONObject parameter){
        try{
            HttpRequest httpRequest = HttpUtil.createPost(url).timeout(10000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            if (parameter != null){
                httpRequest.body(JSONUtil.toJsonStr(parameter));
                Console.log("请求对象:" + parameter);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                if (response.body().equals("")){
                    Console.log("请求成功,返回对象为null");
                    return null;
                }else {
                    Console.log("请求结果:" + response.body());
                    return new JSONObject(response.body());
                }
            }
            Console.log("请求失败!" + response.getStatus() + response.body());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject post(String url,JSONObject parameter){
        String headerName = null;
        String token = null;
        try{
            HttpRequest httpRequest = HttpUtil.createPost(url).timeout(10000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            if (parameter != null){
                httpRequest.body(JSONUtil.toJsonStr(parameter));
                Console.log("请求对象:" + parameter);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                if (response.body().equals("")){
                    Console.log("请求成功,返回对象为null");
                    return null;
                }else {
                    Console.log("请求结果:" + response.body());
                    return new JSONObject(response.body());
                }
            }
            Console.log("请求失败!" + response.getStatus() + response.body());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject get(String url, String headerName, String token){
        try{
            HttpRequest httpRequest = HttpUtil.createGet(url).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                Console.log("请求url:" + url);
                Console.log("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            Console.log("请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject get(String url){
        String headerName = null;
        String token = null;
        try{
            HttpRequest httpRequest = HttpUtil.createGet(url).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                Console.log("请求url:" + url);
                Console.log("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            Console.log("请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject delete(String url, String headerName, String token){
        try{
            HttpRequest httpRequest = HttpUtil.createRequest(Method.DELETE, url).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                Console.log("请求url:" + url);
                Console.log("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            Console.log("请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject delete(String url){
        String headerName = null;
        String token = null;
        try{
            HttpRequest httpRequest = HttpUtil.createRequest(Method.DELETE, url).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            if (response.isOk()){
                Console.log("请求url:" + url);
                Console.log("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            Console.log("请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject put(String url, String headerName, String token, JSONObject parameter){
        try {
            HttpRequest httpRequest = HttpUtil.createRequest(Method.PUT, url).body(JSONUtil.toJsonStr(parameter)).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            Console.log("请求对象:" + parameter);
            if (response.isOk()){
                Console.log("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            Console.log("请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject put(String url,JSONObject parameter){
        String headerName = null;
        String token = null;
        try {
            HttpRequest httpRequest = HttpUtil.createRequest(Method.PUT, url).body(JSONUtil.toJsonStr(parameter)).timeout(5000);
            if (StrUtil.isNotBlank(headerName) && StrUtil.isNotBlank(token)){
                httpRequest.header(headerName,token);
            }
            HttpResponse response = httpRequest.execute();
            Console.log("请求对象:" + parameter);
            if (response.isOk()){
                Console.log("请求结果:" + response.body());
                return new JSONObject(response.body());
            }
            Console.log("请求失败!" + response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

package queryServiceTest;

import cn.hutool.json.JSONObject;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import toolsUnit.CSVData;
import toolsUnit.HuToolHttpUnit;
import toolsUnit.ToolsUnit;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by andy on 2020/8/27.
 *
 * 天气查询自动化测试
 * allure serve
 *
 */
public class QueryWeather {

    //读取配置文件获取url头
    String urlHead = ToolsUnit.getPropertiesInfo("BaseUrl.properties", "weatherUrl");

    @DataProvider(name = "cityCodeData")
    public static Iterator<Object[]> testDataA() throws Exception{
        return (Iterator<Object[]>) new CSVData("queryServiceCase/queryWeather.csv");
    }

    @Story("正常测试")
    @Step("输入城市码验证天气查询接口的城市信息")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "天气查询",dataProvider = "cityCodeData")
    public void queryWeather(Map<String,String> map){

        HuToolHttpUnit httpRequest = new HuToolHttpUnit();

        String cityCode = map.get("cityCode");
        String url = urlHead + cityCode + ".html";
        //执行请求
        JSONObject response = httpRequest.get(url);
        //从返回json中获取需要的信息
        JSONObject weatherinfo = response.getJSONObject("weatherinfo");
        String cityName = weatherinfo.getStr("city");
        //断言
        Assert.assertEquals(cityName,map.get("expectCity"));
    }
}

package queryServiceTest;

import cn.hutool.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import toolsUnit.ExcelUnit;
import toolsUnit.HuToolHttpUnit;
import toolsUnit.ToolsUnit;

import java.util.Map;

/**
 * Created by andy on 2020/10/19.
 */
public class QueryWeatherExcel {

    String urlHead = ToolsUnit.getPropertiesInfo("BaseUrl.properties", "weatherUrl");

    @DataProvider(name = "cityCodeData")
    public Object[][] testData() throws Exception{
        ExcelUnit excelUnit = new ExcelUnit("queryServiceCase/queryWeather.xls","Sheet1");
        return excelUnit.getExcelData();
    }

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

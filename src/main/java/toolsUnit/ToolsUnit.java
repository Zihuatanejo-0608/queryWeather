package toolsUnit;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by andy on 2019/6/28.
 */
public class ToolsUnit {

    //随机数生成
    public static String randomData(){
        try{
            int r = (int)((Math.random()*9+1)*100000000);
            return String.valueOf(r);
        }catch (Exception e){
            System.out.println("随机数生成失败!");
            return null;
        }
    }

    //统计类中含有多少个方法
    public void sumClass() {
        int length = ToolsUnit.class.getMethods().length;//Tools改成对应的类名
        System.out.println(length);
    }

    //本地取文件相对路径
    public static String getPath(String fileName) throws Exception {
        String absolutePath = FileUtil.getAbsolutePath(fileName);
        System.out.println("数据资源路径:" + absolutePath);
        return absolutePath;
    }

    //写入txt文件
    public static void writeTxt(String file,List<String> list) throws Exception{
        String path = ToolsUnit.getPath(file);
        FileUtil.writeLines(list,path, Charset.defaultCharset(),false);
        //logger.info("写入" + file + "成功!");
    }

    public static void write_map_toTxT(String file,List<Map<Object,Object>> list) throws Exception{
        String path = ToolsUnit.getPath(file);
        FileUtil.writeLines(list,path, Charset.defaultCharset(),false);
        //logger.info("写入" + file + "成功!");
    }

    //通过文件流解析文件数据
//    public static Map<String,String> getBaseData(String fileName){
//
//        try{
//            List<String> list = new ArrayList();
//            int rowNum = 0;//行数
//
//            //把文件变成流对象
//            InputStream inputStream = ToolsUnit.class.getResourceAsStream("/" + fileName);
//            //读取流对象
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            //遍历流对象,添加到list
//            while (bufferedReader.ready()){
//                list.add(bufferedReader.readLine());
//                rowNum++;
//            }
//
//            Map<String, String> map = list.stream().collect(Collectors.toMap(s -> s.split("=")[0], s -> s.split("=")[1]));
//
//            return map;
//        }catch (Exception e){
//            e.getMessage();
//            System.out.println("获取文件数据异常!");
//        }
//        return null;
//    }

    //获取json文件数据
    public static JSONObject getBaseData(String fileName){
        try{
            //把文件变成流对象
            InputStream inputStream = ToolsUnit.class.getResourceAsStream("/" + fileName);
            //读取流,转换成String
            String readStream = IoUtil.read(inputStream, Charset.defaultCharset());

            JSONObject jsonObject = new JSONObject(readStream);

            return jsonObject;
        }catch (Exception e){
            e.getMessage();
            System.out.println("获取文件数据异常!");
        }
        return null;
    }

    public static String read_txt(String fileName) throws Exception{
        String ocr_path = ToolsUnit.getPath(fileName);
        String str = FileUtil.readString(ocr_path,Charset.defaultCharset());
        return str;
    }

    public static String getPropertiesInfo(String fileName,String key){
        //配置文件
        Properties properties = new Properties();

        try{
            //以流形式读取配置文件
            InputStream inputStream = ToolsUnit.class.getResourceAsStream("/" + fileName);
            //配置文件加载
            properties.load(inputStream);
            String value = properties.getProperty(key);
            return value;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }



}

package toolsUnit;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by andy on 2020/10/15.
 *
 * jxl只支持xls格式的excel
 *
 */
public class ExcelUnit {

    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    int rows;
    int columns;
    private String excelName;
    private String sheetNmae;
    private ArrayList<String> arrkey = new ArrayList<>();

    public ExcelUnit(String excelName, String sheetNmae){
        super();
        this.excelName = excelName;
        this.sheetNmae = sheetNmae;
    }

    //获取excel表中的数据
    public Object[][] getExcelData() throws BiffException,IOException{

        workbook = Workbook.getWorkbook(new File(getPath()));
        sheet = workbook.getSheet(sheetNmae);
        rows = sheet.getRows();//行
        columns = sheet.getColumns();//列
        //返回值为Obj[][],定义一个多行单列的二维数组
        HashMap<String,String>[][] arrmap = new HashMap[rows - 1][1];
        //对数组中的元素haspmap初始化
        if (rows > 1){
            for (int i = 0; i < rows -1; i++){
                arrmap[i][0] = new HashMap<>();
            }
        }else {
            Console.log("excel中没有数据!");
        }
        //获取列名,作为hashmao的key
        for (int c = 0; c < columns; c++){
            String cellvalue = sheet.getCell(c,0).getContents();
            arrkey.add(cellvalue);
        }
        //遍历单元格,添加到hashmap中
        for (int r =1; r < rows; r++){
            for (int c = 0; c < columns; c++){
                String cellvalue = sheet.getCell(c,r).getContents();
                arrmap[r - 1][0].put(arrkey.get(c),cellvalue);
            }
        }
        return arrmap;
    }

    //本地取文件相对路径
    public String getPath(){
        String absolutePath = FileUtil.getAbsolutePath(excelName);
        Console.log("数据资源路径:" + absolutePath);
        return absolutePath;
    }


}

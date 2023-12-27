package Utility;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtility {

    public List<Map<String,String>> readExcel(String path,String fileName, String sheetName) throws IOException {
            List<Map<String, String>> data = new ArrayList<>();
            path +=fileName+".xlsx";
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);
            XSSFRow headRow = sheet.getRow(0);

            for(int i=1;i<sheet.getLastRowNum();i++){
                Map<String,String> map = new HashMap<>();
                for(int j=0;j<headRow.getLastCellNum();j++){
                    map.put(headRow.getCell(j).toString(),sheet.getRow(i).getCell(j).toString());
                }
                data.add(map);
            }
            return data;

    }

    public String[][] readExcelTo2D(String path,String fileName, String sheetName) throws IOException {

        path +=fileName+".xlsx";
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);
        XSSFRow headRow = sheet.getRow(0);
        String[][] data = new String[sheet.getLastRowNum()-1][headRow.getLastCellNum()];
        for(int i=1;i<sheet.getLastRowNum();i++){
            for(int j=0;j<headRow.getLastCellNum();j++){
                data[i-1][j]=sheet.getRow(i).getCell(j).toString();
            }
        }
        return data;

    }

    public String[] readColumn(String path,String fileName, String sheetName, int c ) throws IOException {

        path +=fileName+".xlsx";
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);
        String[] data = new String[sheet.getLastRowNum()-1];

        for(int i=1;i<sheet.getLastRowNum();i++){
                data[i-1]=sheet.getRow(i).getCell(c).toString();
        }
        return data;

    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class POIExcel {
    public static ArrayList<ArrayList<String>> readExcelRow() throws IOException {
        Workbook workbook = null;
        File excelFile = new File("F:\\java_doc\\software_testing\\software_testing_experiment2\\src\\Selenium+Lab2020.xlsx");
        InputStream is = new FileInputStream(excelFile);
        workbook = new XSSFWorkbook(is);
        ArrayList<ArrayList<String>> als = new ArrayList<ArrayList<String>>();
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);
            //当前sheet页面为空,继续遍历
            if (sheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                // 遍历每一行的每一列
                ArrayList<String> al = new ArrayList<String>();
                for (int columnNum = 1; columnNum < row.getLastCellNum(); columnNum++) {
                    Cell cell = row.getCell(columnNum);
                    al.add(cell.getRichStringCellValue().getString());
                }
                als.add(al);
            }
        }
        is.close();
        return als;
    }

    public static void main(String args[])
    {
        ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
        try {
           test = readExcelRow();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(test.get(148));
    }
}

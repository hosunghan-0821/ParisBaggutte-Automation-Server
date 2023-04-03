package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.sql.SQLOutput;

public class ReadExcelInfo {

    public static final String FILE_PATH = "C:\\Users\\winso\\Downloads\\20230403185806_주문현황.xlsx";


    public void readExcel() {

        try {
            OPCPackage opcPackage = OPCPackage.open(new File(FILE_PATH));
            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
            String sheetName = workbook.getSheetName(0);
            Sheet sheet = workbook.getSheet(sheetName);


            int rows = sheet.getPhysicalNumberOfRows();
            //System.out.println(rows);

            for(int rowNo = 0 ; rowNo<rows; rowNo++){
                XSSFRow row = (XSSFRow) sheet.getRow(rowNo);
                if(row!=null){

                    int cells = row.getPhysicalNumberOfCells();
                    for(int cellIndex = 0 ; cellIndex <= cells ;cellIndex++){
                        XSSFCell cell = row.getCell(cellIndex);
                        String value = "";
                        if(cell==null){
                            continue;
                        }else{
                            // 타입 별로 내용을 읽는다
                            switch (cell.getCellType()){
                                case FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                case NUMERIC:
                                    value = cell.getNumericCellValue() + "";
                                    break;
                                case STRING:
                                    value = cell.getStringCellValue() + "";
                                    break;
                                case BLANK:
                                    value = cell.getBooleanCellValue() + "";
                                    break;
                                case ERROR:
                                    value = cell.getErrorCellValue() + "";
                                    break;
                            }
                        }

                        System.out.println(rowNo + "번행" + cellIndex+" 번 열 값: "+value );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}

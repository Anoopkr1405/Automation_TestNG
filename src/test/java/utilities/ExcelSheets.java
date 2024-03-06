package utilities;
 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
 
public class ExcelSheets {
	
	
 
    public static XSSFWorkbook workbook;
 // Method Implementation to read Data From Excel File
 	 public static String readSearchQueryFromExcel(String fileName, String sheetName, int rowNum, int cellNum) {
 	        String searchQuery = null;

 	        try {
 	        	 // Open the Excel file for reading
 	            FileInputStream file = new FileInputStream(fileName);
 	            XSSFWorkbook workbook = new XSSFWorkbook(file);
 	            XSSFSheet sheet = workbook.getSheet(sheetName);
 	            
 	            // Get the specified row and cell to read the search query
 	            XSSFRow row = sheet.getRow(rowNum);
 	            XSSFCell cell = row.getCell(cellNum);

 	           if (cell != null) {
 	              if (cell.getCellType() == CellType.STRING) {
 	                  searchQuery = cell.getStringCellValue();
 	              } else if (cell.getCellType() == CellType.NUMERIC) {
 	                  searchQuery = String.valueOf(cell.getNumericCellValue());
 	              }
 	          }
 	            
 	            // Close the workbook to release resources
 	           workbook.close();
 	        } catch (IOException e) {
 	            e.printStackTrace();
 	        }

 	        return searchQuery;
 	    }
 	
 	 //Write Car washing Services in Excel Sheet
    public static void writeCarSrvcsDetails(List<WebElement> name, List<WebElement> Contact_No) throws IOException {
    	workbook = new XSSFWorkbook();
    	XSSFSheet sheet = workbook.createSheet("CarWashingServices");
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Index");
        headerRow.createCell(1).setCellValue("CarSrvcs Name");
        headerRow.createCell(2).setCellValue("Contact Number");
 
        for (int i = 0; i < 5 && i < name.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(name.get(i).getText());
            row.createCell(2).setCellValue(Contact_No.get(i).getText());
        }
        ExcelSheets.writeToExcelFile("CarWashingSrvcs.xlsx");
    }
    
    //Write Gym SubList in Excel Sheet
    public static void writeGymSubList(List<WebElement> SubList) throws IOException {
    
    	FileInputStream file=new FileInputStream(".\\CarWashingSrvcs.xlsx");
    	
    	workbook = new XSSFWorkbook(file);
    	 XSSFSheet sheet;
    	try {
         sheet = workbook.createSheet("GymSubList");
    	}
    	catch(Exception e) {
    	 sheet = workbook.getSheet("GymSubList");
    	}
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Index");
        headerRow.createCell(1).setCellValue("Gym Sub-Menu Items");
 
        for (int i = 0; i < SubList.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(SubList.get(i).getText());
            
        }
        file.close();
        ExcelSheets.writeToExcelFile("CarWashingSrvcs.xlsx");
    }
    
    //Write Gym Sub-Menu DropDown Lists in Excel Sheet
    public static void writeGymSubMenu(List<String> sortList, List<String> amenitiesList, List<String> ratingsList) throws IOException {
        FileInputStream file = new FileInputStream(".\\CarWashingSrvcs.xlsx");
        workbook = new XSSFWorkbook(file);

        XSSFSheet sheet;
        try {
            sheet = workbook.createSheet("GymSubMenu");
        } catch (Exception e) {
            sheet = workbook.getSheet("GymSubMenu");
        }

        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Index");
        headerRow.createCell(1).setCellValue("Sort By");
        headerRow.createCell(2).setCellValue("Amenities");
        headerRow.createCell(3).setCellValue("Ratings");

        int maxSize = Math.max(sortList.size(), Math.max(amenitiesList.size(), ratingsList.size()));

        for (int i = 0; i < maxSize; i++) {
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i+1);
            if (i < sortList.size()) {
                row.createCell(1).setCellValue(sortList.get(i));
            }
            if (i < amenitiesList.size()) {
                row.createCell(2).setCellValue(amenitiesList.get(i));
            }
            if (i < ratingsList.size()) {
                row.createCell(3).setCellValue(ratingsList.get(i));
            }
        }
        file.close();
        writeToExcelFile("CarWashingSrvcs.xlsx");
    }

 
 
    public static void writeToExcelFile(String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            ExcelSheets.closeWorkbook();
        }
    }
    public static void closeWorkbook() throws IOException {
        workbook.close();
    }
}
package connect_OCBaseMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
@Test
public class ExcelDataProvider {
	//XSSFWorkbook wb;
	XSSFWorkbook wb;
	FileInputStream fis;
	String path = "C:\\Users\\tgandhi\\Connect\\Connect_order\\resources\\sample_order_creation.xlsx";
	
	public ExcelDataProvider() {
		File src = new File(path);
		
		try {
			fis = new FileInputStream(src);
			 wb = new XSSFWorkbook(fis);
			fis.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read Excel file" + e.getMessage());
		}
		
	}

	

	public String getData(String sheetName, int row, int column) {
		
	   return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
         //return null;
			}
	/*
	public int getNumericData(String sheetName, int row, int column) {
		
		return (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}
	*/
	
	public int rowCount(String sheetName) {
		return wb.getSheet(sheetName).getLastRowNum();
	}
	
	public int colCount(String sheetName) {
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}
	
	public void writeData(String sheetName, int row, int column, String pid) {
		//System.out.println("*****" + pid);
		wb.getSheet(sheetName).getRow(row).createCell(column).setCellValue(pid);
				
		FileOutputStream fos;
		try {
			fis.close();
			fos = new FileOutputStream(path);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


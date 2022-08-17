package utillsApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOpearations {
	
		XSSFWorkbook wb;
		//constructor for reading excel path
		public ExcelOpearations(String excelpath)throws Throwable
		{
			FileInputStream fi = new FileInputStream(excelpath);
			wb = new XSSFWorkbook(fi);
		}
		//count no of rows in a sheet
		public int rowCount(String sheetName)
		{
			return wb.getSheet(sheetName).getLastRowNum();
		}
		//count no of cells in a row
		public int cellCount(String sheetName)
		{
			return wb.getSheet(sheetName).getRow(0).getLastCellNum();
		}
		//read cell data from sheet
		public String getCellData(String sheetName,int row,int column)
		{
			String data="";
			if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
			{
				int celldata =(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
				data=String.valueOf(celldata);
			}
			else
			{
				data=wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
			}
			return data;
		}
		//setcell data
		public void setCellData(String sheetName,int row,int column,String status,String writeexcel)throws Throwable
		{
			//get sheet from wb
			XSSFSheet ws =wb.getSheet(sheetName);
			//get row from sheet
			XSSFRow rowNum =ws.getRow(row);
			//create cell
			XSSFCell cell =rowNum.createCell(column);
			//write status int cell
			cell.setCellValue(status);
			if(status.equalsIgnoreCase("Pass"))
			{
				XSSFCellStyle style = wb.createCellStyle();
				XSSFFont font = wb.createFont();
				font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
				font.setBold(true);
				font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rowNum.getCell(column).setCellStyle(style);
			}
			else if(status.equalsIgnoreCase("Fail"))
			{
				XSSFCellStyle style = wb.createCellStyle();
				XSSFFont font = wb.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rowNum.getCell(column).setCellStyle(style);
			}
			else if(status.equalsIgnoreCase("Blocked"))
			{
				XSSFCellStyle style = wb.createCellStyle();
				XSSFFont font = wb.createFont();
				font.setColor(IndexedColors.BLUE.getIndex());
				font.setBold(true);
				font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rowNum.getCell(column).setCellStyle(style);
			}
			FileOutputStream fo = new FileOutputStream(writeexcel);
			wb.write(fo);
		}
			public void main(String[] args)throws Throwable {
				ExcelOpearations xl = new ExcelOpearations("D://Dummy.xlsx");
				int rc =xl.rowCount("Login");
				System.out.println(rc);
				for(int i=1;i<=rc;i++)
				{
					String user =xl.getCellData("Login", i, 0);
					String pass = xl.getCellData("Login", i, 1);
					System.out.println(user+"    "+pass);
					//xl.setCellData("Login", i, 2, "pass", "d:/Results.xlsx");
					//xl.setCellData("Login", i, 2, "Fail", "d:/Results.xlsx");
					xl.setCellData("Login", i, 2, "blocked", "d:/Results.xlsx");
				}
				

			}
	}




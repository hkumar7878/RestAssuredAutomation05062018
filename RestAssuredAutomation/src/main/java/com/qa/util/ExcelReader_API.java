package com.qa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader_API {
	 	public  String path;
	    public  FileInputStream fis = null;
	    public  FileOutputStream fileOut =null;
	    private XSSFWorkbook workbook = null;
	    private XSSFSheet sheet = null;
	    private XSSFRow row   =null;
	    private XSSFCell cell = null;
	    
	    
	    public ExcelReader_API(String path)
	    {
	        this.path=path;
	        try {
	            fis = new FileInputStream(path);
	            workbook = new XSSFWorkbook(fis);
	            //sheet = workbook.getSheetAt(0);
	            fis.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	public Object[][] retrieveTestData(String wsName)
	{
        int sheetIndex=workbook.getSheetIndex(wsName);
        if(sheetIndex==-1)
            return null;
        else{
            int rowNum = getRowCount(wsName);
            int colNum = getColumnCount(wsName);

            Object data[][] = new Object[rowNum-1][colNum-2];

            for (int i=0; i<rowNum-1; i++){
                XSSFRow row = sheet.getRow(i+1);
                for(int j=0; j< colNum-2; j++){
                    if(row==null){
                        data[i][j] = "";
                    }
                    else{
                        XSSFCell cell = row.getCell(j);

                        if(cell==null){
                            data[i][j] = "";
                        }
                        else{
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String value = cellToString(cell);
                            data[i][j] = value;
                        }
                    }
                }
            }
            return data;
        }

    }
	
	public int getRowCount(String sheetName){

        int index = workbook.getSheetIndex(sheetName);
        if(index==-1)
            return 0;
        else
        {
            sheet = workbook.getSheetAt(index);
            int number=sheet.getLastRowNum()+1;
            return number;
        }
    }
	
	public int getColumnCount(String sheetName){
        // check if sheet exists
        if(!isSheetExist(sheetName))
            return -1;
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        if(row==null)
            return -1;
        return row.getLastCellNum();

    }
	
	public boolean isSheetExist(String sheetName){

        int index = workbook.getSheetIndex(sheetName);

        if(index==-1){

            index=workbook.getSheetIndex(sheetName.toUpperCase());

            if(index==-1)

                return false;

            else

                return true;

        }

        else

            return true;

    }
	
	public static String cellToString(XSSFCell cell){
        int type;
        Object result;
        type = cell.getCellType();
        switch (type){
            case 0 :
                result = cell.getNumericCellValue();
                break;

            case 1 :
                result = cell.getStringCellValue();
                break;

            default :
                throw new RuntimeException("Unsupportd cell.");
        }
        return result.toString();
    }
	
	public String getCellData(String sheetName,int colNum,int rowNum){

        try{
            if(rowNum <=0)
                return "";
            int index = workbook.getSheetIndex(sheetName);
            if(index==-1)
                return "";
            sheet = workbook.getSheetAt(index);
            //row = sheet.getRow(rowNum-1);
            //XSSFRow row = sheet.getRow(rowNum-1);
            XSSFRow row = sheet.getRow(rowNum);
            if(row==null)
            {

                System.out.println("Row is null");
                return "";
            }
            cell = row.getCell(colNum);
            if(cell==null)
                return "";
            if(cell.getCellType()==Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
                String cellText  = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();
                    Calendar cal =Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH)+1 + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cellText;

                }
                return cellText;
            }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e){
            e.printStackTrace();
            return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
        }
    }
	
	  public String [][] getDatafromExcel(String sheetName, String ExcelName)
	    {
			
	    	String dataSet[][]=null;
	    	try
	    	{
	    		// get data from excel workbook
	    		XSSFSheet sheet=workbook.getSheet(sheetName);
	    		// count of active rows in sheet
	    		int totalRow=sheet.getLastRowNum()+1;
	    		// count number of active cell
	    		
	    		int totalColumn=sheet.getRow(0).getLastCellNum();
	    		// Create array of row and column
	    		dataSet = new String [totalRow-1][totalColumn];
	    		
	    		// Run for loop and store data in 2D array
	    		
	    		// This first loop will run on rows
	    		
	    		for(int i=1;i<totalRow;i++)
	    		{
	    			XSSFRow rows=sheet.getRow(i);
	    			
	    			// This loop will run the column of that row
	    			for(int j=0;j<totalColumn;j++)
	    			{
	    					// get cellMethod will get cell data
	    				
	    				XSSFCell cell= rows.getCell(j);
	    				// if Cell type is of String type then this If condition will work
	    				
	    				if(cell.getCellType()==Cell.CELL_TYPE_STRING)
	    					dataSet[i-1][j]=cell.getStringCellValue();
	    				else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
	    				{
	    					String cellText=String.valueOf(cell.getNumericCellValue());
	    					dataSet[i-1][j]=cellText;
	    				}
	    				else
	    					// if Cell type is of boolean type then this  condition will work
	    					dataSet[i-1][j]=String.valueOf(cell.getBooleanCellValue());
	    			}
	    		}
	    		return dataSet;
	    	}
	    	
	    	catch(Exception e)
	    	{
	    		System.out.println("Exception in reading excel file " + e.getMessage());
	    		e.printStackTrace();
	    	}
	    	
	    	return null;
	    	
	    }
}

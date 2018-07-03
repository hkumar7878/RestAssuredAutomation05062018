package com.qa.util;

public class API_DataProviderUtil {

	public static ExcelReader_API excelReader = null;
    static String SheetName = null;
  
    public static String [][] getUsersData(String ExcelName,String SheetName)
    {
    
    	String usersfilepath= System.getProperty("user.dir")+"\\ExcelData\\" + ExcelName;
    	excelReader=new ExcelReader_API(usersfilepath);
    	String[][] excelData = excelReader.getDatafromExcel(SheetName, ExcelName);
    	return excelData;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /* @DataProvider(name="usersTestData")
     public static Object[][] getUsersData(){

         Object[][] arrayObject = getExcelData(usersfilepath,"Users URIs");

         return arrayObject;
     }
     
     public static Object [][] getExcelData(String fileName, String sheetName) {
         int rows=usersExcel.getRowCount(sheetName);
         int cols=usersExcel.getColumnCount(sheetName);
         Object data [][]=new Object[rows-1][cols];
         for (int rowNum=1;rowNum<rows;rowNum++)
         {

             for (int colNum=0;colNum<cols;colNum++)
             {
                 data [rowNum-1][colNum]=usersExcel.getCellData(sheetName, colNum, rowNum);
             }
         }
         return data;
     }*/
}

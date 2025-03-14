package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public Object[][] getRow() throws IOException
	{
		String path = ".\\TestData\\OpenCart_LoginData.xlsx";
		
		ExcelUtility xUtility = new ExcelUtility(path);
	
		int totalRows = xUtility.getRowCount("Sheet1");
		int totalCols = xUtility.getCellCount("Sheet1", 1);
		System.out.println(totalRows+" "+totalCols);
		
		Object[][] loginData = new String[totalRows][totalCols];
		
		for(int i=1;i<=totalRows;i++)
		{
			for(int j=0;j<totalCols;j++)
			{
				loginData[i-1][j] = xUtility.getCellData("Sheet1", i, j);
				System.out.print(xUtility.getCellData("Sheet1", i, j));
			}
			System.out.println();
		}
		
		return loginData;
		
	}
}

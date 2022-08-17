package driversScript;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import constant.AppUtill;
import utillsApp.ExcelOpearations;

public class ExecutableScript extends  AppUtill {
	
String inputpath="C:\\Users\\Ak\\eclipse-workspace\\HybridFramework\\TestInput\\PrimusTestData.xlsx";
String outputpath="C:\\Users\\Ak\\eclipse-workspace\\HybridFramework\\TestOutput\\primusresult.xlsx";
String TSSheet="TestSteps";
String TCSheet="TestCases";
ExtentReports report;
ExtentTest test;


@Test
public void startTest()throws Throwable
{
	report=new ExtentReports("C:\\Users\\Ak\\eclipse-workspace\\HybridFramework\\AdvanceReports\\primusreport.html");
	test=report.startTest("startTest", "Verify Primus Login,new branch creation,branch updation and logout");
	test.assignAuthor("AK");
	test.assignCategory("Functonality Test");
	boolean res=false;
	String tcres="";
	ExcelOpearations xl =new ExcelOpearations(inputpath);
	//count no of rows in TCSHeet, TSSheet
	int TCCount =xl.rowCount(TCSheet);
	int TSCount =xl.rowCount(TSSheet);
	//count no of cells in TCSHeet, TSSheet
	int TCCell=xl.cellCount(TCSheet);
	int TSCell=xl.cellCount(TSSheet);
	Reporter.log(TCCount+"  "+TSCount+" "+TCCell+"   "+TSCell,true);
	//itearte all rows in TCSheet
	for(int i=1;i<=TCCount;i++)
	{
		String ExecutionMode=xl.getCellData(TCSheet, i, 2);
		if(ExecutionMode.equalsIgnoreCase("Y"))
		{
			String tcid =xl.getCellData(TCSheet, i, 0);
			String ModuleName =xl.getCellData(TCSheet, i, 1);
			//iterate all rows in TSSheet
			for(int j=1;j<TSCount;j++)
			{
				String tsid =xl.getCellData(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					String keyword =xl.getCellData(TSSheet, j, 3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						res= FunctionLibrary.verifyLogin(para1, para2);
						test.log(LogStatus.PASS,"Login Success");
					}
					else if(keyword.equalsIgnoreCase("NewBranch"))
					{
						String para1=xl.getCellData(TSSheet, j, 5);
						String para2=xl.getCellData(TSSheet, j, 6);
						String para3=xl.getCellData(TSSheet, j, 7);
						String para4=xl.getCellData(TSSheet, j, 8);
						String para5=xl.getCellData(TSSheet, j, 9);
						String para6=xl.getCellData(TSSheet, j, 10);
						String para7=xl.getCellData(TSSheet, j, 11);
						String para8=xl.getCellData(TSSheet, j, 12);
						String para9=xl.getCellData(TSSheet, j, 13);
						//FunctionLibrary.clickBranches();
						res= FunctionLibrary.verifybranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							test.log(LogStatus.PASS, "New branch created successfuly");												
					}
					else if(keyword.equalsIgnoreCase("BranchUpdate"))
					{
						String para1=xl.getCellData(TSSheet, j, 5);
						String para2=xl.getCellData(TSSheet, j, 6);
						String para5=xl.getCellData(TSSheet, j, 9);
						String para6=xl.getCellData(TSSheet, j, 10);
						//FunctionLibrary.clickBranches();
						res= FunctionLibrary.updateBranch(para1, para2, para5, para6);
						test.log(LogStatus.PASS, "Branch updation successully");
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res=FunctionLibrary.verifyLogout();
					String tsres="";
					if(res)
					{
						tsres="Pass";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					else
					{
						tsres="Fail";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					tcres=tsres;
										
				}
			}
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		}
		else
		{
			
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
	
	report.endTest(test);
	report.flush();
	
}

}

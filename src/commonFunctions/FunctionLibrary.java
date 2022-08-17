package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import constant.AppUtill;

public class FunctionLibrary  extends AppUtill{
	public ExtentReports report;
	 public ExtentTest test;
//public  void reports() {
	//report=new ExtentReports("C:\\Users\\Ak\\eclipse-workspace\\HybridFramework\\AdvanceReports\\PrimusReport.html");
	
//}
	public static  boolean verifyLogin(String user, String pass) throws Throwable {
		//test=report.startTest("StratTest");
		//test.assignAuthor("*AK*");
		//test.assignCategory("FunctionalityTest");
driver.findElement(By.xpath(pro.getProperty("ObjUser"))).sendKeys(user);
driver.findElement(By.xpath(pro.getProperty("ObjPass"))).sendKeys(pass);
driver.findElement(By.xpath(pro.getProperty("ObjLogin"))).click();
Thread.sleep(2000);
String expected="";
String actual=driver.getCurrentUrl();
if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
	Reporter.log("Login success::"+expected+"  "+actual, true);
//test.log(LogStatus.PASS, actual);

	return true;
		}
else
{
	//test.log(LogStatus.FAIL, expected);
	Reporter.log("Login Failed::"+expected+"  "+actual, true);
	return false;
	}

}
	public void clickBranches() throws Throwable {
		driver.findElement(By.xpath(pro.getProperty("//a[@href='admin_banker_master.aspx']//img"))).click();
		Thread.sleep(3000);
	}
	public  static boolean  verifybranch(String branchname, String add1, String add2 ,String add3, 
			String area, String zip, String country, String state, String city) throws Throwable

	{
		driver.findElement(By.xpath(pro.getProperty("ObjBranches"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(pro.getProperty("ObjNewBranch"))).click();
		driver.findElement(By.xpath(pro.getProperty("ObjBranch"))).sendKeys(branchname);
		driver.findElement(By.xpath(pro.getProperty("ObjAddress1"))).sendKeys(add1);
		driver.findElement(By.xpath(pro.getProperty("ObjAddress2"))).sendKeys(add2);
		driver.findElement(By.xpath(pro.getProperty("ObjAddress3"))).sendKeys(add3);
		driver.findElement(By.xpath(pro.getProperty("ObjArea"))).sendKeys(area);
		driver.findElement(By.xpath(pro.getProperty("Objzipcode"))).sendKeys(zip);
		new Select(driver.findElement(By.xpath(pro.getProperty("Objcountry")))).selectByVisibleText(country);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(pro.getProperty("Objstate")))).selectByVisibleText(state);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(pro.getProperty("Objcity")))).selectByVisibleText(city);
		Thread.sleep(3000);
		driver.findElement(By.xpath(pro.getProperty("Objsubmit"))).click();
		Thread.sleep(3000);
		String exp=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String act="New Branch With ID";
	if(exp.toLowerCase().contains(act.toLowerCase())) {

	//	test.log(LogStatus.PASS, act);
		Reporter.log(exp, true);
		return true;
	}
	else
	{
		//test.log(LogStatus.FAIL, exp);
		Reporter.log(act, true);	
		return false;
	}
	}
public static boolean updateBranch(String para1, String para2, String para5, String para6) throws Throwable {
	driver.findElement(By.xpath(pro.getProperty("ObjBranches"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("ObjEdit"))).click();
	Thread.sleep(3000);
	WebElement element1=driver.findElement(By.xpath(pro.getProperty("ObjBranchName")));
	element1.clear();
	element1.sendKeys(para1);
	WebElement element2=driver.findElement(By.xpath(pro.getProperty("ObjAddress")));
	element2.clear();
	element2.sendKeys(para2);
	WebElement element3=driver.findElement(By.xpath(pro.getProperty("ObjAreaName")));
	element3.clear();
	element3.sendKeys(para5);
	WebElement element4=driver.findElement(By.xpath(pro.getProperty("Objzip")));
	element4.clear();
	element4.sendKeys(para6);
	driver.findElement(By.xpath(pro.getProperty("ObjUpdate"))).click();
	Thread.sleep(3000);
	String el=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String ul="Branch Updated successfully";
	if(ul.contains(el))
	{
		//test.log(LogStatus.PASS, ul);
		Reporter.log(el,true); 
		return true;
	}
	else
	{
		//test.log(LogStatus.FAIL, ul);
		Reporter.log(ul,true);
	return false;
	}
}
	public static boolean  verifyLogout() throws Throwable {
		
		driver.findElement(By.xpath(pro.getProperty("ObjLogout"))).click();
		Thread.sleep(3000);
		if(driver.findElement(By.xpath(pro.getProperty("ObjLogIcon"))).isDisplayed()) {
			
			Reporter.log("Logout success",true);
			return true;
		}
			else
			{
				
			Reporter.log("Loogout failed", true);
				return false;
		}
	}
	public void endreport() {
	
	}


}



package constant;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtill {

	public static WebDriver driver;
	public static Properties pro;
	@BeforeSuite
	public static void setUp() throws Throwable, Throwable {
		pro=new Properties();
		pro.load(new FileInputStream("C:\\Users\\Ak\\eclipse-workspace\\HybridFramework\\PropertiesFiles\\Environment.properties"));
		if(pro.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(pro.getProperty("Url"));
		}
		else if(pro.getProperty("Browser").equalsIgnoreCase("Firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(pro.getProperty("Url"));	
	}
		else if(pro.getProperty("Browser").equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver","C:\\Users\\Ak\\eclipse-workspace\\HybridFramework\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(pro.getProperty("Url"));
		}
			
			
			else
	{
		Reporter.log("Broweser Not Found", true);
	}
	
}
	@AfterSuite
	public void Clouser() {
		driver.close();
		
		
	}
	}
	

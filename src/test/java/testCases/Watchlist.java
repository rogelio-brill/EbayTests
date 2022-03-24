package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Watchlist {

	WebDriver driver;
	
	@BeforeClass
	public void testSetup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rogelio.aguilar\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
		//driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void openBrowser() {
		driver.get("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=https%3A%2F%2Fwww.ebay.com%2Fmye%2Fmyebay%2Fwatchlist");
		//new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("//*[@id=\"checkbox\"]"))).click();
		driver.findElement(By.id("userid")).sendKeys("rogelio.aguilar@brillio.com");
		driver.findElement(By.id("signin-continue-btn")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 // How to automate captcha with selenium
		driver.findElement(By.id("pass")).sendKeys("Bri!!iouser2022");
		driver.findElement(By.id("sgnBt")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void loadWatchlistPage() {
		driver.get("https://www.ebay.com/mye/myebay/watchlist");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Loaded testCases.Watchlist Page");
	}
	
	//@Test(priority=2)
	public void openWatchlistDropdown() {
		driver.findElement(By.xpath("//*[@id=\"nid-dp6-5\"]/button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//@Test(priority=3)
	public void addToCart() {
		driver.findElement(By.xpath("//*[@id=\"middle-wrapper-grid\"]/div[4]/div[3]/div/div[5]/div[1]/div[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("pass")).click();
		driver.findElement(By.id("pass")).sendKeys("Bri!!iouser2022");
		driver.findElement(By.id("sgnBt")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals("https://cart.payments.ebay.com/", driver.getCurrentUrl().toString());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
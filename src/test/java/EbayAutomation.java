import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;


public class EbayAutomation {

    WebDriver driver;

    @BeforeClass
    public void testSetup() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\rogelio.aguilar\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openBrowser() {
        driver.get("https://www.ebay.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority=1)
    public void verifyCategories() {
        click(By.id("gh-shop-a"));
        verifyWebElements(By.xpath("//*[@id=\"gh-sbc\"]"), 3);
    }

    @Test
    public void searchProduct() {
        type(By.id("gh-ac"), "iphone");
        click(By.id("gh-btn"));
        isElementPresent(By.xpath("//*[@id=\"mainContent\"]/div[1]/div[2]/div[2]/div[1]/div[1]/h1"));
    }

    @Test
    public void dynamicAds() {
        isElementPresent(By.xpath("//*[@id=\"s0-0-32-4-0-0[0]-2-match-media-0-ebay-carousel-container\"]"));
        verifyWebElements(By.xpath("//*[@id=\"s0-0-32-4-0-0[0]-2-match-media-0-ebay-carousel-list\"]"), 4);
    }

    @Test
    public void recommendations() {
        scrollToElement(By.xpath("//*[@id=\"container_item_cards1\"]/div[1]/div[1]/div[1]"));
        scrollToElement(By.xpath("//*[@id=\"destinations_list1\"]/div/div/div[1]/h2"));
        scrollToElement(By.xpath("//*[@id=\"items_list1\"]/div[1]/div/div[1]"));
    }

    @Test
    public void dailyDeal() {
        click(By.xpath("//*[@id=\"gh-p-1\"]/a"));
        isElementPresent(By.xpath("//*[@id=\"refit-spf-container\"]/div[2]/div[2]/div[1]/div"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    /* Helper functions
----------------------------------------------------------------------------------------------------------------------
    */
    public void click(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    public boolean isElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyWebElements(By by, int expectedNum) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        try {
            List<WebElement> elements = driver.findElements(by);
            return expectedNum == elements.size();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void type(By by, String text) {
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
        }

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
    }

    public void scrollToElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Find element by link text and store in variable "Element"
        WebElement Element = driver.findElement(By.tagName("body"));
        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

}
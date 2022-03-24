package helper;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;


public class BaseDriver {
    public static ThreadLocal<WebDriver> webdrivers = new ThreadLocal<WebDriver>();
    public static Properties prop;
    public static String url;

    public static WebDriver getDriver(String site) throws IOException {

        WebDriver driver = null;
        prop = new Properties();
        prop.load(ContentReader.getPropertyFile(site));

        if ("us".equals(site)) {
            url = "https://www.ebay.com";
        } else if ("uk".equals(site)) {
            url = "https://www.ebay.co.uk";
        } else if ("au".equals(site)) {
            url = "https://www.ebay.com.au";
        } else if ("de".equals(site)) {
            url = "https://www.ebay.de/";
        } else {
            url = "https://www.ebay.com";
        }

        String browser = System.getProperty("testbrowser");
        System.out.println("Pom value-----------------" + browser);

        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
            driver.get(url);
        }
        webdrivers.set(driver);
        return webdrivers.get();

    }

    @AfterSuite
    public void tearDown() {
        if (webdrivers.get() != null) {
            webdrivers.get().close();
        }
    }
}

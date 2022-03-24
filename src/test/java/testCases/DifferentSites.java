package testCases;

import helper.BaseDriver;
import helper.ContentReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DifferentSites extends BaseDriver {

    @Test()
    @Parameters({ "site" })
    public void test_Site(@Optional String site) throws IOException {
        System.out.println("Response for getting the "+ site +" Ebay website :");

        Properties prop = new Properties();
        prop.load(ContentReader.getPropertyFile(site));

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(prop.getProperty("baseUrl")).
        when().get();

        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @Test()
    @Parameters({ "site" })
    public void test_VerifyDifferentSiteTitle(@Optional String site) throws IOException {
        WebDriver driver = getDriver(site);
        String expectedTitle = "";
        if(site == null) {
            expectedTitle = "Electronics, Cars, Fashion, Collectibles & More | eBay";
        }else if(site.equals("de")) {
            expectedTitle = "Elektronik, Autos, Mode, Sammlerstücke, Möbel und mehr Online-Shopping | eBay";
        }else {
            expectedTitle = "Electronics, Cars, Fashion, Collectibles & More | eBay";
        }
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

}

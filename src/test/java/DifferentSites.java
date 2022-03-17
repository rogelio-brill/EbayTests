import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DifferentSites {
//    @BeforeClass(groups = "product_api")
//    public void setup() {
//        // Base URL/URI for all REST API requests
//        RestAssured.baseURI = "https://www.ebay.com";
//    }

    @Test
    public void USA_Site() throws IOException {
        System.out.println("Response for getting the US Ebay website :");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("global.properties");
        prop.load(fis);

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(prop.getProperty("baseUrl")).
        when().get();

        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @Test
    public void MX_Site() throws IOException {
        System.out.println("Response for getting the MX Ebay website :");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("global.properties");
        prop.load(fis);

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(prop.getProperty("MXUrl")).
                when().get();

        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @Test
    public void UK_Site() throws IOException {
        System.out.println("Response for getting the UK Ebay website :");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("global.properties");
        prop.load(fis);

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(prop.getProperty("UKUrl")).
                when().get();

        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @Test
    public void AU_Site() throws IOException {
        System.out.println("Response for getting the AU Ebay website :");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("global.properties");
        prop.load(fis);

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(prop.getProperty("AUUrl")).
                when().get();

        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
    }

}

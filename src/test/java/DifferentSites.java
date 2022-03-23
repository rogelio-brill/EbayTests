import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DifferentSites {

    @Test()
    @Parameters({ "site" })
    public void test_Site(@Optional String site) throws IOException {
        System.out.println("Response for getting the "+ site +" Ebay website :");

        InputStream is;
        if (site == null) {
            is = DifferentSites.class.getResourceAsStream("/us.properties");
        }else {
           is =  DifferentSites.class.getResourceAsStream("/"+site+".properties");
        }
        Properties prop = new Properties();
        prop.load(is);

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(prop.getProperty("baseUrl")).
        when().get();

        int code = res.getStatusCode();
        Assert.assertEquals(code, 200);
    }

}

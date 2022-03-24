package testCases;

import java.io.IOException;
import java.text.ParseException;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class BackendEbay {
    private String responseBody;
    public String responseBodyPOST;
    //RESTTemplate Object
    private RestTemplate restTemplate;

    //Employee ID
    private String employeeId;
    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
    private ResponseEntity<String> response;

    @BeforeTest
    public void beforeTest() throws IOException, ParseException {
        this.restTemplate = new RestTemplate();
    }

    @Test()
    void firstRedeem() throws IOException, ParseException {
        String getURI = "https://www.ebay.com/nap/napkinapi/v1/ticketing/redeem?ticket=bd92bd9240f448288dc0c51359321411";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "en-US,en;q=0.9");
        headers.add("Connection", "keep-alive");
        headers.add("Content-Type", "text/plain");
        headers.add("Cookie", "__uzma=4265b97f-2945-4d02-b99c-8fd2c6f459b2; __uzmb=1645595789; __uzme=3007; __ssds=2; __ssuzjsr2=a9be0cd8e; __uzmaj2=736fe162-f55c-43ad-bc97-ce81843f0fad; __uzmbj2=1645595791; cid=MV3mNSSPIgc0b6Hk#1097606631; __gads=ID=f2caf0101226132b:T=1645687558:S=ALNI_MYWQkqLTakrFktmMDtuV5SyEvv8Kg; JSESSIONID=BA22B9BE69A7C733CAFAEBE9680AE345; AMCVS_A71B5B5B54F607AB0A4C98A2@AdobeOrg=1; __uzmc=430618217054; __uzmd=1646685899; __uzmf=7f6000abe163f9-ec4e-4263-8191-3a40b618a0f916455957892841090110363-71ec6e56195f26ae82; QuantumMetricUserID=734adefeb08a5c783a7905f1ace1d421; ak_bmsc=49FA01F95C6440F3ED1A5F08B265FDAB~000000000000000000000000000000~YAAQP3tBFzOTEnd/AQAAl60Reg84k/oUuVBdU90QlozT016wzvlUQw6MAFSIImJIAYA1I1BNRJW/zc+M1umYqix2apPfOadNgSJ0PrdmCboF94G3ztH9M9wEhENo1MDbby/XToXbpAxKizKLmKDrULeLMw9Yp1W7Tc0gtARzKiuQV1iIa8xWbcf6jGQU5WTc3Xcfbk7V7fc1rCyMlkmk1uonDb9C/bxq0BQvEZFCzmQScmuYeXj3z1O622nmc4hqUPNwRDzgaonk3kesdxfRy/nxgmuJ0b5qWeePRh452D5ZBDkiVh9Y8sd345KW7JTuOKbYs0ZJd2N3hD4SwJeckSbFfyzCZEbPz0tIvFZkcmS5whO4534DQ9TMCUw=; s=CgAD4ACBiLNslZDdlNmY3Y2QxN2UwYTBkMzE2NzFiYmMwZmZiMTEwNDEpoG0x; AMCV_A71B5B5B54F607AB0A4C98A2@AdobeOrg=-408604571|MCMID|45163264603655371960394402940086538807|MCAAMLH-1647625257|9|MCAAMB-1647625257|RKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y|MCCIDH|928616765|MCOPTOUT-1647027657s|NONE|vVersion|4.6.0; ebay=^js=1^sbf=#000000^; bm_sv=DD14CD633B272C0B8FD65FB3E74371AE~KzbWJA7VsqMQWhBm/y9HArqyO5eNlD/M6AAcLF/J1MLgZpTHJ2IKRpZKUKYnhY0t/X6ZZb22nBMRJBfI1e1qLidsNOfCzkLBJr1yuubeHA3b5p5TnxFJ6IEsuIQOzLXih1REcXhkX0OYS/z2zPoXcg==; __uzmcj2=160667912833; __uzmdj2=1647023926; npii=btguid/d7e6f7cd17e0a0d31671bbc0ffb1104165edfe37^cguid/25270bf517f0ab9712f4cbc5ff6763cb65edfe37^; ns1=BAQAAAX42/BiUAAaAAKUADWQMyrcyMzg5MTcyODU2LzA7ANgAWGQMyrdjODR8NjAxXjE2NDU2MDMzMzkzMTBeY205bllXZDFYekUwXjFeM3wyfDV8NHw3fDExXjFeMl40XjNeMTJeMTJeMl4xXjFeMF4xXjBeMV42NDQyNDU5MDc1dTk00YHvVQAIAjg4ZHVw5UXrPtc*; nonsession=BAQAAAX42/BiUAAaAAAgAHGJTJDcxNjQ1Njg4ODUxeDE5NDMwMDMxMzU3OHgweDJOADMADmQMyrc5NTMxNS05Mzc5LFVTQQBAAAtkDMq3cm9nYWd1XzM0MTEAnQAIZAzKtzAwMDAwMDAxAMoAIGXt/jdkN2U2ZjdjZDE3ZTBhMGQzMTY3MWJiYzBmZmIxMTA0MQDLAAFiK54/MwFkAAdl7f43IzAyMDA4YXYZzDxS9AqchdabJSGBc+LYyz+R; dp1=bu1p/cm9nYWd1XzM0MTE*65edfe37^pbf/#0000c000200000818082000000640ccab7^u1f/Rogelio65edfe37^expt/000164559580931763066661^mpc/0|0640ccab7^bl/USen-US65edfe37^");
        headers.add("Host", "www.ebay.com");
        headers.add("Referer", "https://www.ebay.com/");
        headers.add("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        headers.add("sec-ch-ua-full-version", "99.0.4844.51");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("sec-ch-ua-model","");
        headers.add("sec-ch-ua-platform", "Windows");
        headers.add("sec-ch-ua-platform-version", "14.0.0");
        headers.add("Sec-Fetch-Dest", "empty");
        headers.add("Sec-Fetch-Mode", "cors");
        headers.add("Sec-Fetch-Site", "same-origin");
        headers.add("ufes-cache-key", "731c911209244db9a204be89e6de41a2");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        //GET Method to Get existing Employee
        response = restTemplate.getForEntity(getURI,String.class);

        // Write response to file
        responseBody = response.getBody().toString();

        //Suppressing for log diffs
        System.out.println("GET Response Body :"+responseBody);

        // Check if the status code is 200
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

//    public static String getMessageFromResponse(String json) {
//        String successMessageText = null;
//        try {
//            JSONParser parser = new JSONParser();
//            JSONObject jsonResponseObject = new JSONObject();
//            jsonResponseObject = (JSONObject) (parser.parse(json));
//            String successMessage = jsonResponseObject.get("success").toString();
//
//            jsonResponseObject = (JSONObject) (parser.parse(successMessage));
//            successMessageText = jsonResponseObject.get("text").toString();
//        } catch (org.json.simple.parser.ParseException e) {
//            e.printStackTrace();
//        }
//        return successMessageText;
//    }

    @AfterTest
    public void afterTest() {
        this.restTemplate = new RestTemplate();
    }


}

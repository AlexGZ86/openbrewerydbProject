package testPackage;

import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import org.testng.asserts.SoftAssert;

import java.net.ResponseCache;
import java.util.ArrayList;

import static groovy.xml.Entity.not;
import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;
import static org.hamcrest.core.IsEqual.equalTo;

public class FilterTest {

    /* https://www.openbrewerydb.org/  PUBLIC API WITH ALL Documentation regarding its API and content
     * APi test shown for demo purpose only, by Alex G.
     *
    ---- the tests below validate the schema for each endpoint and test each filter for said endpoint
    * console will show the data expected and that was validated.
     */



//    @Test
//    public void filterFromTypeMicro() {
//        given().get("https://api.openbrewerydb.org/breweries?by_type=micro").then().statusCode(200).body("id[0]", equalTo(2)).
//                body("name[0]", equalTo("Avondale Brewing Co")).
//                body("city[0]", equalTo("Birmingham")).
//                body("state[0]", equalTo("Alabama")).
//                body("postal_code[0]", equalTo("35222-1932")).
//                body("country[0]", equalTo("United States")).
//                body("longitude[0]", equalTo("-86.774322")).
//                body("latitude[0]", equalTo("33.524521")).
//                body("phone[0]", equalTo("2057775456")).
//                body("website_url[0]", equalTo("http://www.avondalebrewing.com")).
//                body("updated_at[0]", equalTo("2018-08-23T23:19:57.825Z")).
//                body("created_at[0]", equalTo("2018-07-24T01:32:47.255Z"))
//                .log().all();
//    }

    /*
    / *No records from Type 'nano' exist  -this should fail as expected
    */
    @Test
    public void filterFromTypeNano() {
        given().get("https://api.openbrewerydb.org/breweries?by_type=nano").then().statusCode(200).
                body(equalTo(notNullValue()))
                .log().all();

    }

    @Test
    public void filterFromTypeRegional() {

        given().get("https://api.openbrewerydb.org/breweries?by_type=regional").then().statusCode(200).body("id[0]", equalTo(187)).
                body("name[0]", equalTo("SanTan Brewing Co")).
                body("brewery_type[0]", equalTo("regional")).
                body("city[0]", equalTo("Chandler")).
                body("state[0]", equalTo("Arizona")).
                body("postal_code[0]", equalTo("85225-7862")).
                body("country[0]", equalTo("United States")).
                body("longitude[0]", equalTo("-111.8423459")).
                body("latitude[0]", equalTo("33.3032436")).
                body("phone[0]", equalTo("4809178700")).
                body("website_url[0]", equalTo("http://www.santanbrewing.com")).
                body("updated_at[0]", equalTo("2018-08-23T23:22:33.482Z")).
                body("created_at[0]", equalTo("2018-07-24T01:32:49.861Z"))
                .log().all();
    }


    @Test
    public void filterFromTypeBrewpub() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=brewpub");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "104", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "BJs Restaurant & Brewery - Chandler", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "brewpub", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "3155 W Chandler Blvd", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Chandler", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "Arizona", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "85226-5175", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "4809170631", "phone is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

    @Test
    public void filterFromTypeLarge() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=large");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "2242", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "10 Barrel Brewing Co", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "large", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "830 W Bannock St", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Boise", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "Idaho", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "83702-5857", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "5416785228", "phone is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

    @Test
    public void filterFromTypePlanning() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=planning");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "264", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "Whistleing Springs Brewing Company / Dark Hills Brewery", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "planning", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Lowell", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "Arkansas", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "72745-9294", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "4792836365", "phone is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }


    @Test
    public void filterFromTypeBar() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=bar");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "4266", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "Matties", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "bar", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "2535 Mountain City Hwy", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Elko", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "Nevada", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "89801-4496", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "7757535100", "phone is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

    @Test
    public void filterFromTypeContract() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=contract");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "98", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "Bad Water Brewing", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "contract", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "4216 N Brown Ave", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Scottsdale", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "Arizona", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "85251-3914", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "5207459175", "phone is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

    @Test
    public void filterFromTypeProprietor() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=proprietor");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "669", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "High Water Brewing", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "proprietor", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Stockton", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "California", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "95204-2943", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "5304409098", "phone is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

    @Test
    public void filterFromTypeClosed() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=closed");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "1775", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "Gordon Biersch Brewery Restaurant - Washington", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "closed", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "900 F St NW", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Washington", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "District of Columbia", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "20004-1404", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "2027835454", "phone is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

    /*    no records from Type 'aaa'(Invalid) this should fail the assertions as Expected for demo purpose
    and display error in console.
     */
    @Test
    public void filterFromTypeInvalid() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=aaa");
        softAssert.assertNull(response);
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }


}
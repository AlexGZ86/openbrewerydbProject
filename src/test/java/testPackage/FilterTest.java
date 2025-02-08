package testPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.apache.http.util.Asserts.notEmpty;
import static org.hamcrest.Matchers.notNullValue;
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
        given().get("https://api.openbrewerydb.org/breweries?by_type=nano").then().statusCode(200)
                .log().all();

    }

    @Test
    public void filterFromTypeRegional() {

        given().get("https://api.openbrewerydb.org/breweries?by_type=regional").then().statusCode(200).body("id[0]",  Matchers.equalTo("63baab9a-b561-4eff-8619-f95290a61b77")).
                body("name[0]", equalTo("21st Amendment Brewery")).
                body("brewery_type[0]", equalTo("regional")).
                body("city[0]", equalTo("San Leandro")).
                body("state[0]", equalTo("California")).
                body("postal_code[0]", equalTo("94577-2334")).
                body("country[0]", equalTo("United States")).
                body("longitude[0]", equalTo("-122.1772928")).
                body("latitude[0]", equalTo("37.71130036")).
                body("phone[0]", equalTo("5105952111")).
                body("website_url[0]", equalTo("http://www.21st-Amendment.com"))
//                body("updated_at[0]", equalTo("2018-08-23T23:22:33.482Z"))
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
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "ef970757-fe42-416f-931d-722451f1f59c", "Id is not equal");
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
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "d6e77d51-2fa2-44cb-89b3-c94bf1dc77df", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "28th State Brewing", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "planning", "brewery_type is not equal");
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
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "d35b40b0-a3ff-4878-a6ee-9caa2149b521", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "1623 Brewing CO, llc", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "contract", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "1146 colonel Joshua Ct", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Westminister", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "Maryland", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "21157", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

    @Test
    public void filterFromTypeProprietor() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=proprietor");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "4b677b60-fef1-42e2-90ef-dadc1bd7fb06", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "14er Brewing Company", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "proprietor", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "2801 Walnut St", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Denver", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "Colorado", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "80205-2235", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "7207731437", "phone is not equal");
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

    @Test
    public void filterFromTypeClosed() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_type=closed");
        System.out.println(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].id").toString().replace("[", "").replace("]", ""), "5ae467af-66dc-4d7f-8839-44228f89b596", "Id is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].name").toString().replace("[", "").replace("]", ""), "101 North Brewing Company", "Name is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].brewery_type").toString().replace("[", "").replace("]", ""), "closed", "brewery_type is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].street").toString().replace("[", "").replace("]", ""), "1304 Scott St Ste D", "street is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].city").toString().replace("[", "").replace("]", ""), "Petaluma", "city is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].state").toString().replace("[", "").replace("]", ""), "California", "state is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].postal_code").toString().replace("[", "").replace("]", ""), "94954-7100", "postal_code is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].country").toString().replace("[", "").replace("]", ""), "United States", "country is not equal");
        System.out.println(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""));
        softAssert.assertEquals(response.getBody().jsonPath().get("[0].phone").toString().replace("[", "").replace("]", ""), "7077534934", "phone is not equal");
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
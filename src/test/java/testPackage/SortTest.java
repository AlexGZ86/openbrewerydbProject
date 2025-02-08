package testPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/* https://www.openbrewerydb.org/  PUBLIC API WITH ALL Documentation regarding its API and content
 * APi test shown for demo purpose only by Alex G.
 */


public class SortTest {

    /*this test scenario is to validate the body and its data for and assert each value . if value does not match test will give an assertion error as
    warning and show value that did NOT match!
     * */
    @Test
    public void sortWithStateTypeAndName() {
        given().get("https://api.openbrewerydb.org/breweries?by_state=ohio&sort=type,-name").then().statusCode(200).body("id[0]", Matchers.equalTo("6ec5c9d0-34df-4a2e-b041-ff956ce3d98a")).
                body("brewery_type[0]", equalTo("brewpub")).
                body("city[0]", equalTo("Marietta")).
                body("state[0]", equalTo("Ohio")).
                body("postal_code[0]", equalTo("45750-3125")).
                body("country[0]", equalTo("United States")).
                body("longitude[0]", equalTo("-81.45339993")).
                body("latitude[0]", equalTo("39.41184635")).
                body("phone[0]", equalTo("7403732739")).
                body("website_url[0]", equalTo("http://www.mariettabrewingcompany.com")).
                log().all();

    }


    @Test
    public void sortWithCityAndName() {
        given().get("https://api.openbrewerydb.org/breweries?by_city=san_diego&sort=-name").then().statusCode(200).body("id[0]", Matchers.equalTo("ef970757-fe42-416f-931d-722451f1f59c")).
                body("brewery_type[0]", equalTo("large")).
                body("city[0]", equalTo("San Diego")).
                body("state[0]", equalTo("California")).
                body("postal_code[0]", equalTo("92101-6618")).
                body("country[0]", equalTo("United States")).
                body("longitude[0]", equalTo("-117.129593")).
                body("latitude[0]", equalTo("32.714813")).
                body("phone[0]", equalTo("6195782311")).
                body("website_url[0]", equalTo("http://10barrel.com"))
                .log().all();

    }

    /*this is to show :No records from invalid State as "aaa"
     *as an expected invalid value in the endpoint- in this case 'state'.
     */

    @Test
    public void sortWithInvalid() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured.get("https://api.openbrewerydb.org/breweries?by_state=aaa&sort=type,-name");
        softAssert.assertNull(response);
        System.out.println(response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code not equal");
        softAssert.assertAll();
    }

}
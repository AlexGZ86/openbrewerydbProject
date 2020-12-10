package testPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.xml.bind.SchemaOutputResolver;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
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
        given().get("https://api.openbrewerydb.org/breweries?by_state=ohio&sort=type,-name").then().statusCode(200).body("id[0]", equalTo(5643)).
                body("brewery_type[0]", equalTo("brewpub")).
                body("city[0]", equalTo("Willoughby")).
                body("state[0]", equalTo("Ohio")).
                body("postal_code[0]", equalTo("44094-7804")).
                body("country[0]", equalTo("United States")).
                body("longitude[0]", equalTo("-81.4054897216877")).
                body("latitude[0]", equalTo("41.64152155")).
                body("phone[0]", equalTo("4409750202")).
                body("website_url[0]", equalTo("http://www.willoughbybrewing.com")).
                body("updated_at[0]", equalTo("2018-08-24T15:45:22.591Z")).
                body("created_at[0]", equalTo("2018-07-24T01:34:03.711Z"))
                .log().all();

    }


    @Test
    public void sortWithCityAndName() {
        given().get("https://api.openbrewerydb.org/breweries?by_city=san_diego&sort=-name").then().statusCode(200).body("id[0]", equalTo(1170)).
                body("brewery_type[0]", equalTo("micro")).
                body("city[0]", equalTo("San Diego")).
                body("state[0]", equalTo("California")).
                body("postal_code[0]", equalTo("92126-4541")).
                body("country[0]", equalTo("United States")).
                body("longitude[0]", equalTo("-117.121435")).
                body("latitude[0]", equalTo("32.895843")).
                body("phone[0]", equalTo("8586933441")).
                body("website_url[0]", equalTo("http://www.whitelabs.com")).
                body("updated_at[0]", equalTo("2018-08-24T00:04:52.152Z")).
                body("created_at[0]", equalTo("2018-07-24T01:33:02.898Z"))
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
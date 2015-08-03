package API;

import com.jayway.restassured.RestAssured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


/**
 * Created by Vignesh Rajan
 *
 * T
 */
public class APITest {
    String baseuri = "https://api.data.gov";
    String key = "obZAWYOQyXvjCGv9xiZK5q4xwhgh3iCMCS1QE3rZ";
    String location = "Austin,TX";
    String stationName = "HYATT AUSTIN";
    String verifyStreetAddress = "208 Barton Springs Rd";
    Integer id = null;

    @BeforeClass
    public void setTest(){
        RestAssured.baseURI = baseuri;
        RestAssured.basePath = "/nrel/alt-fuel-stations/v1";
    }
    @Test
    public void checkforStation(){

        // Create a Array list of objest from the Rest call
        ArrayList<Map<String,?>> jsonArray =
            given().
                    parameter("api_key", key).
                    parameter("location", location).
                    parameter("ev_network","ChargePoint Network").
            when().
                    get("/nearest.json/").
            then().
                    statusCode(200).
                    body("fuel_stations.station_name", hasItems(stationName)).
            extract()
                    .path("fuel_stations");

        //HashMap of objests for searching objects
        HashMap<Object, Map<String,?>> jsonHash = new HashMap<Object, Map<String, ?>>();

        //Create hashmap of json Array List
        for(Map<String,?> entry:jsonArray){
            jsonHash.put(entry.get("station_name"),entry);
        }
        id = (Integer) jsonHash.get(stationName).get("id");

        System.out.println(id);

    }

    @Test(dependsOnMethods ="checkforStation" )
    public void getStationAddressFromID(){

        String streetAddress =
        given().log().all().
                parameter("api_key", key).
        when().
                get("/" + id + ".json/").
        then().log().body().
                statusCode(200).
                body("alt_fuel_station.street_address",equalTo(verifyStreetAddress)).
        extract()
                .path("alt_fuel_station.street_address");

        System.out.println(streetAddress);
    }

}

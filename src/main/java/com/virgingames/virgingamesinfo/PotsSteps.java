package com.virgingames.virgingamesinfo;

import com.virgingames.constants.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.hasEntry;

public class PotsSteps {

    static ValidatableResponse response;
    //@Step annotation shows the step info in Serenity Report
    @Step("Getting all pots information")
    public ValidatableResponse getAllPotsInfo() {

        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_POTS)
                .then().statusCode(200);
    }
    @Step("Getting all pots information where currency is GBP")
    public ValidatableResponse getAllPotsWithCurrencyGBP() {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Connection", "keep-alive")
                .when()
                .queryParam("currency","GBP")
                .get(EndPoints.GET_ALL_POTS)
                .then().log().all().statusCode(200);
    }
    @Step("Getting all pots information where currency is EUR")
    public ValidatableResponse  getAllPotsWithCurrencyEUR(){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Connection", "keep-alive")
                .when()
                .queryParam("currency","EUR")
                .get(EndPoints.GET_ALL_POTS)
                .then().log().all().statusCode(200);
    }
    @Step("Getting all pots information where currency is SEK")
    public ValidatableResponse getAllPotsWithCurrencySEK(){

        return SerenityRest.given().log().all()
                .when()
                .queryParam("currency","SEK")
                .get(EndPoints.GET_ALL_POTS)
                .then().statusCode(200);
    }
    @Step("Verify pot name by it's id ,name, currency and amount")
    public void getVerifyPotIdWithName() {
        response = getAllPotsInfo();
        response
                .body("data,pots[4]", hasEntry("id", "classicwildsprogressive"))
                .body("data,pots[4]", hasEntry("name", "play-classic-wilds-progressive"))
                .body("data,pots[4]", hasEntry("currency", "GBP"))
                .body("data.pots[4]", hasEntry("amount", 13336.4));
    }
        @Step
        public void getTotalRecords(){
            response= getAllPotsInfo();
            int total=43;
            int size = response.extract().path("data.pots.size");
            System.out.println("Total number of records are : " +size);
//        response = steps.getAllPotsInfo();
//        List<Integer> length =  response.extract().path("data.pots.length");
//        System.out.println("Size of the data :" +length.size());
//        response = getAllPotsInfo();
//        int total = 43;
//        int size = response.extract().path("data.pots.size");
//        Assert.assertEquals(total,size);
    }

}

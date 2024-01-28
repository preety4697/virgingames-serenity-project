package com.virgingames.steps;

import com.virgingames.testbase.TestBase;
import com.virgingames.virgingamesinfo.PotsSteps;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;

@RunWith(SerenityRunner.class)

public class VirginGamesSteps extends TestBase {
    static ValidatableResponse response;
    public String id = "progressive_id1";
    public String name =  "play-around-the-reels-jackpot";
    public String currency = "GBP";
    public double amount = 2447.23;

    @Steps
    PotsSteps steps;

    @Title("This will fetch all games information")
    @Test
    public void test001(){
       response =  steps.getAllPotsInfo().statusCode(200);
    }
    @Title("This will fetch all the information about Pots where currency is GBP")
    @Test
    public void test002(){
        response = steps.getAllPotsWithCurrencyGBP().log().all().statusCode(200);
        response.body("data.pots.currency" , hasItem("GBP"));
    }


    @Title("This will get all the information about Pots where currency is EUR")
    @Test
    public void testoo3(){
         response = steps.getAllPotsWithCurrencyEUR().log().all().statusCode(200);
        response.body("data.pots.currency" ,hasItem("EUR"));
    }


    @Title("This will get all the information about Pots where currency is SEK")
    @Test
    public void testoo4(){

        response = steps.getAllPotsWithCurrencySEK().log().all().statusCode(200);
        response.body("data.pots.currency" ,hasItem("SEK"));
    }

    @Title("This will verify id and name of different pots")
    @Test
    public void  getToVerifyDifferentPots()
    {
        response = steps.getAllPotsInfo();
        response
                .body("data.pots[6]", hasEntry("id", "diamondbonanza100"))
                .body("data.pots[19]", hasEntry("name", "play-legendary-queen"));
        response.log().all().statusCode(200);
    }

    @Title("This will print the size of the data")
    @Test
    public void sizeOfTheData()
    {
        response = steps.getAllPotsInfo();
        List<Integer> length =  response.extract().path("data.pots.length");
        System.out.println("Size of the data :" +length.size());
    }
}

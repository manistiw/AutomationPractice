package apicomponent;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


@Component
public class RestAssuredClient {

    @Autowired
    public RestAssuredRequestSpec reqSpecs;


   public ValidatableResponse doGet(String path){
        return given().
                spec(reqSpecs.getSpecification()).log().body().
                when().
                get(path).
                then().log().body();
    }

    public ValidatableResponse doPost(String url, JSONObject requestBody) {
        return given().
                spec(reqSpecs.getSpecification()).log().all().
                body(requestBody.toString()).
                post(url).
                then().log().body();
    }


    public ValidatableResponse doDelete(String path){
        return given().
                spec(reqSpecs.getSpecification()).log().all().
                when().
                delete(path).then().log().body();
    }
    public ValidatableResponse doPut(String strBody,String url) {
        return given().
                spec(reqSpecs.getSpecification()).log().all().
                body(strBody).
                put(url).
                then().log().body();
    }
}

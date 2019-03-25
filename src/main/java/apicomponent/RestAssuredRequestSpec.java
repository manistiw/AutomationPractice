package apicomponent;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
public class RestAssuredRequestSpec {
    private final Logger log = LoggerFactory.getLogger(RestAssuredRequestSpec.class);

    private RestRequestSpecBuilder reqSpecBuilder;

    @Autowired
    public RestAssuredRequestSpec(RestRequestSpecBuilder reqSpecBuilder) {
        this.reqSpecBuilder = reqSpecBuilder;
    }


    public RequestSpecification getSpecification(){
        return reqSpecBuilder.getReqBuilder().build();
    }

    public RestRequestSpecBuilder resetSpecification(){
        this.reqSpecBuilder.setReqBuilder(new RequestSpecBuilder());
        return reqSpecBuilder;
    }




}

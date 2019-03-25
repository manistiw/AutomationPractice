package apicomponent;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

@Component
public class RestRequestSpecBuilder {

    public RestRequestSpecBuilder() {

    }

    public RequestSpecBuilder getReqBuilder() {
        return this.reqBuilder;
    }

    public void setReqBuilder(RequestSpecBuilder reqBuilder) {
        this.reqBuilder = reqBuilder;
        this.reqBuilder.setContentType(ContentType.JSON);
        this.reqBuilder.setBaseUri(baseUrl);
    }

    private RequestSpecBuilder reqBuilder;

    private ContentType conType;

    public String getBaseUrl() {
        return baseUrl;
    }

    @Value("${environment.baseurl}")
    private String baseUrl;

    @Autowired
    public RestRequestSpecBuilder(@Value("${environment.username}") String username,@Value("${environment.password}") String password,@Value("${environment.baseurl}") String baseUrl) {
        this.reqBuilder=new RequestSpecBuilder();
        this.reqBuilder.setContentType(ContentType.JSON);
        this.reqBuilder.addHeader("Content-Type", "text/html; charset=UTF-8");
        this.reqBuilder.setBaseUri(baseUrl);
    }
}

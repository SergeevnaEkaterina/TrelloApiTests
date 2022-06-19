package core;

import static org.hamcrest.Matchers.lessThan;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.ResponseSpecification;
import java.util.Map;
import org.apache.http.HttpStatus;

public class BaseService {
    protected Method requestedMethod;
    protected Map<String, String> queryParameters;
    protected Map<String, String> pathParameters;

    public BaseService(Method requestedMethod, Map<String, String> queryParameters,
                       Map<String, String> pathParameters) {
        this.requestedMethod = requestedMethod;
        this.queryParameters = queryParameters;
        this.pathParameters = pathParameters;
    }


    public static ResponseSpecification okResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(30000L))
                .build();
    }

    public static ResponseSpecification notFoundResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .build();
    }


    public String convertToString() {
        StringBuilder builder = new StringBuilder();
        pathParameters.keySet().forEach(p -> builder.append("{").append(p).append("}").append("/"));
        return builder.toString();
    }
}

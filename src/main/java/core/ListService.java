package core;

import static constants.ParametersData.KEY;
import static constants.ParametersData.TOKEN;
import static constants.PropertyValues.URL_LIST;
import static constants.PropertyValues.USER_KEY;
import static constants.PropertyValues.USER_TOKEN;

import beans.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.net.URI;
import java.util.Map;

public class ListService extends BaseService {
    public static final URI TRELLO_LIST_URI = URI.create(URL_LIST);


    public ListService(Method requestMethod, Map<String, String> queryParams, Map<String, String> pathParams) {
        super(requestMethod, queryParams, pathParams);
    }


    public Response sendRequest() {
        return RestAssured
                .given(requestSpecification()).log().all()
                .pathParams(pathParameters)
                .queryParams(queryParameters)
                .queryParam(KEY, USER_KEY)
                .queryParam(TOKEN, USER_TOKEN)
                .request(requestedMethod, convertToString())
                .prettyPeek();
    }

    public static List extractListFromJson(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<List>() {
        }.getType());
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBaseUri(TRELLO_LIST_URI)
                .build();
    }

}


package core;

import static constants.ParametersData.KEY;
import static constants.ParametersData.TOKEN;
import static constants.PropertyValues.URL_BOARD;
import static constants.PropertyValues.USER_KEY;
import static constants.PropertyValues.USER_TOKEN;

import beans.Board;
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

public class BoardService extends BaseService {
    public static final URI BOARD_URL = URI.create(URL_BOARD);

    public BoardService(Method requestedMethod, Map<String, String> queryParameters,
                        Map<String, String> pathParameters) {
        super(requestedMethod, queryParameters, pathParameters);
    }


    public Response sendRequest() {
        return RestAssured
                .given(requestSpecification()).log().all()
                .queryParam(KEY, USER_KEY)
                .queryParam(TOKEN, USER_TOKEN)
                .pathParams(pathParameters)
                .queryParams(queryParameters)
                .request(requestedMethod, convertToString())
                .prettyPeek();
    }

    public static Board extractBoardFromJson(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<Board>() {
        }.getType());
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBaseUri(BOARD_URL)
                .build();
    }


}

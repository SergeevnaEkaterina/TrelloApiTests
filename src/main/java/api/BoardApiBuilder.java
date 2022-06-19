package api;

import static constants.ParametersData.DESCRIPTION;
import static constants.ParametersData.ID;
import static constants.ParametersData.IS_CLOSED;
import static constants.ParametersData.NAME;

import core.BoardService;
import io.restassured.http.Method;
import java.util.HashMap;
import java.util.Map;

public class BoardApiBuilder {
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> pathParameters = new HashMap<>();
    private Method requestedMethod = Method.GET;

    public static BoardApiBuilder boardApiBuilder() {
        return new BoardApiBuilder();
    }

    public BoardApiBuilder setMethod(Method method) {
        requestedMethod = method;
        return this;
    }

    public BoardApiBuilder setId(String id) {
        pathParameters.put(ID, id);
        return this;
    }

    public BoardApiBuilder setName(String name) {
        queryParameters.put(NAME, name);
        return this;
    }

    public BoardApiBuilder setDesc(String desc) {
        queryParameters.put(DESCRIPTION, desc);
        return this;
    }

    public BoardApiBuilder setClosed(String closed) {
        queryParameters.put(IS_CLOSED, closed);
        return this;
    }

    public BoardService buildBoardApiRequest() {
        return new BoardService(requestedMethod, queryParameters, pathParameters);
    }
}

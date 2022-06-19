package api;

import static constants.ParametersData.ID;
import static constants.ParametersData.ID_BOARD;
import static constants.ParametersData.IS_CLOSED;
import static constants.ParametersData.NAME;

import core.ListService;
import io.restassured.http.Method;
import java.util.HashMap;
import java.util.Map;

public class ListApiBuilder {
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> pathParameters = new HashMap<>();
    private Method requestedMethod = Method.GET;

    public static ListApiBuilder listApiBuilder() {
        return new ListApiBuilder();
    }

    public ListApiBuilder setMethod(Method method) {
        requestedMethod = method;
        return this;
    }

    public ListApiBuilder setId(String id) {
        pathParameters.put(ID, id);
        return this;
    }

    public ListApiBuilder setIdBoard(String idBoard) {
        queryParameters.put(ID_BOARD, idBoard);
        return this;
    }

    public ListApiBuilder setName(String name) {
        queryParameters.put(NAME, name);
        return this;
    }

    public ListApiBuilder setClosed(String closed) {
        queryParameters.put(IS_CLOSED, closed);
        return this;
    }

    public ListService buildListApiRequest() {
        return new ListService(requestedMethod, queryParameters, pathParameters);
    }
}


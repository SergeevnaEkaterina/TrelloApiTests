package api;


import static constants.ParametersData.DESCRIPTION;
import static constants.ParametersData.ID;
import static constants.ParametersData.ID_LIST;
import static constants.ParametersData.NAME;

import core.CardService;
import io.restassured.http.Method;
import java.util.HashMap;
import java.util.Map;

public class CardApiBuilder {
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> pathParameters = new HashMap<>();
    private Method requestedMethod = Method.GET;

    public static CardApiBuilder cardApiBuilder() {
        return new CardApiBuilder();
    }

    public CardApiBuilder setMethod(Method method) {
        requestedMethod = method;
        return this;
    }

    public CardApiBuilder setId(String id) {
        pathParameters.put(ID, id);
        return this;
    }

    public CardApiBuilder setIdList(String idList) {
        queryParameters.put(ID_LIST, idList);
        return this;
    }

    public CardApiBuilder setName(String name) {
        queryParameters.put(NAME, name);
        return this;
    }

    public CardApiBuilder setDesc(String desc) {
        queryParameters.put(DESCRIPTION, desc);
        return this;
    }

    public CardService buildCardApiRequest() {
        return new CardService(requestedMethod, queryParameters, pathParameters);
    }
}


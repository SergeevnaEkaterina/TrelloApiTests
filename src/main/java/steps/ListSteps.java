package steps;

import static api.ListApiBuilder.listApiBuilder;
import static constants.PropertyValues.NEW_ACCESS;
import static constants.PropertyValues.NEW_LIST_NAME;
import static core.BaseService.okResponseSpecification;
import static core.ListService.extractListFromJson;

import api.ListApiBuilder;
import beans.List;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class ListSteps {

    @Step("Create list in board in Trello")
    public String createListInBoard(String boardId, List list) {
        ListApiBuilder apiBuilder = listApiBuilder()
                .setMethod(Method.POST)
                .setName(list.getName())
                .setIdBoard(boardId);
        return sendRequestAndGetResponse(apiBuilder, okResponseSpecification()).getId();
    }

    @Step("Get list from board in Trello")
    public List getListFromTrello(String listId) {
        ListApiBuilder apiBuilder = listApiBuilder()
                .setMethod(Method.GET)
                .setId(listId);
        return sendRequestAndGetResponse(apiBuilder, okResponseSpecification());
    }

    @Step("Modify list")
    public List modifyList(String listId) {
        ListApiBuilder builder = listApiBuilder()
                .setMethod(Method.PUT)
                .setId(listId);
        builder.setName(NEW_LIST_NAME);
        builder.setClosed(NEW_ACCESS);
        return sendRequestAndGetResponse(builder, okResponseSpecification());

    }


    public List sendRequestAndGetResponse(ListApiBuilder api, ResponseSpecification resp) {
        Response response = api
                .buildListApiRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(resp);
        return extractListFromJson(response);

    }


}


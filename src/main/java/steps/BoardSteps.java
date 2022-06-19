package steps;

import static api.BoardApiBuilder.boardApiBuilder;
import static constants.PropertyValues.NEW_ACCESS;
import static constants.PropertyValues.NEW_BOARD_NAME;
import static constants.PropertyValues.NEW_DESC;
import static core.BaseService.notFoundResponseSpecification;
import static core.BaseService.okResponseSpecification;
import static core.BoardService.extractBoardFromJson;

import api.BoardApiBuilder;
import beans.Board;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class BoardSteps {

    @Step("Create board")
    public String createNewBoard(Board board) {
        BoardApiBuilder api = boardApiBuilder()
                .setMethod(Method.POST)
                .setName(board.getName());
        return extractBoardFromJson(sendRequestAndGetResponse(api, okResponseSpecification())).getId();

    }


    @Step("Get board")
    public Board getBoard(String boardId) {
        BoardApiBuilder api = boardApiBuilder()
                .setMethod(Method.GET)
                .setId(boardId);
        return extractBoardFromJson(sendRequestAndGetResponse(api, okResponseSpecification()));

    }

    @Step("Delete board")
    public void deleteBoard(String boardId) {
        BoardApiBuilder api = boardApiBuilder()
                .setMethod(Method.DELETE)
                .setId(boardId);
        sendRequestAndGetResponse(api, okResponseSpecification());

    }

    public Response sendRequestAndGetResponse(BoardApiBuilder api, ResponseSpecification resp) {
        Response response = api
                .buildBoardApiRequest()
                .sendRequest();
        response.then()
                .assertThat()
                .spec(resp);
        return response;

    }

    @Step("Get deleted board")
    public void getDeletedBoard(String boardId) {
        BoardApiBuilder builder = boardApiBuilder()
                .setMethod(Method.GET)
                .setId(boardId);
        sendRequestAndGetResponse(builder, notFoundResponseSpecification());

    }

    @Step("Delete board")
    public Board modifyExistingBoard(String boardId) {
        BoardApiBuilder api = boardApiBuilder()
                .setMethod(Method.PUT)
                .setId(boardId);
        api.setName(NEW_BOARD_NAME);
        api.setDesc(NEW_DESC);
        api.setClosed(NEW_ACCESS);
        return extractBoardFromJson(sendRequestAndGetResponse(api, okResponseSpecification()));
    }


}

package data;

import static constants.PropertyValues.CARD_NAME;
import static constants.PropertyValues.LIST_NAME;
import static constants.PropertyValues.NAME_BOARD;

import beans.Board;
import beans.Card;
import beans.List;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider
    public Object[][] boardData() {
        Board board = new Board();
        board.setName(NAME_BOARD);
        return new Object[][]{
                {board}
        };
    }


    @DataProvider
    public Object[][] listData() {
        Board board = new Board();
        board.setName(NAME_BOARD);
        List list = new List();
        list.setName(LIST_NAME);
        return new Object[][]{
                {board, list}
        };
    }

    @DataProvider
    public Object[][] cardData() {
        Board board = new Board();
        board.setName(NAME_BOARD);
        List list = new List();
        list.setName(LIST_NAME);
        Card card = new Card();
        card.setName(CARD_NAME);
        return new Object[][]{
                {board, list, card}
        };
    }

}

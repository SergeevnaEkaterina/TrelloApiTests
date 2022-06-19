package com.epam.tc.hw9;

import static constants.PropertyValues.CARD_DESC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import beans.Board;
import beans.Card;
import beans.List;
import data.DataProviders;
import org.testng.annotations.Test;
import steps.CardSteps;
import steps.ListSteps;

public class CardTests extends BaseTests {

    @Test(dataProvider = "cardData", dataProviderClass = DataProviders.class)
    public void createCardTest(Board board, List list, Card card) {
        listSteps = new ListSteps();
        cardSteps = new CardSteps();
        sharedBoardId = boardSteps.createNewBoard(board);
        sharedListId = listSteps.createListInBoard(sharedBoardId, list);
        sharedCardId = cardSteps.createNewCard(sharedListId, card);
        Card createdCard = cardSteps.getExistingCard(sharedCardId);
        assertThat(createdCard.getName(), equalTo(card.getName()));
    }

    @Test(dataProvider = "cardData", dataProviderClass = DataProviders.class)
    public void deleteCardTest(Board board, List list, Card card) {
        listSteps = new ListSteps();
        cardSteps = new CardSteps();
        sharedBoardId = boardSteps.createNewBoard(board);
        sharedListId = listSteps.createListInBoard(sharedBoardId, list);
        sharedCardId = cardSteps.createNewCard(sharedListId, card);
        cardSteps.getExistingCard(sharedCardId);
        cardSteps.deleteCard(sharedCardId);
        cardSteps.getDeletedCard(sharedCardId);
    }

    @Test(dataProvider = "cardData", dataProviderClass = DataProviders.class)
    public void modifyCardTest(Board board, List list, Card card) {
        listSteps = new ListSteps();
        cardSteps = new CardSteps();
        sharedBoardId = boardSteps.createNewBoard(board);
        sharedListId = listSteps.createListInBoard(sharedBoardId, list);
        sharedCardId = cardSteps.createNewCard(sharedListId, card);
        Card cardToUpdate = cardSteps.modifyCard(sharedCardId);
        assertThat(cardToUpdate.getDesc(), equalTo(CARD_DESC));
    }
}

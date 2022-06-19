package com.epam.tc.hw9;

import static constants.PropertyValues.NEW_ACCESS;
import static constants.PropertyValues.NEW_LIST_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import beans.Board;
import beans.List;
import data.DataProviders;
import org.testng.annotations.Test;
import steps.ListSteps;

public class ListTests extends BaseTests {

    @Test(dataProvider = "listData", dataProviderClass = DataProviders.class)
    public void createListTest(Board board, List list) {
        listSteps = new ListSteps();
        sharedBoardId = boardSteps.createNewBoard(board);
        sharedListId = listSteps.createListInBoard(sharedBoardId, list);
        List createdList = listSteps.getListFromTrello(sharedListId);
        assertThat(createdList.getName(), equalTo(list.getName()));
    }

    @Test(dataProvider = "listData", dataProviderClass = DataProviders.class)
    public void modifyListTest(Board board, List list) {
        listSteps = new ListSteps();
        sharedBoardId = boardSteps.createNewBoard(board);
        sharedListId = listSteps.createListInBoard(sharedBoardId, list);
        List listToUpdate = listSteps.modifyList(sharedListId);
        assertThat(listToUpdate.getName(), equalTo(NEW_LIST_NAME));
        assertThat(String.valueOf(listToUpdate.getClosed()), equalTo(NEW_ACCESS));
    }
}

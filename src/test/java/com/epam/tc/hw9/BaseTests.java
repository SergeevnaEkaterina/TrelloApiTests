package com.epam.tc.hw9;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.BoardSteps;
import steps.CardSteps;
import steps.ListSteps;

public class BaseTests {
    BoardSteps boardSteps;
    ListSteps listSteps;
    CardSteps cardSteps;
    String sharedBoardId;
    String sharedListId;
    String sharedCardId;

    @BeforeMethod
    public void beforeMethod() {
        boardSteps = new BoardSteps();
    }

    @AfterMethod
    public void afterMethod() {
        if (sharedBoardId != null) {
            boardSteps.deleteBoard(sharedBoardId);
        }
    }
}

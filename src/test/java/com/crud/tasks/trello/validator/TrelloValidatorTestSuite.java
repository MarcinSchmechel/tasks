package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTestSuite {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardsTest(){
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "List1", true));
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("1", "Board1", trelloList));
        trelloBoard.add(new TrelloBoard("2", "TeSt", trelloList));
        trelloBoard.add(new TrelloBoard("3", "test", trelloList));
        trelloBoard.add(new TrelloBoard("4", "Board1 test", trelloList));

        //When
        List<TrelloBoard> validatedTrelloBoard = trelloValidator.validateTrelloBoards(trelloBoard);

        //Then
        assertEquals(2,validatedTrelloBoard.size());

    }
}

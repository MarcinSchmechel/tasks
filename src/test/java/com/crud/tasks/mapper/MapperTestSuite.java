package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToBoardsTest() {
    //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "List1", true));
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "Board1", trelloListDtos));

    //When
        List<TrelloBoard> mappedTrelloBoard = trelloMapper.mapToBoards(trelloBoardDto);

     //Then
        assertNotNull(mappedTrelloBoard);
        assertEquals(1,mappedTrelloBoard.size());
        assertTrue(mappedTrelloBoard.get(0).getName().contains("Board1"));
        assertTrue(mappedTrelloBoard.get(0).getLists().get(0).getName().contains("List1"));
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "List1", true));
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("1", "Board1", trelloList));

        //When
        List<TrelloBoardDto> mappedTrelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoard);

        //Then
        assertNotNull(mappedTrelloBoardDto);
        assertEquals(1,mappedTrelloBoardDto.size());
        assertTrue(mappedTrelloBoardDto.get(0).getName().contains("Board1"));
        assertTrue(mappedTrelloBoardDto.get(0).getLists().get(0).getName().contains("List1"));
    }

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test1", "Test_content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertNotNull(task);
        assertTrue(task.getTitle().contains("Test1"));
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "Test1", "Test_content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertNotNull(taskDto);
        assertTrue(taskDto.getTitle().contains("Test1"));
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test1", "Test_content1"));
        taskList.add(new Task(2L, "Test2", "Test_content2"));
        taskList.add(new Task(3L, "Test3", "Test_content3"));

        //When
        List<TaskDto> taskDtoList= taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(taskDtoList);
        assertEquals(3,taskDtoList.size());
        assertTrue(taskDtoList.get(2).getTitle().contains("Test3"));
    }
}

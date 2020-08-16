package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;

    @Test
    public void getEmptyTaskListTest() throws Exception{
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
    @Test
    public void getTaskListTest() throws Exception{
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test", "Test_content"));

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test")))
                .andExpect(jsonPath("$[0].content", is("Test_content")));
    }

    @Test
    public void getTasksByIdTest() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Test_content");
        Task task = new Task(1L, "T", "T_content");
        when(service.getTaskById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(service.getTaskById(ArgumentMatchers.anyLong()).orElseThrow(TaskNotFoundException::new))).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test")))
                .andExpect(jsonPath("$.content", is("Test_content")));
    }
    @Test(expected = TaskNotFoundException.class)
    public void getTasksByIdTaskNotFoundExceptionTest() throws Exception{

        taskMapper.mapToTaskDto(service.getTaskById(ArgumentMatchers.anyLong()).orElseThrow(TaskNotFoundException::new));
    }

    public void deleteTaskTest(){
        //Given


        //When & Then

    }

    public void updateTaskTest(){
        //Given


        //When & Then

    }

    @Test
    public void shouldCreateTaskTest() throws Exception{
        //Given
        Task task = new Task(1L, "T", "T_content");
//        TaskDto taskDto = new TaskDto(1L, "T_dto", "T_content_dto");

        //When & Then
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        service.saveTask(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class)));

//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(taskDto);
//
//        mockMvc.perform(post("/v1/task/createTask")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
    }

}
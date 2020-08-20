package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasks(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task());

        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> receivedList = dbService.getAllTasks();

        //Then
        assertNotNull(receivedList);
        assertEquals(1,receivedList.size());
    }

    @Test
    public void getTasksById(){
        //Given
        Task task = new Task(1L, "First", "First_content");

        when(repository.findById(anyLong())).thenReturn(Optional.of(task));

        //When
        Optional<Task> receivedTask = dbService.getTaskById(1L);

        //Then
        assertNotNull(receivedTask);
        assertEquals("First", receivedTask.get().getTitle());
        assertEquals("First_content", receivedTask.get().getContent());
    }

    @Test
    public void saveTasks(){
        //Given
        Task task = new Task(1L, "First", "First_content");

        when(repository.save(ArgumentMatchers.any(Task.class))).thenReturn((task));

        //When
        Task receivedTask = dbService.saveTask(task);

        //Then
        assertNotNull(receivedTask);
        assertEquals("First", receivedTask.getTitle());
        assertEquals("First_content", receivedTask.getContent());
    }

    @Test
    public void deleteTasks() {

    }
}
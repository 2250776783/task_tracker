package service;

import model.StatusType;
import model.Task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    private static int taskId = 0;


    public void listTask(){

    }

    public void addTask(String task){
        Task newTask = new Task();
        newTask.setId(taskId++);
        newTask.setDescription(null);
        newTask.setStatus(StatusType.TODO);
        newTask.setCreatedAt(System.currentTimeMillis());
    }

    public void updateTask(int task, String description){}

    public void deleteTask(int task){}

    public void listTaskByType(String status){}

    public void markInProgress(int task){}

    public void markDone(int task){}
}

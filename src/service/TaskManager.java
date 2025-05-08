package service;

import model.StatusType;
import model.Task;
import java.util.Date;
import java.util.ArrayList;

public class TaskManager {
    public static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskId = 0;
    private TaskFileStroage taskFileStroage;

    public TaskManager(){
        taskFileStroage = new TaskFileStroage();

        // 从文件中加载任务. 如果文件不存在, 则创建一个空列表
        taskFileStroage.loadTask(taskList);
        
        // 初始化taskId为当前最大ID值
        for(Task task : taskList) {
            if(task.getId() > taskId) {
                taskId = task.getId();
            }
        }
    }

    // 显示所有任务
    public void listTask(){
        for(Task task : taskList){
            System.out.println(task.toString());
        }
    }

    // 添加任务
    public void addTask(String task){
        Task newTask = new Task();
        newTask.setId(++taskId);
        newTask.setDescription(task);
        newTask.setStatus(StatusType.TODO);
        String now = new Date(System.currentTimeMillis()).toString();
        newTask.setCreatedAt(now);
        newTask.setUpdatedAt(now);

        // 打印任务信息
        System.out.println("task: " + newTask.toString());
        System.out.println("taskList: " + taskList.toString());
        taskList.add(newTask);
        System.out.println("taskList: " + taskList.toString());

        System.out.println("task added successfully (ID: " + newTask.getId() + ")");

        

        taskFileStroage.saveTask(newTask);
    }

    public void updateTask(int task, String description){}

    public void deleteTask(int task){}

    public void listTaskByType(String status){}

    public void markInProgress(int task){}

    public void markDone(int task){}
}

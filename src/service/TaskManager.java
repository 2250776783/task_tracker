package service;

import model.StatusType;
import model.Task;
import java.util.Date;
import java.io.IOException;
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

        taskFileStroage.saveTask(newTask);  // 保存新任务
    }

    public void updateTask(int task, String description) throws IOException{
        System.out.println("updateTask: " + task + " " + description);
        // 更新任务, arraylist中存的时对象引用，所以直接修改对象的属性即可，不需要删除和添加
        for(Task t : taskList){
            if(t.getId() == task){
                t.setDescription(description);
                t.setUpdatedAt(new Date(System.currentTimeMillis()).toString());
                System.out.println("taskList: " + taskList.toString());
                System.out.println("task updated successfully (ID: " + t.getId() + ")");
                taskFileStroage.saveTaskList(taskList);  // 保存整个任务列表
                return;  // 找到并更新后直接返回
            }
        }
        System.out.println("Task not found with ID: " + task);  // 如果没有找到任务
    }

    public void deleteTask(int task) throws IOException{
        for(Task t : taskList){
            if(t.getId() == task){
                taskList.remove(t);
                System.out.println("task deleted successfully (ID: " + t.getId() + ")");
                taskFileStroage.saveTaskList(taskList);  // 保存更新后的任务列表
                return;
            }
        }
    }

    public void listTaskByType(String status){
        for(Task t : taskList){
            if(t.getStatus().toString().equals(status)){
                System.out.println(t.toString());
            }
        }
    }

    public void markInProgress(int task){
        for(Task t : taskList){
            if(t.getId() == task){
                t.setStatus(StatusType.IN_PROGRESS);
                t.setUpdatedAt(new Date(System.currentTimeMillis()).toString());
                System.out.println("task marked as in progress (ID: " + t.getId() + ")");
            }
        }
    }

    public void markDone(int task){
        for(Task t : taskList){
            if(t.getId() == task){
                t.setStatus(StatusType.DONE);
                t.setUpdatedAt(new Date(System.currentTimeMillis()).toString());
                System.out.println("task marked as done (ID: " + t.getId() + ")");
            }
        }
    }
}

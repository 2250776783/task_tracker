package start;
import service.TaskManager;

import java.util.Arrays;

public class App {
    TaskManager taskManager;

    

    public App(String[] args){
        System.out.println(Arrays.toString(args));

        taskManager = new TaskManager();
        argsPhase(args);
    }

    private void help() {
        System.out.println("Usage: java Task_CLI [options]");
        System.out.println("Options:");
        System.out.println("  -h, --help       Show this help message");
        System.out.println("  -v, --version    Show version information");
        System.out.println("  -l, --list       List all tasks");
        System.out.println("  -a, --add <task> Add a new task");
        System.out.println("  -r, --remove <id> Remove a task by ID");
        System.out.println("  -c, --complete <id> Mark a task as complete by ID");
    }

    private void argsPhase(String[] args){
        if(args.length == 0) {
            help();
            return;
        }

        // 不区分大小写
        String command = args[0].toLowerCase();

        switch(command) {
            case "add" -> taskManager.addTask(args[1]);
            case "update" -> taskManager.updateTask(Integer.parseInt(args[1]), args[2]);
            case "delete" -> taskManager.deleteTask(Integer.parseInt(args[1]));
            // 判断是显示所有任务还是显示特定类型的任务
            case "list" -> {
                if(args.length == 1) {
                    taskManager.listTask();
                } else {
                    taskManager.listTaskByType(args[1]);
                }
            }

        }
    }
}

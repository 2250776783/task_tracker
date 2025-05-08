package service;

import model.StatusType;
import model.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskFileStroage {

    // 任务保存到的文件路径
    private final String filePath = "tasks.json";
    

    // 保存任务, 追加写入
    public void saveTask(Task task){
        try (FileWriter writer = new FileWriter(filePath, true)) {
            String json = String.format(
                "{\"id\":%d,\"description\":\"%s\",\"status\":\"%s\",\"createdAt\":\"%s\",\"updatedAt\":\"%s\"}\n",
                task.getId(),
                task.getDescription() == null ? "" : escapeJson(task.getDescription()),
                task.getStatus() == null ? "TODO" : escapeJson(task.getStatus().toString()),
                task.getCreatedAt() == null ? "" : escapeJson(task.getCreatedAt()),
                task.getUpdatedAt() == null ? "" : escapeJson(task.getUpdatedAt())
            );
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从JSON文件加载任务
    public void loadTask(ArrayList<Task> taskList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseJson(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 解析JSON字符串为Task对象
    private Task parseJson(String json) {
        try {
            // 移除首尾的 {}
            json = json.substring(1, json.length() - 1);
            
            // 分割属性
            String[] properties = json.split(",");
            Task task = new Task();
            
            for (String prop : properties) {
                String[] keyValue = prop.split(":");
                String key = keyValue[0].replace("\"", "").trim();
                String value = keyValue[1].replace("\"", "").trim();
                
                switch (key) {
                    case "id" -> task.setId(Integer.parseInt(value));
                    case "description" -> task.setDescription(unescapeJson(value));
                    case "status" -> task.setStatus(StatusType.valueOf(value));
                    case "createdAt" -> task.setCreatedAt(value);
                    case "updatedAt" -> task.setUpdatedAt(value);
                }
            }
            return task;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 转义JSON字符串中的特殊字符
    private String escapeJson(String str) {
        if(str == null){
            return "";
        }
        return str.replace("\\", "\\\\")
                 .replace("\"", "\\\"")
                 .replace("\n", "\\n")
                 .replace("\r", "\\r")
                 .replace("\t", "\\t");
    }

    // 反转义JSON字符串
    private String unescapeJson(String str) {
        return str.replace("\\\"", "\"")
                 .replace("\\n", "\n")
                 .replace("\\r", "\r")
                 .replace("\\t", "\t")
                 .replace("\\\\", "\\");
    }
}

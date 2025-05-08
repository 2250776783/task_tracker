package model;


public class Task {
    private int id;
    private String description;
    private StatusType status;
    private String createdAt;
    private String updatedAt;

    public Task() {
    }

    public Task(int id, String description, StatusType status, String createdAt, String updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;   
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "model.Task{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    // 从字符串转换为Task对象
    public static Task fromString(String str) {
        Task task = new Task();
        // Remove the "model.Task{" prefix and "}" suffix
        str = str.substring(str.indexOf("{") + 1, str.lastIndexOf("}"));
        
        // Split the string by commas and process each field
        String[] parts = str.split(",");
        for (String part : parts) {
            String[] keyValue = part.trim().split("=");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            
            switch (key) {
                case "id" -> task.setId(Integer.parseInt(value.replace("'", "")));
                case "description" -> task.setDescription(value.replace("'", ""));
                case "status" -> task.setStatus(StatusType.valueOf(value));
                case "createdAt" -> task.setCreatedAt(value);
                case "updatedAt" -> task.setUpdatedAt(value);
            }
        }
        return task;
    }

}

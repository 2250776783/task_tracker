# Task Tracker

一个简单的命令行任务管理工具，帮助你追踪和管理日常任务。

## 功能特性

- 添加新任务
- 更新任务描述
- 删除任务
- 查看所有任务
- 按状态筛选任务
- 更新任务状态（待办/进行中/已完成）
- 任务数据持久化存储

## 系统要求

- Java 8 或更高版本
- Git（用于版本控制）

## 安装说明

1. 克隆仓库：
```bash
git clone https://github.com/2250776783/Task-Tracker.git
cd Task-Tracker
```

2. 编译项目：
```bash
javac -d . *.java service/*.java model/*.java start/*.java
```

## 使用方法

### 添加任务
```bash
java Task_CLI add "任务描述"
```

### 更新任务
```bash
java Task_CLI update <任务ID> "新的任务描述"
```

### 删除任务
```bash
java Task_CLI delete <任务ID>
```

### 查看所有任务
```bash
java Task_CLI list
```

### 按状态查看任务
```bash
java Task_CLI list <状态>
```
状态可以是：TODO, IN_PROGRESS, DONE

### 更新任务状态
```bash
java Task_CLI markInProgress <任务ID>  # 标记为进行中
java Task_CLI markDone <任务ID>        # 标记为已完成
```

## 项目结构

```
src/
├── model/           # 数据模型
│   ├── Task.java    # 任务类
│   └── StatusType.java  # 任务状态枚举
├── service/         # 业务逻辑
│   ├── TaskManager.java     # 任务管理器
│   └── TaskFileStroage.java # 文件存储服务
├── start/           # 程序入口
│   └── App.java     # 主应用类
└── Task_CLI.java    # 命令行入口
```

## 数据存储

任务数据保存在 `tasks.json` 文件中，格式如下：
```json
{
    "id": 1,
    "description": "任务描述",
    "status": "TODO",
    "createdAt": "创建时间",
    "updatedAt": "更新时间"
}
```

## 贡献

欢迎提交 Issue 和 Pull Request！

## 许可证

MIT License 
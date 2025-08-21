package com.camunda.camunda8poc.service;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskList;
import io.camunda.tasklist.dto.TaskSearch;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTaskHandler implements CommandLineRunner {

    private final CamundaTaskListClient taskListClient;

    public UserTaskHandler(CamundaTaskListClient taskListClient) {
        this.taskListClient = taskListClient;
    }

    @Override
    public void run(String... args) throws Exception {
        handleApproverTask();
    }

    private void handleApproverTask() throws TaskListException {
        // POC flags
        boolean autoComplete = true;  // if false → let timer cancel
        boolean approve = true;       // if true → approved = true, else false

        TaskSearch search = new TaskSearch()
                .setState(TaskState.CREATED)
                .setTaskDefinitionId("Activity_008ehai"); // <-- BPMN task id

        // getTasks returns a TaskList wrapper
        TaskList taskList = taskListClient.getTasks(search);

        // Extract the list of tasks
        List<Task> tasks = taskList.getItems();

        for (Task task : tasks) {
            System.out.println("Fetched user task: " + task.getName() + " (id=" + task.getId() + ")");

            if (autoComplete) {
                Map<String, Object> variables = new HashMap<>();
                variables.put("approved", approve);

                taskListClient.completeTask(task.getId(), variables);
                System.out.println("Task " + task.getId() + " auto-completed with approved=" + approve);
            } else {
                System.out.println("Task " + task.getId() + " left open (timer will cancel it).");
            }
        }
    }
}

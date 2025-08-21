package com.camunda.camunda8poc.controller;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/process")
public class ProcessController {

    private final ZeebeClient zeebeClient;

    public ProcessController(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    @PostMapping("/start")
    public String startProcess(@RequestParam int leaves) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("leaves", leaves);

        ProcessInstanceEvent event = zeebeClient
                .newCreateInstanceCommand()
                .bpmnProcessId("leave_request_process") // <-- must match ID in BPMN model
                .latestVersion()
                .variables(variables)
                .send()
                .join();

        return "Process instance started with key: " + event.getProcessInstanceKey();
    }
}

package com.camunda.camunda8poc.controller;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @Autowired
    private ZeebeClient zeebeClient;

    @GetMapping
    public String onBoard(){
        zeebeClient.newActivateJobsCommand().jobType("test");
        return "Success";
    }

}

package com.camunda.camunda8poc.worker;


import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.CompleteJobResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LeaveBalanceWorker {

    @JobWorker(type = "leaveBalance")
    public Map<String, Object> checkLeaveBalance(final ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        int leaves = (int) variables.get("leaves");

        System.out.println("Checking leave balance: " + leaves);
        if (leaves < 1) {
            System.out.println("Leave balance is negative, request rejected.");
        }

        return variables; // sends them back to the workflow
    }


}

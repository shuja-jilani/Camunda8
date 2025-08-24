package com.camunda.camunda8poc.utils;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ZeebeConnectionTest implements CommandLineRunner {

    private final ZeebeClient zeebeClient;

    public ZeebeConnectionTest(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Trying to connect to Zeebe Gateway: " + zeebeClient.getConfiguration().getGatewayAddress());

        zeebeClient.newTopologyRequest()
                .send()
                .join();

        System.out.println("✅ Zeebe topology request successful");
    }
}

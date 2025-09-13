package com.contract;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableDiscoveryClient
public class ContractApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ContractApplication.class, args);
    }
}
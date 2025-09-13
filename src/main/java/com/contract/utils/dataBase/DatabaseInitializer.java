package com.contract.utils.dataBase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    
    private final JdbcTemplate jdbcTemplate;
    
    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // بررسی وجود دیتابیس و ایجاد آن در صورت عدم وجود
        String checkDbSql = "SELECT 1 FROM pg_database WHERE datname = 'contract'";
        
        try {
            Integer result = jdbcTemplate.queryForObject(checkDbSql, Integer.class);
            if (result == null) {
                jdbcTemplate.execute("CREATE DATABASE contract");
                System.out.println("Database created successfully");
            }
        } catch (Exception e) {
            jdbcTemplate.execute("CREATE DATABASE contract");
            System.out.println("Database created successfully");
        }
    }
}
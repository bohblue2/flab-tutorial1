package com.example.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean

import com.example.demo.database.TodoDatabase

@Configuration
class AppConfig {
    @Bean(initMethod="init ")
    fun todoDataBase(): TodoDatabase {
        return TodoDatabase()
    }
}
package com.example.demo.repository

import com.example.demo.config.AppConfig
import com.example.demo.database.Todo
import com.example.demo.repository.TodoRepositoryImpl
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime


@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTest {
    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before() {
        todoRepositoryImpl.todoDatabase.init()
    }

    @Test
    fun saveTest() {
        val todo = Todo().apply {
            this.title = "Test Title"
            this.description = "Test Description"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        Assertions.assertEquals(1, result.index)
        Assertions.assertNotNull(result.createdAt)
        Assertions.assertNotNull(result.updatedAt)
        Assertions.assertEquals("Test Title", result.title)
        Assertions.assertEquals("Test Description", result.description)
    }

    @Test
    fun saveAllTest() {
        val todoList = mutableListOf(
            Todo().apply { 
                this.title = "Test Title1"
                this.description = "Test Description"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply { 
                this.title = "Test Title2"
                this.description = "Test Description"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "Test Title3"
                this.description = "Test Description"
                this.schedule = LocalDateTime.now()
            }
        )
        val result = todoRepositoryImpl.saveAll(todoList)

        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest() { 
        val todoList = mutableListOf(
            Todo().apply { 
                this.title = "Test Title1"
                this.description = "Test Description"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply { 
                this.title = "Test Title2"
                this.description = "Test Description"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "Test Title3"
                this.description = "Test Description"
                this.schedule = LocalDateTime.now()
            }
        )
        todoRepositoryImpl.saveAll(todoList)
        val result = todoRepositoryImpl.findOne(2)
        
        Assertions.assertNotNull(result)
        Assertions.assertEquals("Test Title2", result?.title)
    }

    @Test
    fun updateTest() {
        val todo = Todo().apply {
            this.title = "Test Title"
            this.description = "Test Description"
            this.schedule = LocalDateTime.now()
        }

        val insertedTodo = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply {
            this.index = insertedTodo.index 
            this.title = "Updated Title"
            this.description = "Updated Description"
            this.schedule = LocalDateTime.now()
        }
        
        val updatedTodo = todoRepositoryImpl.save(newTodo)

        Assertions.assertEquals(insertedTodo?.index, updatedTodo?.index)
        Assertions.assertEquals("Updated Title", updatedTodo?.title)

    }
}
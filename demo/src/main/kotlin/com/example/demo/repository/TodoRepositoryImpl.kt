package com.example.demo.repository

import org.springframework.stereotype.Service
import com.example.demo.database.TodoDatabase
import com.example.demo.database.Todo
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl(
    val todoDatabase: TodoDatabase
): TodoRepository {
    override fun save(todo: Todo): Todo {

        // 1. index ? 
        return todo.index?.let { index ->
            // index exists update
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }
            
        }?: kotlin.run {
            return todo.apply {
                this.index = ++todoDatabase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run {
                todoDatabase.todoList.add(todo)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean { 
        return try {
            todoList.forEach {
                save(it)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun delete(index: Int): Boolean { 
        // val todo = findOne(index)
        // return todo?.let {
        //     todoDatabase.todoList.remove(it)
        //     true
        // }?: kotlin.run {
        //     false
        // }

        return findOne(index)?.let {
            todoDatabase.todoList.remove(it)
            true
        }?: kotlin.run {
            false
        }
    }

    override fun findOne(index: Int): Todo? { 
        return todoDatabase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> { 
        return todoDatabase.todoList
    }

}
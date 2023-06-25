package com.example.demo.database

data class TodoDatabase(
    var index: Int=0,
    var todoLists: MutableList<Todo> = mutableListOf()
) {
    fun init() {
        this.todoLists = mutableListOf()
        println("[DEBUG] TodoDatabase Initialized.")
    }
}
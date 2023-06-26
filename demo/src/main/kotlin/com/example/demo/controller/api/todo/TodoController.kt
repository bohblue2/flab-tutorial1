package com.example.demo.controller.api.todo

@RestController
@RequestMapping("/api/todo")
class TodoApiController{
    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?){

    }

    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto) {

    }

    @PutMapping(path = [""])
    fun update(@Valid @RequestBody todoDto: TodoDto) {
        
    }

    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name="index") _index: nt) {
        
    }

}
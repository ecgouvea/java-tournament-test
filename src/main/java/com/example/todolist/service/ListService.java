package com.example.todolist.service;

import com.example.todolist.model.ToDoEntry;
import com.example.todolist.model.ToDoList;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

public interface ListService {

	public Collection<ToDoList> getLists();

	/**
	 * Returns 201 and new entity if operation successful or 400 if invalid data supplied.
	 */
	public ToDoList createList(@RequestBody @Valid ToDoList list);

	/**
	 * Returns 200 if successful, 404 if no such list id is found
	 */
	public void deleteList(@PathVariable Long listId);

}

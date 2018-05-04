package com.example.todolist.service;

import com.example.todolist.model.ToDoEntry;
import com.example.todolist.model.ToDoList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;

public interface EntryService {

	/**
	 * Lists all entries in the specified list, 404 if list not found
	 */
	public Collection<ToDoEntry> getListEntries(@PathVariable Long listId);

	/**
	 * Returns 201 and new entity if operation successful or 400 if invalid data supplied.
	 * Note that creating to do entries with description longer than 16k chars is
	 * not allowed!
	 */
	public void createEntry(@PathVariable Long listId, @RequestBody @Valid ToDoEntry entry);

	/**
	 * Deletes given entry if list and entry is valid. Return 404 if ether list or entry id is incorrect.
	 * Return 400 if specified entry ID does not belong to the list.
	 */
	public void deleteEntry(@PathVariable Long listId, @PathVariable Long entryId);
}

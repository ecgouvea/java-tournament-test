package com.example.todolist.controller;

import com.example.todolist.exceptions.NotFoundException;
import com.example.todolist.model.ToDoEntry;
import com.example.todolist.model.ToDoList;
import com.example.todolist.repository.EntryRepository;
import com.example.todolist.repository.ListRepository;
import java.util.Collection;
import javax.validation.Valid;

import com.example.todolist.service.EntryService;
import com.example.todolist.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ToDoListApiController {

    @Autowired
    private final ListService listService = null;

    @Autowired
    private final EntryService entryService = null;

    /**
     * Returns available lists with code 200
     */
    @GetMapping
    public Collection<ToDoList> getLists() {
        return listService.getLists();
    }

    /**
     * Lists all entries in the specified list, 404 if list not found
     */
    @GetMapping("/{listId}")
    public Collection<ToDoEntry> getListEntries(@PathVariable Long listId) {
        return entryService.getListEntries(listId);
    }

    /**
     * Returns 201 and new entity if operation successful or 400 if invalid data supplied.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoList createList(@RequestBody @Valid ToDoList list) {
        return listService.createList(list);
    }

    /**
     * Returns 201 and new entity if operation successful or 400 if invalid data supplied.
     * Note that creating to do entries with description longer than 16k chars is
     * not allowed!
     */
    @PostMapping("/{listId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEntry(@PathVariable Long listId, @RequestBody @Valid ToDoEntry entry) {
        entryService.createEntry(listId, entry);
    }

    /**
     * Returns 200 if successful, 404 if no such list id is found
     */
    @DeleteMapping("/{listid}")
    public void deleteList(@PathVariable Long listId) {
        listService.deleteList(listId);
    }

    /**
     * Deletes given entry if list and entry is valid. Return 404 if ether list or entry id is incorrect.
     * Return 400 if specified entry ID does not belong to the list.
     */
    @DeleteMapping("/{entryId}/{listId}")
    public void deleteEntry(@PathVariable Long listId, @PathVariable Long entryId) {
        entryService.deleteEntry(listId, entryId);
    }

}

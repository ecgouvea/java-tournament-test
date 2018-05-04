package com.example.todolist.service;

import com.example.todolist.model.ToDoEntry;
import com.example.todolist.model.ToDoList;
import com.example.todolist.repository.ListRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ListServiceImpl implements ListService {

	private final static Logger log = Logger.getLogger(ListServiceImpl.class);

	@Autowired
	private ListRepository listRepository;

	@Override
	public Collection<ToDoList> getLists() {
		return listRepository.findAll();
	}

	@Override
	public ToDoList createList(ToDoList list) {
		return listRepository.save(list);
	}

	@Override
	public void deleteList(Long listId) {
		listRepository.delete(listId);
	}
}

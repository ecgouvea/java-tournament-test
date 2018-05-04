package com.example.todolist.service;

import com.example.todolist.exceptions.NotFoundException;
import com.example.todolist.model.ToDoEntry;
import com.example.todolist.model.ToDoList;
import com.example.todolist.repository.EntryRepository;
import com.example.todolist.repository.ListRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EntryServiceImpl implements EntryService {

	private final static Logger log = Logger.getLogger(EntryServiceImpl.class);

	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private ListRepository listRepository;

	@Override
	public Collection<ToDoEntry> getListEntries(Long listId) {
		return entryRepository.findAllByListId(listId);
	}

	@Override
	public void createEntry(Long listId, ToDoEntry entry) {
		ToDoList list = ensureExists(listRepository.findOne(listId));
		entry.setList(list);
		entryRepository.save(entry);
	}

	@Override
	public void deleteEntry(Long listId, Long entryId) {
		ToDoList list = ensureExists(listRepository.findOne(listId));
		ToDoEntry entry = entryRepository.findOne(entryId);
		if (entry.getList() != list) {
			throw new IllegalArgumentException();
		}
		entryRepository.delete(entry);
	}

	private static <T> T ensureExists(T object) {
		if (object == null) {
			throw new NotFoundException();
		}
		return object;
	}
}

package nl.avthart.todo.app.query.task;

import nl.avthart.todo.app.domain.task.events.TaskCompletedEvent;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TaskStarredEvent;
import nl.avthart.todo.app.domain.task.events.TaskTitleModifiedEvent;
import nl.avthart.todo.app.domain.task.events.TaskUnstarredEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author albert
 */
@Component
public class TaskEntryUpdatingEventHandler {

	private final TaskEntryRepository taskEntryRepository;
	
	@Autowired
	public TaskEntryUpdatingEventHandler(TaskEntryRepository taskEntryRepository) {
		this.taskEntryRepository = taskEntryRepository;
	}
	
	@EventHandler
	void on(TaskCreatedEvent event) {
		TaskEntry task = new TaskEntry(event.getId(), event.getUsername(), event.getTitle(), false, false);
		taskEntryRepository.save(task);
	}

	@EventHandler
	void on(TaskCompletedEvent event) {
		Optional<TaskEntry> taskOption = taskEntryRepository.findById(event.getId());
		if(taskOption.isPresent()){
			final TaskEntry taskEntry = taskOption.get().withCompleted(true);
			taskEntryRepository.save(taskEntry);
		}else{
			throw new TaskNotFoundException(String.format("could not find entry with id = %s", event.getId().toString()));
		}

	}

	@EventHandler
	void on(TaskTitleModifiedEvent event) {
		Optional<TaskEntry> taskOption = taskEntryRepository.findById(event.getId());
		if(taskOption.isPresent()){
			final TaskEntry taskEntry = taskOption.get().withTitle(event.getTitle());
			taskEntryRepository.save(taskEntry);
		}else{
			throw new TaskNotFoundException(String.format("could not find entry with id = %s", event.getId()));
		}

	}
	
	@EventHandler
	void on (TaskStarredEvent event) {
		Optional<TaskEntry> taskOption = taskEntryRepository.findById(event.getId());
		if(taskOption.isPresent()){
			final TaskEntry taskEntry = taskOption.get().withStarred(true);
			taskEntryRepository.save(taskEntry);
		}else{
			throw new TaskNotFoundException(String.format("could not find entry with id = %s", event.getId()));
		}

	}
	
	@EventHandler
	void on (TaskUnstarredEvent event) {
		Optional<TaskEntry> task = taskEntryRepository.findById(event.getId());
		if(task.isPresent()){
			final TaskEntry taskEntry = task.get();
			taskEntry.setStarred(false);
			taskEntryRepository.save(taskEntry);
		}else{
			throw new TaskNotFoundException(String.format("could not find entry with id = %s", event.getId()));
		}
	}


}

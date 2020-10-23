package nl.avthart.todo.app.domain.task.events;

import lombok.Value;

import java.util.UUID;

/**
 * @author albert
 */
@Value
public class TaskCreatedEvent implements TaskEvent {

	private final UUID id;
	
	private final String username;
	
	private final String title;	
}

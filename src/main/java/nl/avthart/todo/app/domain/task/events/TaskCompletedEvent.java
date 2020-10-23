package nl.avthart.todo.app.domain.task.events;

import lombok.Value;

import java.util.UUID;

/**
 * @author albert
 */
@Value
public class TaskCompletedEvent implements TaskEvent {

	private final UUID id;
}

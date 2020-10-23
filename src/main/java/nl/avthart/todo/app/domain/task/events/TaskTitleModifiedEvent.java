package nl.avthart.todo.app.domain.task.events;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;

/**
 * @author albert
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TaskTitleModifiedEvent implements TaskEvent {

	UUID id;
	
	String title;
}

package nl.avthart.todo.app.domain.task.commands;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author albert
 */
@Value
public class CreateTaskCommand {

	@NotNull
	private final UUID id;
	
	@NotNull
	private final String username;
	
	@NotNull
	private final String title;
}

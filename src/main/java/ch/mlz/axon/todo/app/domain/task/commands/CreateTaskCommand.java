package ch.mlz.axon.todo.app.domain.task.commands;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author albert
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateTaskCommand {

	@NotNull UUID id;
	
	@NotNull String username;
	
	@NotNull String title;
}

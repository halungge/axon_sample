package nl.avthart.todo.app.domain.task.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * @author albert
 */
@Value
public class ModifyTaskTitleCommand {

	@TargetAggregateIdentifier
	private final UUID id;

	@NotNull
	private final String title;
}


package nl.avthart.todo.app.domain.task.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

/**
 * @author albert
 */
@Value
public class UnstarTaskCommand {

	@TargetAggregateIdentifier
	UUID id;
}
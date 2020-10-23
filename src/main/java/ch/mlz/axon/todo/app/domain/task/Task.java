package ch.mlz.axon.todo.app.domain.task;

import ch.mlz.axon.todo.app.domain.task.commands.CompleteTaskCommand;
import ch.mlz.axon.todo.app.domain.task.commands.CreateTaskCommand;
import ch.mlz.axon.todo.app.domain.task.commands.ModifyTaskTitleCommand;
import ch.mlz.axon.todo.app.domain.task.commands.StarTaskCommand;
import ch.mlz.axon.todo.app.domain.task.commands.UnstarTaskCommand;
import ch.mlz.axon.todo.app.domain.task.events.TaskCompletedEvent;
import ch.mlz.axon.todo.app.domain.task.events.TaskCreatedEvent;
import ch.mlz.axon.todo.app.domain.task.events.TaskStarredEvent;
import ch.mlz.axon.todo.app.domain.task.events.TaskTitleModifiedEvent;
import ch.mlz.axon.todo.app.domain.task.events.TaskUnstarredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


/**
 * Task
 * @author albert
 */
@Aggregate
public class Task {

	/**
	 * The constant serialVersionUID 
	 */
	private static final long serialVersionUID = -5977984483620451665L;
	
	@AggregateIdentifier
	private UUID id;
	
	@NotNull
	private boolean completed;
	
	/**
	 * Creates a new Task.
	 * 
	 * @param command create Task
	 */
	@CommandHandler
	public Task(CreateTaskCommand command) {
		apply(new TaskCreatedEvent(command.getId(), command.getUsername(), command.getTitle()));
	}
	
	Task() {
	}

	/**
	 * Completes a Task.
	 * 
	 * @param command complete Task
	 */
	@CommandHandler
	void on(CompleteTaskCommand command) {
		apply(new TaskCompletedEvent(command.getId()));
	}
	
	/**
	 * Stars a Task.
	 * 
	 * @param command star Task
	 */
	@CommandHandler
	void on(StarTaskCommand command) {
		apply(new TaskStarredEvent(command.getId()));
	}
	
	/**
	 * Unstars a Task.
	 * 
	 * @param command unstar Task
	 */
	@CommandHandler
	void on(UnstarTaskCommand command) {
		apply(new TaskUnstarredEvent(command.getId()));
	}
	
	/**
	 * Modifies a Task title.
	 * 
	 * @param command modify Task title
	 */
	@CommandHandler
	void on(ModifyTaskTitleCommand command) {
		assertNotCompleted();
		apply(new TaskTitleModifiedEvent(command.getId(), command.getTitle()));
	}

	@EventSourcingHandler
	void on(TaskCreatedEvent event) {
		this.id = event.getId();
	}

	@EventSourcingHandler
	void on(TaskCompletedEvent event) {
		this.completed = true;
	}
	
	private void assertNotCompleted() {
		if (completed) {
			throw new TaskAlreadyCompletedException("Task [ identifier = " + id + " ] is completed.");
		}		
	}
}

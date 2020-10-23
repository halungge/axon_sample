package nl.avthart.todo.app.rest.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.avthart.todo.app.domain.task.commands.CompleteTaskCommand;
import nl.avthart.todo.app.domain.task.commands.CreateTaskCommand;
import nl.avthart.todo.app.domain.task.commands.ModifyTaskTitleCommand;
import nl.avthart.todo.app.domain.task.commands.StarTaskCommand;
import nl.avthart.todo.app.query.task.TaskEntry;
import nl.avthart.todo.app.query.task.TaskEntryRepository;
import nl.avthart.todo.app.rest.task.requests.CreateTaskRequest;
import nl.avthart.todo.app.rest.task.requests.ModifyTitleRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author albert
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskController {


	private final TaskEntryRepository taskEntryRepository;



	private final CommandGateway commandGateway;

	@RequestMapping(value = "/api/tasks", method = RequestMethod.GET)
	public @ResponseBody
	Page<TaskEntry> findAll( @RequestParam(required = false, defaultValue = "false") boolean completed, Pageable pageable) {
		return taskEntryRepository.findByUsernameAndCompleted("me", completed, pageable);
	}

	@RequestMapping(value = "/api/tasks", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createTask(@RequestBody @Valid CreateTaskRequest request) {
		log.info("creating new task {} by {}", request.getTitle());
		commandGateway.send(new CreateTaskCommand(UUID.randomUUID(), "me", request.getTitle()));
	}

	@RequestMapping(value = "/api/tasks/{identifier}/title", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void changeTitle(@PathVariable UUID identifier, @RequestBody @Valid ModifyTitleRequest request) {
		commandGateway.send(new ModifyTaskTitleCommand(identifier, request.getTitle()));
	}

	@RequestMapping(value = "/api/tasks/{identifier}/complete", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void completeTask(@PathVariable UUID identifier) {
		commandGateway.send(new CompleteTaskCommand(identifier));
	}

	@RequestMapping(value = "/api/tasks/{identifier}/star", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void starTask(@PathVariable UUID identifier) {
		commandGateway.send(new StarTaskCommand(identifier));
	}

	@RequestMapping(value = "/api/tasks/{identifier}/unstar", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void unstarTask(@PathVariable String identifier) {
		throw new RuntimeException("Could not unstar task...");
		//commandGateway.sendAndWait(new UnstarTaskCommand(identifier));
	}

	@ExceptionHandler

	public void handleException(Throwable exception) {
		log.error("EXCEPTION: {}",  exception.getMessage());
	}

}

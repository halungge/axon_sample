package nl.avthart.todo.app.query.task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String msg) {
    super(msg);
    }
}

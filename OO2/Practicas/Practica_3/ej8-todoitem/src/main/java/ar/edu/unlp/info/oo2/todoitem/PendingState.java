package ar.edu.unlp.info.oo2.todoitem;
import java.time.Duration;
import java.time.Instant;

import java.time.Duration;

public class PendingState implements ToDoItemState {

	@Override
	public void start(ToDoItem item) {
		item.setState(new InProgressState());
		item.setStartTime(Instant.now());
	}

	@Override
	public void togglePause(ToDoItem item) {
		throw new RuntimeException("El objeto ToDoItem no se encuentra en pause o in-progress");
	}

	@Override
	public void finish(ToDoItem item) {
		
	}

	@Override
	public Duration workedTime(ToDoItem item) {
		throw new RuntimeException("El objeto No se a Iniciado");
	}

	@Override
	public void addComment(ToDoItem item, String comment) {
		item.getComments().add(comment);
		
	}

}

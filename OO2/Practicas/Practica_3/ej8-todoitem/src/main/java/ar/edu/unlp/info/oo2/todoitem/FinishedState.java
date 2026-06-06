package ar.edu.unlp.info.oo2.todoitem;

import java.time.Duration;

public class FinishedState implements ToDoItemState{

	@Override
	public void start(ToDoItem item) {		
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
		return 		Duration.between(item.getStartTime(),item.getEndTime());
	}

	@Override
	public void addComment(ToDoItem item, String comment) {
		// TODO Auto-generated method stub
		
	}

}

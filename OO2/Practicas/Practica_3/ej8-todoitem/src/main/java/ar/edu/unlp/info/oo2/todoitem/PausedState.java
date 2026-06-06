package ar.edu.unlp.info.oo2.todoitem;

import java.time.Duration;
import java.time.Instant;

public class PausedState implements ToDoItemState{

	@Override
	public void start(ToDoItem item) {
		
	}

	@Override
	public void togglePause(ToDoItem item) {
		item.setState(new InProgressState());
	}

	@Override
	public void finish(ToDoItem item) {
		item.setState(new FinishedState());
		item.setEndTime(Instant.now());
	}

	@Override
	public Duration workedTime(ToDoItem item) {
		return Duration.between(item.getStartTime(), Instant.now());
	}

	@Override
	public void addComment(ToDoItem item, String comment) {
		item.getComments().add(comment);		
	}

}

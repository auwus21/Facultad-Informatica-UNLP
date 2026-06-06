package ar.edu.unlp.info.oo2.todoitem;

import java.time.Duration;


public interface ToDoItemState {
	void  start(ToDoItem item);
	void togglePause(ToDoItem item);
	void finish(ToDoItem item);
	Duration workedTime(ToDoItem item);
	void addComment(ToDoItem item,String comment);
	
}

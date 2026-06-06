package ar.edu.unlp.info.oo2.todoitem;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class ToDoItem {
    private String name;
    private List<String> comments;
    private ToDoItemState state;
    private Instant startTime;
    private Instant endTime;
    
    
    
    protected void setState(ToDoItemState state) {
    	this.state = state;
    }
    
    protected void setStartTime(Instant startTime) {
    	this.startTime = startTime;
    }
    
    protected void setEndTime(Instant endTime) {
    	this.endTime = endTime;
    }

    /**
     * Instancia un ToDoItem nuevo en estado pending con <name> como nombre.
     */
    public ToDoItem(String name) {
        this.name = name;
        this.comments = new ArrayList<>();
    	this.state = new PendingState();
    }

    /**
     * Pasa el ToDoItem a in-progress, siempre y cuando su estado actual sea
     * pending. Si se encuentra en otro estado, no hace nada.
     */
    public void start() {
    	this.state.start(this);
    }

    /**
     * Pasa el ToDoItem a paused si su estado es in-progress, o a in-progress si
     * su estado es paused. Caso contrario (pending o finished) genera un error
     * informando la causa específica del mismo.
     */
    public void togglePause() {
    	this.state.togglePause(this);
    }

    /**
     * Pasa el ToDoItem a finished, siempre y cuando su estado actual sea
     * in-progress o paused. Si se encuentra en otro estado, no hace nada.
     */
    public void finish() {
    	this.state.finish(this);
    }

    /**
     * Retorna el tiempo que transcurrió desde que se inició el ToDoItem (start)
     * hasta que se finalizó. En caso de que no esté finalizado, el tiempo que
     * haya transcurrido hasta el momento actual. Si el ToDoItem no se inició,
     * genera un error informando la causa específica del mismo.
     */
    public Instant getStartTime() {
    	return this.startTime;
    }
    
    public Instant getEndTime() {
    	return this.endTime;
    }
    
    public Duration workedTime() {
    	return this.state.workedTime(this);
    }

    /**
     * Agrega un comentario al ToDoItem siempre y cuando no haya finalizado.
     * Caso contrario no hace nada.
     */
    public void addComment(String comment) {
    	this.state.addComment(this, comment);
    }

    // Getters y setters auxiliares para los estados si los necesitás
    public String getName() {
        return name;
    }

    public List<String> getComments() {
        return comments;
    }
}

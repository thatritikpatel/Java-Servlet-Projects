package models;

public class TodoTeam {
	private Integer todoTeamId;
	private ToDoList toDoList;
	private User user;
	private Status status;
	
	public Integer getTodoTeamId() {
		return todoTeamId;
	}
	
	public void setTodoTeamId(Integer todoTeamId) {
		this.todoTeamId = todoTeamId;
	}
	
	public ToDoList getToDoList() {
		return toDoList;
	}
	
	public void setToDoList(ToDoList toDoList) {
		this.toDoList = toDoList;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}	
}

package models;

public class ToDoType{
	private Integer toDoTypeId;
	private String toDoType;

	public ToDoType(){
	
	}

	public ToDoType(Integer toDoTypeId){
		this.toDoTypeId = toDoTypeId;
	}

	public void setToDoType(String toDoType){
		this.toDoType = toDoType;
	}

	public String getToDoType(){
		return toDoType;
	}

	public void setToDoTypeId(Integer toDoTypeId){
		this.toDoTypeId = toDoTypeId;
	}

	public Integer getToDoTypeId(){
		return toDoTypeId;
	}
}
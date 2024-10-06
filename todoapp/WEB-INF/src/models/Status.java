package models;


public class Status{
	private Integer statusId;
	private String status;

	public static final Status PENDING = new Status(9,"Pending"); 

	public Status(){
	
	}

	public Status(Integer statusId,String status){
		this.statusId = statusId;
		this.status = status;
	}

	public Status(Integer statusId){
		this.statusId = statusId;
	}

	public static final int ACTIVE = 1; 

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setStatusId(Integer statusId){
		this.statusId = statusId;
	}

	public Integer getStatusId(){
		return statusId;
	}
}
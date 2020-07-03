package jwt.app.resources;

import lombok.Data;

public @Data class ApiResponse<T> {
	
	private int status;
	
	private String message;
	
	private Object result;
	
	private String token;
	
	
	
	public ApiResponse(int status, String message, Object result){
		this.status=status;
		this.message=message;
		this.result= result;
	}
	
	
	public ApiResponse(int status, String message, Object result, String token){
		this.status=status;
		this.message=message;
		this.result= result;
		this.token =token;
	}
	
	
}

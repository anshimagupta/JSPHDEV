package exception;

public class AutoOptionException extends Exception {
	private String msg;
	
	public String getMsg(){
		return msg;
	}
	
	public AutoOptionException(String message){
		this.msg = message;
	}
}

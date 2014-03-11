package exception;

public class AutoOptionException extends Exception {
	private static final long serialVersionUID = -3550434016626013075L;
	private String msg;
	
	public String getMsg(){
		return msg;
	}
	
	public AutoOptionException(String message){
		this.msg = message;
	}
}

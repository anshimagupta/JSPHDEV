package exception;

public class NoSuchOptionException extends Exception {
	private static final long serialVersionUID = -3550434016626013075L;
	private String msg;
	
	public String getMsg(){
		return msg;
	}
	
	public NoSuchOptionException(String message) {
		this.msg = message;
	}
}

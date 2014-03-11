package exception;

public class FileNameMissingException extends Exception {
	private static final long serialVersionUID = -3550434016626013075L;
	private String msg;
	
	public String getMsg(){
		return msg;
	}
	
	public FileNameMissingException(String message) {
		this.msg = message;
	}
}

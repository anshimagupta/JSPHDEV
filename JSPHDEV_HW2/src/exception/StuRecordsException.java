package exception;

public class StuRecordsException extends Exception {
	private String msg = null;

	public String getMsg() {
		return msg;
	}
	
	public StuRecordsException(String msg){
		this.msg = msg;
	}
}

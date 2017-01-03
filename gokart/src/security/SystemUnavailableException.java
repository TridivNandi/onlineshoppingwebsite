package security;

public class SystemUnavailableException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemUnavailableException() {
		super();
	}
	
	public SystemUnavailableException(String msg) {
		super(msg);
	}
}

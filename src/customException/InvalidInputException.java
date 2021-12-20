package customException;

public class InvalidInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidInputException(String Msg){
		super(Msg);
	}

}

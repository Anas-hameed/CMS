package customException;

public class UnauthorizedAcessResources  extends Exception{
	private static final long serialVersionUID = 1L;
	int Status;
	public UnauthorizedAcessResources(String Message){
		super(Message);
	}
}

package customException;

import java.io.IOException;

public class ResourceNotFound extends IOException{
	private static final long serialVersionUID = 1L;
	public ResourceNotFound(String Message) {
		super(Message);
	}
	
}

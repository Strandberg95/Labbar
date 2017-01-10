package laboration3;

public class AlreadyExistsException extends Exception {
	public AlreadyExistsException(int data){
		super("This number is already in the BinaryTree: " + data);
	}
}

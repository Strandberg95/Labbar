package laboration3punkt5;
/**
 * This class represents a node in
 * the Binary Search Tree
 * @author Christoffer strandberg
 */
public class Node {
	
	private int data;
	
	private Node leftChild;
	private Node rightChild;
	
	/**
	 * This constructor is used to
	 * set the data in the node
	 * @param data - the data in the node
	 */
	public Node(int data){
		this.data = data;
	}
	
	/**
	 * This method is used to return 
	 * the data saved in the node
	 * @return
	 */
	public int getData(){
		return data;
	}
	
	/**
	 * This method is used to set the
	 * data in the node
	 * @param data - the data to change
	 */
	public void setData(){
		this.data = data;
	}
	
	/**
	 * This method is used to return the
	 * left child of the current node
	 * @return the left child
	 */
	public Node getLeftChild(){
		return leftChild;
	}
	
	/**
	 * This method is used to set left
	 * child of the current node
	 * @param newLeftNode - the new Left node
	 */
	public void setLeftChild(Node leftChild){
		this.leftChild = leftChild;
	}
	
	/**
	 * This method is used to get the 
	 * right child of the current node
	 * @return the right child
	 */
	public Node getRightChild(){
		return rightChild;
	}
	
	/**
	 * This method is used to set the
	 * right child of the current node
	 * @param newRightNode - the new right node
	 */
	public void setRightChild(Node rightChild){
		this.rightChild = rightChild;
	}
	
	/**
	 * These methods are used to print the
	 * current tree
	 */
	public void printTree(){
		if(this.rightChild != null){
			rightChild.printTree(true,"");
		}
		printNodeValue();
		if(leftChild != null){
			leftChild.printTree(false, "");
		}
	}
	
	private void printTree(boolean isRight, String indent){
		if(rightChild != null){
			rightChild.printTree(true, indent + (isRight ? "       " : " |      "));
		}
		System.out.print(indent);
		if(isRight) {
			System.out.print("  /");
		}else{
			System.out.print("  \\");
		}
		System.out.print("---- ");
		printNodeValue();
		if(leftChild != null){
			leftChild.printTree(false, indent + (isRight ? "  |     ": "       "));
		}
	}
	
	private void printNodeValue(){
		System.out.print(data);
		System.out.print("\n");
	}

}

package laboration3;

/**
 * This class represents a node in
 * the Binary Search Tree
 * @author Christoffer strandberg
 */
public class TreeNode {
	private int data;
	private TreeNode leftNode;
	private TreeNode rightNode;
	
	/**
	 * This constructor is used to
	 * set the data in the node
	 * @param data - the data in the node
	 */
	public TreeNode(int data){
		this.data = data;
	}
	
	/**
	 * This method is used to set the
	 * data in the node
	 * @param data - the data to change
	 */
	public void setData(int data){
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
	 * This method is used to set left
	 * child of the current node
	 * @param newLeftNode - the new Left node
	 */
	public void setLeftNode(TreeNode newLeftNode){
		this.leftNode = newLeftNode;
	}

	/**
	 * This method is used to return the
	 * left child of the current node
	 * @return the left child
	 */
	public TreeNode getLeftNode(){
		return leftNode;
	}
	
	/**
	 * This method is used to set the
	 * right child of the current node
	 * @param newRightNode - the new right node
	 */
	public void setRightNode(TreeNode newRightNode){
		this.rightNode = newRightNode;
	}
	
	/**
	 * This method is used to get the 
	 * right child of the current node
	 * @return the right child
	 */
	public TreeNode getRightNode(){
		return rightNode;
	}
	
	
	
	/**
	 * These methods are used to print the
	 * current tree
	 */
	public void printTree(){
		if(this.rightNode != null){
			rightNode.printTree(true,"");
		}
		printNodeValue();
		if(leftNode != null){
			leftNode.printTree(false, "");
		}
	}
	
	private void printTree(boolean isRight, String indent){
		if(rightNode != null){
			rightNode.printTree(true, indent + (isRight ? "       " : " |      "));
		}
		System.out.print(indent);
		if(isRight) {
			System.out.print("  /");
		}else{
			System.out.print("  \\");
		}
		System.out.print("---- ");
		printNodeValue();
		if(leftNode != null){
			leftNode.printTree(false, indent + (isRight ? "  |     ": "       "));
		}
	}
	
	private void printNodeValue(){
		System.out.print(data);
		System.out.print("\n");
	}
}

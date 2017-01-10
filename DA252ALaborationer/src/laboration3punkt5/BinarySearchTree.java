package laboration3punkt5;

import laboration3.AlreadyExistsException;
import laboration3.TreeNode;

/**
 * This class represents a Binary Search Tree
 * @author Christoffer Strandberg
 */

public class BinarySearchTree {
	
	private Node root;
	private int size = 0;
	
	/**
	 * The add method is a public method used to add
	 * data in the Binary Search Tree. This method
	 * then uses the private insert method to add it
	 * @param data - the data to be added
	 */
	public void add(int data){
		if(size > 0){
			try{
				insert(root,new Node(data));
			}catch(AlreadyExistsException e){
				e.printStackTrace();
			}
		}else{
			root = new Node(data);
			size++;
			System.out.println("Root satt som: " + data);
		}
	}
	
	/**
	 * This method is a public method used to remove
	 * data from the Binary Search Tree. This then uses 
	 * the private remove method to remove it
	 * @param data - the data to remove
	 */
	public void delete(int data){
		if(remove(data)){
			System.out.println(data + " Removed");
		} else{
			System.out.println(data + "Could not be removed");
		}
	}
	
	/**
	 * This method is a private method used by the Add method
	 * to add the to the Binary Search Tree. This method is recursive
	 * @param parentNode - the starter node
	 * @param newNode - the node to insert with parentNode as parent
	 * @throws AlreadyExistsException - is thrown if the data is already in the 
	 * Binary Search Tree
	 */
	private void insert(Node parentNode, Node newNode)throws AlreadyExistsException{
		
		if(newNode.getData() == parentNode.getData()){
			throw new AlreadyExistsException(newNode.getData());
		}
		if(newNode.getData() > parentNode.getData()){
			try{
				insert(parentNode.getRightChild(),newNode);
			}catch(NullPointerException e){
				parentNode.setRightChild(newNode);
			}
		}
		if(newNode.getData() < parentNode.getData()){
			try{
				insert(parentNode.getLeftChild(),newNode);
			}catch(NullPointerException e){
				parentNode.setLeftChild(newNode);
			}
		}
	}
	
	/**
	 * This method is used to find a specific node
	 * in the Binary Search Tree. This method is recursive
	 * @param parentNode - the node used to find the specific data
	 * @param dataToFind - the data to be found
	 * @return returns the node when found
	 */
	public TreeNode find(TreeNode parentNode, int dataToFind){
		if(parentNode.getData() == dataToFind){
			return parentNode;
		}
		if(dataToFind > parentNode.getData()){
			try{
				return find(parentNode.getRightNode(),dataToFind);
			}catch(NullPointerException e){
				return null;
			}
		}
		if(dataToFind < parentNode.getData()){
			try{
				return find(parentNode.getLeftNode(),dataToFind);
			}catch(NullPointerException e){
				return null;
			}
		}
		return null;
	}
	
	/**
	 * This method is used to internally 
	 * remove a node from the BST. It traverses
	 * the tree and then chooses to swap place
	 * or simply just remove the node, depending 
	 * on the three main cases the removal of a node
	 * might have
	 * @param data - the data of the node to be
	 * removed
	 * @return returns if the node was removed or not
	 */
	private boolean remove(int data){
		Node focusNode = root;
		Node parent = root;
		
		boolean isItALeftChild = true;
		
		while(focusNode.getData() != data){
			parent = focusNode;
			if(data < focusNode.getData()){
				isItALeftChild = true;
				focusNode = focusNode.getLeftChild();
			}else {
				isItALeftChild = false;
				focusNode = focusNode.getRightChild();
			}
			if(focusNode == null)
				return false;
		}
		if(focusNode.getLeftChild() == null && focusNode.getRightChild() == null){
			if(focusNode == root){
				root = null;
			} else if(isItALeftChild){
				parent.setLeftChild(null);
			} else{
				parent.setRightChild(null);
			}
		}
		else if(focusNode.getRightChild() == null){
			if(focusNode == root){
				root = focusNode.getLeftChild();
			}else if(isItALeftChild){
				parent.setLeftChild(focusNode.getLeftChild());
			}else{
				parent.setRightChild(focusNode.getLeftChild());
			}
		}
		else if(focusNode.getLeftChild() == null){
			if(focusNode == root){
				root = focusNode.getRightChild();
			}
			else if(isItALeftChild){
				parent.setLeftChild(focusNode.getRightChild());
			}
			else{
				parent.setRightChild(focusNode.getLeftChild());
			}
		}
		
		else{
			Node replacement = getReplacementNode(focusNode);
			
			if(focusNode == root){
				root = replacement;
			}
			else if(isItALeftChild){
				parent.setLeftChild(replacement);
			}
			else{
				parent.setRightChild(replacement);
			}
			replacement.setLeftChild(focusNode.getLeftChild());
		}
		return true;
	}
	
	/**
	 * This method is used to find the replacement
	 * of a specific node when to be removed
	 * @param nodeToReplace - the node to be replaced
	 * @return returns the node that is to replace the 
	 * current
	 */
	private Node getReplacementNode(Node nodeToReplace){
		Node replacementParent = nodeToReplace;
		Node replacement = nodeToReplace;
		
		Node focusNode = nodeToReplace.getRightChild();
		
		while(focusNode != null){
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.getLeftChild();
		}
		if(replacement != nodeToReplace.getRightChild()){
			replacementParent.setLeftChild(replacement.getRightChild());
			replacement.setRightChild(nodeToReplace.getRightChild());
		}
		return replacement;
	}
	
	/** 
	 * This method is just used to simply traverse 
	 * the tree using in order traversal
	 * @param focusNode - the starting node of the traversal
	 */
	private void inOrderTraverseTree(Node focusNode){
		if(focusNode != null){
			inOrderTraverseTree(focusNode.getLeftChild());
			System.out.println(focusNode);
			inOrderTraverseTree(focusNode.getRightChild());
			System.out.println(focusNode);
			
		}
	}

	/**
	 * This method is used to print the current
	 * tree to the console
	 */
	public void printTree(){
		root.printTree();
	}	
}

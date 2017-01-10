package laboration3;

import java.util.Optional;

/**
 * This class represents a Binary Search Tree
 * @author Christoffer Strandberg
 */
public class BinaryTree {
	
	private TreeNode root;
	private int size;
	
	/**
	 * The add method is a public method used to add
	 * data in the Binary Search Tree. This method
	 * then uses the private insert method to add it
	 * @param data - the data to be added
	 */
	public void add(int data){
		if(size > 0){
			try{
				insert(root,new TreeNode(data));
			}catch(AlreadyExistsException e){
				e.printStackTrace();
			}
		}else{
			root = new TreeNode(data);
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
	private void insert(TreeNode parentNode, TreeNode newNode)throws AlreadyExistsException{
		
		if(newNode.getData() == parentNode.getData()){
			throw new AlreadyExistsException(newNode.getData());
		}
		if(newNode.getData() > parentNode.getData()){
			try{
				insert(parentNode.getRightNode(),newNode);
			}catch(NullPointerException e){
				parentNode.setRightNode(newNode);
			}
		}
		if(newNode.getData() < parentNode.getData()){
			try{
				insert(parentNode.getLeftNode(),newNode);
			}catch(NullPointerException e){
				parentNode.setLeftNode(newNode);
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
	 * This method is used to find the successor of a 
	 * specific node
	 * @param node - the node we want to find a successor for
	 * @return returns the successor
	 */
	public TreeNode findSuccessor(TreeNode node){
		
		if(node.getRightNode() != null && node.getLeftNode() != null){
			return findMinimumRight(node.getRightNode(),node.getRightNode());
		}
		if(node.getRightNode() == null && node.getLeftNode() != null){
			return node.getLeftNode();
		}
		if(node.getLeftNode() == null && node.getRightNode() != null){
			return node.getRightNode();
		}
		return null;
	}
	
	/**
	 * This method is used to find the leftmost smallest
	 * node from a starter point. This method is recursive
	 * @param rightNode - the current node
	 * @param minimum - the minimum node found
	 * @return returns minimum node
	 */
	private TreeNode findMinimumLeft(TreeNode rightNode, TreeNode minimum){
		TreeNode thisMinimum = minimum;
		if(rightNode.getData() < minimum.getData()){
			thisMinimum = rightNode;
		}
		try{	
			rightNode.getLeftNode().getData();
			return findMinimumLeft(rightNode.getLeftNode(),thisMinimum);
		}catch(NullPointerException e){
			return thisMinimum;
		}
	}
	
	/**
	 * This method traverses the tree from a point down right
	 * and then to the left to find the smallest node in the rightmost subtree.
	 * This method is recursive
	 * @param rightNode - the current node
	 * @param minimum - the smallest node found
	 * @return returns the smallest node found
	 */
	public TreeNode findMinimumRight(TreeNode rightNode,TreeNode minimum){
		TreeNode thisMinimum = minimum;
		if(rightNode.getData() < minimum.getData()){
			thisMinimum = rightNode;
		}
			
		try{
			return findMinimumLeft(rightNode.getLeftNode(),minimum);
		}catch(NullPointerException e){
			try{
				return findMinimumRight(rightNode.getRightNode(),minimum);
			}catch(NullPointerException e1){
				return thisMinimum;
			}
		}
	}
	
	/**
	 * This method is used to find the parent of a node.
	 * This method is recursive.
	 * @param parent - the current node
	 * @param node - the node whos parent we want to find
	 * @return returns the parent os a node
	 */
	private TreeNode findParent(TreeNode parent, TreeNode node){

		if(parent.getLeftNode() != null){
			try{
				if(parent.getLeftNode().getData() == node.getData()){
					return parent;
				}
				if(node.getData() < parent.getData()){
					return findParent(parent.getLeftNode(),node);
				}
			}catch(NullPointerException e){
				return null;
			}
		}
		if(parent.getRightNode() != null){
			try{
				if(parent.getRightNode().getData() == node.getData()){
					return parent;
				}
				if(node.getData() > parent.getData()){
					return findParent(parent.getRightNode(),node);
				}
			}catch(NullPointerException e){
				return null;
			}
		}
		return null;
	}
	
	/**
	 * This method is used internally to remove a
	 * specific node
	 * @param data - the data to be removed
	 * @return returns if the node has been removed or not
	 */
	private boolean remove(int data){
		
			TreeNode node = find(root,data);
			TreeNode successor = findSuccessor(node);
			swap(node,successor,null);
			return true;
	}
	
	private boolean remove(TreeNode node){
		
//		TreeNode node = find(root,data);
		TreeNode successor = findSuccessor(node);
		swap(node,successor,node);
		return true;
	}
	
	/**
	 * This method is used to swap the values of two nodes
	 * @param node - the node to be removed
	 * @param successor - the node to take its place
	 */
	private void swap(TreeNode node, TreeNode successor,TreeNode prevNode){
		int nodeData = node.getData();
//		int successorData = successor.getData();
//		TreeNode successorParent = findParent(root,successor);
//		int successorParentData = successorParent.getData();
		TreeNode nodeParent = findParent(root,node);
		System.out.println("prevNode: " + prevNode.getData());
		System.out.println("parent:" + nodeParent.getData());
//		System.out.println(node.ge);
		
		System.out.println("node: " + node.getData() + " ");
		System.out.println("Successor: " + successor.getData() + " ");
		
//		printTree();
		
		
		
		if(node.getLeftNode() == null && node.getRightNode() == null){
			if(prevNode != null){
				if(prevNode.getLeftNode() == node){
					prevNode.setLeftNode(null);
				}
				if(prevNode.getRightNode() == node){
					prevNode.setRightNode(null);
				}
			}
			if(nodeParent.getLeftNode() == node){
				nodeParent.setLeftNode(null);
			}
			if(nodeParent.getRightNode() == node){
				nodeParent.setRightNode(null);
			}
			
		}else{
			try{
				node.getData();
				node.setData(successor.getData());
				remove(successor);
				
				printTree();
			}catch(NullPointerException e){
//				TreeNode parent = findParent(root,node);
				if(nodeParent.getLeftNode().getData() == node.getData()){
					nodeParent.setLeftNode(null);
				}
				if(nodeParent.getRightNode().getData() == node.getData()){
					nodeParent.setRightNode(null);
				}
			}

		}

		
		
//		System.out.println(findSuccessor(node).getData());
//		
//		if(successor.getData() == node.getData()){
//			if(parent.getLeftNode().getData() == node.getData()){
//				parent.setLeftNode(null);
//			}
//			if(parent.getRightNode().getData() == node.getData()){
//				parent.setRightNode(null);
//			}
//		}
//		else{
//			remove(node.getData());
//		}
//		if(node.getData() == root.getData()){
//			
//		}
//		node.setData(successor.getData());
//		if(successor.getLeftNode() != null){
//			node.setLeftNode(successor.getLeftNode());
//		}
//		if(successor.getRightNode() != null){
//			node.setRightNode(successor.getRightNode());
//		}	
//		if(parent.getLeftNode() == successor){
//			System.out.println("ay lmao");
//			parent.setLeftNode(null);
//		}
//		if(parent.getRightNode() == successor){
//			System.out.println("ay lmao");
//			parent.setRightNode(null);
//		}

	}
	
	/**
	 * This method is used to print 
	 * the whole tree
	 */
	public void printTree(){
		root.printTree();
	}

}

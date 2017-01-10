package laboration3punkt5;

import javax.swing.JOptionPane;

import laboration3.BinaryTree;

public class Tester {
	
	public static void main(String[] args){
		String startMessage = "1: Add Node" + "\n" + "2: Remove Node" + "\n";
		String addMessage = "Write the node to add";
		String removeMessage = "Write the node to remove";
		
		BinarySearchTree btree = new BinarySearchTree();
		
		btree.add(50);
		btree.add(25);
		btree.add(14);
		btree.add(75);
		btree.add(80);
		btree.add(65);
		btree.add(60);
		btree.add(63);
		btree.printTree();
		btree.delete(50);
		btree.printTree();

		
//		while(true){
//			System.out.println("\n");
//			System.out.println("\n");
//			System.out.println("\n");
//			int option = Integer.parseInt(JOptionPane.showInputDialog(startMessage));
//			switch(option){
//			case(1):
//				btree.add(Integer.parseInt(JOptionPane.showInputDialog(addMessage)));
//				break;
//			case(2):
//				btree.remove(Integer.parseInt(JOptionPane.showInputDialog(removeMessage)));
//				break;
//			}
//			btree.printTree();
//		}
	}

}

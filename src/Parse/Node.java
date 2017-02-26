package Parse;
/**
 * Node class will be used to parse commands.
 * @author Ashka Stephen
 *
 */
public class Node {
	String myVal;
	Node child1;
	Node child2;
	
	//command to INSERT a child node given a command string? needed or not?
	
	
	/**
	 * Default constructor
	 * @param value held in node
	 * @param left child
	 * @param right child
	 */
	public Node(String value, Node leftChild, Node rightChild) {
		myVal = value;
		child1 = leftChild;
		child2 = rightChild;
	}

	/**
	 * Value getter
	 */
	public String getValue() {
		return myVal;
	}
	
	/**
	 * Left Child Getter
	 */
	public Node getChild1(){
		return child1;
	}
	
	/**
	 * Right Child Getter
	 */
	public Node getChild2(){
		return child2;
	}
	
	/**
	 * Is not a leaf node?
	 */
	public Boolean hasChildren(){
		return ((child1!=null) || (child2 != null));
	}
	
	/**
	 * Left Child Exists
	 */
	public Boolean hasChildOne(){
		return (child1!=null);
	}
	
	/**
	 * Right Child Exists
	 */
	public Boolean hasChildTwo(){
		return (child2!=null);
	}
	
	/**
	 * Add child to first available space -> if both children exists, nothing happens -> edit to check???
	 */
	public void addChild(Node childNode) {
		if (!hasChildOne()) {
			child1 = childNode;
		} 
		if ((!hasChildTwo())) {
			child2 = childNode;
		} 
	}

	/**
	 * @return number of children node has
	 */
	public Integer numChildren(){
		int count = 0;
		if (child1!=null){
			count++;
		}
		if (child2!= null){
			count++;
		}
		return count;
	}
	
	/**
	 * Leaf Node?
	 */
	public Boolean isLeafNode(){
		return ((child1==null) && (child2 == null));
	}

	
	

}

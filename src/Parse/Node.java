package Parse;

public class Node {
	String myVal;
	Node child1;
	Node child2;
	
	//command to insert a child node given a command string? needed or no

	
	public Node(String value, Node childone, Node childtwo) {
		myVal = value;
		child1 = childone;
		child2 = childtwo;
	}

	
	public String getValue() {
		return myVal;
	}
	public Node getChild1(){
		return child1;
	}
	public Node getChild2(){
		return child2;
	}
	public Boolean hasChildren(){
		return ((child1!=null) || (child2 != null));
	}
	public Boolean hasChildOne(){
		return (child1!=null);
	}
	public Boolean hasChildTwo(){
		return (child2!=null);
	}
	//when using this need to check if it only has one child -> else an error will occur
	public void addChild(Node childNode) {
		if (child1 == null) {
			child1 = childNode;
		} else {
			child2 = childNode;
		}
	}
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
	public Boolean isLeafNode(){
		return ((child1==null) && (child2 == null));
	}

	
	

}

package main;

public class Node {
	public int key, data;
	public Node left, right;

	public Node(){}
	public Node(int key, int data){
		this.key = key;
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public Node(int key, int data, Node left, Node right){
		this.key = key;
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public int getKey(){
		return key;
	}
	
	public int getData(){
		return data;
	}
	
	public Node getLeft(){
		return left;
	}
	
	public Node getRight(){
		return right;
	}
	
	public void setKey(int key){
		this.key = key;
	}
	
	public void setData(int data){
		this.data = data;
	}
	
	public void setLeft(Node node){
		left = node;
	}
	
	public void setRight(Node node){
		right = node;
	}

	public static String getAsString(Node n){
		if (n != null) {
			return n.toString();
		} else {
			return "N";
		}
	}

	public String toString(){
		return "(" + getAsString(left) + " key: " + key + " data: " + data + " " +getAsString(right) + ")";
	}
}
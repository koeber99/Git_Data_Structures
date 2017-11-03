import java.util.*;

public class LinkedListTest <T> {

	private Node head;
	
	public LinkedListTest(){
		this.head=null;
	}
	
	
	public class Node {
		
		private T data;
		private Node next;
		
		
		public Node (T inputData){
			this.data=inputData;
			this.next=null;
		}
				
		public T getData(){
			return data;
		}
		
		public void setNext(Node inputNode){
			next=inputNode;
		}
		
		public Node getNext(){
			return next;
		}
	}
		
	//add new node to front
	public void append(T inputData){
		Node newNode = new Node(inputData);				
		if (head!=null){
			newNode.setNext(head);
		
		head=newNode;
		
		}
	}
	
	public void appendEnd(T inputData){
		Node newNode =new Node(inputData);
		
		if (head==null){
			head=newNode;
		}else{			
			Node focusNode=head;
		    
			while ( focusNode.getNext()!=null){
				focusNode=focusNode.getNext();
			}
			focusNode.setNext(newNode);
		}
	}
	
	
	public void display(){
		if (head==null){
			System.out.println("Linkedlist is empty");
			return;
		} else{
			Node focusNode=head;
			while(focusNode !=null){
				System.out.print(focusNode.getData()+"-->");
				focusNode=focusNode.getNext();
			}
			System.out.println("NULL");
		}	
	}

	
	
	public void remove(T inputData){
		        // 1st -> Empty list
				// 2nd -> Data is equal to head
				// 3rd -> Removes element at end of list
		
		Node focusNode=head;
		
		if(focusNode==null){
			System.out.println("List is empty");
			return;
		} else if(focusNode.getData().equals(inputData)){
			head=focusNode.getNext();
			return;
		}else{
			
			Node parent=focusNode;
			
			while (focusNode.getNext()!=null){
				focusNode=focusNode.getNext();
				
				if( focusNode.getData().equals(inputData)){
					parent.setNext(focusNode.getNext());
					return;
				}
				
				parent=focusNode;
			}
			
			System.out.println("Value not found to remove");
			return;	
		}
	}	

	public void reverseList2(){
		
		Node prev=null;
		Node current=head;
		Node next =null;

		while (current !=null){
			next=current.next;
			current.next=prev;
			prev=current;
			current=next;
		}
		head=prev;
	}
	
	
	public T kthToLast(int kthValue){
		if (head==null || kthValue <1)
			return null;
		
		Node pointerA=head;
		Node pointerB=head;
		int kCount=kthValue;
		
		while ((pointerB!=null) && (kCount>1)){
			pointerB=pointerB.getNext();
			kCount--;
		}
		
		if(pointerB==null)
			return null;
				
		while(pointerB.getNext()!=null){
			pointerB=pointerB.getNext();
			//pointerB=pointerB.next;
			pointerA=pointerA.getNext();
			//pointerA=pointerA.next;	
		}
		
		return pointerA.getData();
	}
}
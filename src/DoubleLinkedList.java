
public class DoubleLinkedList <T> {
	
	private Node head;
	private Node tail;
	private int size;
	
	public DoubleLinkedList(){
		this.head=null;
		this.tail=null;
		this.size=0;
	}
	
	private class Node {
		private T data;
		public Node next;
		public Node prev;
		
		public Node (T inputData, Node next, Node prev){
			this.data=inputData;
			this.next=next;
			this.prev=prev;
		}
		
		public T getData(){
			return data;
		}
		
		public String toString(){
			return ("<--"+data.toString()+"-->");		
		}
	}
		
	public void appendFront (T inputData){
		Node newNode = new Node (inputData, head,null);
		
		if (head !=null) 
			head.prev=newNode;
		
		head=newNode;
		
		if (tail==null)
			tail=newNode;	
		
		size++;
	}
	
	
	public void appendEnd (T inputData){
		Node newNode = new Node (inputData, null,tail);
		if (tail!=null)
				tail.next=newNode;
		tail=newNode;
		
		if (head ==null)
				head=newNode;
		size++;
	}
	
	public boolean isEmpty(){
		if (size==0)
			return true;
		return false;
	}
	
	public void remove (T inputData){
		
		if (isEmpty()){
			System.out.println("Linked List is empty");
			return;
		}
		
		Node focusNode=head;	
		if (focusNode.getData().equals(inputData)){
			focusNode=head.next;
			focusNode.prev=null;
			head=focusNode;
			size--;
			return;
		}
		
		while (focusNode.next !=null ){
			focusNode=focusNode.next;
			
			if ( focusNode.getData().equals(inputData)){
				if ( focusNode==tail){
					focusNode.prev.next = focusNode.next;
					tail=focusNode.prev;
					size--;
					return;
				}
				
				focusNode.prev.next = focusNode.next;
				focusNode.next.prev = focusNode.prev;
				
				//check
				//focusNode.next.prev = focusNode.prev.next;
				size--;
				return;
			}
		}
		
		//If you are here, the value is not found
		System.out.println("Cannot remove, string value:\""+ inputData+ "\", not found in linked list");
	}
	
	public void printList(){
		Node focusNode =head;
		System.out.print(focusNode.prev);
		while(focusNode.next !=null){
			focusNode=focusNode.next;
			System.out.print(focusNode);
		}
		System.out.println(focusNode.next);
	}
}

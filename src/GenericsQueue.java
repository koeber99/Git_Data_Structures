
public class GenericsQueue <T> {

	private Node first,last;
	public int size;
	
	public GenericsQueue(){
		this.first=null;
		this.last=null;
		this.size=0;
	}
	
	public class Node{
		public Node next;
		private T data;
		
		public Node (T inputData){
			this.data=inputData;
			this.next=null;
		}
		
		public T getData(){
			return data;
		}	
	}
	
	public void enqueue(T inputData){
		Node inputNode = new Node(inputData);
		
		if (first==null){
			first=inputNode;
			last=first;
		}else{
			last.next=inputNode;
			last=inputNode;
		}
		
		size++;
	}
	
	public T dequeue (){	
		if (first !=null){
			T outData=first.getData();
			first=first.next;
			size--;
			return outData;
		}
		return null;
	}
}

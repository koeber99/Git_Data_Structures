public class GenericsStack <T> {
	
	private Node top;
	public long size;
	
	public  GenericsStack (){
		this.top=null;
		this.size=0;
	}
	
	public class Node {
		private T data;
		public Node next;		
		
		Node( T inputData){
			this.data=inputData;
			this.next=null;
		}
		
		public T getData(){
			return data;
		}
		
		public String toString(){
			return data.toString();
		}
	}
	
	public T peek(){
		if (top!=null){
			return top.getData();
		}
		return null;
	}
	
	public void push(T inputData){
		Node item = new Node (inputData);
		size++;
		if (top !=null)
			item.next=top;

		top=item;		
	}
	
	public T pop(){	
		if (top!=null){
			T item =  top.getData();
			top=top.next;
			size--;
			return item;
		}
		return null;
	}
}

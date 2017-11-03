import java.util.Arrays;

public class IntegerMinHeap{
	private int [] items;
	private int size;
	private int capacity;
		
	public IntegerMinHeap (){
		this.size=0;
		this.capacity=10;
		this.items=new int [ capacity];		
	}
	
	public IntegerMinHeap (int capacity ){
		if (capacity <=0) throw new IllegalStateException();
		this.size=0;
		this.capacity=capacity;
		this.items=new int [ capacity];		
	}
	
	private void ensureCapacity(){
		if (size==capacity){
			items=Arrays.copyOf(items, capacity*2);
			capacity=capacity*2;
		}		
	}
	
	private void swap (int indexOne, int indexTwo){
		int temp= items[indexOne];
		items[indexOne]=items[indexTwo];
		items[indexTwo]=temp;		
	}
	
	private boolean hasParent(int index){ return (getParentIndex( index)>=0);  }	
	private boolean hasLeftChild(int index){ return ( getLeftIndex(index)<size); }
	private boolean hasRightChild(int index){ return ( getRightIndex(index)<size); }
	
	private int getParentValue(int index){  return items[getParentIndex(index)]; }
	private int getLeftValue(int index){  return items[getLeftIndex(index)]; }
	private int getRightValue(int index){  return items[getRightIndex(index)]; }
	
	private int getParentIndex(int index){  return (index-1)/2;}	
	private int getLeftIndex(int index){ return index*2+1;}	
	private int getRightIndex(int index){ return index*2+2;}
	
	public int getSize(){  return size; }
	
	public int peek(){
		if (size==0) throw new IllegalStateException();
		return items[0];
	}
	
	public int poll(){
		if (size <0) throw new IllegalStateException();
		int item = items[0];
		items[0]=items[size-1];		
		size--;
		heapifyDown();
		return item;
	}
	
	
	public void offer(int item){		
		 ensureCapacity();
		 items [size]=item;
		 size++;
		 heapifyUp();
	}
		
	private void heapifyUp(){
		int index=size-1;
		while (hasParent(index) && items [index] < getParentValue(index)  ){
			swap (index, getParentIndex(index) );
			index=getParentIndex(index);
		}
	}
	
	private void heapifyDown(){
		int currIndex=0;
		
		while ( hasLeftChild(currIndex)){		
			int smallChildIndex=getLeftIndex(currIndex);			
			if ( hasRightChild(currIndex) && items[smallChildIndex]>  getRightValue(currIndex)   ){
				smallChildIndex=getRightIndex(currIndex);
			}
			if ( items[currIndex] >items[smallChildIndex]){
				swap(currIndex,smallChildIndex);
				currIndex=smallChildIndex;
			}else{
				break;
			}
		}	
	}
}

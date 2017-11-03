import java.util.Iterator;
import java.util.LinkedList;

public class GenericHashMap<K,V> {
	   public LinkedList <Node> [] bucket;
	   int size;
	   int  bucketSize;
	   double loadFactor;
	   
	   GenericHashMap (){
		    size=0;
	        bucketSize=10;
	        bucket= new LinkedList [ bucketSize];	        
	        loadFactor =.75;
	    }
	   
	   GenericHashMap (int intialCapacity){
		    size=0;
	        this.bucketSize=intialCapacity;
	        bucket= new LinkedList [ bucketSize];	        
	        loadFactor =.75;
	    }
	   
	   GenericHashMap (int intialCapacity, double loadFactor){
		    size=0;
	        this.bucketSize=intialCapacity;
	        bucket= new LinkedList [ bucketSize];	        
	        this.loadFactor =loadFactor;
	    }
	
	public class Node {
		private K key;
		private V value;		
		Node (K key, V value){
			this.key=key;
			this.value=value;
		}
		public K getKey(){
			return key;
		}
		public V getValue(){
			return value;
		}
		
		@Override
		public boolean equals(Object obj){
			if (obj ==this)
				return true;
			
			if (this.getClass()!=obj.getClass())
				return false;
						
			Node  otherNode  = (Node) obj;
						
			//Note: HashMap can have null keys and values,therefore, we need to handle these special cases 
			
			//Test if keys both null or not
			if ( (this.key==null &&  otherNode.key!=null) || (this.key!=null &&  otherNode.key==null))
				return false;
			
			//Test if values both null or not		
			if ( (this.value!=null && otherNode.value==null) ||  (this.value==null && otherNode.value!=null))
				return false;
			
			if (this.key==null ){
				if ( this.value!=null)
					return value.equals(otherNode);				
				
				return this.value==null&& otherNode.value==null;				
			}else{				
				if ( this.value!=null )
					return key.equals(otherNode.key) && value.equals(otherNode);
				
				return (key.equals(otherNode.key) && this.value==null && otherNode.value==null);
			}
			
		}
	}
	
	   public int hashFun (int hashCode ){
	       return hashCode%bucketSize;
	   }
	   
	   public void put(K key, V value){				   
		   //keys and values can be null
		   int index=(key!=null)?  hashFun( key.hashCode()) :0;   
	       
	       LinkedList <Node> list = (bucket[index]==null)? new LinkedList<Node>() :  bucket[index];	       
	    
	       for (Node items : list){
	           if (items.getKey()==key ||  ( items.getKey()!=null && items.getKey().equals(key) )   ){
	                items.value=value;
	                return;
	           }
	       }
	        list.add( new Node (key,value));
	        size++;
	        bucket[index]=list;	       
	   } 
	   
	      
	   public V get(K key){
		   int index=(key!=null)?  hashFun( key.hashCode())   :0;		   
	       LinkedList <Node> list = bucket[index];
	       
	       if (list==null)
	    	   return null;
	       
	       for (Node items : list){
	    	   //items.getKey()==key use for null
	           if ( items.getKey()==key ||   ( items.getKey()!=null && items.getKey().equals(key) ) )
	                    return items.getValue();
	       }
	        return null;   
	   } 
	   
		public void remove (K key){			
			 int index=(key!=null)?  hashFun( key.hashCode())   :0;
			
			LinkedList <Node> list = bucket[index];
		       if (list==null)
		    	   return;
		      
		     Iterator<Node> iter = list.iterator();
			
		     while (iter.hasNext()){
		    	 Node items = iter.next();
		    	//items.getKey()==key use for null
		           if (items.getKey()==key || ( items.getKey()!=null && items.getKey().equals(key) ) ){
		        	   iter.remove();
		        	   size--;
		           }
		     }		     
		}
				
		public void display(){			
			for (int i=0;i<bucket.length;i++){
				if (bucket[i]!=null){
					LinkedList <Node> list = bucket[i];
					
					for (Node item:list){
						
					 System.out.print(item.getKey()+":"+item.getValue()+", ");
					}
					System.out.println();
				}					
				}
			}
	 
}
import java.util.*;

public class BinarySearchTree_NotSelf_Balance <T>{
	private TreeNode root;
	private int size;
	
	private class TreeNode {
		private int key;
		public TreeNode  leftChild;
		public TreeNode  rightChild;
		public T data;
		
		TreeNode (int key, T inputData){
			this.key=key;
			this.data=inputData;
			leftChild=null;
			rightChild=null;
		}
		
		public T getData(){
			return data;
		}
		
		public int getKey(){
			return key;
		}
		
		public String toString(){
			return ("Node key"+key+" Data= "+data.toString());
		}		
	}

	public BinarySearchTree_NotSelf_Balance(){
		this.root=null;
		this.size=0;
	}
	
	
	public static void main (String args []){
		BinarySearchTree_NotSelf_Balance <Integer> testobject = new BinarySearchTree_NotSelf_Balance<Integer>();
			
			testobject.add(111, 111);
			testobject.add(22, 22);
			testobject.add(333, 333);
			testobject.add(21, 21);
			testobject.add(55, 55);
			testobject.add(330, 330);
			testobject.add(888, 888);
			testobject.add(880, 880);		
			testobject.add(999, 999);
			testobject.add(1000, 1000);
			testobject.add(3, 3);
			testobject.add(-3, -3);
			testobject.bfsPrint();
			testobject.printInorderTraversal();
			
			
			List<List<Integer>> levelResult=testobject.getLevelOrderTraversal();
			
			System.out.println();
			int levelCount=1;
			for (List<Integer> levels :  levelResult   ){
						System.out.print("\nLevel#"+levelCount+" ");	
						System.out.print(levels);				
						levelCount++;	
			}
			int sum =-154;
			System.out.println("\nDoes the tree have a sum of "+ sum + ": "+testobject.hasPathSum(sum));			
	}
	
	public void add(int key, T inputData){
	     TreeNode addedNode = new TreeNode (key,inputData);
	     size++;
		
	     if(root==null){
	    	 root=addedNode;
	    	 return;
	     }else{
	    	 TreeNode focusNode = root;
	    	 
	    	 while(true){
	    		 TreeNode parent=focusNode;
	    		 if (key <=focusNode.getKey()){
	    			 focusNode=focusNode.leftChild;
	    			 if(focusNode==null){
	    				 parent.leftChild=addedNode;
	    				 return;
	    			 }
	    		 }else{
	    			 focusNode=focusNode.rightChild;	    			 
	    			 if(focusNode==null){
	    				 parent.rightChild=addedNode;
	    				 return; 
	    		      }
	    	       }
	            }
	       }
       }

	public boolean remove(int key){
		if (root==null){
			return false;
		}
		
		TreeNode focusNode =root;
		TreeNode parent =root;
		
		boolean isItALeftChild=true;
		
		//find and check to see if the node exist in tree
		while(focusNode.getKey() !=key){
			parent=focusNode;
			if (key < focusNode.getKey()){
				isItALeftChild=true;
				focusNode = focusNode.leftChild;
			} else{
				isItALeftChild=false;
				focusNode = focusNode.rightChild;
			}
			
			if(focusNode==null){
				return false;
			}		
		}
		
		//Node exist and needs to be deleted
		if ( (focusNode.leftChild==null) &&  (focusNode.rightChild==null) ){
			
				if(focusNode==root){
					root=null;
				} else if(isItALeftChild){
					parent.leftChild=null;
				}else{
					parent.rightChild=null;
				}	
			
		} else if ( (focusNode.rightChild==null)){
			
			if(focusNode==root){
				root=focusNode.leftChild;
			} else if(isItALeftChild){
				parent.leftChild=focusNode.leftChild;
			}else{
				parent.rightChild=focusNode.leftChild;
			}	
			
		} else if  ( (focusNode.leftChild==null)){
			if(focusNode==root){
				root=focusNode.rightChild;
			}else if( (isItALeftChild)){
				parent.leftChild=focusNode.rightChild;
			}else{
				parent.rightChild=focusNode.rightChild;
			
			}
		} else{ 
			//focusNode has two children
			TreeNode replacement = getReplacementNode(focusNode);	
			if(focusNode == root){
				root=replacement;
			} else if(isItALeftChild){
				parent.leftChild=replacement;
			}else {
				parent.rightChild=replacement;
			}
			replacement.leftChild=focusNode.leftChild;
		}
		return true;
	}
	
	
	
	public TreeNode getReplacementNode(TreeNode replacedNode){
		
		TreeNode replacementParent = replacedNode;
		TreeNode replacement = replacedNode;
		
		TreeNode focusNode = replacedNode.rightChild;
		
		while( focusNode !=null){
			replacementParent = replacement;
			replacement =focusNode;
			focusNode =focusNode.leftChild;
		}
		
		if(replacement !=replacedNode.rightChild  ){
			replacementParent.leftChild= replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
		}
		return replacement;
	}
	
	
	public ArrayList <LinkedList<TreeNode>> createLevelLinkedList (){	
		 ArrayList <LinkedList<TreeNode>> result = new  ArrayList <LinkedList<TreeNode>>();
		 /* "Visit" the root */  
		 LinkedList<TreeNode> current = new LinkedList <TreeNode>();
		 if (root !=null){
			 current.add(root);
		 }
		 
		 while (current.size()>0){
			result.add(current); //add previous level
			LinkedList<TreeNode> parents = current;
			current = new LinkedList<TreeNode>();
			
			for (TreeNode parent : parents){
				/*Visit the children */
				if(parent.leftChild !=null){
					current.add(parent.leftChild);
				}
				if(parent.rightChild !=null){
					current.add(parent.rightChild);
				}	
			  }
		  }		 
		 return result;
	}
	
	
	public void bfsPrint(){
		ArrayList <LinkedList<TreeNode>>  result= createLevelLinkedList ();
		
		System.out.println("Number of level "+ result.size());
		int count =result.size();
		
		for (LinkedList<TreeNode> level :  result){
			
			for (TreeNode eachNode :level){
				printWhiteSpace( ((1<<count)));
				System.out.print( eachNode.getKey()+ "");
			}
			System.out.println("");
			
			if ( count>1){
					for (TreeNode eachNode :level){				
						printWhiteSpace( ((1<<count)));
						System.out.print("/\\ ");
					}
			}
			count--;
			System.out.println("");
			//System.out.println("/ \\");
		}		
	}
	
	
	public void printWhiteSpace(int count){
		for (int i=0;i< count;i++){
			System.out.print(" ");
		}		
	}
	
	
	public void printInorderTraversal(){
		System.out.println("InOrder");
		inOrderTraversal(root);
	}
	
	private void inOrderTraversal(TreeNode inputNode){
		
		if(inputNode!=null){
			inOrderTraversal(inputNode.leftChild);
			System.out.print(inputNode.getKey()+",");
			inOrderTraversal(inputNode.rightChild);
		}
	}
	
	public void printReverseInOrderTraversal(){
		revInOrderTraversal(root);	
	}
	
	
	private void revInOrderTraversal(TreeNode inputNode){		
		if(inputNode!=null){
			revInOrderTraversal(inputNode.rightChild);
			System.out.print(inputNode.getKey()+",");
			revInOrderTraversal(inputNode.leftChild);		
		}
	}
	
	public static int countStatic;
	public void getKthLargestElement(int inputK){
		if((inputK<0)|| ( (inputK>size))){
			System.out.println("K value: "+ inputK +" is out of range min: 0 "+ "Max:(tree size) "+size);
			return;
		}
		countStatic=0;
		kthLargestElement(root, inputK);
	}
	
	
	public void kthLargestElement(TreeNode inputNode, int kValue){
		
		if( (inputNode !=null) && (countStatic<kValue)){
			 kthLargestElement(inputNode.rightChild,kValue);
			 if (++countStatic== kValue){
				 System.out.println(inputNode.getKey()+ " count is "+countStatic);
				 return;
			 }
			 kthLargestElement(inputNode.leftChild,kValue);
		}
	}
	
   	
	public ArrayList<Integer> getInOrderTraversal(){
		ArrayList <Integer> resultList = new ArrayList <Integer> ();
		inOrderTravArrayList(root,resultList);
		return resultList;
	}
	
	public void inOrderTravArrayList(TreeNode inputNode,ArrayList <Integer> resultList){	
		if(inputNode!=null){
			inOrderTravArrayList(inputNode.leftChild,resultList);
			resultList.add(inputNode.getKey());
			inOrderTravArrayList(inputNode.rightChild,resultList);		
		}
	}
	
	public void printAllPairsEqualSum(int sumValue){		
		Set <Integer> allKeys = new HashSet <Integer> ();
		System.out.println("Pair values are");
		inOrderTraversalEqualSum(root,sumValue,allKeys);		
	}
	
	public void inOrderTraversalEqualSum(TreeNode inputNode,int sumValue, Set <Integer> keySet ){
		
		if( inputNode!=null){			
			inOrderTraversalEqualSum(inputNode.leftChild,sumValue,keySet);
			
			int needValuePos =  sumValue - inputNode.getKey();
			int needValueNeg =  sumValue + inputNode.getKey() ;
			
			if ( keySet.contains(needValuePos) ){
				
				System.out.println("Pos value "+(needValuePos)+","+inputNode.getKey());
			}
			if ( keySet.contains(needValueNeg) ){
				System.out.println("Neg Value "+(needValueNeg)+","+inputNode.getKey());
			}
			keySet.add(inputNode.getKey());					
			inOrderTraversalEqualSum(inputNode.rightChild,sumValue,keySet);
		}	
	}
		
	public int printCommonAncestor (int key1,int key2){
		if ( key1>=key2)
			return -1;
		
		TreeNode focusNode =root;
		
		while ( focusNode!=null){	
				
			if ( (key1 < focusNode.getKey()) &&(key2 < focusNode.getKey()) )	{
					focusNode=focusNode.leftChild;
				
			}else if ( (key1 > focusNode.getKey()) &&(key2 > focusNode.getKey()) ){
					focusNode=focusNode.rightChild;
			} else{
				
				int commonAncestor= focusNode.getKey();
				System.out.println("least common is "+focusNode.getData());
				
				//verfiy both keys are in tree
				
				//find key1
				TreeNode focusNodeKey1= focusNode;
				TreeNode focusNodeKey2= focusNode;
			
			   if ( (containsKey(focusNodeKey1,key1)) &&  (containsKey(focusNodeKey2,key2)) ){
				   
				   return commonAncestor;
			   } else if (containsKey(focusNodeKey1,key1) ){
				   
				   System.out.println("Tree does not contain key 2");
				   return -1;
			   } else if (containsKey(focusNodeKey2,key2) ){
				   
				   System.out.println("Tree does not contain key 1");
				   return -1;
			   } else {
				   System.out.println("Tree doesnot key 1 or key 2");
				   return -1;
			   }
			
			    }	
		}
		return -1;
	}
	
	public TreeNode commonAncestorNode (int key1,int key2){
			
			if ( key1>=key2)
				return null;
			
			TreeNode focusNode =root;
			
			while ( focusNode!=null){	
					
				if ( (key1 < focusNode.getKey()) &&(key2 < focusNode.getKey()) )	{
						focusNode=focusNode.leftChild;
					
				}else if ( (key1 > focusNode.getKey()) &&(key2 > focusNode.getKey()) ){
						focusNode=focusNode.rightChild;
				} else{
					
					TreeNode commonAncestor= focusNode;
					//System.out.println("least common is "+focusNode.getData());
					
					//verfiy both keys are in tree
					
					//find key1
					TreeNode focusNodeKey1= focusNode;
					TreeNode focusNodeKey2= focusNode;
				
				   if ( (containsKey(focusNodeKey1,key1)) &&  (containsKey(focusNodeKey2,key2)) ){
					   
					   return commonAncestor;
				   } else if (containsKey(focusNodeKey1,key1) ){
					   
					   System.out.println("Tree does not contain key 2");
					   return null;
				   } else if (containsKey(focusNodeKey2,key2) ){
					   
					   System.out.println("Tree does not contain key 1");
					   return null;
				   } else {
					   System.out.println("Tree doesnot key 1 or key 2");
					   return null;
				   }				
				    }	
			}
			return null;
		}
	
	public boolean containsKey(TreeNode inputNode,int key){
		if( inputNode==null)
			return false;
		TreeNode focusNode = inputNode;
		
		while(focusNode.getKey()!= key){
			if( key < focusNode.getKey()){
				focusNode=focusNode.leftChild;	
			}else{
				focusNode=focusNode.rightChild;
			}
			if(focusNode==null){
				return false;
			}	
		}//end of while loop
		
		//found key
		return true;
	}

	public ArrayList<Integer> shortestPath(int key1,int key2){
		
		ArrayList<Integer> result =new ArrayList<Integer>();
		
		
		TreeNode commomAncestorNode = commonAncestorNode (key1,key2);
		
		if ( commomAncestorNode==null){
			return null;
		}
		
		ArrayList<Integer> tempResult= nodePath(commomAncestorNode, key1);
		if (  tempResult==null){
			return null;
		}
		
		for (int i=tempResult.size()-1; i>=0;i--){
			result.add(tempResult.get(i));
		}
		
		
		result.add(commomAncestorNode.getKey());
		tempResult= nodePath(commomAncestorNode, key2);
		if (  tempResult==null){
			return null;
		}
		result.addAll(tempResult);
		
		
		return result;
	}
	
	public ArrayList<Integer> nodePath(TreeNode startNode, int endKey ){
		if (startNode==null){
			return null;
		}
		ArrayList<Integer> result = new  ArrayList<Integer>();
		TreeNode focusNode = startNode;
		
		
		while ( focusNode.getKey() !=endKey){
			
			
			if ( endKey < focusNode.getKey()){
					focusNode=focusNode.leftChild;
					
					if ( focusNode==null){
						return null;
					} else{
						result.add(focusNode.getKey());
					}
			} else{
				
				focusNode=focusNode.rightChild;
				
				if ( focusNode==null){
					return null;
				} else{
					result.add(focusNode.getKey());
				}		
			}
		}//end of While loop
		
		
		return result;
	}

	
	
	public boolean checkBST(){
		if (root==null)
			return false;
		return checkBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	boolean checkBST(TreeNode n, Integer min,Integer max){
       
		if (n==null){
			return true;
		}
		
		if ( (n.getKey() <=min) || (n.getKey() >max)) {
			return false;
		}
		
		if ( !(checkBST(n.leftChild, min,n.getKey()) ) ){
			return false;	
		}
			
		if (  !(checkBST(n.rightChild, n.getKey(),max) ))   {
			return false;	
		}
		
		return true;
	}
	
	
	public void printPathSums(){
		printPathSums(root,0);
	}
	
	public int printPathSums(TreeNode inputNode, int prevalue){
		
		if (inputNode==null)
				return 0;
		
		if (inputNode.leftChild==null && inputNode.leftChild==null){
			int pathValue = inputNode.getKey()+ prevalue;
			System.out.println("PathValue "+inputNode.getKey()+"= "+pathValue);
			return pathValue;
		}else{
			int pathValue = inputNode.getKey()+ prevalue;			
			printPathSums(inputNode.leftChild, pathValue);
			printPathSums(inputNode.rightChild, pathValue);
			return 0;
		}	
	}
	
	
	public void printPathMaxSums(){		
		System.out.println("Max Value =" + printPathMaxSums(root,0));
	}
		
	public int printPathMaxSums(TreeNode inputNode, int prevalue){
		
		if (inputNode==null)
				return 0;
		
		if (inputNode.leftChild==null && inputNode.rightChild==null){
			int pathValue = inputNode.getKey()+ prevalue;
			
			return pathValue;
		}else{
			int pathValue = inputNode.getKey()+ prevalue;
			int leftPathValue=  printPathMaxSums(inputNode.leftChild, pathValue);
			int rightPathValue= printPathMaxSums(inputNode.rightChild, pathValue);
			
			int currentMax= Math.max(leftPathValue,rightPathValue);		
			return  currentMax;
		}	
	}
		
	public List <Integer> findMaxPathList(){
		List<Integer> arrList = findMaxPathList(root);
		arrList.remove(0);			
			return arrList;
		}
		
	public List <Integer> findMaxPathList(TreeNode node){
		if (node==null){
			List<Integer> arrList = new ArrayList<Integer> ();
			arrList.add(0,0);
			return arrList;
		}
		
		List<Integer> leftList = findMaxPathList(node.leftChild);
		List<Integer> rightList = findMaxPathList(node.rightChild);	
		
		List<Integer> result = (leftList.get(0) >  rightList.get(0))?leftList:rightList;
		int currentValue = result.get(0) + node.getKey();
		result.set(0,currentValue);
		result.add(node.getKey());
		return result;
	}
		
	public int printMaxPath(){
		return printMaxPath(root);	
	}
	
	
	public int printMaxPath(TreeNode inputNode){
		if ( inputNode==null){ return 0;}	
		int leftTreeMax = printMaxPath(inputNode.leftChild);
		int rightTreeMax = printMaxPath(inputNode.rightChild);
		return Math.max(leftTreeMax, rightTreeMax)+ inputNode.getKey();
	}
	
	public void mirrorTree (){	
		root=mirrorTree (root);
	}	
	
	public TreeNode mirrorTree (TreeNode inputNode){
		if ( inputNode==null){ return null; }
		
		TreeNode leftNode = mirrorTree (inputNode.leftChild );
		TreeNode rightNode = mirrorTree (inputNode.rightChild );
		inputNode.leftChild=rightNode;
		inputNode.rightChild=leftNode;
	
		return inputNode;
	}
		
	public List <Integer> rootToPath (int target){
		return rootToPath (root , target);		
	}
	
	public List <Integer> rootToPath (TreeNode node , int target){
		
		List<Integer> arrList = new ArrayList<Integer>();
		if (node==null){ return  arrList;}
		
		if ( node.getKey() == target){
				arrList.add( node.getKey());
				return  arrList;
		}
		
		List<Integer> arrListLeft  = rootToPath (node.leftChild , target);
		List<Integer> arrListRight = rootToPath (node.rightChild, target);
		
		if (!arrListLeft.isEmpty())   {  	
			arrListLeft.add(node.getKey()); 
			return arrListLeft;
		}		
		if (!arrListRight.isEmpty())  {  
			arrListRight.add(node.getKey());  
			return arrListRight;
		}		
		return arrList;		
	}
	
	public void printAllLeaf(){
		System.out.println("The Leaves of the tree are ");
		printAllLeaf(root);
		System.out.println("\n");
	}
	
	
	
	
	
	public boolean hasPathSum(int sum){
		
		return hasPathSum(root,0,sum);
		
	}
	
	public boolean hasPathSum(TreeNode inputNode ,int currSum, int sum){
		if (inputNode==null)
			return false;
		
		currSum=currSum + (Integer)inputNode.getData();
				
		if (inputNode.leftChild ==null && inputNode.rightChild==null){
			if (currSum==sum)
				return true;
			else
				return false;
		}
			
		if ( hasPathSum(inputNode.leftChild,currSum,sum))
			return true;
		
		if ( hasPathSum(inputNode.rightChild,currSum,sum))
			return true;
		
		return false;
	}
	
	
	
	
	public void printLeaveSum (){	
		System.out.println("The sum of the tree leaves= " + getLeaveSum (root) );
	}
	
	
	public int getLeaveSum(TreeNode node){
		if (node==null) {return 0;}
		
		if (node.leftChild==null && node.rightChild==null){
			return node.getKey();
		}
		return  getLeaveSum(node.leftChild) + getLeaveSum(node.rightChild);		
	}
	
	public void printLeaveSumItervate (){	
		System.out.println("The sum of the tree leaves= " + getLeavesSumItervative() );
	}
	
	public int getLeavesSumItervative (){	
		if (root ==null){ return 0;}
		Deque <TreeNode> stack = new ArrayDeque <TreeNode>();
		stack.push(root);
		int result =0;
		
		while (!stack.isEmpty()){
			TreeNode current = stack.pop();
			
			if ( current.leftChild==null && current.rightChild==null){
				   result+=current.getKey();
				   continue;
			}	
			if ( current.rightChild!=null){
				stack.push(current.rightChild);
			}
			if ( current.leftChild!=null){
				stack.push(current.leftChild);
			}	
		}
		return result;	
	}
	
	
	public void printAllLeaf(TreeNode node){
		if (node ==null)
			return;
		if ( node.leftChild==null && node.rightChild==null ){
			System.out.print(node.getData()+" ");
			return;
		}else{
			 printAllLeaf(node.leftChild);
			 printAllLeaf(node.rightChild);	
		}
	}
	
	
	
	public void leafCount (){
		System.out.println("The # of leaves in the tree are= "+leafCount(root));		
	}
	
	
	public int leafCount (TreeNode node){
		
		if (node ==null)  {return 0;}
		
		if ( node.leftChild ==null &&node.rightChild ==null ){
			return 1;
		}
		
		return leafCount (node.leftChild) + leafCount (node.rightChild);
	}
	

	
	public void getClostestValue (Integer target){
		Integer closest = Integer.MIN_VALUE;
		getClostestValue(root,target,closest);
		System.out.println("PreOrder traversal: The closest Value is "+closest);				
	}
	
	public void getClostestValue (TreeNode node,Integer target, Integer closest){

		if (node!=null){
			
			if (node.getKey()==target){
				closest=root.getKey();
				return;
			}
			if (closest==target){
				return;
			}
			
			int tmpAbs =Math.abs(node.getKey()-target);
			int currentAbs =Math.abs(closest-target);
			
			if ( tmpAbs< currentAbs){
				closest=node.getKey();
			}
			System.out.println("The closest Value is "+closest);
			getClostestValue (node.leftChild,target,  closest);
			getClostestValue (node.rightChild,target,  closest);
			
		}		
	}
	
	public  void findClostestValue( int target ){
		int closet= findClostestValue (root , target);		
		System.out.println("The closet Value to the target,"+target +", is:");
		System.out.println("closet= "+closet);
	}
	
	
	public int findClostestValue (TreeNode node , int target){
			if (node==null)
				return Integer.MAX_VALUE;
		
			if (node.getKey()==target)
				return node.getKey();
			
			int leftValue = findClostestValue (node.leftChild , target);
			int rightValue = findClostestValue (node.rightChild , target);
				
			int tempAbs = Math.abs(node.getKey()-target);
			int leftAbs = Math.abs(leftValue-target);
			int rightAbs = Math.abs(rightValue-target);
			
			int minAbs = Math.min (tempAbs,(Math.min(leftAbs, rightAbs)  ));
						
			if (minAbs== tempAbs)
				return node.getKey();
			else if (minAbs== leftAbs)
				return leftValue;
			else
				return rightValue;
	}



public void printDepthList (){
		
		HashMap <Integer, ArrayList<Integer>> depthMap = new HashMap <Integer, ArrayList<Integer>> ();
		printDepthList (root,0,depthMap);	
			
		for (Map.Entry<Integer, ArrayList<Integer>> entry :depthMap.entrySet() ){	
			int height = entry.getKey();
			ArrayList<Integer> arrList = entry.getValue();
			System.out.println("Nodes with height of "+height  );
			for ( int value :arrList    ){
				System.out.print(value+", ");				
			}
			System.out.println(" ");
		}
	}
	
	
	public void printDepthList (TreeNode inputNode,int height , HashMap <Integer, ArrayList<Integer>> depthMap){
		
		if (inputNode==null)
			return;
		
		ArrayList<Integer> arrList =(depthMap.containsKey(height)) ? depthMap.get(height) : new ArrayList<Integer>();
			
		arrList.add(inputNode.getKey());
		depthMap.put(height,arrList);
		printDepthList (inputNode.leftChild,height+1 ,depthMap);
		printDepthList (inputNode.rightChild,height+1 ,depthMap);		
	}	
	
		public void printVerticalOrder(){
		
		HashMap <Integer, LinkedList<Integer>> hMap = new HashMap <Integer, LinkedList<Integer>>();
		getVertical (root,0,hMap);
		System.out.println("\n ");	
		Integer [] arrHd = new Integer [hMap.size()];
		hMap.keySet().toArray( arrHd);
	
		Arrays.sort(arrHd);	
		for ( int hdValue: arrHd ){
			System.out.println(" Horizontal Distance= " + hdValue);
			LinkedList<Integer> listValues = hMap.get(hdValue);
			for (int value : listValues ){
				System.out.print(value+ ", ");
			}	
			System.out.println("\n ");
		}
	}
	
	
	public void getVertical (TreeNode inputNode, int hDistance, HashMap <Integer, LinkedList<Integer>> hMap){
				if (inputNode==null)
					return;

				LinkedList <Integer> nodeList = ( hMap.containsKey(hDistance))? hMap.get(hDistance): new LinkedList <Integer>();
			    nodeList.add(inputNode.getKey());
				hMap.put(hDistance,nodeList);
				
				getVertical (inputNode.leftChild,hDistance-1,hMap);
				getVertical (inputNode.rightChild,hDistance+1,hMap);
	}
	
	    public TreeNode lowestCommonAncestor(TreeNode inputNode, T p, T q) {     
	        if ( inputNode ==null) return null;
	        
	        if ( inputNode.getData() == p || inputNode.getData() == q){
	                       return inputNode;
	        } 
	             
	        TreeNode leftNode = lowestCommonAncestor(root.leftChild, p,q);        
	        TreeNode rightNode = lowestCommonAncestor(root.rightChild, p,q);
	         
	        if (leftNode != null &&  rightNode != null  ){
	            return inputNode; //common ancestor
	        }

	        if (leftNode != null ){
	            return leftNode; //pass up node
	        }
	        
	       if (rightNode != null ){
	            return rightNode; //pass up node
	        }      
	         return null;  
	    }

	public List <List<T>> getLevelOrderTraversal (){
		if (root ==null) return null;
		
		List<List<T>> result = new ArrayList <List<T>>();
		Deque <TreeNode> traversalQueue  = new ArrayDeque <TreeNode>();
		traversalQueue.offer(root);

		while (!traversalQueue.isEmpty()){
			List <T> resultList = new LinkedList <T>();
			Deque <TreeNode> levelQueue = traversalQueue;
			//traversalQueue = new LinkedList <TreeNode>();
			traversalQueue = new ArrayDeque <TreeNode>();
			while ( !levelQueue.isEmpty()){
					TreeNode current = levelQueue.poll();
					resultList.add(current.getData());
					if (current.leftChild !=null){
						traversalQueue.offer( current.leftChild);
					}		
					if (current.rightChild !=null){
						traversalQueue.offer( current.rightChild);
					}				
			}
			//level++;
			result.add(resultList);			
		}		
		return result;
	}
}
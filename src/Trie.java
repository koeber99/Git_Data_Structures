import java.util.*;
import java.util.Map.Entry;

public class Trie {
	public TrieNode root;

	private class TrieNode {		
		HashMap <Character,TrieNode> children;
		boolean isCompleteWord;
		
		public TrieNode (){
			this.children=new HashMap <Character,TrieNode>();
			this.isCompleteWord=false;
		}
		
		public ArrayList <Character> getChildrenList(){	
			return new ArrayList <Character>(children.keySet());
		}
	}
	
	public Trie(){
		this.root=new TrieNode();
	}
	
	public void printTrie (){
		printTrie(root);
	}
	
	private void printTrie (TrieNode rootNode){
		if (rootNode==null)
			return;
		System.out.println();
		
		Deque <TrieNode> queue = new ArrayDeque<TrieNode>();
		queue.add(rootNode);
		while (!queue.isEmpty()){
			TrieNode current = queue.poll();
			
			System.out.println(current.getChildrenList());
			
			for (Entry<Character, TrieNode> entry : current.children.entrySet()){
				if (entry.getValue()!=null){
					queue.add(entry.getValue());
				}	
			}		
			System.out.println("\\");
		}
	}
	
	public void insert(String word){
		if (word==null || word.length()==0)
			return;
		
		TrieNode current =root;
		
		for (int i=0;i<word.length();i++){
				char ch = word.charAt(i);	
				TrieNode node =current.children.get(ch);
				if (node==null){
					node=new TrieNode ();
					current.children.put(ch,node);
				}
			current=node;
		}
		current.isCompleteWord=true;	
	}
		
	public boolean contains(String word){
		
		if (word==null ||word.length()==0){
			throw new IllegalStateException();
		}	
		TrieNode current=root;
		
		for (int i=0;i<word.length();i++){
			char ch = word.charAt(i);
			
			TrieNode node=current.children.get(ch);
			if (node ==null){
				return false;
			}
			current=node;		
		}
		return current.isCompleteWord;
	}	
}
//Nina Yang
//CSE 373
//Description: implements the binary search trees and general properties of 
//binary search tree
class bst{
    
    Node root;

    private class Node{
    	
    	// These attributes of the Node class will not be sufficient for those attempting the AVL extra credit.
    	// You are free to add extra attributes as you see fit, but do not remove attributes given as it will mess with help code.
        String keyword;
        Record record;
        int size;
        Node l;
        Node r;

        // Initializes the keyword to value k. also sets record, left and right
        // subtree to null. 
        private Node(String k){
        	keyword=k;
        	record=null;
        	size=0;
        	l=null;
        	r=null;
        }

        // add Record r to the front of the linked list of already existing record.
        // does not check for repeats
        private void update(Record r){
        	r.next= record;
        	record = r;
        }
       
    }

    public bst(){
        this.root = null;
    }
      
    // add given record to the list of record to the node associated with the keyword.
    // if node did not exist create new node. 
    public void insert(String keyword, FileData fd){
        Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        root = insertHelp (keyword, root, recordToAdd);
    }
    
    // helper method to insert. returns the overall root of the tree and inserts record to list
    public Node insertHelp (String keyword, Node addNode, Record recordToAdd) {
    	if (addNode == null) {
    		Node newNode=new Node(keyword);
    		newNode.update(recordToAdd);
    		return newNode; // new node
	    } else if (addNode.keyword.compareTo(keyword) < 0) { //get key
	    	addNode.r = insertHelp (keyword, addNode.r, recordToAdd);
	    	return addNode;
	    } else if (addNode.keyword.compareTo(keyword) > 0) { //get key
	    	addNode.l = insertHelp (keyword, addNode.l, recordToAdd);
	    	return addNode;
	    }
		addNode.update(recordToAdd);
		return addNode;
    }
    
    // returns true if keyword exists in tree, false if not.
    public boolean contains(String keyword){
    	return containsHelp(keyword, root);
    }
    
    // helper method, returns true if keyword is in tree false if not
    public boolean containsHelp (String keyword, Node containNode) {
	    if (containNode == null) {
	    	return false;
	    } else if (containNode.keyword.compareTo(keyword) < 0) { 
	    	return containsHelp (keyword, containNode.r);
	    } else if (containNode.keyword.compareTo(keyword) > 0) { 
	    	return containsHelp (keyword, containNode.l);
	    } else {
	    	return true;
	    }
    }

    // returns the first record for a particular keyword. 
    // returns null if keyword is not in the binary search tree
    public Record get_records(String keyword){
    	if (!contains(keyword)) {
    		return null;
    	} else {
    		Node find = findNode(keyword, root);
    		return find.record; 
    	}
    }
    
    // delete the node associated with the keyword
    public void delete(String keyword){
    	root = deleteHelp (keyword,root);
    }

    // helper method. returns the node that was passed in.
    // deletes the node associated with the keyword. 
    // if key word does not exist the function does nothing. 
    public Node deleteHelp (String keyword, Node keyNode){
    	if(keyNode.keyword.equals(keyword)){
	       if (keyNode.l == null && keyNode.r == null) { // leaf
        	  keyNode = null;
           } else if (keyNode.l != null && keyNode.r != null){ // two child
        	   Node min = min(keyNode.r);
        	   keyNode.keyword = min.keyword;
        	   deleteHelp(min.keyword, keyNode.r);
           } else if (keyNode.l == null) {  // one child
        	   keyNode = keyNode.r;
	       } else {
	    	   keyNode = keyNode.l;
	       }
    	} else if (keyNode.keyword.compareTo(keyword) < 0) {
    		keyNode.r = deleteHelp(keyword, keyNode.r);
    	} else if (keyNode.keyword.compareTo(keyword) > 0) {
    		keyNode.l = deleteHelp(keyword, keyNode.l);
    	}
    	return keyNode;
    }
    
    // helper method. returns the minimum value of the tree passed in. 
    public Node min (Node min) {
    	if (min.l != null) {
    		return min(min.l);
    	}
    	return min;
    }
    
    // helper method. returns the node associated with the keyword
    public Node findNode(String keyword, Node findNode) {
	    if (findNode == null) {
	    	return null;
	    } else if (findNode.keyword.compareTo(keyword) < 0) { 
	    	return findNode(keyword, findNode.r);
	    } else if (findNode.keyword.compareTo(keyword) > 0) { 
	    	return findNode (keyword, findNode.l);
	    } else {
	    	return findNode;
	    }
    }
    

    public void print(){
        print(root);
    }

    private void print(Node t){
        if (t!=null){
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
                System.out.printf("\t%s\n",r.title);
                r = r.next;
            }
            print(t.r);
        } 
    }
    

}

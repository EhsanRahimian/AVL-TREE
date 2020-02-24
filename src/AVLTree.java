
import java.util.LinkedList;
import java.util.Queue;

class Node{
    int data;
    int height;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        height = 0;
    }
}
public class AVLTree {
    Node root;
    //creating the AVLTree
    AVLTree(){   //Time:O(1)  ,   Space:O(1)
        root=null;
    }
    //////////Serach in AVL Tree////////////////////////////////////////////////
    public void searchAVL(int data){   
        searchAVL(root,data);
    }
    Node searchAVL(Node root, int data){  // Time:O(log n)    Space:O(log n)
        if(root == null){
            System.out.println("Value: " + data + " not found in AVL.");
            return null;
        }
        if(root.data == data){
            System.out.println("Value: " + data + " found in AVL.");
            return root;
        }else
            if(root.data>data){
                return searchAVL(root.left, data);
            }
        else
                return searchAVL(root.right, data);  
    }
    
    //////////Insert Node///////////////////////////////////////////////////////
    void insert(int data){
        root = insert(root, data);
    }
    Node insert(Node node , int data){   // Time:O(log n)    Space:O(log n)
        if (node == null) {
            System.out.println("Successfully inserted " + data + " in AVL Tree");
            return new Node(data);
        }else if(data <= node.data){
            node.left = insert(node.left, data);
        }else{
            node.right = insert(node.right, data);
        }
        // THIS IS WHERE WE WILL DO AVL SPECIFIC WORK
        int balance = checkBalance(node.left, node.right);
        if (balance>1){
            if(checkBalance(node.left.left, node.left.right)>0){
                node = rightRotation(node);// LL Condition
            
            
            
            
            }else{
                node.left = leftRotation(node.left);// LR Condition
                node = rightRotation(node);
            }
        }else if(balance<-1){
            if(checkBalance(node.right.right, node.right.left)>0){
                node = leftRotation(node);// RR Condition
                
            }else{
                node.right = rightRotation(node.right);//  RL Condition
                node = leftRotation(node);
            }
        }
        
        if(node.left != null){
            node.left.height = calculateHeight(node.left);
        }
        
        if (node.right != null){
            node.right.height = calculateHeight(node.right);
        }
        node.height= calculateHeight(node);
        return node;
    }
    //////////Right Rotation////////////////////////////////////////////////////
    private Node rightRotation(Node disbalancedNode){  //Time:O(1)  ,   Space:O(1)
        Node newRoot = disbalancedNode.left;
        disbalancedNode.left = disbalancedNode.left.right;
        newRoot.right = disbalancedNode;
        //Height adjusment
        disbalancedNode.height = calculateHeight(disbalancedNode);
        newRoot.height = calculateHeight(newRoot);
        return newRoot;
    }
    //////////Left Rotation/////////////////////////////////////////////////////
    private Node leftRotation(Node disbalancedNode){  //Time:O(1)  ,   Space:O(1)
        Node newRoot = disbalancedNode.right;
        disbalancedNode.right = disbalancedNode.right.left;
        newRoot.left = disbalancedNode;
        //Height adjusment
        disbalancedNode.height = calculateHeight(disbalancedNode);
        newRoot.height = calculateHeight(newRoot);
        return newRoot;
    }
    //////////Calculate Height//////////////////////////////////////////////////
    private int calculateHeight(Node node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(node.left !=null? node.left.height :-1, 
                node.right != null? node.right.height:-1);
    }
    //////////Checking Balance//////////////////////////////////////////////////
    private int checkBalance(Node rootLeft, Node rootRight){
        //if current node is a leaf node then no need to check balance of its children
        if((rootLeft == null)&&(rootRight== null)){ 
            return 0;
        // if left node node is not there then simply return right node's
        // height + 1
        // we need to make it -1 because blank height is considered
        // having height as '-1'
        }else if (rootLeft == null){
            return -1 *((rootRight.height)+1);
        }else if(rootRight == null){
            return rootLeft.height+1;
        }else
        // +1 is not required, as both right and left child
        // exits and 1 gets nullified
            return rootLeft.height - rootRight.height;
    }
    //////////LevelOrder Traversal//////////////////////////////////////////////
    public void levelTraversall(){  //Time:O(n)  ,   Space:O(n)
        if(root == null){
            System.out.println("Root is null!");
            return;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.poll();
            System.out.print(node.data+" ");
            if(node.left != null){
                q.add(node.left);
            }
            if(node.right != null){
                q.add(node.right);
            }
        }    
    }
    //////////PreOrder Traversal////////////////////////////////////////////////
    void preOrder(Node root){   //Time:O(n)  ,   Space:O(n)
        if(root == null){
            return;
        }
        System.out.println(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }
    //////////InOrder Traversal/////////////////////////////////////////////////
    void inOrder(Node node){   //Time:O(n)  ,   Space:O(n)
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.data+" ");
        inOrder(node.right);  
    }
    //////////PostOrder Traversal///////////////////////////////////////////////
    void postOrder(Node node){   //Time:O(n)  ,   Space:O(n)
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    
    public static void main(String[] args) {
        AVLTree t = new AVLTree();
        t.insert(40);
        t.levelTraversall();
        System.out.println("");
        t.insert(50);
        t.levelTraversall();
        System.out.println("");
        t.insert(20);
        t.levelTraversall();
        System.out.println("");
        t.insert(10);
        t.levelTraversall();
        System.out.println("");
        t.insert(30);
        t.levelTraversall();
        System.out.println("");
        t.insert(35);
        t.levelTraversall();
        System.out.println("");
        t.insert(38);
        t.levelTraversall();
        System.out.println("");
        t.insert(25);
        t.levelTraversall();
        System.out.println("");
       
        
       
        
    }
    
}

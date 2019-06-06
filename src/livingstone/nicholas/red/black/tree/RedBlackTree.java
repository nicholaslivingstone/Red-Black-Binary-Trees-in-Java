/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livingstone.nicholas.red.black.tree;


/**
 *
 * @author nicholas
 * @param <T>
 */

enum Color{
    RED, BLACK
}

public class RedBlackTree<T extends Comparable<T>> {
    class Node{
        Color color; 
        T data;
        Node left; 
        Node right; 
        Node parent;
        
        
        public Node(){
            color = Color.BLACK;
            left = right = parent = null; 
        }
        
        public Node(T _data){
            this.data = _data;
        }
        
        public Node(T _data, Color _color){
            this.data = _data; 
            this.color = _color; 
        }
    }
    
    private Node root;  //root node
    final private Node nilT = new Node();
    
    public RedBlackTree(T _headData){
        root = new Node(_headData);
    }
    
    public RedBlackTree(){
        root = nilT; 
    }
    
    public boolean isEmpty(){
        return root == nilT; 
    }
    
    public Node searchFor(Node z, T _data){
        if(z == nilT || _data == z.data)
            return z;
        if(_data.compareTo(z.data) < 0)
            return searchFor(z.left, _data);
        else
            return searchFor(z.right, _data);
    }
    
    private int treeHeight(Node z){
        if(z == null)
            return 0; 
        
        int leftHeight = treeHeight(z.left);
        int rightHeight = treeHeight(z.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    private Node treeMin(Node x){
        while(x.left != null)
            x = x.left;
        return x; 
    }
    
    private Node treeSuccessor(Node x){
        Node y; 
        if(x.right != null)
            return treeMin(x.right);
        y = x.parent;
        while(y != null && x== y.right){
            x = y; 
            y = y.parent;
        }
        return y; 
    }
    
    private void left_rotate(Node _x){
        Node y = _x.right; 
        _x.right = y.left;
        if(y.left != nilT)
            y.left.parent = _x;
        y.parent = _x.parent;
        if(_x.parent == nilT)
            root = y; 
        else if(_x == _x.parent.left)
            _x.parent.left = y; 
        else
            _x.parent.right = y;
        y.left = _x; 
        _x.parent = y;
    }
    
    private void right_rotate(Node _y){
        Node x = _y.left;
        _y.left = x.right; 
        if(x.right != null)
            x.right.parent = _y; 
        x.parent = _y.parent;
        if(_y.parent == null)
            root = x;
        else if(_y == _y.parent.right)
            _y.parent.right = x;
        else
            _y.parent.left = x; 
        x.right = _y; 
        _y.parent = x;
    }
    
    private void insertFix(Node z){
        Node y;
        while(z.parent.color == Color.RED && z.parent != null){
            if(z.parent == z.parent.parent.left){
                y = z.parent.parent.right;
                if(y.color == Color.RED){
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED; 
                    z = z.parent.parent;
                }
                else{
                    if(z == z.parent.right){
                        z = z.parent;
                        left_rotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    right_rotate(z.parent.parent);
                    
                }
            }
            else if(z.parent == z.parent.parent.right){
                y = z.parent.parent.left;
                if(y.color == Color.RED){
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED; 
                    z = z.parent.parent;
                }
                else{
                    if(z == z.parent.left){
                        z = z.parent;
                        right_rotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    left_rotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }
    
    private void deleteFix(Node x){
        Node w; 
        while(x != root && x.color == Color.BLACK){
            if(x == x.parent.left){
                w = x.parent.right;
                if(x.color == Color.RED){
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    left_rotate(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == Color.BLACK && w.right.color == Color.BLACK){
                    w.color = Color.RED;
                    x = x.parent;
                }
                else if(w.right.color == Color.BLACK){
                    w.left.color = Color.BLACK;
                    w.color = Color.RED;
                    right_rotate(w);
                    w = x.parent.right;
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    left_rotate(x.parent);
                    x = root;
                }
            }
            else{
                w = x.parent.left;
                if(x.color == Color.RED){
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    right_rotate(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == Color.BLACK && w.left.color == Color.BLACK){
                    w.color = Color.RED;
                    x = x.parent;
                }
                else if(w.left.color == Color.BLACK){
                    w.right.color = Color.BLACK;
                    w.color = Color.RED;
                    left_rotate(w);
                    w = x.parent.left;
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    right_rotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }
            
    public void insert(T _data){
        Node y = nilT;
        Node x = root; 
        Node z = new Node(_data);
        
        while(x != nilT){
            y = x; 
            if(z.data.compareTo(x.data) < 0)
                x = x.left;
            else
                x = x.right; 
        }
        z.parent = y;
        if(y == nilT)
            root = z;
        else if(z.data.compareTo(y.data) < 0)
            y.left = z;
        else
            y.right = z;
        z.left = nilT;
        z.right = nilT; 
        z.color = Color.RED;
        insertFix(z);
    }
    
    public T delete(Node z){
        Node y, x; 
        if(z.left == nilT || z.right == nilT)
            y = z;
        else
            y = treeSuccessor(z);
        if(y.left != nilT)
            x = y.left;
        else
            x = y.right; 
        x.parent = y.parent;
        if(y.parent == nilT)
            root = x; 
        else if(y.equals(y.parent.left))
            y.parent.left = x; 
        else
            y.parent.right = x;
        if(!y.equals(z))
            z.data = y.data;
        if(y.color == Color.BLACK)
            deleteFix(x);
        return y.data;
    }
    
    public void inorder_traverse_az(Node z){
        if (z == nilT) {
            return;
        }
        inorder_traverse_az(z.left);
        System.out.print(z.data + " ");
        inorder_traverse_az(z.right);
    }
    
    public void inorder_traverse_za(Node z){
        if (z == nilT) {
            return;
        }
        inorder_traverse_za(z.right);
        System.out.print(z.data + " ");
        inorder_traverse_za(z.left);
    }
    
    public void display_level_order(Node z){
        int height = treeHeight(z);
        
        for(int i = 1; i < height; i++){
            System.out.print("Level "  + (i-1) + ": ");
            display_single_level(z, i);
            System.out.println();
        }
    }
    
    private void display_single_level(Node z, int lvl){
        
        if (z == null)
            return;
        if (lvl == 1 && z.data != null) {
            System.out.print(z.data + " ");
        } 
        else if(lvl > 1) {
            display_single_level(z.left, lvl - 1);
            display_single_level(z.right, lvl - 1);
        }
    }
    
    public Node getRoot(){
        return root; 
    }
}

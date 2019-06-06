/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livingstone.nicholas.red.black.tree;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LivingstoneNicholasRedBlackTree {

    
    public static void main(String[] args) {
        Scanner kbInput = new Scanner(System.in);
        RedBlackTree<Integer> myTree = new RedBlackTree<Integer>();
        int choice; int input; 
        do{
            System.out.println("1. Insert Item");
            System.out.println("2. Delete Item");
            System.out.println("3. Print in order");
            System.out.println("4. Print reverse order");
            System.out.println("5. Print Level Order");
            System.out.println("0. Quit");
            System.out.print("Enter choice:");
            try{
                choice = kbInput.nextInt();
            }catch(InputMismatchException e){
                choice = -9999; 
            }
            switch(choice){
                case 1:
                    System.out.print("Enter a number to input:");
                    input = kbInput.nextInt();
                    myTree.insert(input);
                    break;
                case 2:
                    System.out.print("Enter a number to delete:");
                    input = kbInput.nextInt();
                    System.out.println(myTree.delete(myTree.searchFor(myTree.getRoot(), input)) + " Deleted from tree.");
                    break;
                case 3:
                    if(myTree.isEmpty()){
                        System.out.println("Tree is empty");
                        break;
                    }
                    System.out.print("Elements in order: ");
                    myTree.inorder_traverse_az(myTree.getRoot());
                    System.out.println();
                    break;
                case 4:
                    if(myTree.isEmpty()){
                        System.out.println("Tree is empty");
                        break;
                    }
                    System.out.print("Elements in reverse order: ");
                    myTree.inorder_traverse_za(myTree.getRoot());
                    System.out.println();
                    break;
                case 5:
                    if(myTree.isEmpty()){
                        System.out.println("Tree is empty");
                        break;
                    }
                    System.out.println("Elements in level order: ");
                    myTree.display_level_order(myTree.getRoot());
                    System.out.println("");
                    break;
                case 0:
                    System.out.println("Program terminating...");
                    System.exit(0);
                default:
                    System.err.println("Error: Invalid input");
            }
        }while(true); 
    }
    
}

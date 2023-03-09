package assn04;
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    /*
    This is a basic example of how to create a BST and add values to it.
    You should add more examples and use this class to debug your code
    */
    BST<Integer> bst = new NonEmptyBST<Integer>(78);
    bst = bst.insert(31);
    bst = bst.insert(13);
//    bst = bst.insert(10);
//    bst = bst.insert(12);
//    bst = bst.insert(38);
//    bst = bst.insert(40);
//    bst = bst.insert(39);
//    bst = bst.insert(47);
//    bst = bst.insert(84);

    bst.remove(13);

//    System.out.println(bst.getElement());
//    System.out.println(bst.getLeft().getElement());
//    System.out.println(bst.getLeft().getLeft().getElement());
//    System.out.println(bst.getLeft().getLeft().getLeft().getElement());
//    System.out.println(bst.getLeft().getLeft().getLeft().getRight().getElement());
//
//    System.out.println(bst.getLeft().getRight().getElement());
//    System.out.println(bst.getLeft().getRight().getRight().getElement());
//    System.out.println(bst.getLeft().getRight().getRight().getLeft().getElement());
//    System.out.println(bst.getLeft().getRight().getRight().getRight().getElement());;

//    BST<Integer> newBst = bst.remove(47);


  }




}

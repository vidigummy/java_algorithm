package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BinarySearchTree5639 {
    static class Node{
        int value;
        Node leftChild;
        Node rightChild;

        public Node(int value){
            this.value = value;
            leftChild = null;
            rightChild = null;
        }
        public Node(int value, Node leftChild, Node rightChild){
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
        public void insert(int v){
            if(v < this.value){
                if(this.leftChild == null){
                    this.leftChild = new Node(v);
                }else{
                    this.leftChild.insert(v);
                }
            }else{
                if(this.rightChild == null){
                    this.rightChild = new Node(v);
                }else{
                    this.rightChild.insert(v);
                }
            }
        }
    }
    public static void postOrder(Node nowNode){
        if(nowNode == null){
            return ;
        }
        postOrder(nowNode.leftChild);
        postOrder(nowNode.rightChild);
        System.out.println(nowNode.value);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));
        while(true){
            String input = br.readLine();
            if(input == null ||  input.equals("")){
                break;
            }
            root.insert(Integer.parseInt(input));
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huffman;

/**
 *
 * @author fahmy
 */
public class Node {
    private String value;
    private String code;
    private int frequecny;
    private Node right,left,parent;
    
    Node(String v,int f){
        value = v;
        frequecny = f;
        right = left = parent = null;
    }
    
    public void left(Node l){left = l;}
    public void right(Node r){right = r;}
    public void parent(Node p){parent = p;}
    public void value(String s){value = s;}
    public void frequency(int f){frequecny = f;}
    public void code(String c){code=c;}
    
    
    public Node left(){return left;}
    public Node right(){return right;}
    public Node parent(){return parent;}
    public String value(){return value;}
    public int frequency(){return frequecny;}
    public String code(){return code;}
    
}



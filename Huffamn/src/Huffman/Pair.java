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
public class Pair {
    private int first , second;
    
    Pair(int a , int b){
        first = a;
        second = b;
    }

    public int first(){return first;}
    public int second(){return second;}
    
    public void first(int a){first = a;}
    public void second(int b){second=b;}
    
}



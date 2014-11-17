/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huffman;

import java.util.ArrayList;

/**
 *
 * @author fahmy
 */
public class HuffmanCoding {
    public Code compress(String s){
        Code ret = new Code();//this will be returned (coding,table)
        String coding = "";//will hold the final string to be delivered 
        ArrayList<String> table;//will hold the final table
        
        String unique = TextProcessor.getDifferentChars(s);
        ArrayList<Node> tree = new ArrayList<>();
        ArrayList<Node> activeNodes = new ArrayList<>();
        for(char c: unique.toCharArray()){
            Node temp = 
                    new Node(String.valueOf(c),TextProcessor.frequency(s, c));
            tree.add(temp);
            activeNodes.add(temp);
        }
        
        while(activeNodes.size() != 1){
            Pair minimum = getMinTwo(activeNodes);
            Node m1 = activeNodes.get(minimum.first());
            Node m2 = activeNodes.get(minimum.second());
            
            String concat = m1.value()+m2.value();
            int freq = m1.frequency()+m2.frequency();
            Node parent = new Node(concat, freq);
            
            parent.right(m1);
            parent.left(m2);
            m1.parent(parent);
            m2.parent(parent);
            
            activeNodes.remove(m1);
            activeNodes.remove(m2);
            activeNodes.add(parent);
            tree.add(parent);
        }
        
        generateCodings("" , activeNodes.get(0));
        
        for(int i = 0 ; i < tree.size(); i++){
            Node curr = tree.get(i);
            if(curr.value().length() == 1){
                ret.addToTable(curr);
            }
        }
        ret.constructCode(s);
        
        return ret;
    }
    
    public String decompress(Code c){
        String ret = "";
        for(int i = 0 ; i < c.code().length(); ){
            int t = i+1;
            String match = c.code().substring(i, t);
            int found = -1;
            while(true){
                found = c.inTable(match);
                if(found != -1)break;
                match = c.code().substring(i, ++t);
            }
            ret += String.valueOf((char)found);
            i = t;
        }
        return ret;
    }
    
    
    private void generateCodings(String code,Node curr){
        if(curr == null)return;
        curr.code(code);
        generateCodings(code+"0", curr.right());
        generateCodings(code+"1", curr.left());
    }

    private Pair getMinTwo(ArrayList<Node> active){
        int idx1=0 , idx2=1;
        for(int i = 2; i < active.size(); i++){
            int f1 = active.get(idx1).frequency();
            int f2 = active.get(idx2).frequency();
            int f = active.get(i).frequency();
            if(f < f1){
                idx2 = idx1;
                idx1 = i;
            }else if(f < f2){
                idx2 = i;
            }
        }
        return new Pair(idx1,idx2);
    }
}




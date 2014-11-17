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
public class Code {
    private String code;
    private ArrayList<String> table;
    
    Code(){
        table = new ArrayList<>();
        code = new String();
        for(int i = 0 ; i < 128 ; i++){
            table.add("-");
        }
    }
    
    public void addToTable(Node n){
        int idx = n.value().charAt(0);
        table.set(idx, n.code());
    }
    public void constructCode(String s){
        for(int i = 0 ; i < s.length(); i++){
            int idx = s.charAt(i);
            code += table.get(idx);
        }
    }
    public int inTable(String s){
        for(int i = 0 ; i < 128 ; i++){
            if(table.get(i).equals(s))return i;
        }
        return -1;
    }
    public String printableTable(){
        String t = "";
        for(int i = 0 ; i < table.size(); i++){
            t += String.valueOf(i) + " > " + table.get(i) + "\n";
        }
        return t;
    }
    public void loadTable(String t){
        String[] l = t.split("\n");
        for(int i = 0 ; i < l.length; i++){
            int y = l[i].indexOf(">");
            table.set(i, l[i].substring(y+2, l[i].length()));
        }
    }
    
    public void code(String s){code = s;}
    
    public String code(){return code;}
    public ArrayList<String> table(){return table;}
    
}

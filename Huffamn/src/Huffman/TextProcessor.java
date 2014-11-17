/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huffman;

import com.sun.xml.internal.ws.util.StringUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fahmy
 */
public class TextProcessor {
    
    public static String getDifferentChars(String s){
        String ret = "";
        Set<Character> set = new HashSet();
        for(char c: s.toCharArray()){
            set.add(c);
        }
        for(char c: set){
            ret += c;
        }
        return ret;
    }
    
    public static int frequency(String s,char c){
        int count = 0;
        for(char i: s.toCharArray()){
            if(i == c)count++;
        }
        return count;
    }
    
}

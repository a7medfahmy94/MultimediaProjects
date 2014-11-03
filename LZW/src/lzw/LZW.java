/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import java.util.Vector;

/**
 *
 * @author fahmy
 */
public class LZW {
    public Vector<String> dictionary = new Vector<String>();
    
    public LZW(){
        initDictionary();
    }
    
    public void initDictionary(){
        dictionary.clear();
        for(int i = 0 ; i < 128 ; i++){
            dictionary.add(String.valueOf((char)i));
        }
    }
    
    public String compress(String message){
        initDictionary();
        String ret = "";
        for(int i = 0 ; i < message.length();i++){
            int j = i;
            int temp = -1;
            boolean found = true;
            String subject = new String();
            for( ;j < message.length(); j++){
                found = false;
                subject = message.substring(i,j+1);
                for(int k = 0 ; k < dictionary.size(); k++){
                    if(subject.equals(dictionary.get(k))){
                        temp = k;
                        found = true;
                        break;
                    }
                }
                if(!found)break;
            }
            if(!found)
                dictionary.add(subject);
            ret += String.valueOf(temp) + ",";
            i = j-1;
        }
        return ret.substring(0, ret.length()-1);
    }
    
    public String decompress(String message){
        initDictionary();
        String ret = "";
        String[] codes = message.split(",");
        String pre = "" , cur = "";
        for(int i = 0 ; i < codes.length ; i++){
            int current_index = Integer.valueOf(codes[i]);
            if(current_index == dictionary.size()){
                dictionary.add(pre + pre.charAt(0));
            }
            cur = dictionary.get(current_index);
            ret += cur;
            if(pre.length() > 0){
                dictionary.add(pre + cur.charAt(0));
            }
            pre = cur;
        }
        return ret;
    }
    
}

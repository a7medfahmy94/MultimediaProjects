/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz77;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author fahmy
 */
public class LZ77 {
    
    public String compress(String toBeCompressed){
        int length = toBeCompressed.length();
        ArrayList<Tag> ret = new ArrayList<>();
        Tag tempTag , charTag;
        for(int i = 0 ; i < length; i++){//main loop ->
            charTag = new Tag(0,0,toBeCompressed.charAt(i)); 
            for(int j = i -1 ; j >= 0 ; j--){//get best match <-
                if(toBeCompressed.charAt(i) == toBeCompressed.charAt(j)){
                    int jj = j+1 , ii = i+1;
                    for(; ii < length; ii++,jj++){//match jj-> ii->
                        char a = toBeCompressed.charAt(ii) ;
                        char b = toBeCompressed.charAt(jj) ;
                        if(b != a)
                            break;
                    }
                    int back = i - j , forward = jj - j;
                    char n = NULL;
                    if(ii < length-1)n = toBeCompressed.charAt(ii);
                    charTag = maxTag(charTag , new Tag(back,forward,n));
                }
            }
            i = i + charTag.getStepForward();
            ret.add(charTag);
        }
        return arrayListToString(ret);
    }
    public String decompress(String toBeDecompressed){
        String ret = "";
        ArrayList<Tag> tags = stringToArrayList(toBeDecompressed);
        for(int i = 0 ; i < tags.size(); i++){
            Tag tmp = tags.get(i);
            int steps = tmp.getStepForward();
            int j = ret.length() - tmp.getStepBackward();
            for(;steps > 0 ;j++,steps--){
                ret += ret.charAt(j);
            }
            if(tmp.getNextChar() != NULL)
            ret += tmp.getNextChar();
        }
        return ret;
    }
    
    
    private ArrayList<Tag> stringToArrayList(String str){// [<>,<>]
        ArrayList<Tag> ret = new ArrayList<>();
        str = str.substring(1, str.length()-1);
        ArrayList<String> arrStr = new ArrayList<>();
        for(int i = 0 ; i < str.length() ; i++){
            String tmp = "";
            if(str.charAt(i) == '<'){
                while(i < str.length() && str.charAt(i) != '>')
                    tmp += str.charAt(i++);
                tmp += ">";
                arrStr.add(tmp);
            }
        }
        
        for(int i = 0 ; i < arrStr.size(); i++){
            ret.add(new Tag(arrStr.get(i)));
            System.out.println(new Tag(arrStr.get(i)));
        }
        return ret;
    }
    private String arrayListToString(ArrayList<Tag> e){
        return e.toString();
    }
    private Tag maxTag(Tag a , Tag b){
        if(a.getStepForward() > b.getStepForward())return a;
        else if(b.getStepForward() > a.getStepForward())return b;
        else if(a.getStepBackward() < b.getStepBackward())return a;
        return b;
    }
    
    public static char NULL = '!';
}

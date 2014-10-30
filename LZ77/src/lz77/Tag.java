/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz77;


import java.util.regex.*;

/**
 *
 * @author fahmy
 */
public class Tag {
    private int stepBackward , stepForward;
    private char nextChar;
    
    public int getStepBackward(){return stepBackward;}
    public int getStepForward(){return stepForward;}
    public char getNextChar(){return nextChar;}
    
    public void setStepBackward(int s){stepBackward=s;}
    public void setStepforward(int s){stepForward=s;}
    public void setNextChar(char s){nextChar=s;}

    Tag(){}
    Tag(int backward,int forward,char next){
        stepBackward = backward;
        stepForward = forward;
        nextChar = next;
    }
    Tag(String tag){// <int,int,char>
        Pattern p = Pattern.compile("<(\\d+),(\\d+),(.)>");
        Matcher m = p.matcher(tag);
        if(m.matches()){
            stepBackward = Integer.parseInt(m.group(1));
            stepForward = Integer.parseInt(m.group(2));
            nextChar = m.group(3).charAt(0);
        }else{
            
            System.out.println("cant match " + tag);
        }
    }
    public String toString(){
        return String.format("<%d,%d,%c>", stepBackward , stepForward, nextChar);
    }
}

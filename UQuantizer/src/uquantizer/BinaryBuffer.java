/*
 * A simple implementation over BitSet that supports returning a string
 * to be wrote on a file with a simple header indicating number of bits
 * and when that string is retrieved it can be used to get back original bits
 * ...note that bits are written from right to left (RTL)
 */
package uquantizer;

import java.util.BitSet;

/**
 *
 * @author fahmy
 */

public class BinaryBuffer {
    //BitSet responsible for storing single bits
    private BitSet buffer; 
    //This will always point to the next index to be written
    private int nextIndex = 0; 
    
    public BinaryBuffer(){
        buffer = new  BitSet();
    }
    
    /*
    This Constructor is used when reading a file containing a BinaryBuffer string
    that was produced by a previous call to toString() method
    */
    public BinaryBuffer(String base) throws Exception{
        buffer = new BitSet();
        try{
            int sp = base.indexOf("\n");
            int numBits = Integer.parseInt(base.substring(0,sp));
            base = base.substring(sp+1);
            for(int i = 0 ; i < base.length(); i++){
                this.add((byte)base.charAt(i));
            }
            nextIndex = numBits;
        }catch(Exception e){
            throw new Exception("Not supported format");
        }
    }
    //Copy Constructor
    public BinaryBuffer(BinaryBuffer b){
        buffer = new BitSet();
        for(int i = 0 ; i < b.length(); i++){
            this.add(b.get(i));
        }
    }
    
    //Add a single bit
    public void add(boolean value){
        this.buffer.set(nextIndex,value);
        nextIndex++;
    }
    //Add a byte
    public void add(byte b){
        for(int i = 0; i <= 7 ; i++){
            if(((1<<i)&b) > 0){
                this.add(true);
            }else{
                this.add(false);
            }
        }
    }
    //Adds the first n bits from the right of the int
    public void add(int b,int n){
        for(int i = 0 ; i < n ; i++){
            this.add(((1<<i)&b)>0);
        }
    }
    //Add some number of bytes
    public void add(byte[] bytes){
        for(int i = 0;i < bytes.length; i++){
            this.add(bytes[i]);
        }
    }
    
    //This method returns non-readble string representing the bits currently
    //being stored in the buffer (0 -> nextIndex)
    //use it only when you're planning on initializing another BinaryBuffer
    //object using the String returned
    //An example would be:
    /*
    
    
    BinaryBuffer b = new BinaryBuffer();
    b.add(true);
    //some more additions
    String digest = b.toString();
    //some work, maybe write digest to a file and read it back later
    BinaryBuffer newBuffer = new BinaryBuffer(digest);
    
    
    */
    
    public String toString(){
        String ret = String.valueOf(nextIndex) + "\n";
        int newSize = ((nextIndex+7)/8);
        for(int i = 0; newSize > 0;newSize-- ){
            byte fill = 0;
            for(int j = 0 ; j <= 7;j++){
                boolean res = false;
                if(i<nextIndex){res=buffer.get(i++);}
                if(res){
                 fill |= (1 << j);   
                }
            }
            ret += (char)fill;
        }
        return ret;
    }
    
    //CAUTION: returns false if idx is out of range
    public boolean get(int idx){
        if(idx < nextIndex)return buffer.get(idx);
        return false;
    }
    //returns length-bits wrapped in an int padded by zeroes to the left
    //starting from idx
    public int get(int idx,int length){
        int ret = 0;
        length = Math.min(length,32);
        for(int i = 0; i < length; i++){
            if(this.get(idx)){
                ret |= (1 << i);
            }
            idx++;
        }
        return ret;
    }
    //Actual length under concern
    public int length(){return nextIndex;}
    //Pops off last inserted bit
    public void pop(){nextIndex--;}
    
    //used for debugging purposes only
    public void print(){
        System.out.println("====Start====");
        for(int i = nextIndex-1 ; i >= 0 ; i--){
            System.out.println(i + " " +buffer.get(i));
        }
        System.out.println("====End====");
    }
    
}



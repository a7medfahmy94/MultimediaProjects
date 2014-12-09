/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uquantizer;

/**
 *
 * @author fahmy
 */
public class UQantizer {
    private static int numberOfBits   = 3;
    private static int quantizationStep = 32;
    private static int midPoint       = 16;
    
    public static void initNumberOfBits(int n){
        if(n > 8 || n <= 0)n = 8;
        numberOfBits = n;
        quantizationStep = (int)(256/Math.pow(2,n));
        midPoint = quantizationStep/2;
    }
    public static void initQuantizationStep(int n){
        if(n >= 128 || n < 1)n = 1;
        quantizationStep = n;
        int tmp = 256/n;
        numberOfBits = (int)(Math.log(tmp)/Math.log(2));
        midPoint = quantizationStep/2;
    }
    
    
    public static BinaryBuffer compress(int[][] pixels){
        BinaryBuffer binaryBuffer = new BinaryBuffer();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int pixel = pixels[i][j];
                int code = pixel / quantizationStep;
                binaryBuffer.add(code,numberOfBits);
            }
        }
        return binaryBuffer;
    }
    
    public static int[][] decompress(BinaryBuffer compressedPixels){
        int length = (int)Math.sqrt(compressedPixels.length()/numberOfBits);
        int[][] image = new int[length][length];
        for (int i = 0,bitsIdx=0; i < length; i++) {
            for (int j = 0; j < length; j++,bitsIdx+=numberOfBits) {
                int code = compressedPixels.get(bitsIdx,numberOfBits);
                image[i][j] = quantizationStep*code;
                image[i][j] += midPoint;
            }
        }
        return image;
    }
    
}

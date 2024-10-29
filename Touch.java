package com.mycompany.touch;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Touch {
    
    public String touch(String s){
        File file = new File(s);
        
        try{
            
            file.createNewFile();
            return("File created");
            
        }
        
        catch(IOException e){
            return("Error");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the name of the new file");
        
        Touch command = new Touch();
        System.out.println(command.touch(input.next()));
        
    }
}

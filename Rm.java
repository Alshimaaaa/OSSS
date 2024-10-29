package com.mycompany.rm;

import java.io.File;
import java.util.Scanner;

public class Rm {

    public String rm(String s){
        File file = new File(s);
        
        if( file.delete()){
            return ("Delete Successfully");
        }
        
        else{
            return ("Failed");
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Rm command = new Rm();
        
        System.out.println("Enter the name of the file or folder");
        System.out.println(command.rm(input.next()));
       
}
}

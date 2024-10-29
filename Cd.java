package com.mycompany.cd;

import java.io.File;
import java.util.Scanner;

public class Cd {
    
    public String cd(String s){
        File directory = new File(s);
        if(directory.exists() && directory.isDirectory()){
           return ("The current directory : " + directory.getAbsolutePath());
        }
        else{
            return("Directory does not exist");
        }
    }

  public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Cd command = new Cd();
        
        System.out.println("Enter the name of the folder");
        System.out.println(command.cd(input.next()));
        
       
    }
}

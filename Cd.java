package com.mycompany.cd;

import java.io.File;
import java.util.Scanner;

public class Cd {
    
    private String currentDirectory;
    
    public Cd() {
        this.currentDirectory = System.getProperty("user.dir");
    }
    
    public String cd(String s){
        File directory = new File(currentDirectory,s);
        if(directory.exists() && directory.isDirectory()){
           currentDirectory = directory.getAbsolutePath();
           return currentDirectory;
        }
        else{
            return("Directory does not exist");
        }
    }

  public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Cd command = new Cd();
        
        System.out.println("Enter the name of the folder");
        while(true){
            String in = input.next();
            if("exit".equals(in)){
                break;
            }
            System.out.println(command.cd(in));
        }
    }
}

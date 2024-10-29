package first;
//If the user enters a wrong command or bad parameters (invalid path, file instead of
//directory in certain commands, etc.), the program should print some error
//messages without terminating.

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.nio.file.DirectoryNotEmptyException;
//import java.nio.file.Files;
//import java.nio.file.NoSuchFileException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.*;



public class msh {
	static String[] lsA() {
		File directory = new File(System.getProperty("user.dir"));
		File[] files = directory.listFiles();
		String[] fileNames = new String[files.length];
		for(int i=files.length-1;i>=0; i--) {
				fileNames[i]=files[i].getName();
		}
		return fileNames;
	}
	static String[] ls() {
		File directory = new File(System.getProperty("user.dir"));
		File[] files = directory.listFiles();
		String[] fileNames = new String[files.length];
		for(int i=0;i<files.length; i++) {
			if(!files[i].isHidden()){
				fileNames[i]=files[i].getName();
			}
		}
		List<String> values = new ArrayList<String>();
	      for(String file: fileNames) {
	         if(file != null) { 
	            values.add(file);
	         }
	      }
	      String[] withoutNull = values.toArray(new String[values.size()]);
		  return withoutNull;
	}
    static String[] lsR() {
	    File directory = new File(System.getProperty("user.dir"));
		File[] files = directory.listFiles();
		String[] fileNames = new String[files.length];
		int j=0;
		for(int i=files.length-1;i>=0; i--) {
			if(!files[i].isHidden()){
				fileNames[j]=files[i].getName();
			}
			j++;
		}
		List<String> values = new ArrayList<String>();
	      for(String file: fileNames) {
	         if(file != null) { 
	            values.add(file);
	         }
	      }
	      String[] withoutNull = values.toArray(new String[values.size()]);
		  return withoutNull;
  }


	public static void main(String[] args) throws IOException {
		//cd ..? , is it okay that if i was in a certain directory that i can't see it like i can't move anything from the inside to it?
		Scanner scan = new Scanner(System.in);
		boolean f=true;
		while(f) {
			System.out.println(System.getProperty("user.dir"));
			String commandLine = scan.nextLine();
			String[] parts = commandLine.split(" ");
			String command = parts[0];
			if(command.equals("exit")) {
				f=false;
				break;
			}
			else if(command.equals("help")) {
				System.out.println("lista bel commands");
			}

			else if(command.equals("ls")){
				if(parts.length>2) {
					System.out.println("ls command takes only one or two parameters");
				}
				else if(parts.length==2)
				{
					if(parts[1].equals("-r")){
						String[] files = lsR();
						for(int i=0;i<files.length; i++) {
								System.out.print(files[i]);
								System.out.print("  ");}
						}
					
					else if(parts[1].equals("-a")){
						String[] file = lsA();
						for(int i=0;i<file.length; i++) {
							System.out.print(file[i]);
							System.out.print("  ");
						}
					}
					else {
						System.out.println("Wrong second attribute");
					}
				}
				else {
					String[] files = ls();
					for(int i=0;i<files.length; i++) {
							System.out.print(files[i]);
							System.out.print("  ");}
					
				}
				System.out.print('\n');
			}
		}
	
}}


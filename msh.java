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
	static void cd(String part) {
			File directory = new File(System.getProperty("user.dir"),part);
			if(directory.isDirectory()) {
				System.setProperty("user.dir", directory.getAbsolutePath());
			}
			else {
				System.out.println("No such directory");
			}

	}
//	static String pwd() {
//
//		String x= System.getProperty("user.dir");
//		String[] blaa = x.split("Desktop");//Not sure
//		return (blaa[1]);
//	}
	static String[] lsA() {// ls -a command
		File directory = new File(System.getProperty("user.dir"));
		File[] files = directory.listFiles();//take all the files in the current directory
		String[] fileNames = new String[files.length];
		for(int i=files.length-1;i>=0; i--) {
				fileNames[i]=files[i].getName();//put it in a string array as strings not files
		}
		return fileNames;
	}
	static String[] ls() {
		File directory = new File(System.getProperty("user.dir"));
		File[] files = directory.listFiles();//take all the files in the current directory
		String[] fileNames = new String[files.length];
		for(int i=0;i<files.length; i++) {//put them in a string array except for the hidden files
			if(!files[i].isHidden()){//so the hidden files will be null
				fileNames[i]=files[i].getName();
			}
		}
		List<String> values = new ArrayList<String>();
	      for(String file: fileNames) {
	         if(file != null) { 
	            values.add(file);//put them in a list but without the null values
	         }
	      }
	      String[] withoutNull = values.toArray(new String[values.size()]);//put them back in a string array
		  return withoutNull;
	}
    static String[] lsR() {
	    File directory = new File(System.getProperty("user.dir"));
		File[] files = directory.listFiles();//take all the files in the current directory
		String[] fileNames = new String[files.length];
		int j=0;
		for(int i=files.length-1;i>=0; i--) {//put them in a string array except for the hidden files
			if(!files[i].isHidden()){//put them in a string array except for the hidden files
				fileNames[j]=files[i].getName();
			}
			j++;//put the data in reverse order
		}
		List<String> values = new ArrayList<String>();
	      for(String file: fileNames) {
	         if(file != null) { 
	            values.add(file);//put them in a list but without the null values
	         }
	      }
	      String[] withoutNull = values.toArray(new String[values.size()]);
		  return withoutNull;//put them back in a string array
  }


	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		boolean f=true;
		while(f) {
			System.out.println(System.getProperty("user.dir"));
			String commandLine = scan.nextLine();//the whole input
			String[] parts = commandLine.split(" ");//split it to words
			String command = parts[0];//the first word is the command
			if(command.equals("exit")) {
				f=false;
				break;
			}
			else if(command.equals("help")) {
				System.out.println("lista bel commands");
			}
//			else if(command.equals("pwd")) {
//				System.out.println(pwd());
//				
//			} 
//			else if(command.equals("mkdir")) {
//				boolean flag=true;
//                for (int i = 1; i < parts.length; i++) {
//                	flag=false;
//                	Path path = Paths.get(System.getProperty("user.dir"), parts[i]);
//    				//Path path = Paths.get(parts[i]);
//    				Files.createDirectories(path);
//                }
//                if(flag) {
//                	 System.out.println("mkdir: Missing directory name.");
//                }
//			}
//			else if(command.equals("rmdir")) {
//				boolean flag=true;
//                for (int i = 1; i < parts.length; i++) {
//                	flag=false;
//    				Path path = Paths.get(System.getProperty("user.dir"),parts[i]);
//    				File file = new File(System.getProperty("user.dir"),parts[i]);
//    				if(file.isFile()) {
//    					System.out.println("cannot remove it's a file");
//    					continue;
//    				}
//    				try {
//    				Files.delete(path);
//    				}
//    				catch (NoSuchFileException e) {
//    		            System.out.println(
//    		                    "No such directory exists");
//    		            }
//    				catch (DirectoryNotEmptyException e) {
//    		            System.out.println("failed to remove, this directory is not empty");
//    		        }
//                }
//                if(flag) {
//                	 System.out.println("rmdir: Missing directory name.");
//                }
//			}
//			else if(command.equals("touch")) {
//				boolean flag=true;
//                for (int i = 1; i < parts.length; i++) {
//                	flag=false;
//    				Path path = Paths.get(System.getProperty("user.dir"),parts[i]);
//    				try {
//    				Files.createFile(path);
//    				}
//    				catch (FileAlreadyExistsException e) {
//    					System.out.println("File already exist");
//    				}
//    				catch (IOException e) {
//    					System.out.println("failed to add file no such path");
//    				}
//                }
//                if(flag) {
//                	 System.out.println("touch: Missing file name.");
//                }
//			}
//			else if(command.equals("rm")) {
//				boolean flag=true;
//                for (int i = 1; i < parts.length; i++) {
//                	flag=false;
//    				Path path = Paths.get(System.getProperty("user.dir"),parts[i]);
//    				File file = new File(System.getProperty("user.dir"),parts[i]);
//    				if(file.isDirectory()) {
//    					System.out.println("cannot remove it's a directory");
//    					continue;
//    				}
//    				try {
//    				Files.delete(path);
//    				}
//    				catch (NoSuchFileException e) {
//    		            System.out.println(
//    		                    "No such file exists");
//    		            }
//    				catch (DirectoryNotEmptyException e) {
//    		            System.out.println("failed to remove, this file is not empty");
//    		        }
//                }
//                if(flag) {
//                	 System.out.println("rm: Missing file name.");
//                }
//			}
//			else if(command.equals("mv")) {
//				if(parts.length>=3) {
//					File file1 = new File(System.getProperty("user.dir"),parts[1]);
//					File file2 = new File(System.getProperty("user.dir"),parts[parts.length-1]);
//					if((!file2.isDirectory()) && parts.length==3) {//renaming file 
//						if(!file1.isFile()) {
//							System.out.println("ERROR: If you're trying to rename The first input has to be a file but if you're trying to move second input has to be directory");
//							continue;
//						}
//					    Path fileToMovePath = Paths.get(System.getProperty("user.dir"),parts[1]);
//					    Path targetPath = Paths.get(System.getProperty("user.dir"),parts[2]);
//					    Files.move(fileToMovePath,targetPath);
//
//					    }
//					else if(file1.isDirectory() && file2.isDirectory() && parts.length==3){//moving directory to another one
//						Path fileToMovePath = Paths.get(System.getProperty("user.dir"),parts[1]);
//						Path targetPath = Paths.get(System.getProperty("user.dir"),parts[2],fileToMovePath.getFileName().toString());
//		                Files.move(fileToMovePath, targetPath);}
//					else {//moving
//						System.out.println("D");
//
//						for (int i = 1; i < (parts.length-1); i++) {
//							Path fileToMovePath = Paths.get(System.getProperty("user.dir"),parts[i]);
//							Path targetPath = Paths.get(System.getProperty("user.dir"),parts[parts.length-1],fileToMovePath.getFileName().toString());
//							try {
//			                    Files.move(fileToMovePath, targetPath);}
//							catch (NoSuchFileException e) {
//		    		            System.out.println(
//		    		                    "wrong inputs");
//		    		            }
//			                }
//						}
//			      }
//				else {
//					System.out.println("mv: Need two parameters");
//				}
//				}
			else if(command.equals("cd")) {
				if(parts.length<2) {
					System.out.println("Missing directory name");
				}
				else {
				cd(parts[1]);}
			}
			else if(command.equals("ls")){
				if(parts.length>2) {//handling more than two inputs error
					System.out.println("ls command takes only one or two parameters");
				}
				else if(parts.length==2)
				{
					if(parts[1].equals("-r")){
						String[] files = lsR();//calling the function then printing the data
						for(int i=0;i<files.length; i++) {
								System.out.print(files[i]);
								System.out.print("  ");}
						}
					
					else if(parts[1].equals("-a")){
						String[] file = lsA();//calling the function then printing the data
						for(int i=0;i<file.length; i++) {
							System.out.print(file[i]);
							System.out.print("  ");
						}
					}
					else {
						System.out.println("Wrong second attribute");//neither -r or -a 
					}
				}
				else {
					String[] files = ls();//calling the function then printing the data
					for(int i=0;i<files.length; i++) {
							System.out.print(files[i]);
							System.out.print("  ");}
					
				}
				System.out.print('\n');
			}
		}
	
}}


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.io.*;

public class Commands
{
    public static void pwd()
    {
        System.out.println(System.getProperty("user.dir"));
    }

    public static void cd(String directory)
    {
        File dir = new File(directory);
        if(dir.exists() && dir.isDirectory())
            System.setProperty("user.dir", dir.getAbsolutePath());
        else
            System.out.println("Directory does not exist");
    }

    public static void ls()
    {
        File dir = new File(System.getProperty("user.dir"));
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files)
        {
            if (!file.isHidden())
            {
                System.out.print(file.getName());
                System.out.print("  ");
            }
        }
        System.out.print('\n');
    }

    public static void lsA()
    {
        File dir = new File(System.getProperty("user.dir"));
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files)
        {
            System.out.print(file.getName());
            System.out.print("  ");
        }
        System.out.print('\n');
    }

    public static void lsR()
    {
        File dir = new File(System.getProperty("user.dir"));
        File[] files = dir.listFiles();
        assert files != null;
        for (int i = files.length-1; i > -1; i--)
        {
            if (!files[i].isHidden())
            {
                System.out.print(files[i].getName());
                System.out.print("  ");
            }
        }
        System.out.print('\n');
    }

    public static void mkdir(String[] directories)
    {
        for (int i = 1; i < directories.length; i++)
        {
            File dir = new File(directories[i]);
            if (!dir.exists())
            {
                if (dir.mkdirs())
                    System.out.println("Directory created successfully");
                else
                    System.out.println("Error creating directory");
            }
            else
                System.out.println("Directory already exists");
        }
    }

    public static void rmdir(String[] directories)
    {
        for (int i = 1; i < directories.length; i++)
        {
            File dir = new File(directories[i]);
            if (dir.exists())
            {
                if (dir.delete())
                    System.out.println("Directory deleted successfully");
                else
                    System.out.println("Error deleting directory");
            }
            else
                System.out.println("Directory does not exist");
        }
    }

    public static void touch(String[] files) throws IOException
    {
        for (int i = 1; i < files.length; i++)
        {
            File file = new File(files[i]);
            if (file.createNewFile())
                System.out.println("File created successfully");
            else
                System.out.println("Could not create file");
        }
    }

    public static void mv(String[] files) throws IOException
    {
        Path movedFile = Paths.get(files[1]);
        Path targetFile = Paths.get(files[2]);
        if (!Files.exists(movedFile))
        {
            System.out.println("File does not exist");
        }
        else if (!Files.exists(targetFile))
        {
            Files.move(movedFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully");
        }
        else
        {
            Files.move(movedFile, targetFile);
            System.out.println("File moved successfully");
        }
    }

    public static void rm(String[] files)
    {
        for (int i = 1; i < files.length; i++)
        {
            File file = new File(files[i]);
            if (file.exists())
            {
                if (file.delete())
                    System.out.println("Directory deleted successfully");
                else
                    System.out.println("Error deleting directory");
            }
            else
                System.out.println("Directory does not exist");
        }
    }

    public static void cat(String[] files)
    {
        for (int i = 1; i < files.length; i++)
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(files[i])))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    System.out.println(line);
                }
            }
            catch (IOException e)
            {
                System.out.println("Could not read file: " + e.getMessage());
            }
            System.out.print('\n');
        }
    }

    public static void catWrite(String file)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file)))
        {
            String line;
            System.out.println("Enter text to write to " + file + " (press Ctrl+C , or type 'EOF' to end)");
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine() && !(line = sc.nextLine()).equals("EOF"))
            {
                bw.write(line);
                bw.newLine();
            }
            sc.close();
        }
        catch (IOException e)
        {
            System.out.println("Could not write into file: " + e.getMessage());
        }
    }

    public static void catAppend(String file)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true)))
        {
            String line;
            System.out.println("Enter text to write to " + file + " (press Ctrl+C , or type 'EOF' to end)");
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine() && !(line = sc.nextLine()).equals("EOF"))
            {
                bw.write(line);
                bw.newLine();
            }
            sc.close();
        }
        catch (IOException e)
        {
            System.out.println("Could not write into file: " + e.getMessage());
        }
    }

    public static void help()
    {
        System.out.println("Available Commands:");
        System.out.println("pwd:                 Prints the current directory.");
        System.out.println("cd <directory>:      Changes the current directory.");
        System.out.println("ls, ls -a, ls -r:    Lists files in the directory.");
        System.out.println("mkdir <directory>:   Creates a new directory.");
        System.out.println("rmdir <directory>:   Removes an empty directory.");
        System.out.println("touch <file>:        Creates an empty file.");
        System.out.println("mv <src> <dest>:     Moves/renames a file or directory.");
        System.out.println("rm <file>:           Removes a file.");
        System.out.println("cat <file>:          Displays contents of a file.");
        System.out.println("> <file>:            Redirects output to a file (overwrite).");
        System.out.println(">> <file>:           Redirects output to a file (append).");
        System.out.println("|:                   Pipe operator.");
        System.out.println("help:                Displays this help message.");
        System.out.println("exit:                Exits the CLI.");
    }
}

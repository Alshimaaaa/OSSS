import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Commands
{
    public static String pwd()
    {
        return System.getProperty("user.dir");
    }

    public static void cd(String directory)
    {
        File dir = new File(System.getProperty("user.dir"), directory);
        if (dir.exists() && dir.isDirectory())
            System.setProperty("user.dir", dir.getAbsolutePath());
        else
            System.out.println("Directory does not exist");
    }

    public static String[] ls()
    {
        File dir = new File(System.getProperty("user.dir"));
        File[] files = dir.listFiles();
        assert files != null;
        List <String> fileNames = new ArrayList <>();
        for (File file : files)
        {
            if (!file.isHidden())
                fileNames.add(file.getName());
        }
        return fileNames.toArray(new String[0]);
    }

    public static String[] lsA()
    {
        File dir = new File(System.getProperty("user.dir"));
        File[] files = dir.listFiles();
        assert files != null;
        String[] fileNames = new String[files.length];
        for(int i = 0; i < files.length; i++)
        {
            fileNames[i] = files[i].getName();
        }
        return fileNames;
    }

    public static String[] lsR()
    {
        File dir = new File(System.getProperty("user.dir"));
        File[] files = dir.listFiles();
        assert files != null;
        List <String> fileNames = new ArrayList <>();
        for (int i = files.length-1; i > -1; i--)
        {
            if (!files[i].isHidden())
                fileNames.add(files[i].getName());
        }
        return fileNames.toArray(new String[0]);
    }

    public static void mkdir(String[] directories)
    {
        for (int i = 1; i < directories.length; i++)
        {
            File dir = new File(System.getProperty("user.dir"), directories[i]);
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
            File dir = new File(System.getProperty("user.dir"), directories[i]);
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
            File file = new File(System.getProperty("user.dir"), files[i]);
            if (file.createNewFile())
                System.out.println("File created successfully");
            else
                System.out.println("Could not create file");
        }
    }

    public static void mv(String[] files)
    {
        Path movedFile = Paths.get(System.getProperty("user.dir"), files[1]);
        Path targetFile = Paths.get(System.getProperty("user.dir"), files[2]);
        if (!Files.exists(movedFile))
        {
            System.out.println("File does not exist");
            return;
        }
        if (!targetFile.isAbsolute())
        {
            targetFile = Paths.get(Paths.get("").toAbsolutePath().toString(), files[2]);
        }
        if (Files.isDirectory(targetFile))
        {
            targetFile = targetFile.resolve(movedFile.getFileName());
        }
        try
        {
            Files.move(movedFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully");
        }
        catch (IOException e)
        {
            System.out.println("Error moving file");
        }
    }

    public static void rm(String[] files)
    {
        for (int i = 1; i < files.length; i++)
        {
            File file = new File(System.getProperty("user.dir"), files[i]);
            if (file.exists())
            {
                if (file.delete())
                    System.out.println("File deleted successfully");
                else
                    System.out.println("Error deleting file");
            }
            else
                System.out.println("File does not exist");
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
        }
    }

    public static void catWrite(String file, Scanner sc)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file)))
        {
            String line;
            System.out.println("Enter text to write to " + file + " (press Ctrl+C , or type 'EOF' to end)");
            while (sc.hasNextLine() && !(line = sc.nextLine()).equals("EOF"))
            {
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Could not write into file: " + e.getMessage());
        }
    }

    public static void catWrite(String file)
    {
        catWrite(file, new Scanner(System.in));
    }

    public static void catAppend(String file, Scanner sc)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true)))
        {
            String line;
            System.out.println("Enter text to write to " + file + " (press Ctrl+C , or type 'EOF' to end)");
            while (sc.hasNextLine() && !(line = sc.nextLine()).equals("EOF"))
            {
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Could not write into file: " + e.getMessage());
        }
    }

    public static void catAppend(String file)
    {
        catAppend(file, new Scanner(System.in));
    }

    public static void pipe(String[] commands)
    {
        commands[0] = commands[0].trim();
        String[] firstCommand = commands[0].split(" ");
        commands[1] = commands[1].trim();
        String[] secondCommand = commands[1].split(" ");
        String[] output = new String[0];
        if (firstCommand.length == 1)
        {
            output = ls();
        }
        else if (firstCommand[1].equals("-a"))
        {
            output = lsA();
        }
        else if (firstCommand[1].equals("-r"))
        {
            output = lsR();
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        for (String line : output)
        {
            System.out.println(line);
        }
        System.out.println("EOF");
        Scanner sc = new Scanner(outContent.toString());
        if (secondCommand[1].equals(">"))
        {
            catWrite(secondCommand[2], sc);
        }
        else if (secondCommand[1].equals(">>"))
        {
            catAppend(secondCommand[2], sc);
        }
        System.setOut(originalOut);
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

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        Path movedFile = Paths.get(files[1]);
        Path targetFile = Paths.get(files[2]);
        if (!Files.exists(movedFile))
        {
            System.out.println("File does not exist");
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

    public static String[] sort(String file)
    {
        List <String> lines = new ArrayList <>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
            Collections.sort(lines);
            return lines.toArray(new String[0]);
        }
        catch (IOException e)
        {
            return new String[]{"Could not sort the file: " + e.getMessage()};
        }
    }

    public static void uniq(String[] lines)
    {
        List <String> uniqueLines = new ArrayList <>();
        for (int i = 0; i < lines.length; i++)
        {
            if (i > 0 && !lines[i].equals(lines[i - 1]))
                uniqueLines.add(lines[i]);
            else if (i == 0)
                uniqueLines.add(lines[i]);
        }
        for (String line : uniqueLines)
            System.out.println(line);
    }

    public static void pipe(String[] commands)
    {
        String firstCommand = commands[0].trim();
        String secondCommand = commands[1].trim();
        if (firstCommand.startsWith("sort"))
        {
            String fileName = firstCommand.split(" ")[1];
            String[] sortedOutput = Commands.sort(fileName);
            if (secondCommand.equals("uniq"))
            {
                Commands.uniq(sortedOutput);
            }
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
        System.out.println("sort <file>:         Sorts the contents of a file.");
        System.out.println("uniq:                Deletes adjacent duplicates in a file.");
        System.out.println("|:                   Pipe operator.");
        System.out.println("help:                Displays this help message.");
        System.out.println("exit:                Exits the CLI.");
    }
}

import java.io.IOException;
import java.util.Scanner;

public class CLI
{
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        String commandLine;
        boolean quit = false;

        while (!quit)
        {
            System.out.print('\n');
            System.out.print(System.getProperty("user.dir"));
            System.out.print("> ");
            commandLine = sc.nextLine();
            String[] command = commandLine.split(" ");
            switch (command[0])
            {
                case "pwd":
                    Commands.pwd();
                    break;

                case "cd":
                    if (command.length > 1)
                        Commands.cd(command[1]);
                    else
                        System.out.println("No directory specified");
                    break;

                case "ls":
                    if (command.length == 1)
                    {
                        Commands.ls();
                    }
                    else
                    {
                        if (command[1].equals("-a"))
                            Commands.lsA();
                        else if (command[1].equals("-r"))
                            Commands.lsR();
                        else
                            System.out.println("Unknown command");
                    }
                    break;

                case "mkdir":
                    Commands.mkdir(command);
                    break;

                case "rmdir":
                    Commands.rmdir(command);
                    break;

                case "touch":
                    Commands.touch(command);
                    break;

                case "mv":
                    if (command.length > 2)
                        Commands.mv(command);
                    else
                        System.out.println("No destination file specified");
                    break;

                case "rm":
                    Commands.rm(command);
                    break;

                case "cat":
                    if (command[2].equals(">"))
                        Commands.catWrite(command[1]);
                    else if (command[2].equals(">>"))
                        Commands.catAppend(command[1]);
                    else
                        Commands.cat(command);
                    break;

                case "help":
                    Commands.help();
                    break;

                case "exit":
                    quit = true;
                    break;

                default:
                    System.out.println("Unknown command");
            }
        }
    }
}

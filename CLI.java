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
            if (commandLine.contains("|"))
            {
                String[] command = commandLine.split("\\|");
                Commands.pipe(command);
            }
            else
            {
                String[] command = commandLine.split(" ");
                switch (command[0])
                {
                    case "pwd":
                        System.out.println(Commands.pwd());
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
                            String[] files = Commands.ls();
                            for (String file : files)
                            {
                                System.out.print(file);
                                System.out.print("  ");
                            }
                            System.out.print('\n');
                        }
                        else
                        {
                            if (command[1].equals("-a"))
                            {
                                String[] files = Commands.lsA();
                                for (String file : files)
                                {
                                    System.out.print(file);
                                    System.out.print("  ");
                                }
                                System.out.print('\n');
                            }
                            else if (command[1].equals("-r"))
                            {
                                String[] files = Commands.lsR();
                                for (String file : files)
                                {
                                    System.out.print(file);
                                    System.out.print("  ");
                                }
                                System.out.print('\n');
                            }
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
                        if (command.length > 2 && command[1].equals(">"))
                            Commands.catWrite(command[2]);
                        else if (command.length > 2 && command[1].equals(">>"))
                            Commands.catAppend(command[2]);
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
}

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestingCLI
{
    @Test
    public void testPwd()
    {
        String expectedDir = System.getProperty("user.dir");
        assertEquals(expectedDir, Commands.pwd());
    }

    @Test
    public void testCd()
    {
        String originalDir = System.getProperty("user.dir");
        File tempDir = new File("testDir");
        tempDir.mkdir();
        Commands.cd("testDir");
        assertEquals(tempDir.getAbsolutePath(), System.getProperty("user.dir"));
        tempDir.delete();
        System.setProperty("user.dir", originalDir);
    }

    @Test
    public void testLs()
    {
        String[] files = Commands.ls();
        assertTrue(files.length > 0);
    }

    @Test
    public void testLsA()
    {
        String[] files = Commands.lsA();
        assertTrue(files.length > 0);
    }

    @Test
    public void testLsR()
    {
        String[] files = Commands.lsR();
        assertTrue(files.length > 0);
    }

    @Test
    public void testMkdir()
    {
        String[] directories = {"mkdir", "testDir"};
        Commands.mkdir(directories);
        File dir = new File("testDir");
        assertTrue(dir.exists() && dir.isDirectory());
    }

    @Test
    public void testRmdir()
    {
        String[] directories = {"rmdir", "testDir"};
        Commands.rmdir(directories);
        File dir = new File("testDir");
        assertFalse(dir.exists());
    }

    @Test
    public void testTouch () throws IOException
    {
        String[] files = {"touch", "testFile.txt"};
        Commands.touch(files);
        File file = new File("testFile.txt");
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    public void testMv() throws IOException
    {
        Path targetDir = Files.createTempDirectory("targetDir");
        Path sourceFile = Files.createTempFile("source", ".txt");
        Path targetFile = targetDir.resolve(sourceFile.getFileName());
        String[] files = {"mv", sourceFile.toString(), targetDir.toString()};
        Commands.mv(files);
        assertFalse(Files.exists(sourceFile));
        assertTrue(Files.exists(targetFile));
    }

    @Test
    public void testRm()
    {
        String[] directories = {"rm", "testFile"};
        Commands.rm(directories);
        File dir = new File("testFile");
        assertFalse(dir.exists());
    }

    @Test
    public void testCat() throws IOException
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        try (FileWriter writer = new FileWriter(tempFile))
        {
            writer.write("to be checked if it exists in the output of cat function");
        }
        var outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String[] files = {"cat", tempFile.getAbsolutePath()};
        Commands.cat(files);
        assertTrue(outputStream.toString().contains("to be checked if it exists in the output of cat function\r\n"));
        tempFile.delete();
    }

    @Test
    public void testCatWrite() throws IOException
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        String simulatedInput = "Line1\nLine2\nEOF\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Commands.catWrite(tempFile.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile)))
        {
            assertEquals("Line1", reader.readLine());
            assertEquals("Line2", reader.readLine());
        }
        tempFile.delete();
    }

    @Test
    public void testCatAppend() throws IOException
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        try (FileWriter writer = new FileWriter(tempFile))
        {
            writer.write("Existing line\n");
        }
        String simulatedInput = "New line1\nNew line2\nEOF\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Commands.catAppend(tempFile.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile)))
        {
            assertEquals("Existing line", reader.readLine());
            assertEquals("New line1", reader.readLine());
            assertEquals("New line2", reader.readLine());
        }
        tempFile.delete();
    }

    @Test
    public void testPipe()
    {
        
    }
}

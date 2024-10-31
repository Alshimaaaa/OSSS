package first;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class Testing {
	 @Test
	    public void testPwd()
	    {
	        String expectedDir = System.getProperty("user.dir");
	        assertEquals(expectedDir, Commands.pwd());
	    }

	    @Test
	    public void testCd()
	    {
	        File tempDir = new File("testDir");
	        tempDir.mkdir();
	        Commands.cd("testDir");
	        assertEquals(tempDir.getAbsolutePath(), System.getProperty("user.dir"));
	        tempDir.delete();
	    }
	
	@Test
	public void testLs() {// ls command test
		msh cli = new msh();//add your code file here
		//cli.cd("f/fd");//you can enter another directory that you have if you want.
		String[] files= cli.ls();
		int x=files.length;
		//assertTrue(x==1);//you can check the exact number of data you have in this cd.
		assertTrue(x>0);
	}
	
	@Test
	public void testLsA() {// ls -a command test
		msh cli = new msh();//add your code file here
		String[] files= cli.lsA();
		int x=files.length;
		assertTrue(x>0);
	}
	
	@Test
	public void testLsR() {// ls -r command test
		msh cli = new msh();//add your code file here
		//cli.cd("f/fd");//you can enter another directory that you have if you want
		String[] files= cli.lsR();
		int x=files.length;
		assertTrue(x>0);
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
	     public void TouchTest (){
	    
	        Touch t = new Touch();
	        assertEquals("File created",t.touch(""));
	}
	    
	    
	    @Test
	     public void RmTest (){
	         Rm t = new Rm();
	         assertEquals("Delete Successfully",t.rm(""));
	     }
	    
	    @Test
	    public void testCatWithNoExistingFile(){
	        String NonExistingFile = "NonExistingFile.txt";
	        CommandLine.cat(NonExistingFile);
	    }
	    
	    
	    @Test
	    public void testCat() throws IOException {

	        //sample content
	        try (FileWriter writer = new FileWriter(tempFile)) {
	            writer.write("to be checked if it exists in the output of cat function\n");
	        }

	        // making the console output reassigned and stored in a variable to check if it contains
	        // the output of the function
	        var outputStream = new ByteArrayOutputStream();// ==> to store the bytes that will come from output(Console)
	        System.setOut(new PrintStream(outputStream));

	        // Call the cat method
	        CommandLine.cat(tempFile.getAbsolutePath());

	        // Verify the output
	        assertTrue(outputStream.toString().contains("to be checked if it exists in the output of cat function"));
	    }

	    @Test
	    public void testCatWrite() throws IOException {
	        // Redirect System.in and System.out to simulate user input and capture output
	        String simulatedInput = "Line1\nLine2\nEOF\n";
	        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

	        CommandLine.catWrite(tempFile.getAbsolutePath());

	        // Verify that content was written to the file
	        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
	            assertEquals("Line1", reader.readLine());
	            assertEquals("Line2", reader.readLine());
	        }
	    }

	    @Test
	    public void testCatAppend() throws IOException {
	        // Initial content
	        try (FileWriter writer = new FileWriter(tempFile)) {
	            writer.write("Existing line\n");
	        }

	        // Redirect System.in to simulate user input
	        String simulatedInput = "New line1\nNew line2\nEOF\n";
	        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

	        CommandLine.catAppend(tempFile.getAbsolutePath());

	        // Verify appended content
	        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
	            assertEquals("Existing line", reader.readLine());
	            assertEquals("New line1", reader.readLine());
	            assertEquals("New line2", reader.readLine());
	        }
	    }
	    
}

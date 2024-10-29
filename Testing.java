package first;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class Testing {
	
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
}

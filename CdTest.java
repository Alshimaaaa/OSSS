import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.cd.Cd;

public class CdTest {
 
     @Test
    public void testCd (){
        Cd t =  new Cd();
        String expectedPath = "C:\\Third Level\\Operating System\\Assignment1\\cd\\Tests";
        assertEquals(expectedPath,t.cd(""));
        
}
}

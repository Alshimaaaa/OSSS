import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.rm.Rm;

public class RmTest {
    
     @Test
     public void Test (){
         Rm t = new Rm();
         assertEquals("Delete Successfully",t.rm(""));
     }
}

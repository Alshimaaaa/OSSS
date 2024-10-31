import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.touch.Touch;

public class TouchTest {
    
     @Test
     public void Test (){
    
        Touch t = new Touch();
        assertEquals("File created",t.touch(""));
}
}

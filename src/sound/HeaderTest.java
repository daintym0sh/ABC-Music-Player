package sound;

import java.io.IOException;
import java.util.List;
/**
 * HeaderTest tests the functionality of the Header class
 * 
 * @author John
 */
public class HeaderTest {
    public static void main (String args[]) throws IOException{
        List<String> List = new Lexer("piece1.abc").searchHeader();
        int BPM = new Header(List).getBPM();
        System.out.println(BPM);
    }
}

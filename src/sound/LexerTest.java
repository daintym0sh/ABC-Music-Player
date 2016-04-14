package sound;
import java.io.IOException;
import java.util.List;
/**
 * LexerTest tests the functionality of the Lexer class
 * 
 * @author John
 */
public class LexerTest {
    
    public static void main (String args[]) throws IOException {
        List<String> List = new Lexer("piece1.abc").searchBody();
        List<String> List2 = new Lexer("piece1.abc").searchHeader();
        System.out.println(List.toString());
        System.out.println(List2.toString());
    }
}

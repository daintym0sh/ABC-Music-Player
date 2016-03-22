package sound;
import java.io.IOException;

public class LexerTest {
    
    public static void main (String args[]) throws IOException {
        Lexer lex = new Lexer("piece1.abc");
        lex.Search();
    }
}

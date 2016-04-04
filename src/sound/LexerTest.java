package sound;
import java.io.IOException;
import java.util.List;

public class LexerTest {
    
    public static void main (String args[]) throws IOException {
        Lexer lex = new Lexer("piece1.abc");
        List<String> List = lex.searchAdd();
        System.out.println(List.get(6));
    }
}

package sound;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jfugue.player.Player;

public class JFugueTest {
  public static void main(String[] args) throws IOException {
    Lexer lex = new Lexer("piece1.abc");
    List<String> List = lex.searchAdd();
    Player player = new Player();
  }
}
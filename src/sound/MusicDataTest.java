package sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class MusicDataTest {
    public static void main(String args[]) throws IOException, MidiUnavailableException, InvalidMidiDataException{
        List<String> List = new Lexer("piece1.abc").searchBody();
        ArrayList<Token> tokens = new MusicData(List).loadData();
        System.out.println(tokens.get(10).getValue());
    }
}

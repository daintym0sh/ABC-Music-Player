package sound;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
/**
 * ParserTest tests the functionality of the Parser class
 * 
 * @author John
 */
public class ParserTest {
    public static void main(String[] args) throws IOException, MidiUnavailableException, InvalidMidiDataException{
        Parser p = new Parser("piece1.abc", 12);
        p.parse().play();
     }
}

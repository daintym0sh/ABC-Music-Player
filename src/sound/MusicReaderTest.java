package sound;

import java.io.IOException;
/**
 * MusicReaderTest tests the functionality of the MusicReader class
 * 
 * @author John
 */
public class MusicReaderTest {
    
    public static void main (String args[]) throws IOException {
        
        MusicReader sample = new MusicReader("piece1.abc");
        System.out.println(sample.readMusic());
    }

}

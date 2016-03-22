package sound;

import java.io.IOException;

public class MusicReaderTest {
    
    public static void main (String args[]) throws IOException {
        
        MusicReader sample = new MusicReader("piece1.abc");
        System.out.println(sample.readMusic());
    }

}

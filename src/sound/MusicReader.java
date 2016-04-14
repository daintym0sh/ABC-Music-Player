package sound;
import java.io.*;
/**
 * MusicReader reads in a String corresponding to a .abc file, reads the .abc file, and builds a String out of it
 * 
 * @author John
 */
public class MusicReader {
    
    private String file;
    private String path = "sample_abc/";
    private String line = null;
    private StringBuilder total = new StringBuilder();
    /**
     * Constructs and initializes MusicReader objects
     * 
     * @param file - a String that corresponds to the name of an .abc file, including the file extension
     */
    public MusicReader(String file){
        this.file = file;
    }
    /**
     * Reads in a String corresponding to an .abc file and returns a String built from this .abc file
     * 
     * @return a String built from the .abc file with the same name as the String file
     * @throws IOException
     */
    public String readMusic() throws IOException {
        path = path + file;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            total.append(line+"\n");
        }
        reader.close();
        return total.toString();
    }
}
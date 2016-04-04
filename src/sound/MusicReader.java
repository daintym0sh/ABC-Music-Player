package sound;
import java.io.*;

public class MusicReader {
    
    private String file;
    private String path = "sample_abc/";
    private String line = null;
    private StringBuilder total = new StringBuilder();
    
    public MusicReader(String file){
        this.file = file;
    }
    
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
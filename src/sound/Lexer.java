package sound;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;

public class Lexer {
    
    private String input;
    private String note = "(__?|\\^\\^?|=)?[A-Ga-g]['+,+]*([0-9]*/[0-9]*|[0-9]+)?";
    private String searchValue = 
            "^(X\\s*:\\s*[0-9]+\n)" + "|" + //Field number
            "(T\\s*:[^\n]+\n)" + "|" + //Field title
            "(C\\s*:[^\n]+\n)" + "|" + //Composer name
            "(M\\s*:\\s*C+\n|[0-9]+/[0-9]+\n)" + "|" + //Meter
            "(Q\\s*:\\s*[0-9]+\n)" + "|" + //Tempo
            "(L\\s*:\\s*[0-9]+/[0-9]+\n)" + "|" + //Default length
            "(K\\s*:\\s*[a-gA-G][#b]?m?\n)" + "|" + //Key
            "(z([0-9]*/[0-9]*|[0-9]+)?)" + "|" + //Rest
            "((" + note + ")\\s*)" + "|" + //Note
            "(\\[(" + note + ")+\\]([0-9]*/[0-9]*)?\\s*)" + "|" + //Chord
            "(\\(3(" + note + "){3}\\s*)" //Triplet
            ;
    private Pattern pattern;
    private Matcher matcher; 
    
    public Lexer (String input) {
        this.input = input;
    }
    
    public void Search() throws IOException {
        String music = new MusicReader(input).readMusic();
        pattern = Pattern.compile(searchValue);
        matcher = pattern.matcher(music);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}

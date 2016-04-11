package sound;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import java.io.IOException;

public class Lexer {
    private String input;
    private String music;
    private String note = "(__?|\\^\\^?|=)?[A-Ga-g]['+,+]*([0-9]*/[0-9]*|[0-9]+)?";
    private String searchValue = 
            "^(X\\s*:\\s*[0-9]+\n)" + "|" + //Field number
            "(T\\s*:[^\n]+\n)" + "|" + //Field title
            "(C\\s*:[^\n]+\n)" + "|" + //Composer name
            "(M\\s*:\\s*C+\n|[0-9]+/[0-9]+\n)" + "|" + //Meter
            "(Q\\s*:\\s*[0-9]+\n)" + "|" + //Tempo
            "(L\\s*:\\s*[0-9]+/[0-9]+\n)" + "|" + //Default Note Length
            "(K\\s*:\\s*[a-gA-G][#b]?m?+\n)" + "|" + //Key
            "(z([0-9]*/[0-9]*|[0-9]+)?)" + "|" + //Rest
            "((" + note + ")\\s*)" + "|" + //Note
            "(\\[(" + note + ")+\\]([0-9]*/[0-9]*)?\\s*)" + "|" + //Chord
            "(\\(3(" + note + "){3}\\s*)" //Triplet
            ;
    private String headerValue = 
            "^(X\\s*:\\s*[0-9]+\n)" + "|" + //Field number
            "(T\\s*:[^\n]+\n)" + "|" + //Field title
            "(C\\s*:[^\n]+\n)" + "|" + //Composer name
            "(M\\s*:\\s*C+\n|[0-9]+/[0-9]+\n)" + "|" + //Meter
            "(Q\\s*:\\s*[0-9]+\n)" + "|" + //Tempo
            "(L\\s*:\\s*[0-9]+/[0-9]+\n)" + "|" + //Default Note Length
            "(K\\s*:\\s*[a-gA-G][#b]?m?\n)"//Key
            ;
    private Pattern pattern;
    private Matcher matcher; 
    private Pattern patternHead;
    private Matcher matcherHead; 
    private List<String> bodyList = new ArrayList<String>();
    private List<String> headerList = new ArrayList<String>();
    
    public Lexer(String input){
        this.input = input;
    }
    
    public List<String> searchHeader() throws IOException{
        music = new MusicReader(input).readMusic();
        pattern = Pattern.compile(headerValue);
        matcher = pattern.matcher(music);
        while (matcher.find()){
            headerList.add(matcher.group());
        }
        return headerList;
    }
    
    public List<String> searchBody() throws IOException{
        music = new MusicReader(input).readMusic();
        pattern = Pattern.compile(searchValue);
        patternHead = Pattern.compile(headerValue);
        matcher = pattern.matcher(music);
        while(matcher.find()){
            matcherHead = patternHead.matcher(matcher.group());
            if(matcherHead.find() == false){
                bodyList.add(matcher.group());
            }
        }
        return bodyList;
    }
}

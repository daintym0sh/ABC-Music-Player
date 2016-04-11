package sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MusicData{
    
    private Token token;
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private List<String> bodyList = new ArrayList<String>();
    private Iterator<String> it;
    private Pattern pattern;
    private Matcher matcher;
    private Type type;
    private String value;
    
    public MusicData(List<String> bodyList){
        this.bodyList = bodyList;
    }
    
    public ArrayList<Token> loadData(){
        it = bodyList.iterator();
        while(it.hasNext()){
            String next = it.next();
            if(next.charAt(0)=='('){
                pattern = Pattern.compile("([A-Ga-g]+)");
                matcher = pattern.matcher(next);
                while (matcher.find()) {
                    value = matcher.group();
                }
                type = Type.Triplet;
            }
            else{
                value = next;
                type = Type.Note;
            }
            
            token = new Token(type,value);
            tokens.add(token);
        }
        return tokens;
    }
}

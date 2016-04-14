package sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * MusicData reads in a List<String> of body terms from an .abc file and adds each term to an ArrayList of Token objects
 * 
 * @author John
 */
public class MusicData{
    
    private Token token;
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private List<String> bodyList = new ArrayList<String>();
    private Iterator<String> it;
    private Pattern pattern;
    private Matcher matcher;
    private Type type;
    private String value;
    /**
     * Constructs and initializes MusicData objects.
     * 
     * @param bodyList - a List<String> of body terms from an .abc file
     */
    public MusicData(List<String> bodyList){
        this.bodyList = bodyList;
    }
    /**
     * Makes Token objects out of the elements of the List<String> bodyList
     * 
     * @return an ArrayList of token objects
     */
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

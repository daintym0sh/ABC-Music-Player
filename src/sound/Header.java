package sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Header reads in a List<string> and searches for header terms of an .abc file.
 * 
 * @author John
 */
public class Header {
    
    private int BPM;
    private List<String> piece = new ArrayList<String>();
    private Pattern pattern;
    private Matcher matcher;
    /**
     * Constructs and initializes a Header object.
     * 
     * @param piece - A List<String> that contains individual terms of an .abc file
     */
    public Header(List<String> piece){
        this.piece=piece;
    }
    /**
     * Searches the List<String> piece and returns the BPM value as an integer
     * 
     * @return
     */
    public int getBPM() {
        //Iterator for List<String> piece
        Iterator<String> it = piece.iterator();
        while(it.hasNext()) {
            String next = it.next();
            //Finds the element of piece that starts with a 'Q'
            if (next.charAt(0) == 'Q') {
                //Extracts the BPM value as an integer
                pattern = Pattern.compile("([0-9]+)");
                matcher = pattern.matcher(next);
                while (matcher.find()) {
                    BPM = Integer.parseInt(matcher.group());
                }
            }
        }
        return BPM;
    }
}
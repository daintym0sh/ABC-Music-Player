package sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Header {
    
    private int BPM;
    private List<String> piece = new ArrayList<String>();
    private Pattern pattern;
    private Matcher matcher;
            
    public Header(List<String> piece){
        this.piece=piece;
    }
    
    public int getBPM() {
        Iterator<String> it = piece.iterator();
        while(it.hasNext()) {
            String next = it.next();
            if (next.charAt(0) == 'Q') {
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
package sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
/**
 *Parser constructs a SequencePlayer object based off from an .abc file.
 *
 *Parser takes a String equal to the name of an 'abc' music file and fetches an ArrayList
 *of Token objects to create a SequencePlayer that can play the contents of the .abc file.
 *Parser also takes in an int value equal to the resolution of a tick value in the SequencePlayer.
 * 
 * @author John Meier
 */
public class Parser {

    private ArrayList<Token> tokens;
    private Iterator<Token> it;
    private List<String> list;
    private List<String> head;
    private String piece;
    private int bpm;
    private int resolution;
    private SequencePlayer player;
    private Pattern pattern;
    private Matcher matcher;
    
    /**
     * Constructs and initializes a Parser objects.
     * 
     * @param piece - The name of the 'abc' file to be parsed by the Parser objects
     * @param resolution - The resolution of a tick value in the SequencePlayer
     */
    public Parser(String piece, int resolution){
        this.piece = piece;
        this.resolution = resolution;
    }
    
    /**
     * Adds notes to the SequencePlayer, player, based of the Token objects.
     * 
     * @return a SequencePlayer object that contains playable notes from the .abc file
     * @throws IOException 
     * @throws InvalidMidiDataException 
     * @throws MidiUnavailableException 
     */
    public SequencePlayer parse() throws IOException, MidiUnavailableException, InvalidMidiDataException {
        //List of body terms from the .abc file
        list = new Lexer(piece).searchBody();
        //List of header terms from the .abc file
        head = new Lexer(piece).searchHeader();
        //Fetches BPM value
        bpm = new Header(head).getBPM();
        //List of Token objects
        tokens = new MusicData(list).loadData();
        //SequencePlayer object which will be returned
        player = new SequencePlayer(bpm,resolution);
        //Iterates over the list of Token objects
        it = tokens.iterator();
        //Keeps track of tick location
        int count = 0;
        //The following section loops through the Token objects and adds notes to SequencePlayer player
        while(it.hasNext()){
            Token next = it.next();
            switch(next.getType()){
                case Note:
                    String note = null;
                    String time = null;
                    double res = (double) resolution;
                    double fraction = 1;
                    //Finds note letter and stores value to the variable note
                    pattern = Pattern.compile("[A-Ga-g]");
                    matcher = pattern.matcher(next.getValue());
                    while (matcher.find()){
                        note = matcher.group();
                    }
                    
                    //Finds note length and stores value to variable time;
                    pattern = Pattern.compile("([0-9]*/[0-9]*|[0-9]+)");
                    matcher = pattern.matcher(next.getValue());
                    while (matcher.find()){
                        time = matcher.group();
                        //Processes note length fractions starting with "/"
                        if(time.charAt(0) == '/'){
                            String[] rat = time.split("/");
                            fraction = 1 / Double.parseDouble(rat[1]);
                        }
                        //Processes note length fractions that do not start with "/"
                        else if(time.charAt(0) != '/' && time.contains("/")){
                            String[] rat = time.split("/");
                            fraction = Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
                        }
                    }
                    //Calculates tick length
                    Double d = new Double(res*fraction);
                    int length = d.intValue();
                    //Adds notes to SequencePlayer player
                    if(Character.isLowerCase(note.charAt(0))){
                        player.addNote(new Pitch(note.toUpperCase().charAt(0)).transpose(Pitch.OCTAVE).toMidiNote(), count, count + length);
                    }else{
                        player.addNote(new Pitch(note.toUpperCase().charAt(0)).toMidiNote(), count, count + length);
                    }
                    count = count + length;
                    break;
                case Triplet:
                    ArrayList<String> notes = new ArrayList<String>();
                    //Finds note letter and stores value to ArrayList note
                    pattern = Pattern.compile("[A-Ga-g]");
                    matcher = pattern.matcher(next.getValue());
                    while (matcher.find()){
                        notes.add(matcher.group());
                    }
                    Iterator<String> n = notes.iterator();
                    //Adds notes to SequencePlayer player
                    while(n.hasNext()){
                        String nextNote = n.next();
                        if(Character.isLowerCase(nextNote.charAt(0))){
                            player.addNote(new Pitch(nextNote.toUpperCase().charAt(0)).transpose(Pitch.OCTAVE).toMidiNote(), count, resolution/3);
                        }else{
                            player.addNote(new Pitch(nextNote.toUpperCase().charAt(0)).toMidiNote(), count, resolution/3);
                        }
                        count = count + (resolution/3);
                    }
                    break;
            }
        }
        return player;
    }
}

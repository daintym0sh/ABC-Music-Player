package player;

import java.io.IOException;
import java.util.Scanner;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import sound.Parser;

/**
 * Main entry point of your application.
 */
public class Main {

	/**
	 * Plays the input file using Java MIDI API.
	 * @throws InvalidMidiDataException 
	 * @throws IOException 
	 * @throws MidiUnavailableException 	 * 
	 */
    public static void main(String[] args) throws MidiUnavailableException, IOException, InvalidMidiDataException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter file name with extension: ");
        String musicName = scan.next();
        System.out.println("Enter time step resolution: ");
        int res = scan.nextInt();
        scan.close();
        Parser p = new Parser(musicName, res);
        p.parse().play();
    }
}

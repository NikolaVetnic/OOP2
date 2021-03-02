package teorijske_vezbe.ponavljanje_nabrojivi.midi;

import static org.svetovid.Svetovid.in;
import static org.svetovid.Svetovid.out;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * Program koji od korisnika ucitava naziv instrumenta i niz nota, a zatim svira zadate note
 * koristeci zadati instrument.
 * 
 * @author Dejan Mitrovic
 * @author Ivan Pribela
 */
public class MidiPlayer {

	public static void main(String[] args) throws Exception {

		// Inicijalizacija MIDI sistema
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		MidiChannel channel = synth.getChannels()[0];

		out.print("Instrument and notes, in form of 'instrument note1 note2 note3 ...': ");
		String[] input = in.readLine().split(" ");

		if (input.length < 2) {
			out.println("You need to specify an instrument and at last one note");
			return;
		}

		// Postavi ucitani instrument
		try {
			Instruments i = Instruments.valueOf(input[0].toUpperCase());
			channel.programChange(i.getPatch());
			out.println("Playing notes using instrument " + i);
		} catch (IllegalArgumentException e) {
			out.println("No such instrument - " + input[0]);
			return;
		}

		// Sviraj note
		Notes previous = null;
		for (int i = 1; i < input.length; i++) {
			try {
				Notes n = Notes.valueOf(input[i].toUpperCase());
				out.println("Playing note " + n);

				// Ako smo vec odsvirali neku notu, moramo je "iskljuciti"
				if (previous != null) {
					channel.noteOff(previous.getNoteNum());
				}

				channel.noteOn(n.getNoteNum(), 100); // 100 - volume [0..127]
				previous = n;

				// Sacekaj 1000 milisekundi pre nego sto odsviras sledecu notu
				Thread.sleep(1000);
			} catch (IllegalArgumentException e) {
				out.println("Unrecognized note - " + input[i]);
				return;
			}
		}
	}
}

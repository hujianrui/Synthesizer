package Driver;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import Sources.Source;

public class Sound {

	public static void main(String[] args) throws LineUnavailableException {
		//play();
		//filter;
		mixer();
	}
	
	
	public static void play (int freq) throws LineUnavailableException {
		Clip c = AudioSystem.getClip(); 

		AudioFormat format16 = new AudioFormat(c.getFormat().getSampleRate(), 16, 1, true, false);

		//This is the format that we're following, 44.1KHz mono audio, 16 bits per sample

		Source gen = new SineWave(freq);
		 

		c.open(format16, gen.getAudioClip().getClip(), 0, gen.getAudioClip().getClip().length); //reads data from my byte array to play it

		System.out.println("about to play");

		c.start(); //plays it

		c.loop(2); //plays it 2 more times if desired

		while(c.getFramePosition() < gen.getAudioClip().getSampleCount() || c.isActive() || c.isRunning()){} //makes sure the program doesn't quit before the sound plays

		System.out.println("done");

		c.close();
	}

	public static void filter(int freq, double scale) throws LineUnavailableException {
		Clip c = AudioSystem.getClip(); 

		AudioFormat format16 = new AudioFormat(c.getFormat().getSampleRate(), 16, 1, true, false);

		//This is the format that we're following, 44.1KHz mono audio, 16 bits per sample

		Source tem = new SineWave(freq);
		
		ScaleWave gen = new ScaleWave(tem, scale);
		 

		c.open(format16, gen.getAudioClip().getClip(), 0, gen.getAudioClip().getClip().length); //reads data from my byte array to play it

		System.out.println("about to play");

		c.start(); //plays it

		c.loop(2); //plays it 2 more times if desired

		while(c.getFramePosition() < gen.getAudioClip().getSampleCount() || c.isActive() || c.isRunning()){} //makes sure the program doesn't quit before the sound plays

		System.out.println("done");

		c.close();
	}

	public static void mixer() throws LineUnavailableException {
		Clip c = AudioSystem.getClip(); 

		AudioFormat format16 = new AudioFormat(c.getFormat().getSampleRate(), 16, 1, true, false);

		//This is the format that we're following, 44.1KHz mono audio, 16 bits per sample

		MixWave gen = new MixWave(new SineWave(400));
		
	//	gen.add(new SineWave(900));
		 
		c.open(format16, gen.getAudioClip().getClip(), 0, gen.getAudioClip().getClip().length); //reads data from my byte array to play it

		System.out.println("about to play");

		c.start(); //plays it

		c.loop(2); //plays it 2 more times if desired

		while(c.getFramePosition() < gen.getAudioClip().getSampleCount() || c.isActive() || c.isRunning()){} //makes sure the program doesn't quit before the sound plays

		System.out.println("done");

		c.close();
	}
	
	
	
}



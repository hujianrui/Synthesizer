package Driver;

import AudioClip.AudioClip;
import Sources.Mixer;
import Sources.Source;

public class MixWave implements Mixer{
	
	private Source input;
	
	private double scale;
	
	public MixWave(Source input) {
		connectInput(input);
		this.scale = 0.5;
	}

	@Override
	public AudioClip getAudioClip() {
		return input.getAudioClip();
	}

	@Override
	public void connectInput(Source input) {
		this.input = input;
		
	}

	@Override
	public void add(Source input) {
		scale(this);
		scale(input);
		AudioClip clip = this.getAudioClip();
		for(int i = 0; i<clip.getSampleRate();i++) {
			input.getAudioClip().setSample(i, (int)(clip.getSample(i)+ input.getAudioClip().getSample(i)));
		}
	}
	
	
	public void scale(Source input) {
		AudioClip clip = input.getAudioClip();
		for(int i = 0; i<clip.getSampleRate();i++) {
			clip.setSample(i, (int)(clip.getSample(i)*scale));
		}
	}

}

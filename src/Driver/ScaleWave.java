package Driver;

import AudioClip.AudioClip;
import Sources.Filter;
import Sources.Source;

public class ScaleWave implements Filter{
		
	private Source input;
	private double scale;
	
	public ScaleWave(Source input, double scale) {
		connectInput(input);
		setScale(scale);
	}

	@Override
	public void connectInput(Source input) {
		this.input = input;		
	}

	@Override
	public AudioClip getAudioClip() {
		AudioClip original = input.getAudioClip();
			
		AudioClip newClip = new AudioClip();
			
		for(int i = 0; i<original.getSampleRate();i++) {
			newClip.setSample(i, (int)(original.getSample(i)*scale));
		}	
		return newClip;       
	}
		
	public void setScale(double scale) {
		this.scale  = scale;
	}

}


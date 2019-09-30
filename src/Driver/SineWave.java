package Driver;
import AudioClip.AudioClip;
import Sources.Source;

public class SineWave implements Source{
	private int freq;
	private AudioClip sound;
	
	public SineWave(int freq) {
		this.freq = freq;
		this.sound = new AudioClip();
	}
	
	@Override
	public AudioClip getAudioClip() {
		for(int i = 0; i < sound.getSampleRate(); i++) {
			sound.setSample(i,(int)(Short.MAX_VALUE * Math.PI*this.freq*i/this.sound.getSampleRate()));
		}
		return sound;
	}
	
	public int getFrequency() {
		return freq;
	}
	
	public void setFrequency(int freq) {
		this.freq = freq;
	}

}

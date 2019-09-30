package AudioClip;

public class AudioClip {
	private double duration;
	private int sampleRate;
	private byte[] clip;
	
	public AudioClip() {
		this.duration = 1.0;
		this.sampleRate = 44100;
		clip = new byte[(int)duration*sampleRate*2];
	}
	
	public void setSample(int index, int val) {
		clip[index*2] = (byte) (val) ;
		clip[index*2 +1] = (byte) (val >>8);
	}
	
	public int getSample(int index) {
		int start = clip[index*2];
		int end = clip[index*2 +1] << 8;
		return end | 0xFF & start;		
	}
	
	public double getDuration() {
		return duration;
	}
	
	public int getSampleRate() {
		return sampleRate;
	}
	
	public byte[] getClip() {
		return clip;
	}
	
	public int getSampleCount() {
		return (int) (duration * sampleRate);
	}
}

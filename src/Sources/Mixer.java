package Sources;

public interface Mixer extends Source{
     
	public void connectInput(Source input);
		
	public void add(Source input);

}
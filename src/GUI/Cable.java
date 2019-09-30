package GUI;

import javafx.scene.shape.Line;

public class Cable extends Line{

	private String from;
	private String to;
	
	public Cable() {
		super();
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
}

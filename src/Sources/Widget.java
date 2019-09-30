package Sources;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public interface Widget {
	
	public BorderPane getWidget();
	public Circle getOutputCircle();
	public Circle getInputCircle();
	
}

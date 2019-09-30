package GUI;

import Sources.Widget;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class SineWaveWidget implements Widget{

	private BorderPane sineWave;
	private double dragStartX;
	private double dragStartY;
	private double translateStartX;
	private double translateStartY;
	private FreqSlider freqSlider;
	private Circle Circle;
	
	public SineWaveWidget() {
		sineWave = new BorderPane();
		freqSlider = new FreqSlider();
		
		Label lable = new Label("Sine Wave");
		Circle = new Circle(15);
		
		BorderPane center = new BorderPane();
		center.setCenter(freqSlider.getFreqSlider());
		center.setTop(lable);

		sineWave.setCenter(center);
		sineWave.setRight(Circle);
		sineWave.setStyle("-fx-border-color: black");
		
		center.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
					dragStartX = event.getSceneX();
					dragStartY = event.getSceneY();
					translateStartX = sineWave.getTranslateX();
					translateStartY = sineWave.getTranslateY();
				}
		});
	
		center.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				sineWave.setTranslateX(translateStartX +event.getSceneX() - dragStartX);
				sineWave.setTranslateY(translateStartY +event.getSceneY() - dragStartY);
			}
		});
	}
	
	public FreqSlider getSlider() {
		return freqSlider;
	}
	
	public BorderPane getWidget() {
		return sineWave;
	}
	
	public Circle getOutputCircle() {
		return Circle;
	}
	
	public Circle getInputCircle() {
		return Circle;
	}
	
}

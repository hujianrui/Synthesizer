package GUI;

import Sources.Widget;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FilterWidget implements Widget{
	private BorderPane filter;
	private double dragStartX;
	private double dragStartY;
	private double translateStartX;
	private double translateStartY;
	private VolSlider volSlider;
	private Circle inputCircle;
	private Circle outputCircle;
	
	public FilterWidget() {
		filter = new BorderPane();
		volSlider = new VolSlider();
	
		Label lable = new Label("Filter");
		Circle circleR = new Circle(15);
		Circle circleL = new Circle(15,Color.BLUE);
		inputCircle = circleL;
		outputCircle = circleR;
		
		
		BorderPane center = new BorderPane();
		center.setCenter(volSlider.getVolSlider());
		center.setTop(lable);
		
		filter.setCenter(center);
		filter.setRight(circleR);
		filter.setLeft(circleL);
		filter.setStyle("-fx-border-color: black");
	
		center.setOnMousePressed(new EventHandler<MouseEvent>() {
		
			@Override
			public void handle(MouseEvent event) {
				dragStartX = event.getSceneX();
				dragStartY = event.getSceneY();
				translateStartX = filter.getTranslateX();
				translateStartY = filter.getTranslateY();
			}
		});
	
		center.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				filter.setTranslateX(translateStartX +event.getSceneX() - dragStartX);
				filter.setTranslateY(translateStartY +event.getSceneY() - dragStartY);
			}
		});
	}
	
	public VolSlider getSlider() {
		return volSlider;
	}
	
	public BorderPane getWidget() {
		return filter;
	}
	
	public Circle getInputCircle() {
		return inputCircle;
	}
	
	public Circle getOutputCircle() {
		return outputCircle;
	}
}

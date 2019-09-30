package GUI;


import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import Driver.Sound;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

	private Button sineWave;
	private Button play;
	private Button filter;
	private Button clear;
	private Cable line;
	private ArrayList<Cable> cableList;
	private ArrayList<SineWaveWidget> SWWList;
	private ArrayList<FilterWidget> FWList;
	private boolean speakerCheck;
	private boolean sinewaveCheck;
	private boolean filterCheck;
	

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		
		SWWList = new ArrayList<SineWaveWidget> ();
		FWList = new ArrayList<FilterWidget> ();
		cableList = new ArrayList<Cable> ();
		

		speakerCheck = false;
		sinewaveCheck = false;
		filterCheck = false;
		
		Circle space = new Circle(50,Color.TRANSPARENT);
		Circle speaker = new Circle(30,Color.GREEN);
		
		BorderPane borderPane = new BorderPane();
		Pane pane = new Pane();
		borderPane.setCenter(pane);
		
		stage.setTitle("Sound");

		pane.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				for(int i = 0; i < SWWList.size(); i++) {
					
					Point2D pointerMouse = new Point2D (event.getSceneX(), event.getSceneY());
					Circle checkCricle = SWWList.get(i).getOutputCircle();
					Point2D circleCenter = new Point2D(checkCricle.getCenterX(),checkCricle.getCenterY());
					
					if(checkCricle.contains (checkCricle.sceneToLocal(pointerMouse))) {
					
						line = new Cable();
						line.setStartX(checkCricle.localToScene(circleCenter).getX());
						line.setStartY(checkCricle.localToScene(circleCenter).getY());
						line.setEndX(checkCricle.localToScene(circleCenter).getX());
						line.setEndY(checkCricle.localToScene(circleCenter).getY());
						line.setFrom("SineWave");
						sinewaveCheck = true;
						pane.getChildren().add(line);
					}
				}
				
				for(int i = 0; i < FWList.size(); i++) {
					
					Point2D pointerMouse = new Point2D (event.getSceneX(), event.getSceneY());
					Circle checkCricle = FWList.get(i).getOutputCircle();
					Point2D circleCenter = new Point2D(checkCricle.getCenterX(),checkCricle.getCenterY());
					
					if(checkCricle.contains (checkCricle.sceneToLocal(pointerMouse))) {
					
						line = new Cable();
						line.setStartX(checkCricle.localToScene(circleCenter).getX());
						line.setStartY(checkCricle.localToScene(circleCenter).getY());
						line.setEndX(checkCricle.localToScene(circleCenter).getX());
						line.setEndY(checkCricle.localToScene(circleCenter).getY());
						line.setFrom("Filter");
						filterCheck = true;
						pane.getChildren().add(line);
					}
				}
				
			}
		});
		
		pane.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(line != null) {
				line.setEndX(event.getSceneX());
				line.setEndY(event.getSceneY());
				}
			}
		});
		
		pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				for(int i = 0; i < FWList.size(); i++) {
					
					Point2D pointerMouse = new Point2D (event.getSceneX(), event.getSceneY());
					Circle checkCricle = FWList.get(i).getInputCircle();
					Point2D circleCenter = new Point2D(checkCricle.getCenterX(),checkCricle.getCenterY());
					Point2D speakerCenter = new Point2D(speaker.getCenterX(),speaker.getCenterY());
					
					if(checkCricle.contains (checkCricle.sceneToLocal(pointerMouse))) {
						
						line.setEndX(checkCricle.localToScene(circleCenter).getX());
						line.setEndY(checkCricle.localToScene(circleCenter).getY());
						line.setTo("Filter");
						filterCheck = true;
						cableList.add(line);
						pane.getChildren().remove(line);
						pane.getChildren().add(cableList.get(cableList.size()-1));
					}else if(speaker.contains (speaker.sceneToLocal(pointerMouse))){
						line.setEndX(speaker.localToScene(speakerCenter).getX());
						line.setEndY(speaker.localToScene(speakerCenter).getY());
						line.setTo("Speaker");
						speakerCheck = true;
						cableList.add(line);
						pane.getChildren().remove(line);
						pane.getChildren().add(cableList.get(cableList.size()-1));
					}else {pane.getChildren().remove(line);}
				}
			}
		});
		
		
		play = new Button();
		play.setText("play");
		play.setOnAction(e -> {
			
			if(speakerCheck && sinewaveCheck && filterCheck) {
				try {
					Sound.filter(SWWList.get(0).getSlider().getFreq(), FWList.get(0).getSlider().getScale());
				} catch (LineUnavailableException ex) {
					ex.printStackTrace();
				}
			}
			
			else if(speakerCheck || sinewaveCheck) {
				try {
					Sound.play(SWWList.get(0).getSlider().getFreq());
				} catch (LineUnavailableException ex) {
					ex.printStackTrace();
				}
			}
			
			
		});
		
		sineWave = new Button();
		sineWave.setText("Sine Wave");
		sineWave.setOnAction(e -> {
			SineWaveWidget sineWaveWidget = new SineWaveWidget();
			SWWList.add(sineWaveWidget);
			pane.getChildren().add(sineWaveWidget.getWidget());
		});
		
		filter = new Button();
		filter.setText("Filter");
		filter.setOnAction(e -> {
			FilterWidget filterWidget = new FilterWidget();
			FWList.add(filterWidget);
			pane.getChildren().add(filterWidget.getWidget());
		});
	
		clear = new Button();
		clear.setText("Clear");
		clear.setOnAction(e -> {
			pane.getChildren().clear();
		});
		
		borderPane.setBottom(play);
		VBox list = new VBox();
		list.getChildren().addAll(sineWave,filter,clear,space,speaker);
		borderPane.setRight(list);
		
		
		Scene scene = new Scene(borderPane, 800, 600);
		stage.setScene(scene);
		stage.show();	
	}
	
	
	
}

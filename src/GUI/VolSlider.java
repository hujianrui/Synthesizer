package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class VolSlider {

	private double scale = 1.0;
	private Slider volSlider;
	
	public VolSlider() {
		volSlider = new Slider();
		volSlider.setMin(0);
		volSlider.setMax(2);
		volSlider.setValue(1);
		volSlider.setShowTickLabels(true);
		volSlider.setShowTickMarks(true);
		volSlider.setMajorTickUnit(1);
		volSlider.setMinorTickCount(10);
		volSlider.setBlockIncrement(0.1);

		volSlider.valueProperty().addListener(new ChangeListener<Number>() {
	 
     
		public void changed(ObservableValue<? extends Number> observable,
				Number oldValue, Number newValue) {
    	 			scale = (double) newValue.doubleValue();
			}
		});
	}
	
	public double getScale() {
		return scale;
	}
	
	public Slider getVolSlider() {
		return volSlider;
	}
	
}

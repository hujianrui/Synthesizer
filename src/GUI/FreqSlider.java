package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class FreqSlider {

	private int freq = 440;
	private Slider freqSlider;
	
	public FreqSlider() {
		
		freqSlider = new Slider();
		freqSlider.setMin(0);
		freqSlider.setMax(2000);
		freqSlider.setValue(440);
		freqSlider.setShowTickLabels(true);
		freqSlider.setShowTickMarks(true);
		freqSlider.setMajorTickUnit(1000);
		freqSlider.setMinorTickCount(10);
		freqSlider.setBlockIncrement(100);
	
		freqSlider.valueProperty().addListener(new ChangeListener<Number>() {
		  
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
        	 			freq = (int) newValue.doubleValue();
			}
		});
	}
	
	public int getFreq() {
		return freq;
	}
	
	public Slider getFreqSlider() {
		return freqSlider;
	}
	
}

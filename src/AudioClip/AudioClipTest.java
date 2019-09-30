package AudioClip;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AudioClipTest {

	AudioClip clip = new AudioClip();

	@Test
	void testPositiveSmall() {
		clip.setSample(2, 2);
		assertEquals(2, clip.getSample(2));
	}
	
	@Test
	void testPositiveBig() {
		clip.setSample(10,255);
		assertEquals(255, clip.getSample(10));
	}
	
	@Test
	void testNegBig() {
		clip.setSample(500,-255);
		assertEquals(-255, clip.getSample(500));
	}

}

package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class assets {
	
	public Font[] fonts;
	
	public assets() {
		loadAssets();
	}
	
	public void loadAssets() {
		fonts = new Font[3];
		// 0: 12pt font, 1: 16pt font, 2: 8pt font
		try {
			fonts[0] = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().
					getResourceAsStream("birdo/resources/Press Start.ttf")).deriveFont(12f);
			fonts[1] = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().
					getResourceAsStream("birdo/resources/Press Start.ttf")).deriveFont(16f);
			fonts[2] = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().
					getResourceAsStream("birdo/resources/Press Start.ttf")).deriveFont(8f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

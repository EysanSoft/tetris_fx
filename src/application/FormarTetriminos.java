package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FormarTetriminos {
	Rectangle a;
	Rectangle b;
	Rectangle c;
	Rectangle d;
	Color color;
	private String hash;
	public int forma = 1;

	public FormarTetriminos(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public FormarTetriminos(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.hash = name;

		switch (name) {
		case "j":
			color = Color.BLUE;
			break;
		case "l":
			color = Color.ORANGE;
			break;
		case "o":
			color = Color.YELLOW;
			break;
		case "s":
			color = Color.LIME;
			break;
		case "t":
			color = Color.MAGENTA;
			break;
		case "z":
			color = Color.RED;
			break;
		case "i":
			color = Color.SKYBLUE;
			break;

		}
		this.a.setFill(color);
		this.b.setFill(color);
		this.c.setFill(color);
		this.d.setFill(color);
	}


	public String getName() {
		return hash;
	}


	public void changeForm() {
		if (forma != 4) {
			forma++;
		} else {
			forma = 1;
		}
	}

}

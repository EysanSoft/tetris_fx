package application;

import javafx.scene.shape.Rectangle;

public class ControlarTetriminos {

		public static final int MOVER = Main.MOVER;
		public static final int TAMA�O = Main.TAMA�O;
		public static int XMAX = Main.XMAX;
		public static int YMAX = Main.YMAX;
		public static int[][] TABLERO = Main.TABLERO;

		public static void MoveRight(FormarTetriminos form) {
			if (form.a.getX() + MOVER <= XMAX - TAMA�O && form.b.getX() + MOVER <= XMAX - TAMA�O
					&& form.c.getX() + MOVER <= XMAX - TAMA�O && form.d.getX() + MOVER <= XMAX - TAMA�O) {
				int movea = TABLERO[((int) form.a.getX() / TAMA�O) + 1][((int) form.a.getY() / TAMA�O)];
				int moveb = TABLERO[((int) form.b.getX() / TAMA�O) + 1][((int) form.b.getY() / TAMA�O)];
				int movec = TABLERO[((int) form.c.getX() / TAMA�O) + 1][((int) form.c.getY() / TAMA�O)];
				int moved = TABLERO[((int) form.d.getX() / TAMA�O) + 1][((int) form.d.getY() / TAMA�O)];
				if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
					form.a.setX(form.a.getX() + MOVER);
					form.b.setX(form.b.getX() + MOVER);
					form.c.setX(form.c.getX() + MOVER);
					form.d.setX(form.d.getX() + MOVER);
				}
			}
		}

		public static void MoveLeft(FormarTetriminos form) {
			if (form.a.getX() - MOVER >= 0 && form.b.getX() - MOVER >= 0 && form.c.getX() - MOVER >= 0
					&& form.d.getX() - MOVER >= 0) {
				int movea = TABLERO[((int) form.a.getX() / TAMA�O) - 1][((int) form.a.getY() / TAMA�O)];
				int moveb = TABLERO[((int) form.b.getX() / TAMA�O) - 1][((int) form.b.getY() / TAMA�O)];
				int movec = TABLERO[((int) form.c.getX() / TAMA�O) - 1][((int) form.c.getY() / TAMA�O)];
				int moved = TABLERO[((int) form.d.getX() / TAMA�O) - 1][((int) form.d.getY() / TAMA�O)];
				if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
					form.a.setX(form.a.getX() - MOVER);
					form.b.setX(form.b.getX() - MOVER);
					form.c.setX(form.c.getX() - MOVER);
					form.d.setX(form.d.getX() - MOVER);
				}
			}
		}

		public static FormarTetriminos makeRect() {
			int block = (int) (Math.random() * 100);
			String name;
			Rectangle a = new Rectangle(TAMA�O-1, TAMA�O-1), b = new Rectangle(TAMA�O-1, TAMA�O-1), c = new Rectangle(TAMA�O-1, TAMA�O-1),
					d = new Rectangle(TAMA�O-1, TAMA�O-1);
			if (block < 15) { 
				a.setX(XMAX / 2 - TAMA�O);
				b.setX(XMAX / 2 - TAMA�O);
				b.setY(TAMA�O);
				c.setX(XMAX / 2);
				c.setY(TAMA�O);
				d.setX(XMAX / 2 + TAMA�O);
				d.setY(TAMA�O);
				name = "j";
			} else if (block < 30) { 
				a.setX(XMAX / 2 + TAMA�O);
				b.setX(XMAX / 2 - TAMA�O);
				b.setY(TAMA�O);
				c.setX(XMAX / 2);
				c.setY(TAMA�O);
				d.setX(XMAX / 2 + TAMA�O);
				d.setY(TAMA�O);
				name = "l";
			} else if (block < 45) { 
				a.setX(XMAX / 2 - TAMA�O);
				b.setX(XMAX / 2);
				c.setX(XMAX / 2 - TAMA�O);
				c.setY(TAMA�O);
				d.setX(XMAX / 2);
				d.setY(TAMA�O);
				name = "o";
			} else if (block < 60) { 
				a.setX(XMAX / 2 + TAMA�O);
				b.setX(XMAX / 2);
				c.setX(XMAX / 2);
				c.setY(TAMA�O);
				d.setX(XMAX / 2 - TAMA�O);
				d.setY(TAMA�O);
				name = "s";
			} else if (block < 75) { 
				a.setX(XMAX / 2 - TAMA�O);
				b.setX(XMAX / 2);
				c.setX(XMAX / 2);
				c.setY(TAMA�O);
				d.setX(XMAX / 2 + TAMA�O);
				name = "t";
			} else if (block < 90) { 
				a.setX(XMAX / 2 + TAMA�O);
				b.setX(XMAX / 2);
				c.setX(XMAX / 2 + TAMA�O);
				c.setY(TAMA�O);
				d.setX(XMAX / 2 + TAMA�O + TAMA�O);
				d.setY(TAMA�O);
				name = "z";
			} else { 
				a.setX(XMAX / 2 - TAMA�O - TAMA�O);
				b.setX(XMAX / 2 - TAMA�O);
				c.setX(XMAX / 2);
				d.setX(XMAX / 2 + TAMA�O);
				name = "i";
			}
			return new FormarTetriminos(a, b, c, d, name);
		}

}

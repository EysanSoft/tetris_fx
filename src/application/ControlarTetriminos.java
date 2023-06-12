package application;

import javafx.scene.shape.Rectangle;

public class ControlarTetriminos {

		public static final int MOVER = Main.MOVER;
		public static final int TAMAÑO = Main.TAMAÑO;
		public static int XMAX = Main.XMAX;
		public static int YMAX = Main.YMAX;
		public static int[][] TABLERO = Main.TABLERO;

		public static void MoveRight(FormarTetriminos form) {
			if (form.a.getX() + MOVER <= XMAX - TAMAÑO && form.b.getX() + MOVER <= XMAX - TAMAÑO
					&& form.c.getX() + MOVER <= XMAX - TAMAÑO && form.d.getX() + MOVER <= XMAX - TAMAÑO) {
				int movea = TABLERO[((int) form.a.getX() / TAMAÑO) + 1][((int) form.a.getY() / TAMAÑO)];
				int moveb = TABLERO[((int) form.b.getX() / TAMAÑO) + 1][((int) form.b.getY() / TAMAÑO)];
				int movec = TABLERO[((int) form.c.getX() / TAMAÑO) + 1][((int) form.c.getY() / TAMAÑO)];
				int moved = TABLERO[((int) form.d.getX() / TAMAÑO) + 1][((int) form.d.getY() / TAMAÑO)];
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
				int movea = TABLERO[((int) form.a.getX() / TAMAÑO) - 1][((int) form.a.getY() / TAMAÑO)];
				int moveb = TABLERO[((int) form.b.getX() / TAMAÑO) - 1][((int) form.b.getY() / TAMAÑO)];
				int movec = TABLERO[((int) form.c.getX() / TAMAÑO) - 1][((int) form.c.getY() / TAMAÑO)];
				int moved = TABLERO[((int) form.d.getX() / TAMAÑO) - 1][((int) form.d.getY() / TAMAÑO)];
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
			Rectangle a = new Rectangle(TAMAÑO-1, TAMAÑO-1), b = new Rectangle(TAMAÑO-1, TAMAÑO-1), c = new Rectangle(TAMAÑO-1, TAMAÑO-1),
					d = new Rectangle(TAMAÑO-1, TAMAÑO-1);
			if (block < 15) { 
				a.setX(XMAX / 2 - TAMAÑO);
				b.setX(XMAX / 2 - TAMAÑO);
				b.setY(TAMAÑO);
				c.setX(XMAX / 2);
				c.setY(TAMAÑO);
				d.setX(XMAX / 2 + TAMAÑO);
				d.setY(TAMAÑO);
				name = "j";
			} else if (block < 30) { 
				a.setX(XMAX / 2 + TAMAÑO);
				b.setX(XMAX / 2 - TAMAÑO);
				b.setY(TAMAÑO);
				c.setX(XMAX / 2);
				c.setY(TAMAÑO);
				d.setX(XMAX / 2 + TAMAÑO);
				d.setY(TAMAÑO);
				name = "l";
			} else if (block < 45) { 
				a.setX(XMAX / 2 - TAMAÑO);
				b.setX(XMAX / 2);
				c.setX(XMAX / 2 - TAMAÑO);
				c.setY(TAMAÑO);
				d.setX(XMAX / 2);
				d.setY(TAMAÑO);
				name = "o";
			} else if (block < 60) { 
				a.setX(XMAX / 2 + TAMAÑO);
				b.setX(XMAX / 2);
				c.setX(XMAX / 2);
				c.setY(TAMAÑO);
				d.setX(XMAX / 2 - TAMAÑO);
				d.setY(TAMAÑO);
				name = "s";
			} else if (block < 75) { 
				a.setX(XMAX / 2 - TAMAÑO);
				b.setX(XMAX / 2);
				c.setX(XMAX / 2);
				c.setY(TAMAÑO);
				d.setX(XMAX / 2 + TAMAÑO);
				name = "t";
			} else if (block < 90) { 
				a.setX(XMAX / 2 + TAMAÑO);
				b.setX(XMAX / 2);
				c.setX(XMAX / 2 + TAMAÑO);
				c.setY(TAMAÑO);
				d.setX(XMAX / 2 + TAMAÑO + TAMAÑO);
				d.setY(TAMAÑO);
				name = "z";
			} else { 
				a.setX(XMAX / 2 - TAMAÑO - TAMAÑO);
				b.setX(XMAX / 2 - TAMAÑO);
				c.setX(XMAX / 2);
				d.setX(XMAX / 2 + TAMAÑO);
				name = "i";
			}
			return new FormarTetriminos(a, b, c, d, name);
		}

}

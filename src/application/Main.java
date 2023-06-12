package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	public static final int MOVER = 30;
	public static final int TAMAÑO = 30;
	public static int XMAX = TAMAÑO * 10;
	public static int YMAX = TAMAÑO * 20;
	public static int[][] TABLERO = new int[XMAX / TAMAÑO][YMAX / TAMAÑO];
	private static Pane programa = new Pane();
	private static FormarTetriminos tetrimino;
	private static Scene escenario = new Scene(programa, XMAX + 150, YMAX);
	public static int puntuacion = 0;
	public static int nivel = 0;
	private static int tope = 0;
	private static boolean juego = true;
	private static FormarTetriminos sigTetrimino = ControlarTetriminos.makeRect();
	private static int numLineas = 0;
	//MediaPlayer mediaPlayer;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		for (int[] a : TABLERO) {
			Arrays.fill(a, 0);
		}
		/*
		Media musicFile = new Media("file:///C:\\Users\\Usuario\\eclipse-workspace\\tetris\\src\\application\\tetrisPiano.mp3");
		mediaPlayer = new MediaPlayer(musicFile);
		mediaPlayer.setAutoPlay(true);
		*/
		
		Line line = new Line(XMAX, 0, XMAX, YMAX);
		Text scoretext = new Text("Puntos: 0");
		scoretext.setStyle("-fx-font: 15 Serif;");
		scoretext.setY(60);
		scoretext.setX(XMAX + 10);
		scoretext.setFill(Color.BLUE);
		Text lines = new Text("Lineas: 0");
		lines.setStyle("-fx-font: 15 Serif;");
		lines.setY(120);
		lines.setX(XMAX + 10);
		lines.setFill(Color.BLUE);
		Text level = new Text("Nivel: 0");
		level.setStyle("-fx-font: 15 Serif;");
		level.setY(180);
		level.setX(XMAX + 10);
		level.setFill(Color.BLUE);
		Text instruciones1 = new Text("W = Girar tetrimino");
		instruciones1.setStyle("-fx-font: 15 Serif;");
		instruciones1.setY(230);
		instruciones1.setX(XMAX + 10);
		Text instruciones2 = new Text("A = Izquierda");
		instruciones2.setStyle("-fx-font: 15 Serif;");
		instruciones2.setY(250);
		instruciones2.setX(XMAX + 10);
		Text instruciones3 = new Text("S = Abajo");
		instruciones3.setStyle("-fx-font: 15 Serif;");
		instruciones3.setY(270);
		instruciones3.setX(XMAX + 10);
		Text instruciones4 = new Text("D = Derecha");
		instruciones4.setStyle("-fx-font: 15 Serif;");
		instruciones4.setY(290);
		instruciones4.setX(XMAX + 10);
		Button botonSalir = new Button("Salir del Juego");
		botonSalir.setLayoutY(550);
		botonSalir.setLayoutX(XMAX + 30);
		
		programa.getChildren().addAll(scoretext, line, level, lines, instruciones1, instruciones2, instruciones3, instruciones4, botonSalir);

		FormarTetriminos a = sigTetrimino;
		programa.getChildren().addAll(a.a, a.b, a.c, a.d);
		moveOnKeyPress(a);
		tetrimino = a;
		sigTetrimino = ControlarTetriminos.makeRect();
		stage.setScene(escenario);
		stage.setTitle("Eysan´s Tetris!");
		stage.show();
		
		botonSalir.setOnMouseClicked(event -> {
             System.exit(1);
        });
 
		Timer fall = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						if (tetrimino.a.getY() == 0 || tetrimino.b.getY() == 0 || tetrimino.c.getY() == 0
								|| tetrimino.d.getY() == 0)
							tope++;
						else
							tope = 0;

						if (tope == 2) {
							Rectangle r = new Rectangle ();
					        r.setX (0);
					        r.setY (0);
					        r.setWidth (XMAX + 150);
					        r.setHeight (YMAX);
					        r.setFill (Color.BLACK);
					     	programa.getChildren().add(r);
							Text over = new Text("FIN DEL JUEGO");
							over.setFill(Color.RED);
							over.setStyle("-fx-font: 57 arial;");
							over.setY(250);
							over.setX(10);
							programa.getChildren().add(over);
							juego = false;
						}
						// Exit
						if (tope == 15) {
							System.exit(0);
						}

						if (juego) {
							MoveDown(tetrimino);
							scoretext.setText("Puntos: " + Integer.toString(puntuacion));
							lines.setText("Lineas: " + Integer.toString(numLineas));
						}
					}
				});
			}
		};	
	    fall.schedule(task, 0, 1000);
	}
    
	private void moveOnKeyPress(FormarTetriminos forma) {
		escenario.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case D:
					ControlarTetriminos.MoveRight(forma);
					break;
				case S:
					MoveDown(forma);
					puntuacion++;
					break;
				case A:
					ControlarTetriminos.MoveLeft(forma);
					break;
				case W:
					MoveTurn(forma);
					break;
				}
			}
		});
	}

	private void MoveTurn(FormarTetriminos forma) {
		int f = forma.forma;
		Rectangle a = forma.a;
		Rectangle b = forma.b;
		Rectangle c = forma.c;
		Rectangle d = forma.d;
		switch (forma.getName()) {
		case "j":
			if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
				moverDerecha(forma.a);
				moverAbajo(forma.a);
				moverAbajo(forma.c);
				moverIzquierda(forma.c);
				moverAbajo(forma.d);
				moverAbajo(forma.d);
				moverIzquierda(forma.d);
				moverIzquierda(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
				moverAbajo(forma.a);
				moverIzquierda(forma.a);
				moverIzquierda(forma.c);
				moverArriba(forma.c);
				moverIzquierda(forma.d);
				moverIzquierda(forma.d);
				moverArriba(forma.d);
				moverArriba(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
				moverIzquierda(forma.a);
				moverArriba(forma.a);
				moverArriba(forma.c);
				moverDerecha(forma.c);
				moverArriba(forma.d);
				moverArriba(forma.d);
				moverDerecha(forma.d);
				moverDerecha(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
				moverArriba(forma.a);
				moverDerecha(forma.a);
				moverDerecha(forma.c);
				moverAbajo(forma.c);
				moverDerecha(forma.d);
				moverDerecha(forma.d);
				moverAbajo(forma.d);
				moverAbajo(forma.d);
				forma.changeForm();
				break;
			}
			break;
		case "l":
			if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
				moverDerecha(forma.a);
				moverAbajo(forma.a);
				moverArriba(forma.c);
				moverDerecha(forma.c);
				moverArriba(forma.b);
				moverArriba(forma.b);
				moverDerecha(forma.b);
				moverDerecha(forma.b);
				forma.changeForm();
				break;
			}
			if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
				moverAbajo(forma.a);
				moverIzquierda(forma.a);
				moverDerecha(forma.b);
				moverDerecha(forma.b);
				moverAbajo(forma.b);
				moverAbajo(forma.b);
				moverDerecha(forma.c);
				moverAbajo(forma.c);
				forma.changeForm();
				break;
			}
			if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
				moverIzquierda(forma.a);
				moverArriba(forma.a);
				moverAbajo(forma.c);
				moverIzquierda(forma.c);
				moverAbajo(forma.b);
				moverAbajo(forma.b);
				moverIzquierda(forma.b);
				moverIzquierda(forma.b);
				forma.changeForm();
				break;
			}
			if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
				moverArriba(forma.a);
				moverDerecha(forma.a);
				moverIzquierda(forma.b);
				moverIzquierda(forma.b);
				moverArriba(forma.b);
				moverArriba(forma.b);
				moverIzquierda(forma.c);
				moverArriba(forma.c);
				forma.changeForm();
				break;
			}
			break;
		case "o":
			break;
		case "s":
			if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
				moverAbajo(forma.a);
				moverIzquierda(forma.a);
				moverIzquierda(forma.c);
				moverArriba(forma.c);
				moverArriba(forma.d);
				moverArriba(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
				moverArriba(forma.a);
				moverDerecha(forma.a);
				moverDerecha(forma.c);
				moverAbajo(forma.c);
				moverAbajo(forma.d);
				moverAbajo(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
				moverAbajo(forma.a);
				moverIzquierda(forma.a);
				moverIzquierda(forma.c);
				moverArriba(forma.c);
				moverArriba(forma.d);
				moverArriba(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
				moverArriba(forma.a);
				moverDerecha(forma.a);
				moverDerecha(forma.c);
				moverAbajo(forma.c);
				moverAbajo(forma.d);
				moverAbajo(forma.d);
				forma.changeForm();
				break;
			}
			break;
		case "t":
			if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
				moverArriba(forma.a);
				moverDerecha(forma.a);
				moverAbajo(forma.d);
				moverIzquierda(forma.d);
				moverIzquierda(forma.c);
				moverArriba(forma.c);
				forma.changeForm();
				break;
			}
			if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
				moverDerecha(forma.a);
				moverAbajo(forma.a);
				moverIzquierda(forma.d);
				moverArriba(forma.d);
				moverArriba(forma.c);
				moverDerecha(forma.c);
				forma.changeForm();
				break;
			}
			if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
				moverAbajo(forma.a);
				moverIzquierda(forma.a);
				moverArriba(forma.d);
				moverDerecha(forma.d);
				moverDerecha(forma.c);
				moverAbajo(forma.c);
				forma.changeForm();
				break;
			}
			if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
				moverIzquierda(forma.a);
				moverArriba(forma.a);
				moverDerecha(forma.d);
				moverAbajo(forma.d);
				moverAbajo(forma.c);
				moverIzquierda(forma.c);
				forma.changeForm();
				break;
			}
			break;
		case "z":
			if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
				moverArriba(forma.b);
				moverDerecha(forma.b);
				moverIzquierda(forma.c);
				moverArriba(forma.c);
				moverIzquierda(forma.d);
				moverIzquierda(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
				moverAbajo(forma.b);
				moverIzquierda(forma.b);
				moverDerecha(forma.c);
				moverAbajo(forma.c);
				moverDerecha(forma.d);
				moverDerecha(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
				moverArriba(forma.b);
				moverDerecha(forma.b);
				moverIzquierda(forma.c);
				moverArriba(forma.c);
				moverIzquierda(forma.d);
				moverIzquierda(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
				moverAbajo(forma.b);
				moverIzquierda(forma.b);
				moverDerecha(forma.c);
				moverAbajo(forma.c);
				moverDerecha(forma.d);
				moverDerecha(forma.d);
				forma.changeForm();
				break;
			}
			break;
		case "i":
			if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
				moverArriba(forma.a);
				moverArriba(forma.a);
				moverDerecha(forma.a);
				moverDerecha(forma.a);
				moverArriba(forma.b);
				moverDerecha(forma.b);
				moverAbajo(forma.d);
				moverIzquierda(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
				moverAbajo(forma.a);
				moverAbajo(forma.a);
				moverIzquierda(forma.a);
				moverIzquierda(forma.a);
				moverAbajo(forma.b);
				moverIzquierda(forma.b);
				moverArriba(forma.d);
				moverDerecha(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
				moverArriba(forma.a);
				moverArriba(forma.a);
				moverDerecha(forma.a);
				moverDerecha(forma.a);
				moverArriba(forma.b);
				moverDerecha(forma.b);
				moverAbajo(forma.d);
				moverIzquierda(forma.d);
				forma.changeForm();
				break;
			}
			if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
				moverAbajo(forma.a);
				moverAbajo(forma.a);
				moverIzquierda(forma.a);
				moverIzquierda(forma.a);
				moverAbajo(forma.b);
				moverIzquierda(forma.b);
				moverArriba(forma.d);
				moverDerecha(forma.d);
				forma.changeForm();
				break;
			}
			break;
		}
	}

	private void eliminarColumnas(Pane pane) {
		ArrayList<Node> rects = new ArrayList<Node>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		ArrayList<Node> newrects = new ArrayList<Node>();
		int full = 0;
		for (int i = 0; i < TABLERO[0].length; i++) {
			for (int j = 0; j < TABLERO.length; j++) {
				if (TABLERO[j][i] == 1)
					full++;
			}
			if (full == TABLERO.length)
			lines.add(i);
			//lines.add(i + lines.size());
			full = 0;
		}
		if (lines.size() > 0)
			do {
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle)
						rects.add(node);
				}
				puntuacion += 50;
				numLineas++;

				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() == lines.get(0) * TAMAÑO) {
						TABLERO[(int) a.getX() / TAMAÑO][(int) a.getY() / TAMAÑO] = 0;
						pane.getChildren().remove(node);
					} else
						newrects.add(node);
				}

				for (Node node : newrects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() < lines.get(0) * TAMAÑO) {
						TABLERO[(int) a.getX() / TAMAÑO][(int) a.getY() / TAMAÑO] = 0;
						a.setY(a.getY() + TAMAÑO);
					}
				}
				lines.remove(0);
				rects.clear();
				newrects.clear();
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle)
						rects.add(node);
				}
				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					try {
						TABLERO[(int) a.getX() / TAMAÑO][(int) a.getY() / TAMAÑO] = 1;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
				rects.clear();
			} while (lines.size() > 0);
	}

	private void moverAbajo(Rectangle rect) {
		if (rect.getY() + MOVER < YMAX)
			rect.setY(rect.getY() + MOVER);

	}

	private void moverDerecha(Rectangle rect) {
		if (rect.getX() + MOVER <= XMAX - TAMAÑO)
			rect.setX(rect.getX() + MOVER);
	}

	private void moverIzquierda(Rectangle rect) {
		if (rect.getX() - MOVER >= 0)
			rect.setX(rect.getX() - MOVER);
	}

	private void moverArriba(Rectangle rect) {
		if (rect.getY() - MOVER > 0)
			rect.setY(rect.getY() - MOVER);
	}

	private void MoveDown(FormarTetriminos form) {
		if (form.a.getY() == YMAX - TAMAÑO || form.b.getY() == YMAX - TAMAÑO || form.c.getY() == YMAX - TAMAÑO
				|| form.d.getY() == YMAX - TAMAÑO || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
			TABLERO[(int) form.a.getX() / TAMAÑO][(int) form.a.getY() / TAMAÑO] = 1;
			TABLERO[(int) form.b.getX() / TAMAÑO][(int) form.b.getY() / TAMAÑO] = 1;
			TABLERO[(int) form.c.getX() / TAMAÑO][(int) form.c.getY() / TAMAÑO] = 1;
			TABLERO[(int) form.d.getX() / TAMAÑO][(int) form.d.getY() / TAMAÑO] = 1;
			eliminarColumnas(programa);

			FormarTetriminos a = sigTetrimino;
			sigTetrimino = ControlarTetriminos.makeRect();
			tetrimino = a;
			programa.getChildren().addAll(a.a, a.b, a.c, a.d);
			moveOnKeyPress(a);
		}

		if (form.a.getY() + MOVER < YMAX && form.b.getY() + MOVER < YMAX && form.c.getY() + MOVER < YMAX
				&& form.d.getY() + MOVER < YMAX) {
			int movea = TABLERO[(int) form.a.getX() / TAMAÑO][((int) form.a.getY() / TAMAÑO) + 1];
			int moveb = TABLERO[(int) form.b.getX() / TAMAÑO][((int) form.b.getY() / TAMAÑO) + 1];
			int movec = TABLERO[(int) form.c.getX() / TAMAÑO][((int) form.c.getY() / TAMAÑO) + 1];
			int moved = TABLERO[(int) form.d.getX() / TAMAÑO][((int) form.d.getY() / TAMAÑO) + 1];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				form.a.setY(form.a.getY() + MOVER);
				form.b.setY(form.b.getY() + MOVER);
				form.c.setY(form.c.getY() + MOVER);
				form.d.setY(form.d.getY() + MOVER);
			}
		}
	}

	private boolean moveA(FormarTetriminos form) {
		return (TABLERO[(int) form.a.getX() / TAMAÑO][((int) form.a.getY() / TAMAÑO) + 1] == 1);
	}

	private boolean moveB(FormarTetriminos form) {
		return (TABLERO[(int) form.b.getX() / TAMAÑO][((int) form.b.getY() / TAMAÑO) + 1] == 1);
	}

	private boolean moveC(FormarTetriminos form) {
		return (TABLERO[(int) form.c.getX() / TAMAÑO][((int) form.c.getY() / TAMAÑO) + 1] == 1);
	}

	private boolean moveD(FormarTetriminos form) {
		return (TABLERO[(int) form.d.getX() / TAMAÑO][((int) form.d.getY() / TAMAÑO) + 1] == 1);
	}

	private boolean cB(Rectangle rect, int x, int y) {
		boolean xb = false;
		boolean yb = false;
		if (x >= 0)
			xb = rect.getX() + x * MOVER <= XMAX - TAMAÑO;
		if (x < 0)
			xb = rect.getX() + x * MOVER >= 0;
		if (y >= 0)
			yb = rect.getY() - y * MOVER > 0;
		if (y < 0)
			yb = rect.getY() + y * MOVER < YMAX;
		return xb && yb && TABLERO[((int) rect.getX() / TAMAÑO) + x][((int) rect.getY() / TAMAÑO) - y] == 0;
	}

}

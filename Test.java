import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.lang.Exception;
import java.lang.Thread;


public class Test extends JFrame implements MouseListener, KeyListener {

	public BoundableEntityFrame[] frames;
	public double incVX;
	public double incVY;
	public double rate = .1;

	public Test(int width, int height) {
		super("Test..");

		frames = new BoundableEntityFrame[1];
		frames[0] = new QuadTreeCollisionFrame(0, 0, width, height);
		incVX = 0;
		incVY = 0;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setVisible(true);
		setResizable(true);
		createBufferStrategy(2);

		addMouseListener(this);
		addKeyListener(this);
	}

	public void run() {
		while (true) {
			for (int i = 0; i < frames.length; i++) {
				frames[i].update();
				for (int j = frames[i].ents.size() - 1; j >= 0; j--) {
					frames[i].ents.get(j).vx += incVX;
					frames[i].ents.get(j).vy += incVY;
				}
			}
			draw();
			try {
				Thread.sleep(1000 / 100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void draw() {
		BufferStrategy bf = getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			for (int i = 0; i < frames.length; i++) {
				frames[i].draw(g);	
			}
		} finally {
			g.dispose();
		}

		bf.show();

		Toolkit.getDefaultToolkit().sync();
	}

	public void mouseClicked(MouseEvent e) {
		double x1 = e.getX() - 5;
		double y1 = e.getY() - 22;
		double rvx = (int)(Math.random() * 6) - 3;
		double rvy = (int)(Math.random() * 6) - 3;

		double rrad = 5;

		int r = (int)(Math.random() * 256);
		int g = (int)(Math.random() * 256);
		int b = (int)(Math.random() * 256);

		frames[0].add(new Particle(x1, y1, rvx, rvy, rrad, new Color(r, g, b)));
	}

	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < frames.length; i++) {
			// frames[i].clear();
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			incVX = 0;
			incVY = -rate;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			incVX = 0;
			incVY = rate;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			incVX = rate;
			incVY = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			incVX = -rate;
			incVY = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			incVX = 0;
			incVY = 0;
		}
	}

	public void keyReleased(KeyEvent e) {}

	public static void main(String args[]) {
		Test t = new Test(750, 750);
		t.run();
	}
}
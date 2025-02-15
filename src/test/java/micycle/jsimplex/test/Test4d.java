package micycle.jsimplex.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import micycle.jsimplex.generator.NoiseSurface;

public class Test4d extends JPanel {

	private static final long serialVersionUID = -4138498611856148174L;

	Grayscale gs = new Grayscale();
	ColorMapper m = new ColorMapper();

	// Set the number of dimensions calculated. 2,3 and 4 possible.
	int mode = 5;

	float xc = 0;
	float yc = 0;
	float zc = 0;
	float wc = 0;

	static int xRes = 1024;
	static int yRes = 1024;

	float zoom = 0.0015f;

	public Test4d(String title) {
		super();
		addKeyListener(new Nuputaja(this));
		setFocusable(true);

		for (int i = 0; i < 20; i++) {
			m.addRange(-1 + i * 0.1f, -1 + i * 0.1f + 0.09f, new Color(135, 75, 0), new Color(65, 45, 0), 1);
			m.addRange(-1 + i * 0.1f, -1 + i * 0.1f + 0.1f, new Color(135, 75, 0), new Color(65, 45, 0), 1);
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1024, 1024);
		f.setLocation(100, 100);
		f.setTitle("SimplexTest");
		Test4d t = new Test4d("");
		f.add(t);
		f.setVisible(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		long a = System.nanoTime();
		float[] img2 = NoiseSurface.generate2dRawTiledOctaved(xc / zoom / 2, yc / zoom / 2, xRes, yRes, xRes, yRes,
				zoom * 2, 0.5, 10);

		System.out.println((System.nanoTime() - a) / 1000000);
		g.drawImage(m.getBufferedImage(img2, xRes, yRes), 0, 0, 1024, 1024, null);
	}

	private class Nuputaja extends KeyAdapter {

		private Test4d m;
		final private float mul = 30.0f;

		public Nuputaja(Test4d m) {
			this.m = m;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_UP) {
				m.yc -= mul * zoom;
			}
			if (key == KeyEvent.VK_DOWN) {
				m.yc += mul * zoom;
			}
			if (key == KeyEvent.VK_LEFT) {
				m.xc -= mul * zoom;
			}
			if (key == KeyEvent.VK_RIGHT) {
				m.xc += mul * zoom;
			}
			if (key == KeyEvent.VK_Z) {
				zoom *= 0.2f;
			}
			if (key == KeyEvent.VK_X) {
				zoom *= 5.0f;
			}
			if (key == KeyEvent.VK_A) {
				m.zc += mul * zoom / 8;
			}
			if (key == KeyEvent.VK_S) {
				m.zc -= mul * zoom / 8;
			}
			if (key == KeyEvent.VK_Q) {
				m.wc += mul * zoom / 8;
			}
			if (key == KeyEvent.VK_W) {
				m.wc -= mul * zoom / 8;
			}

			m.repaint();
		}
	}

}

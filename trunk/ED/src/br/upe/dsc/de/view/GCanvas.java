package br.upe.dsc.de.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import br.upe.dsc.de.problem.IProblem;
import br.upe.dsc.de.problem.LayoutMachine;
import br.upe.dsc.de.problem.LayoutProblem;

class GCanvas extends Canvas {
	private double[] solution;
	private ArrayList<LayoutMachine> machines;

	public GCanvas() {
		machines = new ArrayList<LayoutMachine>();
		machines.add(new LayoutMachine("A1", 16, 24)); // A1
		machines.add(new LayoutMachine("B1", 8, 8)); // B1
		machines.add(new LayoutMachine("B2", 8, 8)); // B2
		machines.add(new LayoutMachine("B3", 8, 8)); // B3
		machines.add(new LayoutMachine("C1", 8, 8)); // C1
		machines.add(new LayoutMachine("C2", 8, 8)); // C2
		machines.add(new LayoutMachine("C2", 16, 8)); // D1

		solution = new double[21];
		for (int i = 0; i < 21; i++) {
			solution[i] = 10;
		}

	}

	public void setSolution(double[] solution) {
		this.solution = solution;
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2D = (Graphics2D) g; // cast to 2D
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g2D.setBackground(Color.white);
		// Stroke s = new BasicStroke(1);
		// g2D.setStroke(s);
		// g2D.setColor(Color.white);

		IProblem problem = new LayoutProblem();
		double x1, x2, y1, y2, w, h, scale;
		int xx, yy, width, height, widthMax, heightMax, margin;
		x1 = problem.getLowerLimit(0);
		x2 = problem.getUpperLimit(0);
		y1 = problem.getLowerLimit(1);
		y2 = problem.getUpperLimit(1);
		w = Math.abs(x2 - x1);
		h = Math.abs(y2 - y1);

		// Calculating the positions and sizes
		widthMax = 800;
		heightMax = 600;
		margin = 20; // = 20px

		System.out.println(w + " - " + h);
		// Resizing the image
		width = (widthMax - margin);
		height = (int) Math.round((width * (heightMax - margin)) / width);
		if (height > (heightMax - margin)) {
			height = (heightMax - margin);
			width = (int) Math.round((height * (widthMax - margin)) / height);
		}
		scale = width / w;
		xx = (int) Math.round(margin / 2.0);
		yy = (int) Math.round(margin / 2.0);

		g2D.setColor(Color.WHITE);
		g2D.fillRect(xx, yy, width, height);

		g2D.setColor(Color.LIGHT_GRAY);
		g2D.drawRect(xx, yy, (int) Math.ceil(w * scale), (int) Math.ceil(h * scale));

		double x, y, p;
		int pos;
		LayoutMachine machine1;

		// Updating the position of all machines
		for (int i = 0; i < 7; i++) {
			x = solution[((i * 3) + 0)];
			y = solution[((i * 3) + 1)];
			p = solution[((i * 3) + 2)];
			pos = convertPosition(p);
			machine1 = machines.get(i);
			machine1.updatePosition(x, y, pos);

			drawRectangle(g2D, (float) machine1.getX1(), (float) machine1.getY1(), (float) machine1.getX2(),
				(float) machine1.getY2());
		}
	}

	private int convertPosition(double position) {
		if (position <= 0.25)
			return 0;
		if (position <= 0.5)
			return 1;
		if (position <= 0.75)
			return 2;
		return 3;
	}

	public void drawArc(Graphics2D g2D, int x1, int y1, int x2, int y2, int sd, int rd, int cl) {
		Arc2D.Float arc1 = new Arc2D.Float(x1, y1, x2, y2, sd, rd, cl);
		g2D.fill(arc1);
	}

	public void drawEllipse(Graphics2D g2D, int x1, int y1, int x2, int y2) {
		Ellipse2D.Float oval1 = new Ellipse2D.Float(x1, y1, x2, y2);
		g2D.fill(oval1);
	}

	public void drawRectangle(Graphics2D g2D, float x1, float y1, float x2, float y2) {
		Rectangle2D.Float rec1 = new Rectangle2D.Float(x1, y1, x2, y2);
		g2D.draw(rec1);
	}
}
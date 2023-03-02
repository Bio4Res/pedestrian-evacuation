package es.uma.lcc.caesium.evacuation.simulator.environment;

import java.awt.geom.Ellipse2D;

/**
 * Class for circle-shaped obstacles
 * @author ccottap
 *
 */
public class ObstacleCircle extends Obstacle {
	
	/**
	 * internal circle object
	 */
	private Ellipse2D.Double circle;

	/**
	 * Basic constructor for circle-shaped obstacles
	 * @param x x-coordinate of the circle center
	 * @param y y-coordinate of the circle center
	 * @param r raius of the circle
	 */
	public ObstacleCircle(double x, double y, double r) {
		super();
		double diameter = 2*r;
		circle = new Ellipse2D.Double(x-r, y-r, diameter, diameter);
	}

	@Override
	public ObstacleType getType() {
		return ObstacleType.CIRCLE;
	}

	@Override
	public boolean contains(double x, double y) {
		return circle.contains(x, y);
	}

	@Override
	public String toString() {
		String str = "obstacle: {\n";
		str += "\ttype: " + getType() + "\n";
		if (!name.isEmpty())
			str += "\tname: " + name + "\n";
		if (!description.isEmpty())
			str += "\tdescription: " + description + "\n";
		str += "\tcenter: (" + circle.getCenterX() + ", " + circle.getCenterY() + ")\n";
		str += "\tradius: " + (circle.getMaxX()-circle.getCenterX()) + "\n";
		str += "}";
		return str;
	}

}

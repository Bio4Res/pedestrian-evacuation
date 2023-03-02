package es.uma.lcc.caesium.evacuation.simulator.environment;

import java.awt.geom.Rectangle2D;

/**
 * Class for rectangle-shaped obstacles
 * @author ccottap
 *
 */
public class ObstacleRectangle extends Obstacle {
	
	/**
	 * Internal rectangle object
	 */
	private Rectangle2D.Double rectangle;
	
	/**
	 * Basic constructor for rectangle-shaped obstacles
	 * @param x x-coordinate of the rectangle
	 * @param y y-coordinate of the rectangle
	 * @param w width of the rectangle
	 * @param h height of the rectangle
	 */
	public ObstacleRectangle(double x, double y, double w, double h) {
		super();
		rectangle = new Rectangle2D.Double(x, y, w, h);
	}

	@Override
	public ObstacleType getType() {
		return ObstacleType.RECTANGLE;
	}

	@Override
	public boolean contains(double x, double y) {
		return rectangle.contains(x, y);
	}

	@Override
	public String toString() {
		String str = "obstacle: {\n";
		str += "\ttype: " + getType() + "\n";
		if (!name.isEmpty())
			str += "\tname: " + name + "\n";
		if (!description.isEmpty())
			str += "\tdescription: " + description + "\n";
		str += "\tcoordinates: (" + rectangle.getMinX() + ", " + rectangle.getMinY() + ")\n";
		str += "\twidth: " + rectangle.getWidth() + "\n";
		str += "\theight: " + rectangle.getHeight() + "\n";
		str += "}";
		return str;
	}

}

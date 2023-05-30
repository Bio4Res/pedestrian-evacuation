package es.uma.lcc.caesium.pedestrian.evacuation.simulator.environment;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.List;

import static es.uma.lcc.caesium.pedestrian.evacuation.simulator.environment.Path2D.fromPoints;


/**
 * Class for polygon-shaped obstacles
 * @author ccottap
 *
 */
public class ObstaclePolygon extends Obstacle {

	/**
	 * Internal polygon object
	 */
	private final Path2D.Double path;
	
	/**
	 * Basic constructor for polygon-shaped obstacles
	 * @param points a list of points defining the polygon
	 */
	public ObstaclePolygon(List<Point2D.Double> points) {
		path = fromPoints(points);
	}

	@Override
	public ObstacleType getType() {
		return ObstacleType.POLYGON;
	}

	@Override
	public boolean contains(double x, double y) {
		return path.contains(x, y);
	}

	@Override
	public Shape getShape() { return path; }

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("obstacle: {\n");
		str.append("\ttype: ").append(getType()).append("\n");
		if (!name.isEmpty())
			str.append("\tname: ").append(name).append("\n");
		if (!description.isEmpty())
			str.append("\tdescription: ").append(description).append("\n");
		str.append("\tpoints: [");
		PathIterator pathIterator = path.getPathIterator(null);
		double[] coords = new double[6];
		while (!pathIterator.isDone()) {
			if (pathIterator.currentSegment(coords) != PathIterator.SEG_CLOSE) {
				str.append("(").append(coords[0]).append(", ").append(coords[1]).append(") ");
			}
			pathIterator.next();
		}
		str.append("]\n}");
		return str.toString();
	}

}

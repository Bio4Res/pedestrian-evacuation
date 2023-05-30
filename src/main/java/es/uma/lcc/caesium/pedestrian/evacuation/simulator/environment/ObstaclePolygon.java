package es.uma.lcc.caesium.pedestrian.evacuation.simulator.environment;

import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Class for polygon-shaped obstacles
 * @author ccottap
 *
 */
public class ObstaclePolygon extends Obstacle {

	/**
	 * Internal polygon object
	 */
	private final Path2D.Double polygon;
	
	/**
	 * Basic constructor for polygon-shaped obstacles
	 * @param points a list of points defining the polygon
	 */
	public ObstaclePolygon(List<Point2D.Double> points) {
		polygon = new Path2D.Double();
		Point2D.Double p = points.get(0);
		polygon.moveTo(p.getX(), p.getY());
		int n = points.size();
		for (int i=1; i<n; i++) {
			p = points.get(i);
			polygon.lineTo(p.getX(), p.getY());
		}
		polygon.closePath();
	}

	@Override
	public ObstacleType getType() {
		return ObstacleType.POLYGON;
	}

	@Override
	public boolean contains(double x, double y) {
		return polygon.contains(x, y);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("obstacle: {\n");
		str.append("\ttype: ").append(getType()).append("\n");
		if (!name.isEmpty())
			str.append("\tname: ").append(name).append("\n");
		if (!description.isEmpty())
			str.append("\tdescription: ").append(description).append("\n");
		str.append("\tpoints: [");
		PathIterator pathIterator = polygon.getPathIterator(null);
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

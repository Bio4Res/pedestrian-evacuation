package es.uma.lcc.caesium.pedestrian.evacuation.simulator.environment;

import java.awt.geom.Point2D;
import java.util.Iterator;

/**
 * Auxiliary class for building a 2D path from an iterable of points.
 *
 * @author Pepe Gallardo
 */
public class Path2D {
  /**
   * Returns a closed path constructed from an iterable of points.
   * @param points iterable of points.
   * @return a closed path constructed from an iterable of points.
   */
  public static java.awt.geom.Path2D.Double fromPoints(Iterable<Point2D.Double> points) {
    assert points.iterator().hasNext(): "path should include at least one point.";
    java.awt.geom.Path2D.Double path = new java.awt.geom.Path2D.Double();
    Iterator<Point2D.Double> it = points.iterator();
    Point2D.Double point = it.next();
    path.moveTo(point.getX(), point.getY());
    while (it.hasNext()) {
      point = it.next();
      path.lineTo(point.getX(), point.getY());
    }
    path.closePath();
    return path;
  }
}

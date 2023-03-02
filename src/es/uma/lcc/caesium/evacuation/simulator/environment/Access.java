package es.uma.lcc.caesium.evacuation.simulator.environment;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Class for representing accesses, namely the physical instantiation of a gateway within a certain domain.
 * @param id the id of the access
 * @param name the name of the access (can be empty)
 * @param description the description of the access (can be empty)
 * @param shape shape of the access, namely a list of points defining the boundary whose crossing leads to passing to another domain
 * @author ccottap
 *
 */
public record Access (
	int id,
	String name,
	String description,
	List<Point2D.Double> shape
)
{
	/**
	 * Returns a printable version of the access
	 * @return a string representation of the access
	 */
	public String toString() {
		String str = "access: {\n";
		str += "\tid: " + id + "\n";
		if (!name.isEmpty())
			str += "\tname: " + name + "\n";
		if (!description.isEmpty())
			str += "\tdescription: " + description + "\n";
		str += "\tshape: [";
		for (Point2D.Double p: shape) {
			str += "(" + p.getX() + ", " + p.getY() + ") ";
		}
		str += "]\n}";
		return str;
	}
}

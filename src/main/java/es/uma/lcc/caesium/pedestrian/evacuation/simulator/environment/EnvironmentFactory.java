package es.uma.lcc.caesium.pedestrian.evacuation.simulator.environment;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

/**
 * Generates environments from specified data sources or parameters
 * @author ccottap
 *
 */
public class EnvironmentFactory {
	
	/**
	 * Returns an environment as specified in a JSON object
	 * @param json the JSON object
	 * @return the environment defined in the JSON object.
	 */
	public static Environment buildFromJSON (JsonObject json) {
		Environment e = new Environment();
		
		JsonArray jarr = (JsonArray) json.get("domains");
		Iterator<Object> itr = jarr.iterator();
        while (itr.hasNext()) {
        	JsonObject jd = (JsonObject) itr.next();
        	Domain d = getDomain(jd);
        	e.addDomain(d);
        }
        
        jarr = (JsonArray) json.get("gateways");
		itr = jarr.iterator();
        while (itr.hasNext()) {
        	JsonObject jd = (JsonObject) itr.next();
        	Gateway g = getGateway(jd);
        	if (!e.addGateway(g)) {
        		System.err.println("WARNING: gateway " + g.id() + " could not be added\n");
        	}
        }
		
		return e;
	}
	
	/**
	 * Gets a gateway from a JSON object
	 * @param jo the JSON object
	 * @return the gateway defined in the JSON object
	 */
	private static Gateway getGateway(JsonObject jo) {
		int id = getInt(jo, "id");
		String name = jo.containsKey("name") ? (String)jo.get("name") : "";
		String description = jo.containsKey("description") ? (String)jo.get("description") : "";
		int dom1 = getInt(jo, "domain1");
		int dom2 = getInt(jo, "domain2");
		return new Gateway (id, name, description, dom1, dom2);
	}

	/**
	 * Gets a domain from a JSON object
	 * @param jd the JSON object
	 * @return the domain defined in the JSON object
	 */
	private static Domain getDomain(JsonObject jd) {
		Domain d = new Domain(
								getInt(jd, "id"),
								getDouble(jd, "width"),
								getDouble(jd, "height")
							);
		if (jd.containsKey("name"))
			d.setName((String)jd.get("name"));
		if (jd.containsKey("description"))
			d.setDescription((String)jd.get("description"));

		JsonArray jarr = (JsonArray) jd.get("obstacles");
		Iterator<Object> itr = jarr.iterator();
		while (itr.hasNext()) {
			JsonObject jo = (JsonObject) itr.next();
			Obstacle obs = getObstacle(jo);
			d.addObstacle(obs);
		}
		
		jarr = (JsonArray) jd.get("accesses");
		itr = jarr.iterator();
		while (itr.hasNext()) {
			JsonObject jo = (JsonObject) itr.next();
			Access acc = getAccess(jo);
			d.addAccess(acc);
		}
		
		return d;
	}
	
	/**
	 * Gets an access from a JSON object
	 * @param jo the JSON object
	 * @return the access defined in the JSON object
	 */
	private static Access getAccess(JsonObject jo) {
		int id = getInt(jo, "id");
		String name = jo.containsKey("name") ? (String)jo.get("name") : "";
		String description = jo.containsKey("description") ? (String)jo.get("description") : "";
		JsonArray jpoints = (JsonArray) jo.get("shape");
		List<Point2D.Double> points = getPoints(jpoints);
		return new Access (id, name, description, points);
	}

	/**
	 * Gets an obstacle from a JSON object
	 * @param jo the JSON object
	 * @return the obstacle defined in the JSON object
	 */
	private static Obstacle getObstacle(JsonObject jo) {
		ObstacleType type;
		if (jo.containsKey("type")) {
			type = ObstacleType.valueOf(((String) jo.get("type")).toUpperCase());
		} else {
			type = ObstacleType.RECTANGLE;
		}
		Obstacle obs = null;
		switch (type) {
			case CIRCLE: {
				double r = getDouble(jo, "radius");
				double x = getDouble((JsonObject) jo.get("center"), "X");
				double y = getDouble((JsonObject) jo.get("center"), "Y");
				obs = new ObstacleCircle(x, y, r);
				break;
			}
			case POLYGON: {
				JsonArray jpoints = (JsonArray) jo.get("points");
				List<Point2D.Double> points = getPoints(jpoints);
				obs = new ObstaclePolygon(points);
				break;
			}
			case RECTANGLE: {
				double x = getDouble((JsonObject) jo.get("top"), "X");
				double y = getDouble((JsonObject) jo.get("top"), "Y");
				double w = getDouble(jo, "width");
				double h = getDouble(jo, "height");
				obs = new ObstacleRectangle(x, y, w, h);
				break;
			}
			default:
				System.out.println("Should not be here " + type);
				break;
		}
		if (jo.containsKey("name")) {
			obs.setName((String) jo.get("name"));
		}
		if (jo.containsKey("description")) {
			obs.setDescription((String) jo.get("description"));
		}
		return obs;
	}
	
	
	/**
	 * Gets a list of points from a JSON array
	 * @param jpoints a JSON array
	 * @return the list of points defined in the JSON array
	 */
	private static List<Point2D.Double> getPoints(JsonArray jpoints) {
		Iterator<Object> ip = jpoints.iterator();
		List<Point2D.Double> points = new LinkedList<>();
		while (ip.hasNext()) {
			JsonObject jp = (JsonObject) ip.next();
			points.add(new Point2D.Double(getDouble(jp, "X"), getDouble(jp, "Y")));
		}
		return points;
	}
	
	/**
	 * Convenience method to obtain a double from the JSON object
	 * @param obj the JSON object
	 * @param key the key whose value is sought
	 * @return the value of the key as a double
	 */
	private static double getDouble (JsonObject obj, String key) {
		return ((BigDecimal)obj.get(key)).doubleValue();
	}
	
	/**
	 * Convenience method to obtain an int from the JSON object
	 * @param obj the JSON object
	 * @param key the key whose value is sought
	 * @return the value of the key as an int
	 */
	private static int getInt (JsonObject obj, String key) {
		return ((BigDecimal)obj.get(key)).intValue();
	}

}

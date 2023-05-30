package es.uma.lcc.caesium.pedestrian.evacuation.simulator.environment;


/**
 * Class for representing gateways, namely connections that allow pedestrians
 * moving from a certain domain to another adjacent domain
 * @param id the id of the gateway
 * @param name the name of the gateway (can be empty)
 * @param description the description of the gateway (can be empty)
 * @param domain1 a domain in one of the endpoints of the gateway
 * @param domain2 a domain in the other endpoint of the gateway
 * @author ccottap
 *
 */
public record Gateway (int id, String name, String description, int domain1, int domain2) 
{
	/**
	 * Returns a printable version of the gateway
	 * @return a string representation of the gateway
	 */
	public String toString() {
		String str = "gateway: {\n";
		str += "\tid: " + id + "\n";
		if (!name.isEmpty())
			str += "\tname: " + name + "\n";
		if (!description.isEmpty())
			str += "\tdescription: " + description + "\n";
		str += "\tdomain1: " + domain1 + "\n";
		str += "\tdomain2: " + domain2 + "\n";
		str += "}";
		return str;
	}
}

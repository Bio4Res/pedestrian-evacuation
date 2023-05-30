package es.uma.lcc.caesium.pedestrian.evacuation.simulator.environment;

import java.util.HashMap;
import java.util.Set;


/**
 * This class allows handling the basic information of the physical environment
 * to be simulated.
 * @author ccottap
 *
 */
public class Environment {
	/**
	 * Domains in the environment
	 */
	protected HashMap<Integer,Domain> domains;
	
	/**
	 * Gateways in the environment
	 */
	protected HashMap<Integer,Gateway> gateways;

	/**
	 * Basic constructor. Creates an environment with no domains or gateways.
	 */
	public Environment() {
		domains  = new HashMap<>();
		gateways = new HashMap<>();
	}
	
	/**
	 * Adds a domain to the environment. Note that it is not possible to
	 * add a domain whose id is 0, since this is assumed to represent 
	 * the safety domain.
	 * @param d the domain to be added
	 */
	public void addDomain (Domain d) {
		int id = d.id();
		if (id != 0)
			domains.put(id, d);
	}
	
	/**
	 * Adds a gateway to the environment. The domains must already exist in the environment, otherwise nothing is done.
	 * @param g the gateway to be added
	 * @return true iff the gateway could be added
	 */
	public boolean addGateway (Gateway g) {
		if ((g.domain1() != g.domain2()) && ((g.domain1() == 0) || domains.containsKey(g.domain1())) && ((g.domain2() == 0) || domains.containsKey(g.domain2()))) {
			gateways.put(g.id(), g);
			return true;
		}
		return false;
	}
	
	/**
	 * Gets a specific domain.
	 * @param id the id of the domain sought
	 * @return the domain whose id is indicated (null if it does not exist).
	 */
	public Domain getDomain (int id) {
		return domains.get(id);
	}
	
	/**
	 * Returns a set with the ids of all domains in the environment 
	 * @return a set with the ids of all domains in the environment 
	 */
	public Set<Integer> getDomainsIDs () {
		return domains.keySet();
	}
	
	/**
	 * Gets a specific gateway.
	 * @param id the id of the gateway sought
	 * @return the gateway whose id is indicated (null if it does not exist).
	 */
	public Gateway getGateway (int id) {
		return gateways.get(id);
	}
	
	/**
	 * Returns a set with the ids of all gateways in the environment 
	 * @return a set with the ids of all gateways in the environment
	 */
	public Set<Integer> getGatewayIDs () {
		return domains.keySet();
	}
	
	/**
	 * Returns a printable version of the environment
	 * @return a string version of the environment
	 */
	public String toString() {
		StringBuilder str = new StringBuilder("Environment: {\n");
		str.append("\tdomains: [\n");
		for (Domain d: domains.values()) {
			str.append("\t\t").append(d.toString().replace("\n", "\n\t\t")).append("\n");
		}
		str.append("\t]\n");
		str.append("\tgateways: [\n");
		for (Gateway g: gateways.values()) {
			str.append("\t\t").append(g.toString().replace("\n", "\n\t\t")).append("\n");
		}
		str.append("\t]\n");
		str.append("\n}");
		return str.toString();
	}
}

/**
 * 
 */
package es.uma.lcc.caesium.evacuation.simulator.environment;


/**
 * Abstract class for representing obstacles
 * @author ccottap
 *
 */
public abstract class Obstacle {

	/**
	 * the name of the obstacle
	 */
	protected String name;
	/**
	 * the type of the obstacle
	 */
	protected String description;
		
	
	/**
	 * Basic constructor of the base obstacle class
	 */
	public Obstacle() {
		name = "";
		description = "";
	}
	
	/**
	 * Returns the name of the obstacle
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the obstacle
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the obstacle
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the obstacle
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the type of the obstacle
	 * @return the type of the obstacle
	 */
	public abstract ObstacleType getType();

	/**
	 * Returns true iff the point (x,y) is contained within the obstacle.
	 * @param x x-coordinate of a point
	 * @param y y-coordinate of a point
	 * @return true iff the point (x,y) is contained within the obstacle.
	 */
	public abstract boolean contains (double x, double y);
	
	/**
	 * Returns a printable version of the obstacle
	 * @return a string version of the obstacle
	 */
	public abstract String toString();

}

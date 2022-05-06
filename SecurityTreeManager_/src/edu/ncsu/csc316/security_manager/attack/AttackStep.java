package edu.ncsu.csc316.security_manager.attack;

/**
 * Step in the attack tree for obtaining the goal.
 * An attack step can have a type, a description, a
 * probability, a cost, and an impact.
 * @author jakebackhouse
 *
 */
public class AttackStep implements Comparable<AttackStep> {
	
	/** The type of AttackStep (can be Goal, OR, or AND) */
	private String type;
	
	/** Probability of an attack step */
	private double probability;
	
	/** Cost of an attack step */
	private double cost;
	
	/** Impact of an attack step */
	private double impact;
	
	/** Risk of an attack step */
	private double risk;
	
	/** Description of an attack step */
	private String description;
	
	/**
	 * Constructs an attack step given its type and description.
	 * Its probability, cost, and impact are all set to 0.
	 * @param type The type of attack step.
	 * @param description The description of the attack step.
	 */
	public AttackStep(String type, String description) {
		if(type.equals("O")) {
			this.type = "OR";
		} else if(type.equals("A")) {
			this.type = "AND";
		} else {
			this.type = type.trim();
		}
		this.description = description.trim();
		this.probability = 0;
		this.cost = 0;
		this.impact = 0;
		this.risk = 0;
	}
	
	/**
	 * Constructs an attack step given its type, probability,
	 * impact, cost, and description.
	 * @param type The type of attack step.
	 * @param probability The probability of the attack step.
	 * @param impact The impact of the attack step.
	 * @param cost The cost of the attack step.
	 * @param description The description of the attack step.
	 */
	public AttackStep(String type, double probability, double impact, double cost, String description) {
		
		if(type.equals("O")) {
			this.type = "OR";
		} else if(type.equals("A")) {
			this.type = "AND";
		} else {
			this.type = type.trim();
		}
		this.probability = probability;
		this.cost = cost;
		this.impact = impact;
		this.description = description.trim();
		
	}
	
	/**
	 * Constructs an empty Attack Step.
	 */
	public AttackStep() {
		this.type = "";
		this.probability = 0;
		this.cost = 0;
		this.impact = 0;
		this.description = "";
	}
	
	/**
	 * Gets the type of the attack step.
	 * @return The type of the attack step.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the probability of the attack step.
	 * @return the probability of the attack step.
	 */
	public double getProbability() {
		return probability;
	}
	
	/**
	 * Gets the cost of the attack step.
	 * @return the cost of the attack step.
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Gets the impact of the attack step.
	 * @return the impact of the attack step.
	 */
	public double getImpact() {
		return impact;
	}
	
	/**
	 * Gets the risk of the attack step.
	 * @return the risk of the attack step.
	 */
	public double getRisk() {
		return risk;
	}
	
	/**
	 * Gets the description of the attack step.
	 * @return The description of the attack step.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the probability of the attack step with the given probability.
	 * @param prob The probability to be set.
	 */
	public void setProbability(double prob) {
		this.probability = prob;
	}
	
	/**
	 * Sets the cost of the attack step with the given cost.
	 * @param cost The cost to be set.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * Sets the impact of the attack step with the given impact.
	 * @param impact The impact to be set.
	 */
	public void setImpact(double impact) {
		this.impact = impact;
	}

	/**
	 * Compares two attack steps. Compares the cost, impact, probability, type, and description
	 * of the attack steps.
	 * @return 0 if the two are equal, -1 if the one calling compareTo is less than, and 1 if 
	 * the one calling compareTo is greater than.
	 */
	@Override
	public int compareTo(AttackStep o) {
		if(this.getCost() == o.getCost() && this.getImpact() == o.getImpact() && this.getProbability() == o.getProbability() && this.getRisk() == o.getRisk() && this.getType().equals(o.getType()) && this.getDescription().equals(o.getDescription())) {
			return 0;
		} else {
			return -1;
		}
	}
	
	/**
	 * Gets the string format of an attack step.
	 * @return the string format of an attack step.
	 */
	@Override
	public String toString() {

		return (this.getType() + " Step[" + this.getDescription() + ", C=" + String.format("%.2f", this.getCost()) + ", P=" + String.format("%.3f", this.getProbability()) + ", I=" + String.format("%.2f", this.getImpact()) + "]");
		
	}

}

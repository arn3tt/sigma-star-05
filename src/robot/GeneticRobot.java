package robot;

public class GeneticRobot implements Comparable<GeneticRobot> {

	public static final int TREES = 3;

	private String name;
	private int fitness;
	private Node[] trees = new Node[3];

	public GeneticRobot(String name, int fitness, Node[] trees) {
		this.name = name;
		this.fitness = fitness;
		this.trees = trees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public Node[] getTrees() {
		return trees;
	}

	public void setTrees(Node[] trees) {
		this.trees = trees;
	}

	@Override
	public int compareTo(GeneticRobot o) {
		return new Integer(fitness).compareTo(new Integer(o.getFitness()));
	}

}

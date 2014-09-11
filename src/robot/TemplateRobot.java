package robot;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TemplateRobot {

	private static final String CODE_PATH = "src/gen/%s.java";

	private String name;
	private int fitness;
	private Node[] trees = new Node[3];

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

	public String codigo() {
		return "";
	}

	public void compila() throws IOException {
		String path = String.format(CODE_PATH, getName());
		FileWriter robotFile = new FileWriter(path);
		PrintWriter gravarArq = new PrintWriter(robotFile);

		gravarArq.printf(codigo());
		robotFile.close();

	}

}

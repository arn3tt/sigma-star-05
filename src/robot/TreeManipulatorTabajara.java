package robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class TreeManipulatorTabajara {

	private static Random generator = new Random();

	public final static String FUNCTION = "Function";
	public final static String TERMINAL = "Terminal";
	public final static double FUNCTION_PROBABILITY = 0.9;

	private static final double TERMINALS_RATE = Double.valueOf(Component
			.terminals().length) / Component.values().length;

	private static final int MINIMUM_ALLOWED_DEPTH = 4;

	private static final int MAXIMUM_ALLOWED_DEPTH = 10;

	public static Node selectRandomNode(Node root) {
		Map<String, List<Node>> nodeMap = treeAsMap(root);
		double chooseNodeType = generator.nextDouble();
		if (chooseNodeType <= FUNCTION_PROBABILITY) {
			return getRandomNodeNotRoot(nodeMap.get(FUNCTION));
		} else {
			return getRandomNodeNotRoot(nodeMap.get(TERMINAL));
		}
	}
	
	private static Node getRandomNodeNotRoot(List<Node> listNode) {
		int randomIndex = generator.nextInt(listNode.size());
		Node node = listNode.get(randomIndex);
		while (node.getParent() == null) {
			randomIndex = generator.nextInt(listNode.size());
			node = listNode.get(randomIndex);
		}
		return node;
	}

	public static Map<String, List<Node>> treeAsMap(Node root) {
		Map<String, List<Node>> nodeMap = new HashMap<String, List<Node>>();
		nodeMap.put(FUNCTION, new ArrayList<Node>());
		nodeMap.put(TERMINAL, new ArrayList<Node>());

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			root = queue.poll();
			if (root.isLeaf()) {
				nodeMap.get(TERMINAL).add(root);
			} else {
				nodeMap.get(FUNCTION).add(root);
			}
			queue.addAll(Arrays.asList(root.getChildren()));
		}
		return nodeMap;
	}

	public static Node generateRandomTree() {
		return generateRandomTree(0);
	}

	private static Node generateRandomTree(int currentDepth) {
		if (currentDepth >= MINIMUM_ALLOWED_DEPTH && currentDepth < MAXIMUM_ALLOWED_DEPTH) {
			if (Math.random() < TERMINALS_RATE) {
				return generateRandomTerminalNode();
			} else {
				return generateRandomFunctionNode(currentDepth);
			}
		} else if (currentDepth < MINIMUM_ALLOWED_DEPTH) {
			return generateRandomFunctionNode(currentDepth);
		} else {
			return generateRandomTerminalNode();
		}
	}

	private static Node generateRandomTerminalNode() {
		Component terminal = chooseRandomTerminal();
		return new Node(new Node[] {}, terminal);
	}

	private static Node generateRandomFunctionNode(int currentDepth) {
		Component function = chooseRandomFunction();
		Node[] children = new Node[function.getArity()];
		for (int i = 0; i < children.length; i++) {
			children[i] = generateRandomTree(currentDepth + 1);
		}
		return new Node(children, function);
	}

	private static Component chooseRandomTerminal() {
		return Component.terminals()[generator.nextInt(Component.terminals().length)];
	}

	private static Component chooseRandomFunction() {
		return Component.functions()[generator.nextInt(Component.functions().length)];
	}

	public static void crossover(Node root1, Node root2) {
		Node nodeTree1 = selectRandomNode(root1);
		Node nodeTree2 = selectRandomNode(root2);
		
		Node parentTree1 = nodeTree1.getParent();
		nodeTree1.setParent(nodeTree2.getParent());
		nodeTree1.getParent().replaceChild(nodeTree2, nodeTree1);
		nodeTree2.setParent(parentTree1);
		nodeTree2.getParent().replaceChild(nodeTree1, nodeTree2);
	}
	
	public static void mutation(Node root) {
		Node randomNode = selectRandomNode(root);
		Node parent = randomNode.getParent();
		
		Node mutationTreeRoot = generateRandomTree();
		mutationTreeRoot.setParent(parent);
		parent.replaceChild(randomNode, mutationTreeRoot);
	}

}

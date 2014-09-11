package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import robot.Component;
import robot.Node;
import robot.TreeManipulatorTabajara;

public class TreeTest {

	@Test
	public void test() {
		Node tree = createSampleTree();
		assertEquals(tree.composite(), "sin(cos(add(abs(getRandomNumber()), getEnemyDistance(e))))");

		Map<String, List<Node>> m = TreeManipulatorTabajara.treeAsMap(tree);
		assertEquals(m.get(TreeManipulatorTabajara.FUNCTION).size(), 4);
		assertEquals(m.get(TreeManipulatorTabajara.TERMINAL).size(), 2);
	}
	
	@Test
	public void testGetRandomNode() {
		Node tree = createSampleTree();
		Node node;
		for (int i = 0; i < 50; i++) {
			node = TreeManipulatorTabajara.selectRandomNode(tree);
			assertNotEquals(node, tree);
			assertNotNull(node.getParent());
		}
	}

	public Node createSampleTree() {
		Node getRandomNumber = new Node(new Node[] {},
				Component.GET_RANDOM_NUMBER);
		assertEquals(getRandomNumber.composite(), "getRandomNumber()");

		Node getDistance = new Node(new Node[] {}, Component.GET_ENEMY_DISTANCE);
		assertEquals(getDistance.composite(), "getEnemyDistance(e)");

		Node abs = new Node(new Node[] { getRandomNumber }, Component.ABS);
		assertEquals(abs.composite(), "abs(getRandomNumber())");

		Node add = new Node(new Node[] { abs, getDistance }, Component.ADD);
		assertEquals(add.composite(),
				"add(abs(getRandomNumber()), getEnemyDistance(e))");

		Node cos = new Node(new Node[] { add }, Component.COS);
		assertEquals(cos.composite(),
				"cos(add(abs(getRandomNumber()), getEnemyDistance(e)))");

		Node sin = new Node(new Node[] { cos }, Component.SIN);
		assertEquals(sin.composite(),
				"sin(cos(add(abs(getRandomNumber()), getEnemyDistance(e))))");

		return sin;
	}

	@Test
	public void testGenerateRandomTree() {
		for (int i = 0; i < 20; i++) {
			Node randomTree = TreeManipulatorTabajara.generateRandomTree1(0);
			System.out.println(randomTree.composite());
			System.out.println(TreeManipulatorTabajara.treeAsMap(randomTree));
			System.out.println("===================");
			System.out.println();
		}
	}
}

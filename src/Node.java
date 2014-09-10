public class Node {

	private Node[] children;
	private Component component;

	public Node(Node[] children, Component component) {
		if (children == null) {
			throw new RuntimeException("");
		}
		this.children = children;
		this.component = component;
	}

	public Node[] getChildren() {
		return children;
	}

	public void setChildren(Node[] children) {
		this.children = children;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public String composite() {
		String representation = component.toString();
		if (!isLeaf()) {
			representation += "(";
			for (Node n : children) {
				representation += n.composite();
			}
			representation += ")";
		}
		return representation;
	}

	public boolean isLeaf() {
		return children.length == 0;
	}
}

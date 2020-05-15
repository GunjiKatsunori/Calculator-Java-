package Sample;

public class Calculate {
	public static int generateNodes(String[] str) {
		Node<String> node1 = new Node<String>(str[1]);
		Node<Integer> node2 = new Node<Integer>(Integer.parseInt(str[0]));
		Node<Integer> node3 = new Node<Integer>(Integer.parseInt(str[2]));

		node1.setLeft(node2);
		node1.setRight(node3);

		if (node1.getValue() == "+") {
			return node2.getValue() + node3.getValue();
		}
		else if (node1.getValue() == "-") {
			return node2.getValue() - node3.getValue();
		}
		else if (node1.getValue() == "*") {
			return node2.getValue() * node3.getValue();
		}
		else if (node1.getValue() == "/") {
			return node2.getValue() / node3.getValue();
		}

		return 0;
	}
}
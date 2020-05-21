public class Node {
	Token token;
	Node parent;
	Node left;
	Node right;

	public Node(Token token) {
		this.token = token;
	}

	public setToken(Token token) {
		this.token = token;
	}

	/* positionで指定した位置のノードを変更
	 * 
	 */
	public setNeighbor(Node node, String position) {
		if (position.equals("parent")) {
			this.parent = node;
		}
		else if (position.equals("left")) {
			this.left = node;
		}
		else if (position.equals("right")) {
			this.right = node;
		}
		else {
			System.out.println("位置の指定が不正です");
		}
	}
}
import java.util.ArrayList;
import java.util.List;

public class Node extends Token {
	private Node parent;

	public Node() {
		super();
	}

	public Node(Token token) {
		super(token.get(), token.getCategory());
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getParent() {
		return parent;
	}

	static Node lastOperator = new Node();
	static Node currentNode = new Node();

	public static List<Node> addTree(List<Node> nodeList, Token token) {
		Node newNode = new Node(token);
		if (nodeList.size() >= 1) {
			currentNode = nodeList.get(nodeList.size() - 1);
		}

		// 演算子の場合、優先度にしたがってノードの位置を決める
		if (newNode.isOperator() || newNode.getCategory().equals(")") ) {
			while ( currentNode.parent != null &&
					currentNode.parent.isPrefThan(newNode) ) {
				currentNode = currentNode.parent;
			}
		}
		// ノードの結合
		return connect(nodeList, newNode);
	}

	public boolean isOperator() {
		boolean bool =  this.getCategory().equals("+-") ||
						this.getCategory().equals("*/");
		return bool;
	}

	private boolean isPrefThan(Node node) {
		return ( this.priority() > node.priority() );
	}

	private int priority() {
		String opr = this.get();
		if      (opr.equals("+") || opr.equals("-")) {
			return 1;
		}
		else if (opr.equals("*") || opr.equals("/")) {
			return 2;
		}
		else if (opr.equals("(") || opr.equals(")")) {
			return 100;
		}
		else {
			return 0;
		}
	}

	public static List<Node> connect(List<Node> nodeList, Node newNode) {
		if ( newNode.isOperator() ) { // 演算子の場合
			// 挿入位置の検索
			int i = searchNode(nodeList, currentNode);
			List<Node> list_a = new ArrayList<Node>();
			List<Node> list_b = new ArrayList<Node>();
			if (i >= 0) {
				list_a = nodeList.subList(i, nodeList.size());
			}
			if (i >= 1) {
				list_b = nodeList.subList(0, i);
			}

			// newNodeの挿入
			List<Node> tmpList = new ArrayList<Node>();
			tmpList.add(newNode);
			tmpList.addAll(list_a);
			list_b.addAll(tmpList);
			nodeList = list_b;

			// ノードの親子関係
			newNode.setParent(currentNode.getParent());
			currentNode.setParent(newNode);

			// 最後に追加された演算子として登録
			lastOperator = newNode;
		}
		else if ( newNode.getCategory().equals(")") ) { // )の場合
			// 挿入位置の検索
			int i = searchNode(nodeList, currentNode);
			List<Node> list_a = nodeList.subList(i+1, nodeList.size());
			List<Node> list_b = new ArrayList<Node>();
			if (i >= 1) {
				list_b = nodeList.subList(0, i);
			}

			// newNodeの挿入
			list_b.addAll(list_a);
			nodeList = list_b;

			// 構文木の親子関係
			nodeList.get(i+1).setParent(nodeList.get(i-1));
		}
		else { // 数字の場合
			// newNodeの追加
			nodeList.add(newNode);

			// 親子関係の設定
			while (!currentNode.isOperator() &&
				!currentNode.get().equals("(") &&
				!currentNode.get().equals("")) {
				currentNode = currentNode.getParent();
			}
			newNode.setParent(lastOperator);
		}
		return nodeList;
	}

	private static int searchNode(List<Node> nodeList, Node node) {
		if (nodeList.size() == 0) {
			return 0;
		}
		for (int i=0; i<nodeList.size(); i++) {
			if (node == nodeList.get(i)) {
				return i;
			}
		}
		return -1;
	}
}
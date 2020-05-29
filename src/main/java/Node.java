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

		// currentNodeの更新
		if (nodeList.size() > 0) {
			if (nodeList.get(nodeList.size() - 1).get().equals(")")) {
				currentNode = lastOperator;
			}
			else {
				currentNode = nodeList.get(nodeList.size() - 1);
			}
		}

		// 優先度にしたがってノードの位置を決める
		if (newNode.isOperator()) {
			// 演算子の場合、結合優先度にしたがって移動
			while ( currentNode.parent != null &&
					currentNode.parent.isPrefThan(newNode) ) {
				currentNode = currentNode.parent;
			}
			// 「(」を超えないように１つ上の親ノードに移動する
			if (currentNode.parent != null &&
				!(currentNode.parent.get().equals("("))
				) {
				currentNode = currentNode.parent;
			}
		}
		else if (newNode.getCategory().equals(")")) {
			// 「)」の場合、「(」の位置に移動する
			while ( !currentNode.parent.get().equals("(") ) {
				currentNode = currentNode.parent;
			}
			currentNode = currentNode.parent;
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
			return 0;
		}
		else {
			return 0;
		}
	}

	public static List<Node> connect(List<Node> nodeList, Node newNode) {
		if ( newNode.isOperator() ) { // 演算子の場合
			// 挿入位置の検索
			int i = searchNode(nodeList, currentNode);
			List<Node> latter = new ArrayList<Node>();
			List<Node> former = new ArrayList<Node>();
			if (i >= 0) {
				latter = nodeList.subList(i, nodeList.size());
			}
			if (i >= 1) {
				former = nodeList.subList(0, i);
			}

			// newNodeの挿入
			List<Node> tmpList = new ArrayList<Node>();
			tmpList.add(newNode);
			tmpList.addAll(latter);
			former.addAll(tmpList);
			nodeList = former;

			// ノードの親子関係
			newNode.setParent(currentNode.getParent());
			currentNode.setParent(newNode);

			// 最後に追加された演算子として登録
			lastOperator = newNode;
		}
		else if ( newNode.getCategory().equals(")") ) { // )の場合
			// newNodeの追加
			nodeList.add(newNode);

			// 構文木の親子関係
			int i = searchNode(nodeList, currentNode);
			newNode.setParent(nodeList.get(i));

			// 最後に追加された演算子の更新
			lastOperator = nodeList.get(i);
		}
		else if ( newNode.getCategory().equals("(") ) { // (の場合
			// newNodeの追加
			nodeList.add(newNode);

			// 親子関係の設定
			if (lastOperator.get() != "") {
				// 最初に構文木に(を入れるとき、親ノードに仮置きのlastOperatが入らないようにする
				newNode.setParent(lastOperator);
			}

			// 最後に追加された演算子として登録
			lastOperator = newNode;
		}
		else { // 数字の場合
			// newNodeの追加
			nodeList.add(newNode);

			// 親子関係の設定
			if (lastOperator.get() != "") {
				// 最初に構文木に数字を入れるとき、親ノードに仮置きのlastOperatが入らないようにする
				newNode.setParent(lastOperator);
			}
		}
		return nodeList;
	}

	private static int searchNode(List<Node> nodeList, Node node) {
		if (nodeList.size() == 0) { // 最初の操作ではi=0
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
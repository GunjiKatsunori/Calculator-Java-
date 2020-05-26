import java.util.ArrayList;
import java.util.List;

public class Main {

	public static double calculate(String formulaStr) {
		List<Token> tokenList = tokenize(formulaStr);
		List<Node> nodeList = composeTree(tokenList);
		double result = translateSyntaxTree(nodeList);

		return result;
	}

	/* 数式をトークンに分解するメソッド
	* 引数：in String 入力された数式
	* 戻り値：分割された字句のリスト */
	public static List<Token> tokenize(String formulaStr) {
		List<Token> tokenList = new ArrayList<Token>();
		Token token = new Token("", "space");

		for (int i = 0; i < formulaStr.length(); i++) {
			char ch = formulaStr.charAt(i);
			String tokenVal = token.get();
			String tokenCategory = token.getCategory();
			String category = Token.detCategory(ch);
			if (token.getCategory().equals(category)) { //chがtokenに続く文字の場合
				token.append(ch);
			}
			else {
				if (!token.getCategory().equals("space")){// tokenが空白でない場合
					Token completeToken = new Token(tokenVal, tokenCategory);
					tokenList.add(completeToken);
				}
				token.set("", "space");
				if (!category.equals("space")){ // chが空白でない場合
					String newToken = "" + ch;
					token.set(newToken, category);
				}
			}
		}
		tokenList.add(token);
		return tokenList;
	}

	public static List<Node> composeTree(List<Token> tokenList) {
		List<Node> nodeList    = new ArrayList<Node>();

		Token token = new Token();

		for (int i=0; i<tokenList.size(); i++) {
			token = tokenList.get(i);
			nodeList = Node.addTree(nodeList, token);
		}
		return nodeList;
	}

	public static double translateSyntaxTree(List<Node> nodeList) {
		List<Node> stack = new ArrayList<Node>();
		int len = nodeList.size();
		for (int i=1; i<=len; i++) {
			Node node = nodeList.get(len-i);
			if (node.isOperator()) {
				// stackからのポップ
				int lenStack = stack.size();
				Node node1 = stack.get(lenStack-1);
				stack.remove(lenStack-1);
				Node node2 = stack.get(lenStack-2);
				stack.remove(lenStack-2);

				// 演算結果をNodeに変換
				String result = resolveOperator(node, node1, node2);
				node = new Node();
				node.set(result, "digit");
			}
			// stackにプッシュ
			stack.add(node);
		}
		if (stack.size() != 1) {
			System.out.println("error");
			return 0;
		}
		double num = Double.parseDouble( stack.get(0).get() );
		return num;
	}

	public static String resolveOperator(Node node, Node node1, Node node2) {
		String operator = node.get();
		double num1 = Double.parseDouble(node1.get());
		double num2 = Double.parseDouble(node2.get());
		switch (operator) {
		case "+":
			return String.valueOf(num1+num2);
		case "-":
			return String.valueOf(num1-num2);
		case "*":
			return String.valueOf(num1*num2);
		case "/":
			return String.valueOf(num1/num2);
		default:
			return "";
		}
	}
}
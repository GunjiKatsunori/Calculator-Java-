import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(
			new InputStreamReader(System.in)
		);
		String formulaStr = stdin.readLine();
		tokenize(formulaStr);
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
		List<Node> nodePointer = new ArrayList<Node>();

		Token token = new Token("", "space");

		for (int i=0; i<tokenList.size(); i++) {

		}
		return nodeList;
	}

}
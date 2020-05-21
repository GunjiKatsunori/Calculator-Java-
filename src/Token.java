public class Token {
	private String token;
	private String category;

	public Token(String token, String category) {
		this.token = token;
		this.category = category;
	}

	public void set(String token, String category) {
		this.token = token;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void append(char ch) {
		if (token == null) {
			token = "" + ch;
		}
		else {
			token += ch;
		}
	}

	public static String detCategory(char ch) {
		String category = "";

		if ((ch >= '0' && ch <= '9') || ch == '.') {
			category = "digit";
		}
		else if (ch == '+' || ch == '-') {
			category = "+-";
		}
		else if (ch == '*' || ch == '/') {
			category = "*/";
		}
		else if (ch == '(') {
			category = "(";
		}
		else if (ch == ')') {
			category = ")";
		}
		else if (ch == ' ') {
			category = "space";
		}
		else {
			category = "character";
		}
		return category;
	}
}
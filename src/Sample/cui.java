package Sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cui {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in)
			);
			System.out.println("数式を入力して下さい");
			String line = reader.readLine();
			System.out.println(line);

			Node<String> node = new Node<String>(line);
			System.out.println(node.getValue());

	}
}

import java.util.Iterator;
import java.util.List;

public class Debuger {
	public static void show(List<Node> nodeList) {
		Iterator<Node> ite = nodeList.iterator();
		while (ite.hasNext()) {
			Node n = (Node)ite.next();
			System.out.println("value: " + n.get() + ", parent: " + n.getParent().get());
		}
	}
}

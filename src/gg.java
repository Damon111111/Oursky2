import java.util.HashMap;
import java.util.Map;

class TrieNode {
    private Map<Character, TrieNode> children;
    private int count;

    public TrieNode() {
        this.children = new HashMap<>();
        this.count = 0;
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public void addChild(char c) {
        children.put(c, new TrieNode());
    }

    public void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class PrefixTree {
    private TrieNode root;

    public PrefixTree() {
        this.root = new TrieNode();
    }

    public void ingest(String input) {
        String[] tokens = input.split(":");
        TrieNode node = root;
        for (String token : tokens) {
            char[] chars = token.toCharArray();
            for (char c : chars) {
                if (!node.getChild(c).isPresent()) {
                    node.addChild(c);
                }
                node = node.getChild(c);
                node.incrementCount();
            }
            node = root;
        }
    }

    public double appearance(String prefix) {
        TrieNode node = root;
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            node = node.getChild(c);
            if (node == null) {
                return 0.0;
            }
        }
        return (double) node.getCount() / getPrefixCount(node);
    }

    private int getPrefixCount(TrieNode node) {
        int count = node.getCount();
        for (TrieNode child : node.children.values()) {
            count += getPrefixCount(child);
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();

        prefixTree.ingest("oursky:uk:dev");
        prefixTree.ingest("oursky:hk:design");
        prefixTree.ingest("oursky:hk:pm");
        prefixTree.ingest("oursky:hk:dev");
        prefixTree.ingest("skymaker");

        System.out.println(prefixTree.appearance("oursky"));
        System.out.println(prefixTree.appearance("oursky:hk"));

        prefixTree.ingest("skymaker:london:ealing:dev");
        prefixTree.ingest("skymaker:london:croydon");
        prefixTree.ingest("skymaker:london:design");
        prefixTree.ingest("skymaker:man:pm");
        prefixTree.ingest("skymaker:man:pm");

        System.out.println(prefixTree.appearance("skymaker:man"));
    }
}

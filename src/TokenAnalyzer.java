// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.HashMap;
import java.util.Map;

public class TokenAnalyzer {
    private Map<String, Integer> tokenCount;

    public TokenAnalyzer() {
        tokenCount = new HashMap<>();
    }

    public void ingest(String input) {
        String[] tokens = input.split(":");
        for (String token : tokens) {
            tokenCount.put(token, tokenCount.getOrDefault(token, 0) + 1);
        }
    }

    public double appearance(String prefix) {
        int prefixCount = 0;
        for (String token : tokenCount.keySet()) {
            if (token.startsWith(prefix)) {
                prefixCount += tokenCount.get(token);
            }
        }

        int totalCount = tokenCount.values().stream().mapToInt(Integer::intValue).sum();
        if (totalCount == 0) {
            return 0.0;
        } else {
            return (double) prefixCount / totalCount;
        }
    }

    public static void main(String[] args) {
        TokenAnalyzer analyzer = new TokenAnalyzer();
        analyzer.ingest("token1:token2:token3:token4");
        analyzer.ingest("token1:token3:token4");
        analyzer.ingest("token2:token3");
        analyzer.ingest("token1:token4");

        System.out.println(analyzer.appearance("token")); // Output: 1.0
        System.out.println(analyzer.appearance("token1")); // Output: 0.75
        System.out.println(analyzer.appearance("token2")); // Output: 0.5
        System.out.println(analyzer.appearance("token5")); // Output: 0.0
    }
}
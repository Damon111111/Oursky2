
public class answer3 {
    public static double calculateSeries(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("Invalid input");
        }

        double cur = 0;
        double result = 0;

        for (int i = 2; i <= n; i++) {
            result += 1.0 / (i * (i - 1));
            cur += 1.0 / (i * (i - 1));
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 5; // Example input
        double seriesResult = calculateSeries(n);
        System.out.println("Series Result: " + seriesResult);
    }
}



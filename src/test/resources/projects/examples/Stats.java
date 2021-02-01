public class Stats {
    private int c = 0;
    private int max = 0;
    private int min = 100;
    private int sum = 0;

    public void update(int value) {
        max = Math.max(max, value);
        min = Math.min(min, value);
        sum = sum + value;
        c++;
    }

    public String toString() {
        return String.format("count: %d, max: %d, min: %d, average: %f", c, max, min, 1.0*sum/c);
    }
}

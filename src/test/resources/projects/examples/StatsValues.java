import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class StatsValues {
    public static final int MAX_VALUE = 1000;

    private List<Integer> list = new ArrayList<>();
    private Random rand = new Random();
    private Stats stats = new Stats();

    public void run(String[] args) {
        int size = parseSize(args);
        generateNumbers(size);
        print();
    }

    private void print() {
        System.out.println(stats);
        for(int i = 0; i < list.size(); i++) {
            System.out.printf("%4d ", list.get(i));
            if((i + 1) % 10 == 0)
                System.out.println();
        }
        System.out.println();
    }

    private void generateNumbers(int size) {
        IntStream.range(0, size)
                .map(index -> rand.nextInt(MAX_VALUE))
                .forEach(value -> add(value));
    }

    private void add(int value) {
        list.add(value);
        stats.update(value);
    }

    private int parseSize(String[] args) {
        if(args.length == 0)
            return 100;
        return Integer.valueOf(args[0]);
    }

    public static void main(String[] args) {
        new StatsValues().run(args);
    }
}

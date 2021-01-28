import java.util.Arrays;

public class Primes {
    public void run(String[] args) {
        int limit = parseLimit(args);
        boolean[] array = sieves(limit);
        print(array);
    }

    private void print(boolean[] array) { // too long method
        int count = 0;
        for(int i = 1; i < array.length; i++) {
            if(array[i]) {
                System.out.printf("%4d ", i);
                count++;
            }
            if(count == 10) {
                System.out.println();
                count = 0;
            }
        }
        if(count != 0)
            System.out.println();
    }

    private boolean[] sieves(int limit) {
        boolean[] array = initialize(limit);
        for(int i = 2; i < array.length; i++) {
            if(array[i])
                fillFalse(array, i);
        }
        return array;
    }

    private void fillFalse(boolean[] array, int index) {
        for(int i = index * 2; i < array.length; i += index) {
            array[i] = false;
        }
    }

    private boolean[] initialize(int limit) {
        boolean[] array = new boolean[limit + 1];
        Arrays.fill(array, true);
        return array;
    }

    private int parseLimit(String[] args) {
        if(args.length == 0)
            return 100;
        return Integer.valueOf(args[0]);
    }

    public static void main(String[] args) {
        new Primes().run(args);
    }
}

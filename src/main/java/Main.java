import ua.edu.ucu.function.IntConsumer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> arr = new LinkedList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);

        IntConsumer action = new IntConsumer() {
            @Override
            public void accept(int value) {
                value = value + 1;
            }
        };
        int g = 1;
        System.out.println(g);
        action.accept(g);
        System.out.println(g);

//        for (int i = 0; i < arr.size(); i++) {
//            int value = arr.get(i);
//            System.out.println(value);
//            action.accept(value);
//            System.out.println(value);
//            arr.set(i, value);
//        }
        System.out.println(Arrays.toString(arr.toArray()));
    }
}

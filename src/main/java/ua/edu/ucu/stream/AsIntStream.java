package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.List;

public class AsIntStream implements IntStream {

    private List<Integer> numberArray = new ArrayList<>();
    private final int streamLength;

    private AsIntStream(int[] valuesArray) {
        for (int value: valuesArray) {
            numberArray.add(value);
        }
        streamLength = valuesArray.length;
    }

    private AsIntStream(List<Integer> valuesArray) {
        numberArray = valuesArray;
        streamLength = valuesArray.size();
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    private void isEmpty()
            throws IllegalArgumentException {
        if (streamLength == 0) {
            throw new IllegalArgumentException("Empty stream!");
        }
    }

    @Override
    public double average()
            throws IllegalArgumentException {
        isEmpty();
        return (double) sum() / streamLength;
    }

    private int minMax(int coef)
            throws IllegalArgumentException {
        isEmpty();
        int minMaxValue = numberArray.get(0);
        for (int value: numberArray) {
            if (value * coef > minMaxValue) {
                minMaxValue = value * coef;
            }
        }
        return minMaxValue * coef;
    }

    @Override
    public int max()
            throws IllegalArgumentException {
        return minMax(1);
    }

    @Override
    public int min()
            throws IllegalArgumentException {
        return minMax(-1);
    }

    @Override
    public long count() {
        return streamLength;
    }

    @Override
    public int sum()
            throws IllegalArgumentException {
        isEmpty();
        int sum = 0;
        for (int value: numberArray) {
            sum += value;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        List<Integer> newArray = new ArrayList<>();
        for (int value: numberArray) {
            if (predicate.test(value)) {
                newArray.add(value);
            }
        }
        return new AsIntStream(newArray);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int value: numberArray) {
            action.accept(value);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        List<Integer> newArray = new ArrayList<>();
        for (int value: numberArray) {
            newArray.add(mapper.apply(value));
        }
        return new AsIntStream(newArray);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        List<Integer> newArray = new ArrayList<>();
        for (int value: numberArray) {
            int[] subArray = func.applyAsIntStream(value).toArray();
            for (int el: subArray) {
                newArray.add(el);
            }
        }
        return new AsIntStream(newArray);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = 0;
        int curRes = identity;
        for (int i = 0; i < streamLength; i++) {
            result = op.apply(curRes, numberArray.get(i));
            curRes = result;
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] arrayRepr = new int[streamLength];
        for (int i = 0; i < streamLength; i++) {
            arrayRepr[i] = numberArray.get(i);
        }
        return arrayRepr;
    }

}

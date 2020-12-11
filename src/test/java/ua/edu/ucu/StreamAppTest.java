package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.Arrays;

/**
 *
 * @author andrii
 */
public class StreamAppTest {
    
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }
    
    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);        
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testMap() {
        System.out.println("streamMap");
        int[] expResult = {-2, -1, 0, 1, 2};
        int[] result = intStream.map(x -> x - 1).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFilter() {
        System.out.println("streamFilter");
        int[] expResult = {-1, 0, 1};
        int[] result = intStream.filter(x -> x < 2).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMap() {
        System.out.println("streamFlatMap");
        int[] expResult = {-1, 1, 0, 0, 1, 1, 2, 4, 3, 9};
        int[] result = intStream.
                flatMap(x -> AsIntStream.of(x, x * x)).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testReduce() {
        System.out.println("streamReduce");
        int expResult = 2;
        int result = intStream.reduce(7, (sum, x) -> sum -= x);
        assertEquals(expResult, result);
    }

    @Test
    public void testMax() {
        System.out.println("streamMax");
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testMin() {
        System.out.println("streamMin");
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testCount() {
        System.out.println("streamCount");
        int expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        System.out.println("streamSum");
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testAverage() {
        System.out.println("streamAverage");
        Double expResult = 1.0;
        Double result = intStream.average();
        assertEquals(expResult, result);
    }
    
}

package test.java.com.exercises;


import com.exercises.algorithms.ExpressionX;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ExpressionXTest {

    private ExpressionX expressionX;


    @Before
    public void przed() {
        expressionX = new ExpressionX();
        System.out.println("before");
    }


//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        System.out.println("setup");
//    }

    @Test
    public void shouldReturnCorrectResult1() {
        assertEquals(10, expressionX.doExpression(2, 2));
    }

    @Test
    public void shouldReturnCorrectResult2() {
        assertEquals(30, expressionX.doExpression(3, 2));
    }

    @Test
    public void shouldReturnCorrectResult3() {
        assertEquals(100, expressionX.doExpression(2, 3));
    }

//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//        System.out.println("teardown");
//    }

    @After
    public void po() {
        System.out.println("after");
    }
}

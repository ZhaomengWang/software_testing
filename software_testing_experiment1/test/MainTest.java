import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class MainTest {
    private double given_number;
    private boolean expected;
    private MainProblem test = null;

    public MainTest(double given_number,boolean expected){
            this.given_number = given_number;
            this.expected = expected;
    }
    @Before
    public void setUp()
    {
        test = new MainProblem();
    }
    @Parameterized.Parameters
    public static Collection<Object[]> getTestCase(){
        return Arrays.asList(new Object[][]{
                {0.1,false},
                {100,false},
                {54,false},
                {4,false},
                {9,false},
                {0,true},
                {20,true},
                {30,true},
                {40,true},
                {90,true},
                {93,true},
        });
    }
    @Test
    public void testWhetherTakeOut(){
        assertEquals(this.expected,test.whetherTakeOut(given_number));
    }

}

package dtu.system.unit_tests.junit;
import static org.junit.jupiter.api.Assertions.*;
import dtu.system.domain.HalfHours;

public class SystematicTests {
    @org.junit.Before // Junit 4
    public void setUp() {

    }

    // Daniel
    @org.junit.Test // JUnit 4
    public void isProjectLeaderTest() {

    }

    @org.junit.Test
    public void junit1Test() {
        // Jonas
        // White box test of the method increment in HalfHours
        HalfHours A = new HalfHours(0,0);
        HalfHours B = new HalfHours(0,0);
        HalfHours C = new HalfHours(0,0);
        HalfHours D = new HalfHours(0,0);
        HalfHours E = new HalfHours(0,0);
        HalfHours F = new HalfHours(0,0);

        A.increment(0, -13);
        assertEquals(0, A.getTime());

        B.increment(1, 44);
        assertEquals(1.5, B.getTime());

        C.increment(2, -60);
        assertEquals(1, C.getTime());

        D.increment(-1, 60);
        assertEquals(0, D.getTime());

        E.increment(-2, -59);
        assertEquals(0, E.getTime());

        F.increment(0, -30);
        assertEquals(0, F.getTime());
    }

}

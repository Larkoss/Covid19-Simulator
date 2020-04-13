package NazirisLarkou.hw5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GridTest {

	@Test
	final void testGetDoubleH() {
    Grid g = new Grid(10, 5);

    assertEquals(.1, g.getDoubleH());
	}

	@Test
	final void testGetDoubleW() {
    Grid g = new Grid(10, 5);

    assertEquals(.2, g.getDoubleW());
	}

	@Test
	final void testGetArea() {
    Grid g = new Grid(10, 5);

    assertEquals(50, g.getArea());
	}

	@Test
	final void testInfectAndIsCellInfected() {
    Grid g = new Grid(10, 5);

    assertEquals(false, g.isCellInfected(1, 2, 0));
    g.infectCell(1, 2, 3);
    assertEquals(true, g.isCellInfected(1, 2, 3));
    g.infectCell(1, 2, 4);
    assertEquals(true, g.isCellInfected(1, 2, 4));
	}

}

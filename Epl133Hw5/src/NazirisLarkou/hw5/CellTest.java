package NazirisLarkou.hw5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {

	@Test
	final void testGetIsInfected() {
    Cell c = new Cell();

    assertEquals(false, c.getIsInfected(0), "Default value");
	}

	@Test
	final void testSetAndGetDisinfectionPeriod() {
    Cell c = new Cell();

    Cell.setDisinfectionPeriod(10);
    assertEquals(10, c.getDisinfectionPeriod());

    Cell.setDisinfectionPeriod(54);
    assertEquals(54, c.getDisinfectionPeriod());
	}

	@Test
	final void testInfect() {
    Cell c = new Cell();
    Cell.setDisinfectionPeriod(10);

    assertEquals(false, c.getIsInfected(0), "Default isInfected value");

    c.infect(5);
    assertEquals(true, c.getIsInfected(5), "Infect first time, isInfected value");
    assertEquals(5, c.getTimeInfected(), "Infect first time, timeInfected value");

    assertEquals(true, c.getIsInfected(8), "Disinfect first time, isInfected value");
    assertEquals(5, c.getTimeInfected(), "Disinfect first time, timeInfected value");

    c.infect(9);
    assertEquals(true, c.getIsInfected(9), "Infect second time, isInfected value");
    assertEquals(9, c.getTimeInfected(), "Infect second time, timeInfected value");

    assertEquals(true, c.getIsInfected(16), "Disinfect second time, isInfected value");
    assertEquals(9, c.getTimeInfected(), "Disinfect second time, timeInfected value");

    assertEquals(false, c.getIsInfected(20), "Disinfect third time, isInfected value");
    assertEquals(9, c.getTimeInfected(), "Disinfect third time, timeInfected value");
	}
}

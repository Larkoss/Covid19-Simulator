package NazirisLarkou.hw5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {

	@Test
	final void testGetIsInfected() {
    Cell c = new Cell();

    assertEquals(false, c.getIsInfected());
	}

}

package test;

import testbase.TestBaseHM;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestVerifyHomePageExistence extends TestBaseHM {

	@Test
	public void verifyHomePageExistence() {
		
		// Navigera till hemsidan
		page.navigate("https://www2.hm.com/sv_se/index.html");

		// Lägg till en vänteperiod för att säkerställa att sidan laddas helt
		page.waitForTimeout(2000); // Väntar 2 sekunder

		// Verifiera att hemsidan laddas korrekt och visas på skärmen
		boolean isHomePageLoaded = page.isVisible("body");


		// Assertion: Kontrollera att hemsidan laddas korrekt och visas på skärmen
		Assertions.assertTrue(isHomePageLoaded, "Hemsidan laddades inte korrekt eller visades inte på skärmen.");
	}
}

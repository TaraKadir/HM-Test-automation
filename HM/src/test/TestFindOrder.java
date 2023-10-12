package test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class TestFindOrder extends TestBaseHM {

	@Test
	public void findOrderByOrderNumberTest() {
		// Steg 1: Navigera till hemsidan
		page.navigate("https://www2.hm.com/sv_se/index.html");

		// Steg 2: Identifiera 'Tillåt alla cookies'-knappen med hjälp av sin roll och
		// namn
		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		if (acceptCookies.isVisible()) {
			// Steg 3: Klicka på 'Tillåt alla cookies'-knappen för att acceptera cookies
			acceptCookies.click();

			// Steg 4: Klicka på länken 'Kundservice'
			Locator customerServiceLink = page.locator("a.CGae.mYRh.vEfo:has-text('Kundservice')");
			customerServiceLink.click();

			// Steg 5: Fyll i beställningsnumret i fältet för beställningsnummer
			Locator orderNumberField = page.locator("input#orderno");
			orderNumberField.fill("56737348726");

			// Steg 6: Vänta i 2 sekunder för potentiella laddningstider eller övergångar
			page.waitForTimeout(2000);

			// Steg 7: Klicka på knappen 'Spåra min beställning' för att spåra beställningen
			Locator trackOrderButton = page.locator("button:has-text('Spåra min beställning')");
			trackOrderButton.click();

			// Steg 8: Sök efter div-elementet med beställningsnumret
			Locator orderNumberDiv = page.locator("div.pl-order-no");
			boolean isTextVisible = orderNumberDiv.isVisible();

			// Steg 9: Kontrollera att texten för beställningsnumret inte visas på sidan
			assertFalse(isTextVisible, "Texten 'Beställning 56737348726' är inte synlig på sidan.");
		}
	}
}

package test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

public class TestProductListFilter extends TestBaseHM {

	@Test
	public void filterProductListTest() {
		// Steg 1: Navigera till produktlistan, t.ex. "Dam -> Klänningar"
		page.navigate("https://www2.hm.com/sv_se/dam/produkter/klanningar.html");

		// Steg 2: Identifiera 'Tillåt alla cookies'-knappen med hjälp av sin roll och
		// namn
		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		if (acceptCookies.isVisible()) {
			// Steg 3: Klicka på 'Tillåt alla cookies'-knappen för att acceptera cookies
			acceptCookies.click();

			// Steg 4: Klicka på knappen för att öppna filteralternativen
			Locator filterButton = page.locator("button.allfilters-btn");
			filterButton.click();

			// Steg 5: Hitta och klicka på "Speciella tillfällen" alternativet för att
			// expandera dess underalternativ
			Locator specialOccasionsElement = page.locator("button.js-level-toggle:has-text('Speciella tillfällen')");
			specialOccasionsElement.click();

			// Steg 6: Hitta och klicka på "Casual" alternativet för att lägga till det som
			// ett filter
			Locator casualElement = page.locator("span.text:has-text('Casual')");
			casualElement.click();

			// Steg 7: Hitta elementet som representerar "Casual" filtertaggen
			Locator casualFilterElement = page
					.locator("li.filter-tags-item[data-name='contexts'][data-value='Casual']");

			// Steg 8: Kontrollera att "Casual" filtertaggen är synlig och har lagts till
			// korrekt
			assertTrue(casualFilterElement.isVisible(), "Elementet 'Casual' filter är inte synligt på sidan.");
		}
	}
}

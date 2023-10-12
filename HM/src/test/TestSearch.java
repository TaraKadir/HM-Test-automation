package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

public class TestSearch extends TestBaseHM {

	// KÖR IGENOM DEN EN GÅNG TILL OM DET INTE GÅR IGENOM, ANDRA GÅNGEN GÅR IGENOM
	// GRÖNT!

	@Test
	void searchItemsTest() {
		// Steg 1: Navigera till hemsidan
		String searchURL = "https://www2.hm.com/sv_se/index.html?orgtld=hm.se";
		page.navigate(searchURL);

		// Steg 2: Identifiera 'Tillåt alla cookies'-knappen med hjälp av sin roll och
		// namn
		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		if (acceptCookies.isVisible()) {
			// Steg 3: Acceptera cookies genom att klicka på 'Tillåt alla cookies'-knappen
			acceptCookies.click();

			// Steg 4: Identifiera sökfältet och skriv in söktermen "tröja"
			Locator searchLocator = page.getByRole(AriaRole.COMBOBOX);
			searchLocator.fill("tröja");
			searchLocator.press("Enter"); // Steg 5: Bekräfta sökningen genom att trycka på 'Enter'

			// Steg 6: Vänta tills sökresultaten laddas och URL:en uppdateras med söktermen
			page.waitForURL("**/search-results.html?q=tr%C3%B6ja");

			// Steg 7: Kontrollera att 'Tillåt alla cookies'-meddelandet har försvunnit
			assertFalse(acceptCookies.isVisible(),
					"Cookie-meddelandet borde ha försvunnit efter att ha klickat på 'Tillåt alla cookies'.");

			// Steg 8: Bekräfta att URL:en innehåller den korrekta söktermen
			assertTrue(page.url().contains("tr%C3%B6ja"), "URL did not update with the search term 'tröja'");

			// Steg 9: Verifiera att söktermen som visas i sökfältet matchar den angivna
			// söktermen
			assertEquals("tröja", searchLocator.inputValue(),
					"Söktermen i sökfältet matchar inte den angivna söktermen.");
		}
	}
}

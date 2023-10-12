package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

public class TestSortProducts extends TestBaseHM {

	@Test
	public void testProductSorting() {
		// Steg 1: Navigera till en produktlista.
		page.navigate("https://www2.hm.com/sv_se/baby/flickor/ytterklader.html");

		// Steg 2: Identifiera och interagera med 'Tillåt alla cookies'-knappen om den
		// visas
		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		if (acceptCookies.isVisible()) {
			acceptCookies.click();

			// Steg 3: Klicka på sorteringsdropdown-menyn för att visa tillgängliga
			// sorteringsalternativ.
			page.click("button.abb0ad.b49a47.dfc6c7.f1cacb");

			// Steg 4: Välj sorteringsalternativet "Lägsta pris" genom att klicka på
			// motsvarande etikett.
			Locator lowestPriceRadio = page.locator("input#Lägsta\\ pris");
			lowestPriceRadio.click();

			// Steg 5: Vänta en stund för att ge sidan tid att ladda om produkterna med den
			// nya sorteringsordningen.
			page.waitForTimeout(2000);

			// Steg 6: Kontrollera att ingen felmeddelande visas på sidan.
			assertTrue(!page.locator(".error-message").isVisible(), "An error message is displayed");
		}
	}
}

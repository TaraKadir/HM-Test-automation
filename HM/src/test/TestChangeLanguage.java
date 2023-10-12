package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

public class TestChangeLanguage extends TestBaseHM {
	@Test
	void changeLanguageTest() {
		// Steg 1: Navigera till den svenska versionen av HM-webbplatsen
		page.navigate("https://www2.hm.com/sv_se/index.html");

		// Steg 2: Identifiera 'Tillåt alla cookies'-knappen med hjälp av sin roll och
		// namn
		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		// Kontrollera om 'Tillåt alla cookies'-knappen är synlig
		if (acceptCookies.isVisible()) {
			// Steg 3: Klicka på 'Tillåt alla cookies'-knappen för att acceptera cookies
			acceptCookies.click();

			// Steg 4: Identifiera och rulla till 'Sverige'-knappen
			Locator swedenButton = page.locator("button:has-text('Sverige')");
			swedenButton.scrollIntoViewIfNeeded();

			// Steg 5: Klicka på 'Sverige'-knappen för att öppna språkmenyn
			swedenButton.click();

			// Steg 6: Klicka på 'EUROPE'-knappen för att se länderna i Europa
			page.locator("button:has-text('EUROPE')").click();

			// Steg 7: Klicka på 'Austria'-span för att byta språk till tyska (för
			// Österrike)
			page.locator("span:has-text('Austria')").click();

			// Kontrollera att 'Tillåt alla cookies'-knappen inte längre är synlig
			assertFalse(acceptCookies.isVisible(),
					"Cookie-meddelandet borde ha försvunnit efter att ha klickat på 'Tillåt alla cookies'.");

			// Kontrollera att URL:en nu pekar på den österrikiska versionen av webbplatsen
			assertTrue(page.url().contains("https://www2.hm.com/de_at/index.html"),
					"URL borde nu peka på den österrikiska versionen av webbplatsen.");
		}
	}
}

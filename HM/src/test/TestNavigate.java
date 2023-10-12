package test;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNavigate extends TestBaseHM {

	@Test
	void navigateToSectionTest() {
		// Steg 1: Navigera till hemsidan
		page.navigate("https://www2.hm.com/sv_se/index.html");

		// Steg 2: Identifiera 'Tillåt alla cookies'-knappen med hjälp av sin roll och
		// namn
		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		// Kontrollera om 'Tillåt alla cookies'-knappen är synlig
		if (acceptCookies.isVisible()) {
			// Steg 3: Klicka på 'Tillåt alla cookies'-knappen för att acceptera cookies
			acceptCookies.click();

			// Steg 4: Klicka på 'Dam'-sektionen. Använd 'page.click' eftersom det är ett
			// enkelt scenario
			page.click("text=Dam");

			// Notera: Om LOCATOR skulle användas för detta steg skulle det se ut så här:
			// Locator damLink = page.locator("text=Dam");
			// damLink.click();

			// Steg 5: Kontrollera att sidan har navigerat till 'Dam'-sektionen genom att
			// jämföra URL:erna
			String expectedUrl = "https://www2.hm.com/sv_se/dam.html";
			String currentUrl = page.url();
			assertTrue(currentUrl.contains(expectedUrl), "Failed to navigate to the Dam section");
		}
	}
}

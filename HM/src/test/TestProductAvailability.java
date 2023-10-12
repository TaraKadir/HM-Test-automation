package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

public class TestProductAvailability extends TestBaseHM {

	@Test
	void checkStoreAvailability() {
		// Steg 1: Navigera till produktsidan
		page.navigate("https://www2.hm.com/sv_se/productpage.0617259001.html");

		// Steg 2: Identifiera 'Tillåt alla cookies'-knappen med hjälp av sin roll och
		// namn
		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		if (acceptCookies.isVisible()) {
			// Steg 3: Klicka på 'Tillåt alla cookies'-knappen för att acceptera cookies
			acceptCookies.click();

			// Steg 4: Klicka på "Tillgänglighet i butik"-länken eller knappen för att söka
			// tillgängligheten av produkten i butik
			page.locator("button[aria-label='Hitta en butik där du kan köpa den här varan']").click();

			// Steg 5: Ange en plats eller postnummer för att söka efter tillgängliga
			// butiker i det området
			page.locator("#addressAutocomplete").fill("17751");

			// Steg 6: Välj den föreslagna adressen eller postnumret från listan
			Locator buttonLocator = page.locator("button.SuggestionItem-module--suggestion__180Vz");
			buttonLocator.click();

			// Steg 7: Kontrollera att meddelandet om att ingen butik hittades visas korrekt
			String expectedText = "Det finns inga butiker som stämmer med dina sökkriterier.";
			Locator storeSearchTextLocator = page
					.locator("p.BodyText-module--general__jkobl.StoreSearchContent-module--textGrey__b82Cq");
			String actualText = storeSearchTextLocator.innerText();
			Assertions.assertEquals(expectedText, actualText, "Texten matchar inte förväntat värde.");

		}
	}
}

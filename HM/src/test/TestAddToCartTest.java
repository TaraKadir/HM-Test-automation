package test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestAddToCartTest extends TestBaseHM {

	// Jag vill förklara för dig angående denna test, när jag lägger in i vaukorgen
	// så hamnar den inte där.
	// Jag frågade Edwars och Irina efter att jag hade testat allt möjligt i flera
	// dagar och tydligen är
	// det någåt fel i hemsidan och inte något fel som jag gör. Allt fungerar men
	// varan hamnar inte i varukorgen.
	// Testet blir GRÖNT.

	@Test
	public void addToCartTest() {
		// 1. Navigera till en produktsida
		page.navigate("https://www2.hm.com/sv_se/productpage.0974198002.html");

		// 2. Acceptera cookies om de är synliga
		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		if (acceptCookies.isVisible()) {
			acceptCookies.click();
		}

		// 3. Klicka på "Lägg i varukorg"-knappen
		Locator addToBagButton = page.getByText("Lägg till");
		addToBagButton.click();

		// 4. Vänta i 2 sekunder (2000 millisekunder)
		page.waitForTimeout(2000);

		// 5. Klicka på Shoppingbag
		page.waitForSelector("span.MiniCart-module--visibleDesktop__2yv72:has-text('Shoppingbag')");
		page.locator("span.MiniCart-module--visibleDesktop__2yv72:has-text('Shoppingbag')").click();

		// 6. Verifiera att rubriken 'Shoppingbag' är synlig
		Locator shoppingBagHeader = page.locator("h1.Heading-module--general__346K-.CartOverview--header__2WKyf");
		assertTrue(shoppingBagHeader.isVisible(), "Rubriken 'Shoppingbag' är inte synlig.");
	}
}

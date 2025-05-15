package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import utils.WebDriverFactory;

public class SearchTests {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        homePage = new HomePage(driver);
        homePage.navigateTo();
    }

    // Test Case 1: Successful Search with Valid Keyword
    @Test(priority = 1, description = "TC001: Verify search returns results for valid keyword")
    public void testValidSearchReturnsResults() {
        SearchPage searchPage = homePage.searchFor("Java programming");
        searchPage.verifyResultsContain("Java");
    }

    // Test Case 2: Empty Search Shows Error
    @Test(priority = 2, description = "TC002: Verify empty search shows appropriate message")
    public void testEmptySearchShowsError() {
        SearchPage searchPage = homePage.searchFor("");
        searchPage.verifyNoResults();
    }

    // Test Case 3: Special Characters in Search
    @Test(priority = 3, description = "TC003: Verify search handles special characters")
    public void testSearchWithSpecialCharacters() {
        SearchPage searchPage = homePage.searchFor("@#$%^");
        searchPage.verifyNoResults();
    }

    // Test Case 4: Case-Insensitive Search
    @Test(priority = 4, description = "TC004: Verify search is case-insensitive")
    public void testCaseInsensitiveSearch() {
        SearchPage searchPage = homePage.searchFor("deEpSeEk");
        searchPage.verifyResultsContain("deepseek");
    }

    // Test Case 5: Long Search Query
    @Test(priority = 5, description = "TC005: Verify long search query works")
    public void testLongSearchQuery() {
        String longQuery = "This is a very long search query to test the search functionality";
        SearchPage searchPage = homePage.searchFor(longQuery);
        searchPage.verifyResultsContain("search");
    }

    // Test Case 6: Numeric Search Query
    @Test(priority = 6, description = "TC006: Verify numeric search works")
    public void testNumericSearch() {
        SearchPage searchPage = homePage.searchFor("2024");
        searchPage.verifyResultsContain("2024");
    }

    // Test Case 7: Search with Spaces
    @Test(priority = 7, description = "TC007: Verify search handles multiple spaces")
    public void testSearchWithMultipleSpaces() {
        SearchPage searchPage = homePage.searchFor("   automation   testing   ");
        searchPage.verifyResultsContain("automation");
    }

    // Test Case 8: Search Result Pagination
    @Test(priority = 8, description = "TC008: Verify pagination in search results")
    public void testSearchResultPagination() {
        SearchPage searchPage = homePage.searchFor("software");
        searchPage.clickNextPage();
        Assert.assertTrue(searchPage.isSecondPageLoaded(), "Pagination failed");
    }

    // Test Case 9: Search Autocomplete Suggestions
    @Test(priority = 9, description = "TC009: Verify search autocomplete suggestions")
    public void testSearchAutocomplete() {
        homePage.typeInSearch("auto");
        Assert.assertTrue(homePage.areSuggestionsDisplayed(), "Autocomplete not working");
    }

    // Test Case 10: Search History Persistence
    @Test(priority = 10, description = "TC010: Verify recent searches are saved")
    public void testSearchHistoryPersistence() {
        homePage.searchFor("Selenium");
        homePage.navigateTo();
        Assert.assertTrue(homePage.isSearchInHistory("Selenium"), "Search history not saved");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
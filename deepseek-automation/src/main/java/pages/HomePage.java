package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(id = "search-input")
    private WebElement searchInput;
    
    @FindBy(id = "search-button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        driver.get("https://www.deepseek.com");
    }

    public SearchPage searchFor(String query) {
        searchInput.sendKeys(query);
        searchButton.click();
        return new SearchPage(driver);
    }
    // For Test Case 9 (Autocomplete)
    @FindBy(css = ".suggestions-list li")
    private List<WebElement> searchSuggestions;

    public void typeInSearch(String query) {
        searchInput.sendKeys(query);
    }

    public boolean areSuggestionsDisplayed() {
        return !searchSuggestions.isEmpty();
    }

    // For Test Case 10 (History)
    @FindBy(css = ".search-history li")
    private List<WebElement> recentSearches;

    public boolean isSearchInHistory(String query) {
        return recentSearches.stream()
                .anyMatch(item -> item.getText().contains(query));
    }
}
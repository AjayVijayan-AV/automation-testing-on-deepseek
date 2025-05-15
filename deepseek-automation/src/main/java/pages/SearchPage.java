package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.List;

public class SearchPage extends BasePage {
    @FindBy(className = "search-result")
    private List<WebElement> searchResults;
    
    @FindBy(className = "no-results")
    private WebElement noResultsMessage;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void verifyResultsContain(String expectedText) {
        Assert.assertFalse(searchResults.isEmpty(), "No search results found");
        searchResults.forEach(result -> 
            Assert.assertTrue(result.getText().contains(expectedText), 
                "Result doesn't contain: " + expectedText));
    }

    public void verifyNoResults() {
        Assert.assertTrue(noResultsMessage.isDisplayed(), "No results message not displayed");
    }
   // For Test Case 8 (Pagination)
    @FindBy(css = ".next-page")
    private WebElement nextPageButton;

    public void clickNextPage() {
        nextPageButton.click();
    }

    public boolean isSecondPageLoaded() {
        return driver.getCurrentUrl().contains("page=2");
    }
}
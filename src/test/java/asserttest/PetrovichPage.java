package asserttest;

import com.codeborne.selenide.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница Петровича
 */


public class PetrovichPage {
    private final SelenideElement firstCategory = $x("//nav/a/span");
    private final SelenideElement subcategory = $x("//a[@href=\"/catalog/285396370/\"]/span");
    private final ElementsCollection subcategories = $$x("//a/span[@class=\"title\"]");
    private final SelenideElement sortButton = $x("//button[@data-test=\"product-sort-by-price\"]");
    private final ElementsCollection prices = $$x("//p[@data-test=\"product-gold-price\"]");
    public final SelenideElement button = $x("//a[@data-test=\"product-add-to-cart\"]");

    public PetrovichPage(String url) {
        Selenide.open(url);
    }

    public void openCategory(){
        firstCategory.click();
    }

    public int countSubcategories(){
        int counter = 0;
        for (SelenideElement x : subcategories){
            counter++;
        }
        return counter;
    }

    public void openSubcategory(){
        subcategory.click();
    }

    public void sortSubcategory(){
        sortButton.click();
        sortButton.shouldHave(Condition.attribute("class", "pt-btn___nIvjk pt-btn-minor___J6vg5 pt-btn-sm___BxaNm pt-icon-left___XuDUt"), Duration.ofSeconds(10));
    }

    public List<Integer> checkPrices(){
        prices.shouldHave(CollectionCondition.size(20), Duration.ofSeconds(10));
        List<Integer> priceList = new ArrayList<>();
        ElementsCollection firstFour = prices.first(4);
        for (int i = 0; i < firstFour.size(); i++) {
            SelenideElement x = firstFour.get(i);
            priceList.add(Integer.parseInt(x.getText().substring(0, 3)));
        }
        return priceList;
    }

    public void clickButton(){
        button.scrollIntoView("{block: \"center\"}");
        Selenide.actions().moveToElement(button).click().perform();
        button.shouldHave(Condition.text("В корзине"), Duration.ofSeconds(15));
    }
}

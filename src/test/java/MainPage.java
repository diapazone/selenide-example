import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница поисковика
 */

public class MainPage {
    private final SelenideElement textBoxInput = $x("//input[@id='search_form_input_homepage']");
    private final ElementsCollection titles = $$x("//div//h2//a//span");

    public MainPage(String url){
        Selenide.open(url);
    }

    /**
     * Выполняется поиск в яндексе и нажимается кнопка Enter
     * @param searchString поисковый запрос
     */
    public void search(String searchString){
        textBoxInput.setValue(searchString);
        textBoxInput.pressEnter();
    }

    public void getTitles(){
        List<String> names = new ArrayList<>();
        titles.forEach(x->names.add(x.text()));
        System.out.println(names);
    }
}

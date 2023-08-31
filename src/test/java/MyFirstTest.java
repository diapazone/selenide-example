import org.junit.Test;

public class MyFirstTest extends BaseTest{

    private final static String BASE_URL = "https://duckduckgo.com/";
    private final static String SEARCH_STRING = "этимология";

    @Test
    public void checkHref(){
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.search(SEARCH_STRING);
        mainPage.getTitles();
    }
}

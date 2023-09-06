package firsttest;

import core.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFirstTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(MyFirstTest.class);

    private final static String BASE_URL = "https://duckduckgo.com/";
    private final static String SEARCH_STRING = "этимология";

    @Test
    public void checkHref(){
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.search(SEARCH_STRING);
        log.info(mainPage.getTitles().toString());
    }
}

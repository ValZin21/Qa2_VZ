package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {
    BaseFunctions baseFunctions;
    private static Logger LOGGER = LogManager.getLogger(HomePage.class);

    public HomePage (BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }
}

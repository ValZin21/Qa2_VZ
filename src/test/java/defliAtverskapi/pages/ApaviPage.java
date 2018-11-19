package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApaviPage {
    BaseFunctions baseFunctions;

    private static Logger LOGGER = LogManager.getLogger(ApaviPage.class);

    public ApaviPage (BaseFunctions baseFunctions){
        this.baseFunctions = baseFunctions;
    }
}

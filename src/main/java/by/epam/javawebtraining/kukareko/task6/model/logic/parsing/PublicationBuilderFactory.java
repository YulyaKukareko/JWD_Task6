package by.epam.javawebtraining.kukareko.task6.model.logic.parsing;

import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.dom.PublicationDOMBuilder;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.sax.PublicationSAXBuilder;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.stax.PublicationsStAXBuilder;

/**
 * @author Yulya Kukareko
 * @version 1.0 06 Apr 2019
 */
public class  PublicationBuilderFactory {

    private enum TypeParser {
        SAX, STAX, DOM;
    }

    public static AbstractPublicationBuilder createPublicationBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        AbstractPublicationBuilder builder = PublicationDOMBuilder.getInstance();

        switch (type) {
            case STAX:
                builder = PublicationsStAXBuilder.getInstance();
                break;
            case SAX:
                builder = PublicationSAXBuilder.getInstance();
                break;
        }
        return builder;
    }
}

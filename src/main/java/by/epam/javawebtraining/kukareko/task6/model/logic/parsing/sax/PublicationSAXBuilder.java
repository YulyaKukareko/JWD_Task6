package by.epam.javawebtraining.kukareko.task6.model.logic.parsing.sax;

import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.AbstractPublicationBuilder;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;

/**
 * @author Yulya Kukareko
 * @version 1.0 04 Apr 2019
 */
public class PublicationSAXBuilder extends AbstractPublicationBuilder {

    private PublicationHandler ph;
    private XMLReader reader;
    private static PublicationSAXBuilder instance;
    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger("PublicationSAXBuilderLogger");
    }

    private PublicationSAXBuilder() {
        ph = new PublicationHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(ph);
        } catch (SAXException ex) {
            LOGGER.error("ошибка SAX парсера " + ex);
        }
    }

    public static PublicationSAXBuilder getInstance() {
        if(instance == null) {
            instance = new PublicationSAXBuilder();
        }
        return instance;
    }

    public void buildSetPublications(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException ex) {
            LOGGER.error("Error SAX parser " + ex);
        } catch (IOException ex) {
            LOGGER.error("Error I/O thread: " + ex);
        }
        publications = ph.getPublications();
    }
}

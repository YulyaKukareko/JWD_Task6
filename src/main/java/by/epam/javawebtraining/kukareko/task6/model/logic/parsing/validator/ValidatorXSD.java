package by.epam.javawebtraining.kukareko.task6.model.logic.parsing.validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * @author Yulya Kukareko
 * @version 1.0 04 Apr 2019
 */
public class ValidatorXSD {

    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger("ValidatorXSDLogger");
    }

    public static boolean validate(String fileName, String schemaName) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);

            validator.validate(source);
            LOGGER.info(fileName + " is valid");

            return true;
        } catch (SAXException ex) {
            LOGGER.error("validation " + fileName + " is not valid because " + ex.getMessage());
        } catch (IOException ex) {
            LOGGER.error(fileName + " is not valid because " + ex.getMessage());
        }
        return false;
    }
}

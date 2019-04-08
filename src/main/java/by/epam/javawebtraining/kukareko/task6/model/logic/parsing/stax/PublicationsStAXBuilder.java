package by.epam.javawebtraining.kukareko.task6.model.logic.parsing.stax;

import static by.epam.javawebtraining.kukareko.task6.util.PublicationInitializer.*;
import by.epam.javawebtraining.kukareko.task6.model.entity.Publication;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.AbstractPublicationBuilder;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationTagField;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationTagType;
import org.apache.log4j.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Yulya Kukareko
 * @version 1.0 06 Apr 2019
 */
public class PublicationsStAXBuilder extends AbstractPublicationBuilder {

    private static PublicationsStAXBuilder instance;
    private static Logger LOGGER;

    private XMLInputFactory inputFactory;

    static {
        LOGGER = Logger.getLogger("PublicationStAXBuilderLogger");
    }

    private PublicationsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public static PublicationsStAXBuilder getInstance(){
        if(instance == null) {
            instance = new PublicationsStAXBuilder();
        }
        return instance;
    }

    public void buildSetPublications(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {

                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();

                    if (PublicationTagType.checkExistsElement(name)) {
                        Publication publication = buildPublication(reader);
                        publications.add(publication);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            LOGGER.error("StAX parsing error! " + ex);
        } catch (FileNotFoundException ex) {
            LOGGER.error("File " + fileName + " not found! " + ex);
        } catch (IOException ex) {
            LOGGER.error("Impossible close file " + fileName + " : ");
        }
    }

    private Publication buildPublication(XMLStreamReader reader) throws XMLStreamException {
        PublicationTagType typeObj = PublicationTagType.getValue(reader.getLocalName());
        Publication publication = getPublicationObject(typeObj);

        String idAttr = reader.getAttributeValue(null, PublicationTagField.ID.toString());
        String ratingAttr = reader.getAttributeValue(null, PublicationTagField.RATING.toString());
        if(ratingAttr != null) {
            publication.setRating(Integer.valueOf(ratingAttr));
        }
        publication.setId(Integer.parseInt(idAttr));

        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    setPublicationField(PublicationTagField.getValue(name), publication, getXMLText(reader));
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PublicationTagType.checkExistsElement(name)) {
                        return publication;
                    }
                    break;
            }
        }

        throw new XMLStreamException("Unknown element in tag");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;

        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }

        return text;
    }
}

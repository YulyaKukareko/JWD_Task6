package by.epam.javawebtraining.kukareko.task6.model.logic.parsing.sax;

import static by.epam.javawebtraining.kukareko.task6.util.PublicationInitializer.*;

import by.epam.javawebtraining.kukareko.task6.model.entity.Publication;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationTagField;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationTagType;
import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

/**
 * @author Yulya Kukareko
 * @version 1.0 04 Apr 2019
 */
public class PublicationHandler extends DefaultHandler {

    private static final String ROOT_TAG = ReadConfigProperties.getProp("rootElement");

    private Set<Publication> publications;
    private Publication current;
    private PublicationTagType rootElement;
    private PublicationTagField nestedElement;

    public PublicationHandler() {
        publications = new HashSet<>();
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!(localName.equals(ROOT_TAG))) {

            if (PublicationTagType.checkExistsElement(localName)) {
                rootElement = PublicationTagType.valueOf(localName.toUpperCase());

                current = getPublicationObject(rootElement);
                String idAttr = attributes.getValue(PublicationTagField.ID.toString());
                String ratingAttr = attributes.getValue(PublicationTagField.RATING.toString());
                if (ratingAttr != null) {
                    current.setRating(Integer.valueOf(ratingAttr));
                }
                current.setId(Integer.valueOf(idAttr));
            } else {
                nestedElement = PublicationTagField.getValue(localName);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ((rootElement != null) && (rootElement.name().equals(localName.toUpperCase()))) {
            publications.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();

        if (nestedElement != null) {
            setPublicationField(nestedElement, current, value);
        }
        nestedElement = null;
    }
}

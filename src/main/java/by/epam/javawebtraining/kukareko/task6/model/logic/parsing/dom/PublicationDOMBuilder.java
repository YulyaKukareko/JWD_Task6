package by.epam.javawebtraining.kukareko.task6.model.logic.parsing.dom;

import by.epam.javawebtraining.kukareko.task6.model.entity.Publication;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.AbstractPublicationBuilder;
import by.epam.javawebtraining.kukareko.task6.util.PublicationInitializer;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationTagField;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationTagType;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yulya Kukareko
 * @version 1.0 05 Apr 2019
 */
public class PublicationDOMBuilder extends AbstractPublicationBuilder {

    private DocumentBuilder docBuilder;
    private static final ReentrantLock LOCK;
    private static PublicationDOMBuilder instance;
    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger("PublicationDOMBuilderLogger");
        LOCK = new ReentrantLock();
    }

    private PublicationDOMBuilder() {
        this.publications = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            LOGGER.error("Error config parser: " + ex);
        }
    }

    public static PublicationDOMBuilder getInstance() {
        if (instance == null) {
            LOCK.lock();
            if(instance == null) {
                instance = new PublicationDOMBuilder();
            }
            LOCK.unlock();
        }
        return instance;
    }

    public void buildSetPublications(String fileName) {
        try {
            Document doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList publicationsList = root.getChildNodes();

            for (int i = 1; i < publicationsList.getLength() - 1; i += 2) {
                String typePublicationName = publicationsList.item(i).getNodeName();
                Element currentElement = ((Element) publicationsList.item(i));
                NodeList fieldsList = currentElement.getChildNodes();

                String idAttr = currentElement.getAttribute(PublicationTagField.ID.toString());
                String ratingAttr = currentElement.getAttribute(PublicationTagField.RATING.toString());

                Publication publication = buildPublication(PublicationTagType.getValue(typePublicationName), fieldsList);
                publication.setId(Integer.valueOf(idAttr));
                if(!ratingAttr.isEmpty()) {
                    publication.setRating(Integer.valueOf(ratingAttr));
                }
                publications.add(publication);
            }
        } catch (IOException ex) {
            LOGGER.error("File error or I/O error: " + ex);
        } catch (SAXException ex) {
            LOGGER.error("Parsing failure: " + ex);
        }
    }

    private Publication buildPublication(PublicationTagType typePublication, NodeList fieldsPublication) {
        Publication publication = PublicationInitializer.getPublicationObject(typePublication);

        for (int i = 1; i < fieldsPublication.getLength() - 1; i += 2) {

            Element field = (Element) fieldsPublication.item(i);
            PublicationInitializer.setPublicationField(PublicationTagField.getValue(field.getNodeName()),
                    publication, field.getTextContent());
        }
        return publication;
    }
}

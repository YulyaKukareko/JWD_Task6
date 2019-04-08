package by.epam.javawebtraining.kukareko.task6.controller;

import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationBuilderFactory;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.validator.ValidatorXSD;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.AbstractPublicationBuilder;
import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;
import by.epam.javawebtraining.kukareko.task6.view.ConsoleRenderer;
import by.epam.javawebtraining.kukareko.task6.view.FileRenderer;
import by.epam.javawebtraining.kukareko.task6.view.PublicationRenderer;
import org.apache.log4j.Logger;

/**
 * @author Yulya Kukareko
 * @version 1.0 06 Apr 2019
 */
public class Controller {

    private static final String STAX__PARSER = ReadConfigProperties.getProp("staxType");
    private static final String SAX__PARSER = ReadConfigProperties.getProp("saxType");
    private static final String DOM__PARSER = ReadConfigProperties.getProp("domType");
    private static final String PATH_XML = ReadConfigProperties.getProp("pathToXml");
    private static final String PATH_XSD = ReadConfigProperties.getProp("xsdSchema");
    private static final String CHANGE_LINE = ReadConfigProperties.getProp("changeLine");
    private static PublicationRenderer fileRenderer;
    private static PublicationRenderer consoleRenderer;

    private static Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
        fileRenderer = new FileRenderer();
        consoleRenderer = new ConsoleRenderer();
    }

    public static void main(String[] args) {
        try {
            if (ValidatorXSD.validate(PATH_XML, PATH_XSD)) {
                buildSet(SAX__PARSER);
                buildSet(STAX__PARSER);
                buildSet(DOM__PARSER);
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private static void buildSet(String typeParser) {
        AbstractPublicationBuilder builder = PublicationBuilderFactory.createPublicationBuilder(typeParser);
        builder.buildSetPublications(PATH_XML);
        fileRenderer.render(typeParser + " : " + builder.getPublications().toString() + CHANGE_LINE);
        consoleRenderer.render(typeParser + " : " + builder.getPublications().toString() + CHANGE_LINE);
    }
}

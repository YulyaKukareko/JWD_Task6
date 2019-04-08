package by.epam.javawebtraining.kukareko.task6.view;

import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Yulya Kukareko
 * @version 1.0 06 Apr 2019
 */
public class FileRenderer implements PublicationRenderer {

    private static final boolean isAppend = true;

    public static final Logger LOGGER_CHARACTER_RENDER;

    static {
        LOGGER_CHARACTER_RENDER = Logger.getLogger("FileRendererLogger");
    }

    @Override
    public void render(String data) {

        try (BufferedWriter br = new BufferedWriter(new FileWriter(ReadConfigProperties.getProp("fileRenderer"),
                isAppend))) {

            br.write(data);
            br.flush();

        } catch (IOException ex) {
            LOGGER_CHARACTER_RENDER.error(ex.getMessage());
        }
    }
}

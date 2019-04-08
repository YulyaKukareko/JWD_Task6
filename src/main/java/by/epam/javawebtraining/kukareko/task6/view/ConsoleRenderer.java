package by.epam.javawebtraining.kukareko.task6.view;

/**
 * @author Yulya Kukareko
 * @version 1.0 07 Апр. 2019
 */
public class ConsoleRenderer implements PublicationRenderer {

    @Override
    public void render(String message) {
        System.out.println(message);
    }
}

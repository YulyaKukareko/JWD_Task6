package by.epam.javawebtraining.kukareko.task6.util;

import by.epam.javawebtraining.kukareko.task6.model.entity.Publication;
import by.epam.javawebtraining.kukareko.task6.model.entity.album.Album;
import by.epam.javawebtraining.kukareko.task6.model.entity.book.*;
import by.epam.javawebtraining.kukareko.task6.model.entity.magazine.Magazine;
import by.epam.javawebtraining.kukareko.task6.model.entity.magazine.Musical;
import by.epam.javawebtraining.kukareko.task6.model.entity.magazine.Science;
import by.epam.javawebtraining.kukareko.task6.model.entity.magazine.Sport;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationTagField;
import by.epam.javawebtraining.kukareko.task6.model.logic.parsing.PublicationTagType;

/**
 * @author Yulya Kukareko
 * @version 1.0 05 Apr 2019
 */
public class PublicationInitializer {

    public static Publication getPublicationObject(PublicationTagType typePublication) {
        Publication publication = new Publication();

        switch (typePublication) {
            case PROGRAMMING:
                publication = new Programming();
                break;
            case ALBUM:
                publication = new Album();
                break;
            case SPORT:
                publication = new Sport();
                break;
            case SCIENCE:
                publication = new Science();
                break;
            case MUSICAL:
                publication = new Musical();
                break;
            case TECHNICAL:
                publication = new Technical();
                break;
            case INSTRUCTION:
                publication = new Instruction();
                break;
            case CHILDREN:
                publication = new Children();
                break;
        }
        return publication;
    }

    public static void setPublicationField(PublicationTagField field, Publication publication, String value) {
        switch (field) {
            case PAGECOUNT:
                publication.setPageCount(Integer.parseInt(value));
                break;
            case FONT:
                publication.setFont(Integer.parseInt(value));
                break;
            case NAME:
                publication.setName(value);
                break;
            case PUBLISHING:
                publication.setPublishing(value);
                break;
            case CIRCULATION:
                publication.setCirculation(Integer.parseInt(value));
                break;
            case KINDSPORT:
                ((Sport) publication).setKindSport(value);
                break;
            case SUBJECTAREA:
                if (publication instanceof Science) {
                    ((Science) publication).setSubjectArea(value);
                } else {
                    ((Technical) publication).setSubjectArea(value);
                }
                break;
            case KINDMUSICAL:
                ((Musical) publication).setKindMusical(Musical.KindMusic.valueOf(value));
                break;
            case HAVEDISK:
                ((Musical) publication).setHaveDisk(Boolean.valueOf(value));
                break;
            case COUNTARTICLES:
                ((Magazine) publication).setCountArticles(Integer.parseInt(value));
                break;
            case LEVEL:
                ((Technical) publication).setLevel(value);
                break;
            case LANGUAGE:
                ((Programming) publication).setLanguage(value);
                break;
            case SUBJECT:
                ((Instruction) publication).setSubject(value);
                break;
            case RECOMMENDEDAGE:
                ((Children) publication).setRecommendAge(value);
                break;
            case AUTHOR:
                ((Book) publication).setAuthor(value);
                break;
            case FORMAT:
                ((Album) publication).setFormat(value);
                break;
            default:
                throw new EnumConstantNotPresentException(field.getDeclaringClass(), field.name());
        }
    }
}

package quad.fr.eventdriven.camel.catalogue.application;


public interface ArticleSavedNotificationPort {

    public void notifyArticleSaved(ArticleSaved articleSaved);

}

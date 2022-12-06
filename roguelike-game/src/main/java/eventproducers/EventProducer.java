package eventproducers;

import lib.Event;
import core.GameInitializer;

public class EventProducer {
    /**
    * This method is used to initialize all event producers.
    */
    public static void initializeAll() {
        new UserActionEventProducer().initialize();
    }

    /**
    * This method is used to send event to controller.
    * show the usage of various javadoc Tags.
    * @param event Event to send.
    * @return boolean Returns true iff event was processed successfully.
    */
    protected boolean fireEvent(Event event) {
        if (GameInitializer.getGameController() == null) { return false; }

        GameInitializer.getGameController().handleEvent(event);

        return true;
    }
}

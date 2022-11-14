package eventproducers;

import lib.Event;
import core.GameInitializer;

public class EventProducer {
    public static void initializeAll() {
        new UserActionEventProducer().initialize();
    }

  protected boolean fireEvent(Event event) {
    if (GameInitializer.getGameController() == null) { return false; }

    GameInitializer.getGameController().handleEvent(event);

    return true;
  }
}

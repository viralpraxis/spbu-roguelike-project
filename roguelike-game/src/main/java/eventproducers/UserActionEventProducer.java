package eventproducers;

import lib.Event;

import java.awt.Toolkit;
import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

class UserActionEventProducer extends EventProducer {
    public void initialize() {
        bindEventListeners();
    }

    private void bindEventListeners() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

        @Override
        public void eventDispatched(AWTEvent rawEvent) {
            if (!(rawEvent instanceof KeyEvent)) { return; }

            KeyEvent keyEvent = (KeyEvent) rawEvent;
            if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                Event event = null;

                switch (keyEvent.getKeyCode()) {
                    case (KeyEvent.VK_ESCAPE):
                        event = new Event(Event.Type.GAME_EXIT);
                        break;
                    case (KeyEvent.VK_UP):
                        event = new Event(Event.Type.PLAYER_MOVE_UP);
                        break;
                    case (KeyEvent.VK_DOWN):
                        event = new Event(Event.Type.PLAYER_MOVE_DOWN);
                        break;
                    case (KeyEvent.VK_LEFT):
                        event = new Event(Event.Type.PLAYER_MOVE_LEFT);
                        break;
                    case (KeyEvent.VK_RIGHT):
                        event = new Event(Event.Type.PLAYER_MOVE_RIGHT);
                        break;
                    case (KeyEvent.VK_D):
                        event = new Event(Event.Type.PLAYER_SELECT_ITEM);
                        break;
                    case (KeyEvent.VK_A):
                        event = new Event(Event.Type.PLAYER_UNSELECT_ITEM);
                        break;
                    case (KeyEvent.VK_W):
                        event = new Event(Event.Type.PLAYER_SELECT_PREVIOUS_ITEM);
                        break;
                    case (KeyEvent.VK_S):
                        event = new Event(Event.Type.PLAYER_SELECT_NEXT_ITEM);
                        break;
                    case (KeyEvent.VK_SPACE):
                        event = new Event(Event.Type.PLAYER_EQUIP_ITEM);
                        break;
                    case (KeyEvent.VK_N):
                        event = new Event(Event.Type.PLAYER_MOVE_NEXT_LEVEL);
                        break;
                }

                if (event != null) {
                    fireEvent(event);
                }
            }
        }
    }, AWTEvent.KEY_EVENT_MASK);
  }
}

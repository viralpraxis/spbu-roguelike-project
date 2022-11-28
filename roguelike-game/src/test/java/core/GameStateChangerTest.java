package test.core;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.*;
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

import core.GameState;
import models.Inventory;
import lib.Event;
import core.GameStateChanger;

@RunWith(MockitoJUnitRunner.class)
class GameStateChangerTest {
    @Test
    public void testCallWithPlayerMoveUpEvent() {
        GameState mockedGameState = Mockito.mock(GameState.class);
        Event event = new Event(Event.Type.PLAYER_MOVE_UP);

        perform(mockedGameState, event);

        Mockito.verify(mockedGameState).updateGameState(0, -1);
    }

    @Test
    public void testCallWithPlayerMoveDownEvent() {
        GameState mockedGameState = Mockito.mock(GameState.class);
        Event event = new Event(Event.Type.PLAYER_MOVE_DOWN);

        perform(mockedGameState, event);

        Mockito.verify(mockedGameState).updateGameState(0, 1);
    }

    @Test
    public void testCallWithPlayerMoveLeftEvent() {
        GameState mockedGameState = Mockito.mock(GameState.class);
        Event event = new Event(Event.Type.PLAYER_MOVE_LEFT);

        perform(mockedGameState, event);

        Mockito.verify(mockedGameState).updateGameState(-1, 0);
    }

    @Test
    public void testCallWithPlayerMoveRightEvent() {
        GameState mockedGameState = Mockito.mock(GameState.class);
        Event event = new Event(Event.Type.PLAYER_MOVE_RIGHT);

        perform(mockedGameState, event);

        Mockito.verify(mockedGameState).updateGameState(1, 0);
    }

    @Test
    public void testCallWithPlayerSelectItemEvent() {
        Inventory mockedInventory = Mockito.mock(Inventory.class);
        mockInventorySingletonInstance(mockedInventory);

        GameState gameState = new GameState();
        Event event = new Event(Event.Type.PLAYER_SELECT_ITEM);

        perform(gameState, event);

        Mockito.verify(mockedInventory).setSelectedStatus(true);
    }

    @Test
    public void testCallWithPlayerUnselectItemEvent() {
      Inventory mockedInventory = Mockito.mock(Inventory.class);
      mockInventorySingletonInstance(mockedInventory);

      GameState gameState = new GameState();
      Event event = new Event(Event.Type.PLAYER_UNSELECT_ITEM);

      perform(gameState, event);

      Mockito.verify(mockedInventory).setSelectedStatus(false);
    }

    @Test
    public void testCallWithPlayerSelectNextItemEvent() {
      Inventory mockedInventory = Mockito.mock(Inventory.class);
      mockInventorySingletonInstance(mockedInventory);

      GameState gameState = new GameState();
      Event event = new Event(Event.Type.PLAYER_SELECT_NEXT_ITEM);

      perform(gameState, event);

      Mockito.verify(mockedInventory).selectNext();
    }

    @Test
    public void testCallWithPlayerSelectPreviousEvent() {
      Inventory mockedInventory = Mockito.mock(Inventory.class);
      mockInventorySingletonInstance(mockedInventory);

      GameState gameState = new GameState();
      Event event = new Event(Event.Type.PLAYER_SELECT_PREVIOUS_ITEM);

      perform(gameState, event);

      Mockito.verify(mockedInventory).selectPrevious();
    }

    @Test
    public void testCallWithPlayerEquipItemEvent() {
      Inventory mockedInventory = Mockito.mock(Inventory.class);
      mockInventorySingletonInstance(mockedInventory);

      GameState gameState = new GameState();
      Event event = new Event(Event.Type.PLAYER_EQUIP_ITEM);

      perform(gameState, event);

      Mockito.verify(mockedInventory).selectedItemChangeEquipStatus(gameState.getPlayer());
    }

    @Test
    public void testCallWithPlayerMoveNextLevelEvent() {
        GameState mockedGameState = Mockito.mock(GameState.class);
        Event event = new Event(Event.Type.PLAYER_MOVE_NEXT_LEVEL);

        perform(mockedGameState, event);

        Mockito.verify(mockedGameState).tryMoveToNextLevel();
    }

    private void mockInventorySingletonInstance(Inventory inventory) {
        try {
            Field instance = Inventory.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(instance, inventory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void resetSingleton() throws Exception {
       Field instance = Inventory.class.getDeclaredField("instance");
       instance.setAccessible(true);
       instance.set(null, null);
    }

    private void perform(GameState gameState, Event event) {
        GameStateChanger.call(gameState, event);
    }
}

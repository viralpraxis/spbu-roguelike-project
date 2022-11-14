package ui;

import core.GameState;
import core.Room;
import models.GameObject;
import models.Inventory;
import models.Item;
import models.Player;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class Renderer {
    private Terminal terminal;
    private Screen screen;

    private final int mapPosX;
    private final int mapPosY;
    private final int mapWidth;
    private final int mapHeight;
    private final int inventoryPosX;
    private final int inventoryPosY;
    private final int inventoryWidth;
    private final int inventoryHeight;
    private final int statPosX;
    private final int statPosY;
    private final int statWidth;
    private final int statHeight;

    public Renderer() {
        mapPosX = 0;
        mapPosY = 0;
        mapWidth = 50;
        mapHeight = 24;
        inventoryPosX = 50;
        inventoryPosY = 0;
        inventoryWidth = 30;
        inventoryHeight = 20;
        statPosX = 50;
        statPosY = 19;
        statWidth = 30;
        statHeight = 5;

        try {

            DefaultTerminalFactory dtf = new DefaultTerminalFactory();

            terminal = dtf.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null);

            drawMapFrame();
            drawUtilitiesFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawMapFrame() {
        drawFrame(mapPosX, mapPosY, mapWidth, mapHeight);
    }

    private void drawUtilitiesFrame() {
        drawFrame(inventoryPosX, inventoryPosY, inventoryWidth, inventoryHeight);
        drawFrame(statPosX, statPosY, statWidth, statHeight);
        screen.setCharacter(statPosX, statPosY, new TextCharacter(Symbols.SINGLE_LINE_T_DOUBLE_RIGHT));
        screen.setCharacter(statPosX + statWidth-1, statPosY, new TextCharacter(Symbols.SINGLE_LINE_T_DOUBLE_LEFT));
    }

    private void drawFrame(int x, int y, int width, int height) {
        TerminalPosition labelBoxTopLeft = new TerminalPosition(x, y);
        TerminalSize labelBoxSize = new TerminalSize(width, height);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns()-1);
        TextGraphics textGraphics = screen.newTextGraphics();
        //This isn't really needed as we are overwriting everything below anyway, but just for demonstrative purpose
        textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');

        textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns()-1),
                Symbols.SINGLE_LINE_HORIZONTAL);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(labelBoxSize.getRows()-1).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(labelBoxSize.getRows()-1).withRelativeColumn(labelBoxSize.getColumns()-1),
                Symbols.SINGLE_LINE_HORIZONTAL);

        textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1),
                labelBoxTopLeft.withRelativeRow(labelBoxSize.getRows()-1),
                Symbols.SINGLE_LINE_VERTICAL);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns()-1).withRelativeRow(1),
                labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns()-1).withRelativeRow(labelBoxSize.getRows()-1),
                Symbols.SINGLE_LINE_VERTICAL);

        textGraphics.setCharacter(labelBoxTopLeft, Symbols.SINGLE_LINE_TOP_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(labelBoxSize.getRows()-1),
                Symbols.SINGLE_LINE_BOTTOM_LEFT_CORNER);

        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.SINGLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(labelBoxSize.getRows()-1),
                Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER);
    }

    private void drawGameObject(GameObject object) {
        if (!object.isVisible())
            return;
        for (int i = 0; i < object.getRepresentation().length; ++i) {
            for (int j = 0; j < object.getRepresentation()[0].length; ++j)
                screen.setCharacter(object.posX() + i + mapPosX + 1, object.posY() + i + mapPosY + 1,
                                    new TextCharacter(object.getRepresentation()[i][j]));
        }
    }

    private void drawRoom(Room room) {
        if (!room.isVisible()) return;

        TerminalPosition labelBoxTopLeft = new TerminalPosition(room.posX() + mapPosX + 1,
                room.posY() + mapPosY + 1);
        TerminalSize labelBoxSize = new TerminalSize(room.getSize(), room.getSize());
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns()-1);
        TextGraphics textGraphics = screen.newTextGraphics();
        //This isn't really needed as we are overwriting everything below anyway, but just for demonstrative purpose
        textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');

        textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns()-1),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(room.getSize()-1).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(room.getSize()-1).withRelativeColumn(labelBoxSize.getColumns()-1),
                Symbols.DOUBLE_LINE_HORIZONTAL);

        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(room.getSize()-1),
                                  Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);

        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(room.getSize()-1),
                                  Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

        for (int i = 1; i < room.getSize()-1; ++i) {
            textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(i), Symbols.DOUBLE_LINE_VERTICAL);
            textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(i), Symbols.DOUBLE_LINE_VERTICAL);
        }

        for (GameObject go : room.getRoomContent()) {
            drawGameObject(go);
        }
    }

    private void drawInventory() {
        TextGraphics textGraphics = screen.newTextGraphics();
        int column = inventoryPosX + 1;
        int row = inventoryPosY + 1;

        for (Item item : Inventory.getInventory().getItems()) {
            textGraphics.putString(column, row, item.getName());
            row += 1;
        }
    }

    private void drawPlayerStats(Player player) {
        TextGraphics textGraphics = screen.newTextGraphics();
        int column = statPosX + 1;
        int row = statPosY + 1;

        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        textGraphics.putString(column, row, "Health: " + Integer.toString(player.getHealth()));

        textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        textGraphics.putString(column, row + 1, "Attack: " + Integer.toString(player.getPower()));
    }

    public void render(GameState gameState) {
        try {
            tryRender(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryRender(GameState gs) throws IOException {
        Player player = gs.getPlayer();

        // screen.setCharacter(player.posX(), player.posY(), new TextCharacter(' '));

        // draw rooms
        for (Room room : gs.getRooms()) {
            drawRoom(room);
        }

        // draw player
        drawGameObject(player);

        // draw inventory
        drawInventory();

        drawPlayerStats(player);

        screen.refresh();
    }
}

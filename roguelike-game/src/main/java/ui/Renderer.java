package ui;

import core.GameState;
import core.Room;
import models.GameObject;
import models.Player;
import models.Inventory;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class Renderer {
    private Terminal terminal;
    private Screen screen;

    public Renderer() {
        try {
            DefaultTerminalFactory dtf = new DefaultTerminalFactory();

            terminal = dtf.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawGameObject(GameObject object) {
        if (!object.isVisible())
            return;
        for (int i = 0; i < object.getRepresentation().length; ++i) {
            for (int j = 0; j < object.getRepresentation()[0].length; ++j)
                screen.setCharacter(object.posX() + i + 1, object.posY() + i + 1,
                                    new TextCharacter(object.getRepresentation()[i][j]));
        }
    }

    private void drawRoom(Room room) {
        if (!room.isVisible()) return;

        TerminalPosition labelBoxTopLeft = new TerminalPosition(room.posX() + 1, room.posY() + 1);
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

    private void drawMapFrame() {
        TerminalPosition labelBoxTopLeft = new TerminalPosition(0, 0);
        TerminalSize labelBoxSize = new TerminalSize(50, 24);
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

    public void render(GameState gs) {
        try {
            drawMapFrame();
            while (true) {
                Player player = gs.getPlayer();

                // TODO: Add event handler to handle user input
                KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null && (keyStroke.getKeyType() == KeyType.Escape)) break;

                if (keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowDown)) {
                    screen.setCharacter(player.posX(), player.posY(), new TextCharacter(' '));
                    gs.updateGameState(0, +1);
                }

                if (keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowUp)) {
                    screen.setCharacter(player.posX(), player.posY(), new TextCharacter(' '));
                    gs.updateGameState(0, -1);
                }

                if (keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowRight)) {
                    screen.setCharacter(player.posX(), player.posY(), new TextCharacter(' '));
                    gs.updateGameState(+1, 0);
                }

                if (keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowLeft)) {
                    screen.setCharacter(player.posX(), player.posY(), new TextCharacter(' '));
                    gs.updateGameState(-1, 0);
                }

                //screen.doResizeIfNecessary();
                //drawMapFrame();

                // draw rooms    
                for (Room room : gs.getRooms()) {
                    drawRoom(room);
                }

                // draw player
                drawGameObject(player);

                screen.refresh();
                Thread.yield();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (screen != null) {
                try {
                    screen.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package test;

import main.GameWorld;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class GameWorldTest {

    private final String inputFileName = "instructions.txt";
    private final String fileMapName = "map.txt";

    @Test
    public void testReadInputFileAndMoveHeroOK() throws IOException {
        // Given
        List<String> inputMoves = getMoves();

        // When
        GameWorld gameWorld = new GameWorld(fileMapName, inputFileName);

        // Then
        assertNotNull(gameWorld);
        List<String> instructions = gameWorld.getInstructions();
        assertEquals(inputMoves, instructions);
        assertEquals(9, gameWorld.getHero().getX());
        assertEquals(2, gameWorld.getHero().getY());

    }

    @Test
    public void testLoadMap() throws IOException {
        // Given

        // When
        GameWorld gameWorld = new GameWorld(fileMapName, inputFileName);

        // Then
        assertNotNull(gameWorld);
        assertEquals(20, gameWorld.getWidth());
        assertEquals(20, gameWorld.getHeight());
    }

    @Test
    public void testIsValidPositionOK() throws IOException {
        // Given

        // When
        GameWorld gameWorld = new GameWorld(fileMapName, inputFileName);

        // Then
        assertTrue(gameWorld.isValidPosition(1, 3));
    }

    @Test
    public void testIsValidPositionKO() throws IOException {
        // Given

        // When
        GameWorld gameWorld = new GameWorld(fileMapName, inputFileName);

        // Then
        assertFalse(gameWorld.isValidPosition(0, 0));
        assertFalse(gameWorld.isValidPosition(4, 9));
    }

    private static List<String> getMoves() {
        List<String> inputMoves = new ArrayList<>();
        inputMoves.add("S");
        inputMoves.add("S");
        inputMoves.add("S");
        inputMoves.add("S");

        inputMoves.add("E");
        inputMoves.add("E");
        inputMoves.add("E");
        inputMoves.add("E");
        inputMoves.add("E");
        inputMoves.add("E");

        inputMoves.add("N");
        inputMoves.add("N");
        return inputMoves;
    }

}

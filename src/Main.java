import main.GameWorld;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        final String inputFileName = "instructions.txt";
        final String fileMapName = "map.txt";

        try {
            GameWorld world = new GameWorld(fileMapName, inputFileName);
        } catch (IOException e) {
            System.err.println("Error while loading map : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
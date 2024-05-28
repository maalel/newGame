package main;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import main.modele.Hero;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GameWorld {

    private char[][] map;
    public int width;
    private int height;
    private List<String> instructions;
    private Hero hero;

    /**
     * Method Main
     *
     * @param mapFile
     * @param fileInstructionName
     * @throws IOException
     * @throws FileNotFoundException
     */
    public GameWorld(String mapFile, String fileInstructionName) throws IOException, FileNotFoundException {

        this.hero = new Hero();
        printWorld();
        readInputFile(fileInstructionName);
        loadMap(mapFile);
        moveHero(instructions);
    }

    /**
     * Read the Data from Input File, initial coordinates and character movements
     *
     * @param inputFileName
     */
    private void readInputFile(String inputFileName) {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(inputFileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            System.err.println("Error while reading the instruction file" + e);
        }
        String fileContent = contentBuilder.toString();

        List<String> splitedData = splitData(fileContent, ",");
        List<String> moreSplitedData = splitData(splitedData.get(1), "\\r?\\n");
        hero.setPosition(Integer.parseInt(splitedData.get(0)), Integer.parseInt(moreSplitedData.get(0)));
        instructions = splitData(moreSplitedData.get(1), "");

    }

    /**
     * Method to split subtracted data, following a Regex
     *
     * @param fileContent
     * @param regex
     * @return
     */
    private static List<String> splitData(String fileContent, String regex) {
        String[] arrOfStr = fileContent.split(regex);
        return new ArrayList<>(Arrays.asList(arrOfStr));
    }

    /**
     * Method to read the Map from a txt file and which will define the limits of our Map
     *
     * @param mapFile
     * @throws IOException
     * @throws FileNotFoundException
     */
    private void loadMap(String mapFile) throws IOException, FileNotFoundException {

        BufferedReader br = new BufferedReader(new FileReader(mapFile));

        String line;
        int rows = 0;
        while ((line = br.readLine()) != null) {
            rows++;
            width = line.length();
        }
        br.close();

        height = rows;
        map = new char[height][width];

        br = new BufferedReader(new FileReader(mapFile));
        int row = 0;
        while ((line = br.readLine()) != null) {
            for (int col = 0; col < line.length(); col++) {
                map[row][col] = line.charAt(col);
            }
            row++;
        }
        br.close();
    }

    /**
     * Method which will validate or not the new position of the character
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height && map[y][x] == ' ';
    }

    /**
     * Method which following a series of movements will move our character
     *
     * @param directions
     */
    public void moveHero(List<String> directions) {
        int newX = hero.getX();
        int newY = hero.getY();

        for (String direction : directions) {
            switch (direction) {
                case "N": // up
                    newY--;
                    break;
                case "S": // down
                    newY++;
                    break;
                case "O": // left
                    newX--;
                    break;
                case "E": // right
                    newX++;
                    break;
            }
        }

        if (isValidPosition(newX, newY)) {
            hero.setPosition(newX, newY);
            System.out.println("Le personnage doit se trouver en : " + "(" + newX + "," + newY + ")");
        } else {
            System.err.println("Le personnage ne peut pas se déplacer dans cette case, puisqu'elle est occupée.");
        }

    }

    /**
     * Method to print the Map
     */
    public void printWorld() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public Hero getHero() {
        return hero;
    }
}


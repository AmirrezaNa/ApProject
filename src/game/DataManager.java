package game;

import startPage.EnterNamePage;

import java.io.*;
import java.util.Scanner;

public class DataManager {
    static File dataFile = new File("player_data.txt");
    Scanner scanner = new Scanner(System.in);

    public static boolean checkPlayerExists(String name) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(dataFile);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[0].equals(name)) {
                fileScanner.close();
                return true;
            }
        }
        fileScanner.close();
        return false;
    }

    public static void loadPlayerData(String name) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(dataFile);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[0].equals(name)) {
                if (EnterNamePage.player != null) {
                    EnterNamePage.player.name = parts[0];
                    EnterNamePage.player.XP = Integer.parseInt(parts[1]);
                    EnterNamePage.player.writOfAres = Boolean.parseBoolean(parts[2]);
                    EnterNamePage.player.writOfAceso = Boolean.parseBoolean(parts[3]);
                    EnterNamePage.player.writOfProteus = Boolean.parseBoolean(parts[4]);
                }

            }
        }
    }

    public static void createPlayerData(Player player) throws IOException {
        FileWriter writer = new FileWriter(dataFile, true);
        writer.write(EnterNamePage.player.name + "," + player.XP + "," + player.writOfAres + "," + player.writOfAceso + "," + player.writOfProteus + "\n");
        writer.close();
    }

    public static void updatePlayerData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dataFile));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 2 && parts[0].equals(EnterNamePage.player.name)) {
                parts[1] = Integer.toString(EnterNamePage.player.XP);
                parts[2] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.writOfAres)));
                parts[3] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.writOfAceso)));
                parts[4] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.writOfProteus)));
            }
            content.append(String.join(",", parts)).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
        bw.write(content.toString());
        bw.close();
        br.close();
    }
}

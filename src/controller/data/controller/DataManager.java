package controller.data.controller;

import controller.Constants;
import model.Player;
import view.startPage.EnterNamePage;

import java.io.*;
import java.util.Scanner;


public class DataManager {
    static File dataFile = new File(Constants.PLAYER_DATA_FILE_PATH);
    Scanner scanner = new Scanner(System.in);

    public static boolean checkPlayerExists(String name) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(dataFile);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 11 && parts[0].equals(name)) {
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
            if (parts.length >= 11 && parts[0].equals(name)) {
                if (EnterNamePage.player != null) {
                    EnterNamePage.player.setName(parts[0]);
                    EnterNamePage.player.setXP(Integer.parseInt(parts[1]));
                    EnterNamePage.player.setWritOfAres(Boolean.parseBoolean(parts[2]));
                    EnterNamePage.player.setWritOfAstrape(Boolean.parseBoolean(parts[3]));
                    EnterNamePage.player.setWritOfCerberus(Boolean.parseBoolean(parts[4]));
                    EnterNamePage.player.setWritOfAceso(Boolean.parseBoolean(parts[5]));
                    EnterNamePage.player.setWritOfMelampus(Boolean.parseBoolean(parts[6]));
                    EnterNamePage.player.setWritOfChiron(Boolean.parseBoolean(parts[7]));
                    EnterNamePage.player.setWritOfProteus(Boolean.parseBoolean(parts[8]));
                    EnterNamePage.player.setWritOfEmpusa(Boolean.parseBoolean(parts[9]));
                    EnterNamePage.player.setWritOfDolus(Boolean.parseBoolean(parts[10]));

                }

            }
        }
    }

    public static void createPlayerData(Player player) throws IOException {
        FileWriter writer = new FileWriter(dataFile, true);
        writer.write(EnterNamePage.player.getName() + "," + player.getXP() + ","
                + player.isWritOfAres() + "," + player.isWritOfAstrape() + "," + player.isWritOfCerberus() + ","
                + player.isWritOfAceso() + "," + player.isWritOfMelampus() + "," + player.isWritOfChiron() + ","
                + player.isWritOfProteus() + "," + player.isWritOfEmpusa() + "," + player.isWritOfDolus()+ "\n");
        writer.close();
    }

    public static void updatePlayerData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dataFile));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 2 && parts[0].equals(EnterNamePage.player.getName())) {
                parts[1] = Integer.toString(EnterNamePage.player.getXP());
                parts[2] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfAres())));
                parts[3] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfAstrape())));
                parts[4] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfCerberus())));

                parts[5] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfAceso())));
                parts[6] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfMelampus())));
                parts[7] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfChiron())));

                parts[8] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfProteus())));
                parts[9] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfEmpusa())));
                parts[10] = String.valueOf(Boolean.parseBoolean(String.valueOf(EnterNamePage.player.isWritOfDolus())));
            }
            content.append(String.join(",", parts)).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
        bw.write(content.toString());
        bw.close();
        br.close();
    }
}

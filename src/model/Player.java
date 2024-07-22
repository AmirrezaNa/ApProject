package model;

public class Player {
    private String name;
    private int XP;
    private boolean writOfAres;
    private boolean writOfAceso;
    private boolean writOfProteus;
    private boolean writOfAstrape;
    private boolean writOfCerberus;
    private boolean writOfMelampus;
    private boolean writOfChiron;
    private boolean writOfEmpusa;
    private boolean writOfDolus;
    private boolean writOfAthena;


    public Player() {
        this.name = "";
        this.XP = 0;

        this.writOfAres = false;
        this.writOfAstrape = false;
        this.writOfCerberus = false;

        this.writOfAceso = false;
        this.writOfMelampus = false;
        this.writOfChiron = false;

        this.writOfProteus = false;
        this.writOfEmpusa = false;
        this.writOfDolus = false;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public boolean isWritOfAres() {
        return writOfAres;
    }

    public void setWritOfAres(boolean writOfAres) {
        this.writOfAres = writOfAres;
    }

    public boolean isWritOfAceso() {
        return writOfAceso;
    }

    public void setWritOfAceso(boolean writOfAceso) {
        this.writOfAceso = writOfAceso;
    }

    public boolean isWritOfProteus() {
        return writOfProteus;
    }

    public void setWritOfProteus(boolean writOfProteus) {
        this.writOfProteus = writOfProteus;
    }

    public boolean isWritOfAstrape() {
        return writOfAstrape;
    }

    public void setWritOfAstrape(boolean writOfAstrape) {
        this.writOfAstrape = writOfAstrape;
    }

    public boolean isWritOfCerberus() {
        return writOfCerberus;
    }

    public void setWritOfCerberus(boolean writOfCerberus) {
        this.writOfCerberus = writOfCerberus;
    }

    public boolean isWritOfMelampus() {
        return writOfMelampus;
    }

    public void setWritOfMelampus(boolean writOfMelampus) {
        this.writOfMelampus = writOfMelampus;
    }

    public boolean isWritOfChiron() {
        return writOfChiron;
    }

    public void setWritOfChiron(boolean writOfChiron) {
        this.writOfChiron = writOfChiron;
    }

    public boolean isWritOfEmpusa() {
        return writOfEmpusa;
    }

    public void setWritOfEmpusa(boolean writOfEmpusa) {
        this.writOfEmpusa = writOfEmpusa;
    }

    public boolean isWritOfDolus() {
        return writOfDolus;
    }

    public void setWritOfDolus(boolean writOfDolus) {
        this.writOfDolus = writOfDolus;
    }

    public boolean isWritOfAthena() {
        return writOfAthena;
    }

    public void setWritOfAthena(boolean writOfAthena) {
        this.writOfAthena = writOfAthena;
    }
}

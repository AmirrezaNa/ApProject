package model;

public class Player {
    private String name;
    private int XP;
    private boolean writOfAres;
    private boolean writOfAceso;
    private boolean writOfProteus;

    public Player() {
        this.name = "";
        this.XP = 0;
        this.writOfAres = false;
        this.writOfAceso = false;
        this.writOfProteus = false;
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
}

public class Ability {

    private int dmg;
    private boolean stun;
    private boolean dodge;
    private int cd;

    public Ability() {}

    public Ability(int dmg, boolean stun, boolean dodge, int cd) {
        this.dmg = dmg;
        this.stun = stun;
        this.dodge = dodge;
        this.cd = cd;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "dmg=" + dmg +
                ", stun=" + stun +
                ", dodge=" + dodge +
                ", cd=" + cd +
                '}';
    }
}

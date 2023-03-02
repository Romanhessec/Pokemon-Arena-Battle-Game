import java.util.Arrays;

public class Pokemon {
    private String name;
    private int hp;
    private int normalAttack;
    private int specialAttack;
    private int defense;
    private int specialDefense;
    private Ability[] abilities;
    private Item[] items;
    private boolean isStunned;
    private boolean hasDodge = false;
    private boolean isWinner;

    Pokemon(PokemonBuilder builder){
        this.name = builder.name;
        this.hp = builder.hp;
        this.normalAttack = builder.normalAttack;
        this.specialAttack = builder.specialAttack;
        this.defense = builder.defense;
        this.specialDefense = builder.specialDefense;
        this.abilities = builder.abilities;
        this.items = builder.items;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getNormalAttack() {
        return normalAttack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setNormalAttack(int normalAttack) {
        this.normalAttack = normalAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public boolean hasDodge() {
        return hasDodge;
    }

    public void setDodge(boolean hasDodge) {
        this.hasDodge = hasDodge;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", normalAttack=" + normalAttack +
                ", specialAttack=" + specialAttack +
                ", defense=" + defense +
                ", specialDefense=" + specialDefense +
                ", abilities=" + Arrays.toString(abilities) +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}

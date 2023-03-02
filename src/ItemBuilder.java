public class ItemBuilder {

    private static ItemBuilder instance;
    String name;
    int hp;
    int attack;
    int specialAttack;
    int defense;
    int specialDefense;

    public static ItemBuilder getInstance(){
        if (instance == null)
            instance = new ItemBuilder();
        return instance;
    }

    public static ItemBuilder item() {
        return new ItemBuilder();
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withHp(int hp) {
        this.hp = hp;
        return this;
    }

    public ItemBuilder withAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public ItemBuilder withSpecialAttack (int specialAttack) {
        this.specialAttack = specialAttack;
        return this;
    }

    public ItemBuilder withDefense (int defense) {
        this.defense = defense;
        return this;
    }

    public ItemBuilder withSpecialDefense (int specialDefense) {
        this.specialDefense = defense;
        return this;
    }

    public Item build() {
        return new Item(this);
    }
}

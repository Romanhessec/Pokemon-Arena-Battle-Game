public class PokemonBuilder {

    private static PokemonBuilder instance;
    String name;
    int hp;
    int normalAttack;
    int specialAttack;
    int defense;
    int specialDefense;
    Ability[] abilities;
    Item[] items;

    public static PokemonBuilder getInstance(){
        if (instance == null)
            instance = new PokemonBuilder();
        return instance;
    }

    public static PokemonBuilder pokemon() {
        return new PokemonBuilder();
    }

    public PokemonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PokemonBuilder withHp(int hp) {
        this.hp = hp;
        return this;
    }

    public PokemonBuilder withNormalAttack(int normalAttack){
        this.normalAttack = normalAttack;
        return this;
    }

    public PokemonBuilder withSpecialAttack(int specialAttack){
        this.specialAttack = specialAttack;
        return this;
    }

    public PokemonBuilder withDefense(int defense){
        this.defense = defense;
        return this;
    }

    public PokemonBuilder withSpecialDefense(int normalAttack){
        this.normalAttack = normalAttack;
        return this;
    }

    public PokemonBuilder withAbilities(Ability[] abilities){
        this.abilities = abilities;
        return this;
    }

    public PokemonBuilder withItems(Item[] items){
        this.items = items;
        return this;
    }

    public Pokemon build(){
        return new Pokemon(this);
    }
}

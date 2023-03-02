public class Adventure {

    private Pokemon pokemon1;
    private Pokemon pokemon2;

    public Adventure(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "pokemon1=" + pokemon1 +
                ", pokemon2=" + pokemon2 +
                '}';
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }
}

import java.util.Arrays;

public class PokemonTrainer {

    private String name;
    private int age;
    private Pokemon[] pokemons;

    public PokemonTrainer(String name, int age, Pokemon[] pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public void setPokemons(Pokemon[] pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public String toString() {
        return "PokemonTrainer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pokemons=" + Arrays.toString(pokemons) +
                '}';
    }
}

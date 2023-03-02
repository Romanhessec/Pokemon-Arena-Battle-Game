public class BattleVsEachOtherCommand extends Command{

    public BattleVsEachOtherCommand() {}

    @Override
    public void execute(Pokemon pokemon1, Pokemon pokemon2) {
        int hpPokemon1 = pokemon1.getHp();
        int hpPokemon2 = pokemon2.getHp();

        PokemonThread pokemon1Thread = new PokemonThread(pokemon1, pokemon2);
        PokemonThread pokemon2Thread = new PokemonThread(pokemon2, pokemon1);

        pokemon1Thread.setPokemonOtherThread(pokemon2Thread);
        pokemon2Thread.setPokemonOtherThread(pokemon1Thread);

        pokemon1Thread.start();
        pokemon2Thread.start();

        try {
            Thread.sleep(100);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}

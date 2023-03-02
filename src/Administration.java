import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Administration {

    static Logger logger = Logger.getLogger("logger");

    public Administration(){};

    public static int chooseEveniment(int min, int max){
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public Pokemon[] getNeutrels(PokemonBuilder pokemonBuilder){
        Pokemon[] neutrels = new Pokemon[2];
        neutrels[0] = pokemonBuilder
                .withName("neutrel1")
                .withHp(10)
                .withNormalAttack(3)
                .withSpecialAttack(0)
                .withDefense(1)
                .withSpecialDefense(1)
                .withAbilities(null)
                .withItems(null)
                .build();
        neutrels[1] = pokemonBuilder
                .withName("neutrel1")
                .withHp(20)
                .withNormalAttack(4)
                .withSpecialAttack(0)
                .withDefense(1)
                .withSpecialDefense(1)
                .withAbilities(null)
                .withItems(null)
                .build();
        return neutrels;
    }

    public Adventure[] getAdventures(PokemonTrainer[] pokemonTrainers){
        Adventure[] adventures = new Adventure[3];
        for (int i = 0; i < 3; i++) {
            adventures[i] = new Adventure(pokemonTrainers[0].getPokemons()[i], pokemonTrainers[1].getPokemons()[i]);
        }

        return adventures;
    }

    public void battlesVsEachOhter(Adventure adventure){
        Pokemon pokemon1 = adventure.getPokemon1();
        Pokemon pokemon2 = adventure.getPokemon2();

        if (pokemon1 == null){
            pokemon2.setWinner(true);
            LoggingPrinters.pokemonWins(pokemon2.getName());
            LoggingPrinters.pokemonAlreadyDead();
            return;
        }

        if (pokemon2 == null){
            pokemon1.setWinner(true);
            LoggingPrinters.pokemonWins(pokemon1.getName());
            LoggingPrinters.pokemonAlreadyDead();
            return;
        }

        LoggingPrinters.pokemonVSPokemon(pokemon1, pokemon2);

        Factory factory = Factory.getInstance();
        Command battleVsEachOtherCommand = factory.createCommand("vsEachOther");
        battleVsEachOtherCommand.execute(pokemon1, pokemon2);
    }

    public void battlesVsNeutrel(Adventure adventure, Pokemon neutrel){
        Pokemon pokemon1 = adventure.getPokemon1();
        Pokemon pokemon2 = adventure.getPokemon2();

        Factory factory = Factory.getInstance();
        Command battleNeutrelCommand = factory.createCommand("vsNeutrel");

        LoggingPrinters.pokemonVSPokemon(pokemon1, neutrel);
        battleNeutrelCommand.execute(pokemon1, neutrel);
        LoggingPrinters.pokemonVSPokemon(pokemon2, neutrel);
        battleNeutrelCommand.execute(pokemon2, neutrel);
    }

    public void pokemonUpdateItems(PokemonTrainer[] pokemonTrainers){
        for (PokemonTrainer pokemonTrainer : pokemonTrainers) {
            Pokemon[] pokemons = pokemonTrainer.getPokemons();
            for (Pokemon pokemon : pokemons) {
                Item[] items = pokemon.getItems();
                for (Item item : items) {
                    pokemon.setHp(pokemon.getHp() + item.getHp());
                    pokemon.setNormalAttack(pokemon.getNormalAttack() + item.getAttack());
                    pokemon.setSpecialAttack(pokemon.getSpecialAttack() + item.getSpecialAttack());
                    pokemon.setDefense(pokemon.getDefense() + item.getDefense());
                    pokemon.setSpecialDefense(pokemon.getSpecialDefense() + item.getSpecialDefense());
                }
            }
        }
    }

    public ArrayList<Pokemon> putPokemonWinners(Pokemon[] pokemons){
        ArrayList<Pokemon> pokemonsWinners = new ArrayList<>();
        for (Pokemon pokemonWinner : pokemons) {
            if (pokemonWinner.isWinner())
                pokemonsWinners.add(pokemonWinner);
        }
        return pokemonsWinners;
    }

    public int calculateScore(Pokemon pokemon){
        return pokemon.getHp() +
                pokemon.getDefense() +
                pokemon.getSpecialDefense() +
                pokemon.getNormalAttack() +
                pokemon.getSpecialAttack();
    }

    public Pokemon calculateBestPokemon(ArrayList<Pokemon> pokemons){
        int bestScore = 0;
        Pokemon bestPokemon = null;
        for (Pokemon pokemon : pokemons) {
            int score = calculateScore(pokemon);
            if (score > bestScore)
                bestPokemon = pokemon;
            if (score == bestScore)
                if(pokemon.getName().compareTo(bestPokemon.getName()) <= 0)
                    bestPokemon = pokemon;
        }
        return bestPokemon;
    }

    public Adventure calculateBestPokemons(PokemonTrainer[] pokemonTrainers){
        Pokemon[] pokemons1 = pokemonTrainers[0].getPokemons();
        Pokemon[] pokemons2 = pokemonTrainers[1].getPokemons();

        ArrayList<Pokemon> pokemons1Winners = putPokemonWinners(pokemons1);
        ArrayList<Pokemon> pokemons2Winners = putPokemonWinners(pokemons2);

        Pokemon bestPokemon1 = calculateBestPokemon(pokemons1Winners);
        Pokemon bestPokemon2 = calculateBestPokemon(pokemons2Winners);

        return new Adventure(bestPokemon1, bestPokemon2);
    }

    //main "game" happens here
    public void play(ArrayList<Path> files, Initialization init){
        PokemonBuilder pokemonBuilder = PokemonBuilder.getInstance();
        Pokemon[] neutrels = getNeutrels(pokemonBuilder);

        LoggingPrinters.letTheGamesBegin();
        int i = 0;
        for (Path file : files) {
            i++;
            LoggingPrinters.testFile(i);
            PokemonTrainer[] pokemonTrainers = init.pokemonTrainersInit(file);
            pokemonUpdateItems(pokemonTrainers);
            Adventure[] adventures = getAdventures(pokemonTrainers);

            LoggingPrinters.printAllTrainers(pokemonTrainers);

            for (Adventure adventure : adventures) {
                //eveniment can have 3 values: 0 for battle vs Neutrel1, 1 for battle vs Neutrel2 and 2 for pokemon1 vs pokemon2
                boolean checker = true;
                while (checker) {
                    int eveniment = chooseEveniment(0, 2);
                    switch (eveniment) {
                        case 0:
                            battlesVsNeutrel(adventure, neutrels[0]);
                            break;
                        case 1:
                            battlesVsNeutrel(adventure, neutrels[1]);
                            break;
                        case 2:
                            battlesVsEachOhter(adventure);
                            checker = false;
                    }
                }
            }
            //best pokemons fight
            Adventure finalAdventure = calculateBestPokemons(pokemonTrainers);
            LoggingPrinters.finalAdventure(finalAdventure);
            battlesVsEachOhter(finalAdventure);
        }
    }

    public static void main(String[] args) {

        logger = Logger.getLogger("MyLog");

        System.out.println("Where do you want to print the logging info?");
        System.out.println("Press 1 for printing in a file and 0 for stdoud.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            try {
                Logger parentLog= logger.getParent();
                if (parentLog!=null && parentLog.getHandlers().length > 0)
                    parentLog.removeHandler(parentLog.getHandlers()[0]);
                FileHandler fh = new FileHandler("outFile/MyLog.log");
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
            } catch (SecurityException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Administration administration = new Administration();
        Initialization init = new Initialization();

        File folder = new File("in");
        ArrayList<Path> files = init.fileInit(folder);

        administration.play(files, init);
        }

    }



public class LoggingPrinters {

    public LoggingPrinters() {
    }

    public static void pokemonStunned(String name){
        Administration.logger.info(name + " is stunned!");
    }

    public static void finalAdventure(Adventure adventure){
        Administration.logger.info("Welcome to the final Adventures!");
        if (adventure.getPokemon1() != null && adventure.getPokemon2() != null)
            Administration.logger.info("Competitors: " + adventure.getPokemon1().getName() + " VS " +
                adventure.getPokemon2().getName());
        else {
            Administration.logger.info("Since one of the competitors is already dead, we only have 1 competitor");
            if (adventure.getPokemon1() == null)
                Administration.logger.info("... " + adventure.getPokemon2().getName() + "!");
            else
                Administration.logger.info("... " + adventure.getPokemon1().getName() + "!");
        }
    }

    public static void printAbilities(Ability[] abilities){
        if (abilities == null)
            return;
        Administration.logger.info("\n\tAbility1: " +
                "\n\t\tDamage: " + abilities[0].getDmg() +
                "\n\t\tDodge: " + abilities[0].isDodge() +
                "\n\t\tStun: " + abilities[0].isStun() +
                "\n\t\tCooldown: " + abilities[0].getCd() +
                "\n\tAbility2: " +
                "\n\t\tDamage: " + abilities[1].getDmg() +
                "\n\t\tDodge: " + abilities[1].isDodge() +
                "\n\t\tStun: " + abilities[1].isStun() +
                "\n\t\tCooldown: " + abilities[1].getCd());
    }

    public static void printDraw(){
        Administration.logger.info("It's a Draw!");
    }

    public static void pokemonAlreadyDead(){
        Administration.logger.info("(his corespondant already died)");
    }

    public static void pokemonDodged(String name){
        Administration.logger.info(name + " dodged!");
    }

    public static void pokemonLost(String name){
        Administration.logger.info(name + " lost!");
    }

    public static void printAttackWithAbility(Pokemon attacker, Pokemon defender, int i){
        Administration.logger.info(attacker.getName() + " attacks " + defender.getName() + " with ability " + i);
        printPokemonState(defender);
    }

    public static void printPokemonState(Pokemon pokemon){
        Administration.logger.info(pokemon.getName() + "'s new HP: " + pokemon.getHp());
    }

    public static void printAttack0(Pokemon attacker, Pokemon defender){
        Administration.logger.info(attacker.getName() + " attacks " + defender.getName() + "!");
        printPokemonState(defender);
    }

    public static void printPokemonCaracteristics(Pokemon pokemon){
        Ability[] abilities = pokemon.getAbilities();
        Administration.logger.info(pokemon.getName() + ": \n\t HP:" + pokemon.getHp() +
                "\n\tNormal Attack: " + pokemon.getNormalAttack() +
                "\n\tSpecial Attack: " + pokemon.getSpecialAttack() +
                "\n\tDefense: " + pokemon.getDefense() +
                "\n\tSpecialDefense: " + pokemon.getSpecialDefense());
        printAbilities(abilities);

    }

    public static void pokemonVSPokemon(Pokemon pokemon1, Pokemon pokemon2){
        Administration.logger.info("Fight: " + pokemon1.getName() + " VS " + pokemon2.getName());
        printPokemonCaracteristics(pokemon1);
        printPokemonCaracteristics(pokemon2);
    }

    public static void pokemonWins(String name){
        Administration.logger.info(name + " won!");
    }

    public static void letTheGamesBegin(){
        Administration.logger.info("Let the games begin!");
    }

    public static void testFile(int i){
        Administration.logger.info("Test File nr: " + i);
        Administration.logger.info("-------------------------------------------------------------------------");
    }

    public static void printTrainersPokemons(String name, Pokemon[] pokemons){
        Administration.logger.info(name +"'s pokemons: " + pokemons[0].getName() + ", " + pokemons[1].getName()
                + ", " + pokemons[2].getName());
    }

    public static void printAllTrainers(PokemonTrainer[] pokemonTrainers){
        Administration.logger.info("Trainers: " + pokemonTrainers[0].getName() + " VS " +
                pokemonTrainers[1].getName());

        printTrainersPokemons(pokemonTrainers[0].getName(), pokemonTrainers[0].getPokemons());
        printTrainersPokemons(pokemonTrainers[1].getName(), pokemonTrainers[1].getPokemons());
    }
}

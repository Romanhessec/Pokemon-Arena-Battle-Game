public class BattleNeutrelCommand extends Command{

    public BattleNeutrelCommand() {
    }

    @Override
    public void execute(Pokemon pokemon, Pokemon neutrel) {
        int hpPokemon = pokemon.getHp();
        int hpNeutrel = neutrel.getHp();
        int hasDodge = 0;
        int hasStun = 0;
        int cd1 = 0;
        int cd2 = 0;

        while(true){
            int eveniment = Administration.chooseEveniment(0, 2);
            cd1--;
            cd2--;
            switch (eveniment) {
                case 0:
                    int dif;
                    if (pokemon.getSpecialAttack() != 0){
                        dif = pokemon.getSpecialAttack() - neutrel.getSpecialDefense();
                    }
                    else {
                        dif = pokemon.getNormalAttack() - pokemon.getDefense();
                    }
                    if (dif > 0)
                        neutrel.setHp(neutrel.getHp() - dif);
                    LoggingPrinters.printAttack0(pokemon, neutrel);
                    break;
                case 1:
                    if (cd1 <= 0) {
                        cd1 = useAbility(pokemon.getAbilities()[0], neutrel, pokemon);
                        LoggingPrinters.printAttackWithAbility(pokemon, neutrel, 1);
                        break;
                    }
                    cd1++;
                    cd2++;
                    continue;
                case 2:
                    if (cd2 <= 0) {
                        cd2 = useAbility(pokemon.getAbilities()[1], neutrel, pokemon);
                        LoggingPrinters.printAttackWithAbility(pokemon, neutrel, 2);
                        break;
                    }
                    cd1++;
                    cd2++;
                    continue;
            }
            if (neutrel.getHp() <= 0) {
                LoggingPrinters.pokemonWins(pokemon.getName());
                upgradePokemon(pokemon, hpPokemon);
                LoggingPrinters.printPokemonCaracteristics(pokemon);
                neutrel.setHp(hpNeutrel);
                return;
            }
            if (hasDodge == 0) {
                LoggingPrinters.printAttack0(neutrel, pokemon);
                pokemon.setHp(pokemon.getHp() - (neutrel.getNormalAttack() - pokemon.getDefense()));
                if (pokemon.getHp() <= 0) {
                    LoggingPrinters.pokemonLost(pokemon.getName());
                    return;
                }
            }
            else {
                LoggingPrinters.pokemonDodged(pokemon.getName());
            }
        }
    }

    public int useAbility(Ability ability, Pokemon defender, Pokemon attacker){
        defender.setHp(defender.getHp() - ability.getDmg());
        if (ability.isDodge())
            ability.setDodge(true);
        if (ability.isStun())
            ability.setStun(true);
        return ability.getCd();
    }

    public void upgradePokemon(Pokemon pokemon, int hp){
        pokemon.setHp(hp + 1);
        pokemon.setDefense(pokemon.getDefense() + 1);
        pokemon.setSpecialDefense(pokemon.getSpecialDefense() + 1);
        if (pokemon.getNormalAttack() != 0) {
            pokemon.setNormalAttack(pokemon.getNormalAttack() + 1);
            return;
        }
        pokemon.setSpecialAttack(pokemon.getSpecialAttack() + 1);
    }

}

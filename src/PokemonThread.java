public class PokemonThread extends Thread{
    private Pokemon attacker;
    private Pokemon defender;
    private PokemonThread pokemonOtherThread;

    public PokemonThread(Pokemon attacker, Pokemon defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public void run(){
        int hp = attacker.getHp();
        boolean done = true;
        int cd1 = 0;
        int cd2 = 0;
        while (done){
            cd1--;
            cd2--;
            int eveniment = Administration.chooseEveniment(0, 2);
            if (attacker.isStunned()){
                eveniment = 3;
                attacker.setStunned(false);
                LoggingPrinters.pokemonStunned(attacker.getName());
            } else {
                if (defender.hasDodge()) {
                    eveniment = 3;
                    defender.setDodge(false);
                    LoggingPrinters.pokemonDodged(defender.getName());
                }
            }
            if (attacker.getHp() <= 0){
                attacker.setWinner(false);
                attacker.setHp(hp);
                eveniment = 4;
            }
            switch (eveniment) {
                case 0:
                    int dif;
                    if (attacker.getSpecialAttack() != 0) {
                        dif = attacker.getSpecialAttack() - defender.getSpecialDefense();
                    }
                    else {
                        dif = attacker.getNormalAttack() - defender.getDefense();
                    }
                    if (dif > 0)
                        defender.setHp(defender.getHp() - dif);
                    LoggingPrinters.printAttack0(attacker, defender);
                    break;
                case 1:
                    if (cd1 <= 0){
                        cd1 = useAbility(attacker.getAbilities()[0], defender, attacker);
                        LoggingPrinters.printAttackWithAbility(attacker, defender, 1);
                        continue;
                    }
                    //they will get decreased at the next iteration
                    cd1++;
                    cd2++;
                    break;
                case 2:
                    if (cd2 <= 0){
                        cd2 = useAbility(attacker.getAbilities()[1], defender, attacker);
                        LoggingPrinters.printAttackWithAbility(attacker, defender, 2);
                        break;
                    }
                    cd1++;
                    cd2++;
                    continue;
                case 3:
                    //skips (either the attacker was stunned, or the defender had dodge)
                    break;
                case 4:
                    done = false;
                    continue;
            }

            if (defender.getHp() <= 0){
                if (attacker.getHp() >= 0) {
                    upgradePokemon(attacker, hp);
                    attacker.setWinner(true);
                    LoggingPrinters.pokemonWins(attacker.getName());
                    LoggingPrinters.printPokemonCaracteristics(attacker);
                    done = false;
                    synchronized (pokemonOtherThread) {
                        pokemonOtherThread.notify();
                    }
                    continue;
                }
                else
                    LoggingPrinters.printDraw();
            }

            synchronized (pokemonOtherThread){
                pokemonOtherThread.notify();
            }

            try {
                synchronized (this){
                    wait();
                }
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public int useAbility(Ability ability, Pokemon defender, Pokemon attacker){
        defender.setHp(defender.getHp() - ability.getDmg());
        if (ability.isDodge())
            attacker.setDodge(true);
        if (ability.isStun())
            defender.setStunned(true);
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

    public Pokemon getAttacker() {
        return attacker;
    }

    public void setAttacker(Pokemon attacker) {
        this.attacker = attacker;
    }

    public Pokemon getDefender() {
        return defender;
    }

    public void setDefender(Pokemon defender) {
        this.defender = defender;
    }

    public PokemonThread getPokemonOtherThread() {
        return pokemonOtherThread;
    }

    public void setPokemonOtherThread(PokemonThread pokemonOtherThread) {
        this.pokemonOtherThread = pokemonOtherThread;
    }
}

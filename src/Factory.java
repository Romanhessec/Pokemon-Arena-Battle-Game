public class Factory {

    private static Factory instance;

    public static Factory getInstance(){
        if (instance == null)
            instance = new Factory();
        return instance;
    }

    public Command createCommand(String type){
        switch (type) {
            case "vsNeutrel":
                return new BattleNeutrelCommand();
            case "vsEachOther":
                return new BattleVsEachOtherCommand();
            default:
                return null;
        }
    }
}

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Initialization {

    public Initialization() {}

    public ArrayList<Path> fileInit(File folder){
        File[] listOfFiles = folder.listFiles();
        ArrayList<Path> files = new ArrayList<>();

        for (File file : listOfFiles){
            if (file.isFile()){
                files.add(file.toPath());
            }
        }

        return files;
    }

    public PokemonTrainer[] pokemonTrainersInit(Path file){
        PokemonTrainer[] pokemonTrainers = new PokemonTrainer[2];
        try {
                Reader reader = Files.newBufferedReader(file);
                pokemonTrainers = new Gson().fromJson(reader, new TypeToken<PokemonTrainer[]>() {
                }.getType());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return pokemonTrainers;
    }
}

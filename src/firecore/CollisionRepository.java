package firecore;

import doctrina.Blockade;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollisionRepository {

    private List<Blockade> collisions;

    public List<Blockade> getCollisions() {
        return collisions;
    }

    public CollisionRepository(String COLLISIONS_PATH, int width, int height, int spriteWidth, int idCollision) {
        collisions = new ArrayList<>();
        int[][] coordsCollisions = new int[width][height];

        try {
            File fichier = new File(COLLISIONS_PATH);
            FileReader fileReader = new FileReader(fichier);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne;
            int ligneIndex = 0;

            while ((ligne = bufferedReader.readLine()) != null && ligneIndex < height) {
                String[] donnees = ligne.split(",");

                for (int colonneIndex = 0; colonneIndex < Math.min(width, donnees.length); colonneIndex++) {
                    coordsCollisions[ligneIndex][colonneIndex] = Integer.parseInt(donnees[colonneIndex]);
                    if (coordsCollisions[ligneIndex][colonneIndex] == idCollision) {
                        collisions.add(new Blockade(colonneIndex * spriteWidth, ligneIndex * spriteWidth));
                    }
                }

                ligneIndex++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package firecore;

import doctrina.Blockade;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollisionRepository {

    private static final String COLLISIONS_PATH = "resources/collisions/collisions.txt";
    private List<Blockade> collisions;

    public List<Blockade> getCollisions() {
        return collisions;
    }

    public CollisionRepository() {
        collisions = new ArrayList<>();
        System.out.println(COLLISIONS_PATH);
        int[][] tableau2D = new int[100][100];

        try {
            File fichier = new File(COLLISIONS_PATH);
            FileReader fileReader = new FileReader(fichier);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne;
            int ligneIndex = 0;

            while ((ligne = bufferedReader.readLine()) != null && ligneIndex < 100) {
                String[] donnees = ligne.split(",");

                for (int colonneIndex = 0; colonneIndex < Math.min(100, donnees.length); colonneIndex++) {
                    tableau2D[ligneIndex][colonneIndex] = Integer.parseInt(donnees[colonneIndex]);
                    if (tableau2D[ligneIndex][colonneIndex] == 763) {
                        collisions.add(new Blockade(colonneIndex * 32, ligneIndex * 32));
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

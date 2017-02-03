package jeu;

/**
 * Created by mathi on 03/02/2017.
 */
public class Modeles {

    private int modele1[][] = //facile
            {{0,2,-1,9,-1,0,0,-1,7},
            {5,-1,0,0,5,0,0,0,0},
            {0,1,0,0,-1,-1,0,-1,0},
            {6,-1,-1,0,1,0,-1,0,2},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,-1,0,-1,0,0},
            {2,-1,0,0,0,0,0,0,0},
            {-1,-1,0,2,-1,0,0,-1,4},
            {2,0,0,0,3,-1,0,-1,5}};

    private int modele2[][] = //difficile
            {{0,0,0,0,-1,-1,0,0,0},
            {0,-1,0,0,-1,-1,2,-1,1},
            {0,0,-1,0,0,0,-1,0,0},
            {-1,0,-1,-1,0,0,0,0,0},
            {0,0,0,8,0,0,0,0,-1},
            {-1,0,1,-1,0,-1,0,0,0},
            {0,-1,0,-1,0,0,0,0,0},
            {1,-1,-1,0,0,0,-1,0,0},
            {0,0,5,0,0,-1,-1,-1,0}};

    private int modele3[][] = //difficile
            {{6,-1,0,0,-1,0,3,0,0},
            {0,0,0,0,0,-1,-1,0,1},
            {0,2,0,-1,0,0,0,0,0},
            {0,-1,0,0,0,-1,0,-1,0},
            {0,0,-1,0,-1,-1,-1,0,0},
            {0,0,0,0,-1,-1,0,0,0},
            {-1,0,1,-1,-1,0,0,0,-1},
            {0,0,0,-1,0,0,-1,-1,-1},
            {-1,-1,0,5,0,0,0,-1,-1}};

    private int modele4[][] = //moyen
            {{-1,0,3,0,0,6,0,0,5},
            {0,3,0,-1,0,-1,0,0,0},
            {0,0,0,0,4,0,0,0,9},
            {0,-1,0,0,0,0,0,0,0},
            {0,0,-1,0,0,0,5,2,4},
            {0,4,0,0,-1,0,0,4,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,3,0,2,6,0,-1,0},
            {0,1,2,7,0,0,0,-1,3}};

    private int modele5[][] = //moyen
            {{0,4,0,0,0,0,8,0,0},
            {7,-1,2,0,0,-1,-1,0,0},
            {0,0,0,0,0,0,0,-1,0},
            {0,0,0,0,0,-1,0,0,-1},
            {0,0,0,-1,1,0,-1,0,-1},
            {0,0,0,0,0,0,-1,-1,-1},
            {0,-1,0,-1,0,0,0,0,-1},
            {0,2,0,0,-1,-1,1,0,-1},
            {-1,0,0,0,-1,-1,-1,-1,-1}};

    private int modele6[][] = //moyen
            {{1,0,0,0,7,0,0,9,0},
            {0,0,3,0,4,-1,0,0,0},
            {-1,0,0,-1,0,3,5,0,0},
            {0,0,0,0,6,4,0,7,-1},
            {0,0,0,0,0,2,0,0,0},
            {0,0,0,0,0,0,-1,0,0},
            {0,2,0,5,1,-1,0,0,1},
            {0,0,0,0,0,2,0,0,0},
            {0,-1,0,-1,0,0,0,0,0}};

    private Tableau t1;
    private Tableau t2;
    private Tableau t3;
    private Tableau t4;
    private Tableau t5;
    private Tableau t6;

    public Modeles() {

        t1 = new Tableau(modele1, 9);
        t2 = new Tableau(modele2, 9);
        t3 = new Tableau(modele3, 9);
        t4 = new Tableau(modele4, 9);
        t5 = new Tableau(modele5, 9);
        t6 = new Tableau(modele6, 9);
        try {
            t1.sauvegarderGrilleTexte();
            t2.sauvegarderGrilleTexte();
            t3.sauvegarderGrilleSerial();
            t4.sauvegarderGrilleSerial();
            t5.sauvegarderGrilleSerial();
            t6.sauvegarderGrilleSerial();

        } catch (Exception e){
            e.getMessage();
        }
    }

    public int[][] getModele1() {
        return modele1;
    }

    public int[][] getModele2() {
        return modele2;
    }

    public int[][] getModele3() {
        return modele3;
    }

    public int[][] getModele4() {
        return modele4;
    }

    public int[][] getModele5() {
        return modele5;
    }

    public int[][] getModele6() {
        return modele6;
    }



}

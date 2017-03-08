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
    
    private int modele7[][] = //grille 6x6 de Tests>5.png
    		{{1,0,-1,1,-1,0},
    		{-1,-1,3,0,0,0},
    		{1,-1,1,-1,-1,-1},
    		{0,-1,0,1,0,3},
    		{-1,-1,-1,2,-1,0},
    		{-1,-1,-1,-1,0,0}};
    
    private int modele8[][] = //grille 6x6 de Tests>5.png
		{{1,2,-1,1,-1,2},
		{-1,-1,3,2,4,1},
		{1,-1,1,-1,-1,-1},
		{0,-1,2,1,4,3},
		{-1,-1,-1,2,-1,1},
		{-1,-1,-1,-1,1,2}};
    
    private int modele9[][] = //grille 6x6 de Tests>5.png
		{{2,1,-1,2,-1,1},
		{-1,-1,3,1,4,2},
		{2,-1,1,-1,-1,-1},
		{1,-1,2,1,4,3},
		{-1,-1,-1,2,-1,1},
		{-1,-1,-1,-1,1,2}};
    
    private int modele10[][] = //grille 6x6 de Tests>5.png
		{{1,3,-1,1,-1,2},
		{-1,-1,3,2,4,1},
		{1,-1,1,-1,-1,-1},
		{2,-1,2,1,4,3},
		{-1,-1,-1,2,-1,1},
		{-1,-1,-1,-1,1,2}};

    private Tableau t1;
    private Tableau t2;
    private Tableau t3;
    private Tableau t4;
    private Tableau t5;
    private Tableau t6;
    private Tableau t7;
    private Tableau t8;
    private Tableau t9;
    private Tableau t10;
    

    public Modeles() {

        t1 = new Tableau(modele1, 9);
        t2 = new Tableau(modele2, 9);
        t3 = new Tableau(modele3, 9);
        t4 = new Tableau(modele4, 9);
        t5 = new Tableau(modele5, 9);
        t6 = new Tableau(modele6, 9);
        t7 = new Tableau(modele7, 6);
        t8 = new Tableau(modele8, 6);
        t9 = new Tableau(modele9, 6);
        t10 = new Tableau(modele10, 6);
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
    
    public int[][] getModele7(){
    	return modele7;
    }
    
    public int[][] getModele8(){
    	return modele8;
    }
    
    public int[][] getModele9(){
    	return modele9;
    }
    
    public int[][] getModele10(){
    	return modele10;
    }
    
    public void sauvegarderModeles(){
        try {
            Tableau.sauvegarderGrilleSerial(t1,"Modeles/");
            //.restaurerGrilleSerial("Modeles/grille_1_9x9.ser");
            Tableau.sauvegarderGrilleSerial(t2,"Modeles/");
            Tableau.sauvegarderGrilleSerial(t3,"Modeles/");
            Tableau.sauvegarderGrilleSerial(t4,"Modeles/");
            Tableau.sauvegarderGrilleSerial(t5,"Modeles/");
            Tableau.sauvegarderGrilleSerial(t6,"Modeles/");
            Tableau.sauvegarderGrilleSerial(t7,"Modeles/");
            Tableau.sauvegarderGrilleSerial(t8,"Modeles/");
            Tableau.sauvegarderGrilleSerial(t9,"Modeles/");
            Tableau.sauvegarderGrilleSerial(t10,"Modeles/");

        } catch (Exception e){
            e.printStackTrace();
        }
    }



}
package testRestaurerSauvegarder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/*
 * Classe matrice quelconque
 */
public class Matrice implements Serializable{
	int[][] matrice;
	public void sauvegarderGrilleTexte() throws Exception{
		FileWriter fileWriter = new FileWriter("./matrice.txt");
		for(int i = 0;i<this.matrice.length;i++){
			for (int j = 0;j<this.matrice[i].length;j++){
				fileWriter.write(String.valueOf(this.matrice[i][j]));
			}
			fileWriter.write("\n");
		}
		fileWriter.close();		
	}
	public void restaurerGrilleTexte(String nomFichier) throws Exception{
		LineNumberReader in = new LineNumberReader(new FileReader(nomFichier));
		String str ="";
		int j = 0;
		this.matrice = null; 
		while((str = in.readLine()) != null){
			if (this.matrice == null){
				this.matrice = new int[str.length()][str.length()];
			}
			for(int i = 0;i<str.length();i++) {
				this.matrice[i][j] = Integer.parseInt(Character.toString(str.charAt(i)));
			}
			j++;
		}
		in.close();
	}
	public void sauvegarderGrilleSerial() throws Exception {
		FileOutputStream fileOut = new FileOutputStream("./matrice.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this.matrice);
		out.close();
	}
	public void restaurerGrilleSerial(String nomFichier) throws Exception{
		FileInputStream fileIn = new FileInputStream("./matrice.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		this.matrice = (int[][]) in.readObject();
		in.close();
	}
	public void afficherGrille(){
		for (int i = 0;i<this.matrice.length;i++){
			for (int g = 0;g<this.matrice[i].length;g++){
				if (this.matrice[i][g] == -1) { //case noire
					System.out.print("|X");
				}
				else if (this.matrice[i][g] == 0){
					System.out.print("| ");
				}
				else{
					System.out.print("|"+this.matrice[i][g]);
				}
			}
			System.out.print("|\n");
		}
	}
	public void genererGrillee(int difficulte,int taille) throws Exception {
		matrice = new int[taille][taille];
		int i;
		int j;
		int nbCaseNoire;
		int nbCaseVide; //nombre de case vide (nbCase - nbCaseRemplie)
		int nbCaseChiffre; //nombre de case avec chiffre (nbCaseRemplie - nbCaseNoire)
		Random random = new Random();
		int chiffreAleatoire; // variable pour cree un nombre aleatoire et proportionnel entre les cases noires et les cases préremplie(noire et chiffre proportionnel)
		int nbCaseRemplie; //Case noire + case chiffre
		int nbCase = (taille * taille);

		/*
			La difficulté est déterminée par le nombre de case préremplie (noires ou chiffre)
		 */
		switch (difficulte) {
			case 1 : //facile 50% des cases déja remplie
				nbCaseRemplie  = nbCase/ 2;
				chiffreAleatoire = random.nextInt(nbCaseRemplie - 1)+1; //de 1 à nbCaseRemplie
				nbCaseNoire = chiffreAleatoire;
				nbCaseChiffre = nbCaseRemplie - nbCaseNoire;
				nbCaseVide = nbCase - nbCaseRemplie;
				break;
			case 2 : //normale 40 % des cases remplie
				nbCaseRemplie  = (int) (nbCase * 0.4);
				chiffreAleatoire = random.nextInt(nbCaseRemplie - 1)+1; //de 1 à nbCaseRemplie
				nbCaseNoire = chiffreAleatoire;
				nbCaseChiffre = nbCaseRemplie - nbCaseNoire;
				nbCaseVide = nbCase - nbCaseRemplie;
				break;
			case 3 : //difficile 30 % des cases remplie
				nbCaseRemplie  = (int) (nbCase * 0.3);
				chiffreAleatoire = random.nextInt(nbCaseRemplie - 1)+1; //de 1 à nbCaseRemplie
				nbCaseNoire = chiffreAleatoire;
				nbCaseChiffre = nbCaseRemplie - nbCaseNoire;
				nbCaseVide = nbCase - nbCaseRemplie;
				break;
			default:
				throw new Exception("Pas de difficulte valide, choisir entre 1,2 et 3");
		}
		while (nbCaseNoire != 0){ //On place les nbCasesNoires
			i = random.nextInt(taille);
			j = random.nextInt(taille);
			this.matrice[i][j] = -1;
		}
		// on remplie la grille backtracking

		//remplirGrille();


		while (nbCaseVide!= 0){ //On vide de nbCaseVide
			i = random.nextInt(taille);
			j = random.nextInt(taille);
			this.matrice[i][j] = 0;
		}
	}
}

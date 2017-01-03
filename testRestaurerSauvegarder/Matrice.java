package testRestaurerSauvegarder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/*
 * Classe matrice quelconque
 */
public class Matrice implements Serializable{
	int[][] matrice = new int[2][2];
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
	public void sauvegarderGrilleSerial() throws Exception{
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
				System.out.println(this.matrice[i][g]);
			}
		}
	}
}

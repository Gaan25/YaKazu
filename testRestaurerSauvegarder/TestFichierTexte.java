package testRestaurerSauvegarder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;

public class TestFichierTexte {
	public static void main(String[] args) {
		Matrice m = new Matrice();
		m.matrice[0][0] = 1;
		m.matrice[0][1] = 2;
		m.matrice[1][0] = 3;
		m.matrice[1][1] = 4;
		try {
			m.sauvegarderGrilleTexte();
			m.restaurerGrilleTexte("matrice.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.afficherGrille();
		
	}
}

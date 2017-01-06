package testRestaurerSauvegarder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerialize{
	
	public static void main(String[] args) {
		Matrice m = new Matrice();
		for (int i = 0;i<9;i++){
			for (int j = 0;j<9;j++){
				m.matrice[i][j] = 1;
			}
		}
		try {
			m.sauvegarderGrilleSerial();
			m.restaurerGrilleSerial("matrice.ser");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.afficherGrille();
		
	}
}
		


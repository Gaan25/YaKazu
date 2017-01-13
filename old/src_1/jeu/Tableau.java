package jeu;

import java.io.*;
import java.util.Random;

public class Tableau {

	private Case tabCase[][];
	int size;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getLigne(int position){
		return position/getSize();
	}
	public int getColonne(int position){
		return position%getSize();
	}
	public int getCase(int i , int j){
		return tabCase[i][j].getChiffre();
	}
	public int getCase(int position){
		int i = position/(getSize());
		int j = position%(getSize());
		return tabCase[i][j].getChiffre();
	}
	
	public void modifier_case (int position, int chiffre){
		int i = position/(getSize());
		int j = position%(getSize());

		if (getSize()<chiffre) System.out.println("Impossible la taille tu tableau est de"+ getSize()+"*" + getSize());
		else 
			
		tabCase[i][j].setChiffre(chiffre);
	}
	public void init_tableau(){
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				tabCase[i][j]= new Case(0);
			}
		}
	}
	public Tableau (int size){
		setSize(size);
		if (getSize()>=3 && getSize()<=9){
			tabCase = new Case[size][size];
			init_tableau();
		}
		else System.out.println("Erreur : la grille a un format compris entre 3x3 et 9x9");
		
	}
	//Walid = j'ai rajouter ce constructeur pour alléger l'initialisation de la grille dans le main
	public Tableau (int tab[][], int size){
		this(size);
		int numCase = 0;
		for (int i = 0;i<size;i++){
			for (int j = 0 ;j<size;j++){
				modifier_case(numCase,tab[i][j]);
				numCase++;
			}
		}
	}
	public String toString(){
		StringBuffer s1 = new StringBuffer(255);
		for (int i = 0; i < getSize(); i++) {
			
			for (int j = 0; j < getSize(); j++) {
				s1.append(tabCase[i][j].toString());
					if (j==getSize()-1){
						s1.append("\n");
					}
				/*System.out.println(tabCase[i][j].toString());*/
			}
		}
		String s2 = s1.toString();
		return s2;
	}
	//backtracking

	//position et pas i car ligne i pas assez précis (cases noires)
	public boolean estValide (int position) {
	    // Si on est à la n*n eme case (on sort du tableau)
	    if (position == (getSize()*getSize()))
	        return true;

	    // On récupère les coordonnées de la case
	    int i = position/getSize(), j = position%getSize();
	    
	    // Si la case n'est pas vide, on passe à la suivante (appel récursif)
	    if (tabCase[i][j].getChiffre() != 0)
	        return estValide(position+1);

	    // A implémenter : backtracking
	   
	    //absentLigne(k,i) n'est pas assez précis, ici il nous faut la position en raison des cases noires présentes sur les lignes
	    
		    for (int k=1; k <= getSize(); k++) /*tout le tableau? risque pas de ne pas s'arreter en cas de case noire?*/
		    {
		        if (absentLigne(k,i) && absentColonne(k,j) && valideLigne(k,position,i) && valideColonne(k,position,j))/* && valideColonne(k,position,j)*/
		        {
		        	modifier_case(position,k);
		        	
		            if ( estValide (position+1) )
		                return true;
		        }
		    }
		    modifier_case(position,0);
		    return false;
		    /*on renonce a cette "idée"*/
	}
	public boolean absentLigne (int k, int i) {
	    for (int j=0; j < getSize(); j++)
	    {
	    	if (getCase(i,j) == k)
	            return false;
	        else if (getCase(i,j) == -1) 	
	        	//il y a donc i chiffres dans la ligne avant la case noire allant de 1 à i	
	        	return true;
	    }
	            
	    return true;
	}
	public boolean absentColonne (int k,int j){
	    for (int i=0; i < getSize(); i++)
	    {
	    	if (getCase(i,j) == k)
	            return false;
	        else if (getCase(i,j) == -1)
	        	//il y a donc i chiffres dans la ligne avant la case noire allant de 1 à i
	    		
	        	return true;
	    }
	    return true;
	   
	}
	public boolean valideLigne(int k, int position, int i){
		int pos_dernier_ligne= ((i+1)*getSize())-1;
		int pos_premier_ligne= ((i)*getSize());
		int size_ligne=1; /*car on est sur une case VIDE*/
		int j;
		for (j=1 ; j<getSize(); j++)
		{
			if (position-j<pos_premier_ligne)
			{
				size_ligne=size_ligne+(j-1);
				j=getSize();//break;
			}
			else if (getCase(position-j) == -1)
				size_ligne=size_ligne+(j-1);
		}
		for (j=1 ; j<getSize(); j++)
		{
			if (position+j>pos_dernier_ligne)
			{
				size_ligne=size_ligne+(j-1);
				j=getSize();//break;
			}
			else if (getCase(position+j) == -1)
				size_ligne=size_ligne+(j-1);
		}
		if (k>size_ligne)
			return false;
		else
			return true;
	}
	public boolean valideColonne(int k, int position, int j){
		int pos_dernier_colonne= (getSize()*getSize())+(-getSize()+(j+1));
		int pos_premier_colonne= j;
		int size_colonne=1; /*car on est sur une case VIDE*/
		int i;
		for (i=1 ; i<getSize(); i++)
		{
			if (position-(i*getSize())<pos_premier_colonne)
			{
				size_colonne=size_colonne+(i-1);
				i=getSize();//break;
			}
			else if (getCase(position-(i*getSize())) == -1)
				size_colonne=size_colonne+(i-1);
		}
		for (i=1 ; i<getSize(); i++)
		{
			if (position+(i*getSize())>pos_dernier_colonne)
			{
				size_colonne=size_colonne+(i-1);
				i=getSize();//break;
			}
			else if (getCase(position+(i*getSize())) == -1)
				size_colonne=size_colonne+(i-1);
		}
		if (k>size_colonne)
			return false;
		else
			return true;
	}

	public void sauvegarderGrilleTexte() throws Exception{
		FileWriter fileWriter = new FileWriter("./tabCase.txt");
		for(int i = 0;i<this.tabCase.length;i++){
			for (int j = 0;j<this.tabCase[i].length;j++){
				fileWriter.write(String.valueOf(this.tabCase[i][j]));
			}
			fileWriter.write("\n");
		}
		fileWriter.close();
	}
	public void restaurerGrilleTexte(String nomFichier) throws Exception{
		LineNumberReader in = new LineNumberReader(new FileReader(nomFichier));
		String str ="";
		int j = 0;
		this.tabCase = null;
		while((str = in.readLine()) != null){
			if (this.tabCase == null){
				this.tabCase = new Case[str.length()][str.length()];
			}
			for(int i = 0;i<str.length();i++) {
				this.tabCase[i][j].setChiffre(Integer.parseInt(Character.toString(str.charAt(i))));
			}
			j++;
		}
		in.close();
	}
	public void sauvegarderGrilleSerial() throws Exception {
		FileOutputStream fileOut = new FileOutputStream("./tabCase.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this.tabCase);
		out.close();
	}
	public void restaurerGrilleSerial(String nomFichier) throws Exception{
		FileInputStream fileIn = new FileInputStream("./tabCase.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		this.tabCase = (Case[][]) in.readObject();
		in.close();
	}
	public void afficherGrille(){
		System.out.println("Grille : ");

		System.out.println(this.toString());
	}
	public void genererGrillee(int difficulte,int taille) throws Exception {
		init_tableau();

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
			this.tabCase[i][j].setChiffre(-1);
			nbCaseNoire --;
		}
		// on remplie la grille backtracking

		estValide(0);


		while (nbCaseVide!= 0){ //On vide de nbCaseVide
			i = random.nextInt(taille);
			j = random.nextInt(taille);
			if (tabCase[i][j].getChiffre() == -1){
				continue;
			}
			this.tabCase[i][j].setChiffre(0);
			nbCaseVide --;
		}
	}
}

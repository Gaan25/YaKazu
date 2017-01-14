package jeu;

public class Tableau {

	private Case TabCase[][];
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
		return TabCase[i][j].getChiffre();
	}
	
	public int getCase(int position){
		if (position<0) return -1;
		
		int i = position/(getSize());
		int j = position%(getSize());
		return TabCase[i][j].getChiffre();
	}
	
	public void modifier_case (int position, int chiffre){
		int i = position/(getSize());
		int j = position%(getSize());

		if (getSize()<chiffre) System.out.println("Impossible la taille tu tableau est de"+ getSize()+"*" + getSize());
		else 
			
		TabCase[i][j].setChiffre(chiffre);
	}
	
	public void init_tableau(){
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				TabCase[i][j]= new Case(0);
			}
		}
	}
	
	public Tableau (int size){
		setSize(size);
		if (getSize()>=3 && getSize()<=9){
			TabCase = new Case[size][size];
			init_tableau();
		}
		else System.out.println("Erreur : la grille a un format compris entre 3x3 et 9x9");
		
	}
	
	public String toString(){
		StringBuffer s1 = new StringBuffer(255);
		for (int i = 0; i < getSize(); i++) {
			
			for (int j = 0; j < getSize(); j++) {
				s1.append(TabCase[i][j].toString());
					if (j==getSize()-1){
						s1.append("\n");
					}
				/*System.out.println(TabCase[i][j].toString());*/
			}
		}
		String s2 = s1.toString();
		return s2;
	}
	
	//backtracking
	
	
	
	//position et pas i car ligne i pas assez précis (cases noires)
	
	public boolean estValide (int position)
	{
	    // Si on est à la n*n eme case (on sort du tableau)
	    if (position == (getSize()*getSize()))
	        return true;

	    // On récupère les coordonnées de la case
	    int i = position/getSize(), j = position%getSize();
	    
	    // Si la case n'est pas vide, on passe à la suivante (appel récursif)
	    if (TabCase[i][j].getChiffre() != 0)
	        return estValide(position+1);

	    // A implémenter : backtracking
	   
	    //absentLigne(k,i) n'est pas assez précis, ici il nous faut la position en raison des cases noires présentes sur les lignes
		    for (int k=1; k <= getSize(); k++) /*tout le tableau? risque pas de ne pas s'arreter en cas de case noire?*/
		    {
		        if ((absentLigneCourante(k,i,position)) && (absentColonneCourante(k,j,position))  && (taille_valide(k,i,j,position)) ) /*&& valideLigne(k,position,i) && valideColonne(k,position,j)*//* && valideColonne(k,position,j)*/
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
	
	public boolean taille_valide (int k , int i ,int j ,int position){
		if (k > taille_ligne_courante(i,position) || (k > taille_colonne_courante(j,position)))
			return false;
			else return true;
		
	}
	public int taille_ligne_courante(int i , int position){
		
	int pos_dernier_ligne= ((i+1)*getSize())-1;
	int pos_premier_ligne= ((i)*getSize());
	int size_ligne = 1;
	int j = 1 ;
	
	while ((position-j>=pos_premier_ligne) && ( getCase(position-j) !=- 1 )){
		size_ligne++;
		j++;
	}
	j=1;
	while ((position+j<=pos_dernier_ligne) && (getCase(position+j) != -1)){
		size_ligne++;
		j++;
	}
	if (size_ligne==1) size_ligne=getSize();
	return size_ligne;
	}	
	
	public int pos_case_precedente(int j,int position){
		return position-(getSize()*1);
	}
	
	public int taille_colonne_courante(int j , int position){
		
		int pos_dernier_colonne= ((getSize()*getSize())-1) - (getSize()-(j+1));
		int pos_premier_colonne= j;
		int size_colonne= 1;
		int i = 1 ;
		int pos_case_precedente= position-(getSize()*i);
		int pos_case_suivante= position+(getSize()*i);
		
		while ((pos_case_precedente >= pos_premier_colonne) && ( getCase(pos_case_precedente) != -1 )){
			size_colonne++;
			i++;
			pos_case_precedente= position-(getSize()*i);
		}
		i=1;
		while ((pos_case_suivante<=pos_dernier_colonne) && (getCase(pos_case_suivante) != -1)){
			size_colonne++;
			i++;
			pos_case_suivante= position+(getSize()*i);
		}
		if(size_colonne==1) size_colonne=getSize();
		return size_colonne;
	}
	
	
	
	public int trouver_premiere_case_ligne_courante(int i , int position){
		int pos_premier_ligne= ((i)*getSize());
		int j =1;
		while ((position-j>=pos_premier_ligne) && ( getCase(position-j) !=- 1 )){
			j++;
			
		}
		return position-(j-1);
	}
	
	public int trouver_derniere_case_ligne_courante(int i , int position){
		int pos_dernier_ligne= ((i+1)*getSize())-1;
		int j =1;
		while ((position+j<=pos_dernier_ligne) && ( getCase(position+j) !=- 1 )){
			j++;
		}
		return position+(j-1);
	}
	
	public boolean absentLigneCourante (int k, int i, int position)
	{
	//	if (k>taille) return false;
		int debut = trouver_premiere_case_ligne_courante(i,position);
		int fin = trouver_derniere_case_ligne_courante(i,position);
		for (int j=debut; j < fin; j++)
	    {
	    	if (getCase(j) == k)
	            return false;
	    }     
	    return true;
	}
	
	public int trouver_premiere_case_colonne_courante(int j , int position){
		int pos_premier_colonne= j;
		int i =1;
		int pos_case_precedente= position-(getSize()*i);
		
		while ((pos_case_precedente>=pos_premier_colonne) && ( getCase(pos_case_precedente) !=- 1 )){
			i++;
			pos_case_precedente= position-(getSize()*i);
		}
		return pos_case_precedente+(getSize());
	}
	
	public int trouver_derniere_case_colonne_courante(int j , int position){
		
		int pos_dernier_colonne= ((getSize()*getSize())-1) - (getSize()-(j+1));
		int i =1;
		int pos_case_suivante= position+(getSize()*i);
		
		while ((pos_case_suivante<=pos_dernier_colonne) && ( getCase(pos_case_suivante) !=- 1 )){
			i++;
			pos_case_suivante= position+(getSize()*i);
		}
		return pos_case_suivante-(getSize());
	}
	
	public boolean absentColonneCourante (int k, int j, int position)
	{
	//	if (k>taille) return false;
		int debut = trouver_premiere_case_colonne_courante(j,position);
		int fin = trouver_derniere_case_colonne_courante(j,position);
		for (int i=debut; i < fin; i=i+getSize())
	    {
	    	if (getCase(i) == k)
	            return false;
	    }     
	    return true;
	}
	
	
	/*public boolean absentLigne (int k, int i)
	{
	    for (int j=0; j < getSize(); j++)
	    {
	    	if (getCase(i,j) == k)
	            return false;
	        else if (getCase(i,j) == -1) 	
	        
	        	return true;
	    }
	            
	    return true;
	}

	public boolean absentColonne (int k,int j)
	{
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
	*/
	
}

package jeu;

public class Case {
	private int chiffre ;
	
	public int getChiffre() {
		return chiffre;
	}

	public void setChiffre(int chiffre) {
		/*setVide(false);*/
		this.chiffre = chiffre;
	}

	public String toString(){
		String s;
		if(getChiffre()<0) s=" N";
		else if(getChiffre()==0) s=" V";
		else s= " "+getChiffre();
		return s;
	}
	
	
	public Case (int chiffre){
		if (chiffre<-1) System.out.println("Le chiffre d'une case ne peut pas être inférieur à -1");
		else this.chiffre=chiffre;
	}
	
}

	
/*
private boolean valide;
	private boolean vide;
public boolean getValide(){
	return valide;
}

public void setValide(boolean valide){
	this.valide=valide;
}
public boolean getVide(){
	return vide;
}

public void setVide(boolean vide){
	this.vide=vide;
}
*/
//public String toString(){
//	if (getValide()==true)
//	{
//		if (getVide()==false)
//		return /*"Chiffre case" + */" "+getChiffre();
//		else return /*"Case Valide */" V";
//	}
//	else
//	return " N"; /*case noire = invalide*/
//}
/*public Case (boolean valide){
			setValide(valide);
			if (valide == true){
				setVide(true);
			}
			else {
				setVide(false);
			}
	}
	
	public Case (int chiffre, boolean valide){
		this(valide);
		if (getValide()==true){
			setChiffre(chiffre);
		}
	}
	*/


	
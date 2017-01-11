import java.util.Random;
public class Case {
	private boolean valide;
	private boolean vide;
	private int chiffre ;
	/*private Point position ;*/
	
	public int getChiffre() {
		return chiffre;
	}

	public void setChiffre(int chiffre) {
		setVide(false);
		this.chiffre = chiffre;
	}
	public boolean getValide(){
		return valide;
	}
	
	public void setValide(boolean vide){
		this.vide=vide;
	}
	public boolean getVide(){
		return vide;
	}
	
	public void setVide(boolean vide){
		this.vide=vide;
	}
	
	public String toString(){
		if (getValide()==true)
		{
		if (getVide()==false)
		return "Chiffre case" + getChiffre();
		else return "Case Valide vide";
		}
		else
		return "Case noire";
	}
	public Case (boolean valide){
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
}

	
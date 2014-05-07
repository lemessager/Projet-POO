



public class Vetement extends Marchandise {
	
	private String taille ;
	
	public Vetement(double prix, String reference,String taille,String nom) {
		super(prix, reference,nom);
		this.taille=taille;
	}
	
	
	public Vetement(String reference,String taille) {
		super(reference);
		this.taille=taille;
	}
	

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}
	
	public boolean equals(Vetement v){// verifie que 2 vetements sont identiques
		if(this.taille.equals(v.getTaille()) && super.equals(v)) return true;
		return false ; 
	}

	@Override
	public String toString() {
		return super.toString()+"("+getTaille()+")";
	}
	
	
	
	

}

public class Marchandise {
	private double prix;
	private final String reference;
	private String nom;
	
	public Marchandise(double prix,String reference,String nom) {
		this.prix=prix;
		this.reference=reference;
		this.nom=nom;
	}
	
	public Marchandise(String reference) {
		this.reference=reference;
	}

	public String getReference() {
		return reference;
	}

	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public boolean equals(Marchandise m){// verifie que 2 marchandises sont identiques
		if(this.reference.equals(m.getReference())) return true;
		return false;
	}
	
	public boolean estUnVetement(){// verifie que la marchandise est bien un vetement
		if(this instanceof Vetement)
			return true;
		return false;
	}

	@Override
	public String toString() {// renvoie le nom de la marchandise et de sa reference
		return nom+": "+getReference();
	}
}

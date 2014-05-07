import java.util.ArrayList;





public class Test {
	public static void main(String[] args) throws Exception {
		/**
		 * // crée le magasin
		Magasin data=new Magasin();
		// ajoute les differentes marchandises
		Marchandise rds36=new Vetement(59.99,"1234506","36","robedesoiree");Marchandise rds38=new Vetement(59.99,"1234506","38","robedesoiree");Marchandise rds40=new Vetement(59.99,"1234506","40","robedesoiree");
		
		Marchandise riec38=new Vetement(35.45,"1238845","38","robeimprimeencoton");Marchandise riec40=new Vetement(35.45,"1238845","40","robeimprimeencoton");Marchandise riec42=new Vetement(35.45,"1238845","42","robeimprimeencoton");Marchandise riec44=new Vetement(35.45,"1238845","44","robeimprimeencoton");
		
		Marchandise pj40=new Vetement(52.69,"1234507","40","pantalonjean");Marchandise pj42=new Vetement(52.69,"1234507","42","pantalonjean");Marchandise pj44=new Vetement(52.69,"1234507","44","pantalonjean");
		
		Marchandise pa38=new Vetement(32.45,"1204566","38","pullacrylique");Marchandise pa40=new Vetement(32.45,"1204566","40","pullacrylique");Marchandise pa42=new Vetement(32.45,"1204566","42","pullacrylique");Marchandise pa44=new Vetement(32.45,"1204566","44","pullacrylique");
		
		Marchandise batE=new ElectroMenager(26.10,"2554507","batteurelectrique");
		Marchandise rasE=new ElectroMenager(350.40,"2553563","rasoirelectrique");
		Marchandise br=new ElectroMenager(125.40,"2552333","brand");
		Marchandise we=new ElectroMenager(140.40,"2552332","weber");
		Marchandise fmo=new ElectroMenager(120.90,"2004507","fourmicroonde");
		
		Marchandise bcc=new Mobilier(420.50,"2002345","banquetteclicclac");
		Marchandise bbz=new Mobilier(399.99,"2002330","braquettebz");
		Marchandise eta=new Mobilier(36.20,"2002320","etagere");
		
		
		data.addItem(rds36,1);data.addItem(rds38,4);data.addItem(rds40,5);
		data.addItem(riec38,1);data.addItem(riec40,1);data.addItem(riec42,2);data.addItem(riec44,2);
		data.addItem(pj40,1);data.addItem(pj42,2);data.addItem(pj44,2);
		data.addItem(pa38,1);data.addItem(pa40,1);data.addItem(pa42,2);data.addItem(pa44,2);
		data.addItem(batE,100);
		data.addItem(rasE,10);
		data.addItem(br,3);
		data.addItem(we,2);
		data.addItem(fmo,10);
		data.addItem(bcc,3);
		data.addItem(bbz,3);
		data.addItem(eta,100);

		
		
		//System.out.println(rds36);

		// permet d'effectuer les achats; il suffit de suivre ce qu'on demande
		data.faitTout();
		//System.out.println(data.vente);

		
		//System.out.println(data.getCA());
		**/
		
		ArrayList<String> s=new ArrayList<String>();
		s.add("0");
		s.add("1");
		String[] tabQuantite = (String[]) s.toArray();
        for(int i=0;i<1;i++)
       	 System.out.println(tabQuantite[i]=""+i);
       
	}
	

}

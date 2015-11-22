
public class Jeu {

	private int id;
	private String nom;
	private String editeur;
	private int annee;
	private int age;
	private String nbJoueurs;
	private String extensions;
	private int reference;
	private int statut;
	
	public Jeu(int _id, String _nom, String _editeur, int _annee, int _age, String _nbJoueurs,String _extensions, int _reference, int _statut){
		super();
		id = _id;
		nom = _nom;
		editeur = _editeur;
		annee = _annee;
		age = _age;
		nbJoueurs = _nbJoueurs;
		extensions = _extensions;
		reference = _reference;
		statut = _statut;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getEditeur() {
		return editeur;
	}

	public int getAnnee() {
		return annee;
	}

	public int getAge() {
		return age;
	}

	public String getNbJoueurs() {
		return nbJoueurs;
	}

	public String getExtensions() {
		return extensions;
	}

	public int getReference() {
		return reference;
	}

	public int getStatut() {
		return statut;
	}
}

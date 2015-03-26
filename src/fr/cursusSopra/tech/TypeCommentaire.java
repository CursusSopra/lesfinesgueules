/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.tech;

/**
 * @author Julien Caillon
 */
public enum TypeCommentaire {
	// Les objets énumérés sont forcément au début de la déclaration, ils doivent faire appel à un des constructeurs définis
	PRODUIT("commentaires_produits", "id_produit"),
	PRODUCTEURS("commentaires_producteurs", "id_producteur");

	private final String nomTable;
	private final String nomId;

	private TypeCommentaire(final String nomTable, final String nomId) {
		this.nomTable = nomTable;
		this.nomId = nomId;
	}

	public String getNomTable() {
		return nomTable;
	}

	public String getNomId() {
		return nomId;
	}

//	public boolean equalsName(String otherName) {
//		return (otherName == null) ? false : value.equals(otherName);
//	}

//	@Override
//	public String toString() {
//		return nomTable;
//	}
}

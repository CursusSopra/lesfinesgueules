/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.tech;

/**
 * @author Julien Caillon
 */
public enum EtatCommande {
	// Les objets énumérés sont forcément au début de la déclaration, ils doivent faire appel à un des constructeurs définis
	PANIER(-1), VALIDEE(0), ARCHIVEE(1);

	private final int etat;

	private EtatCommande(int etat) {
		this.etat = etat;
	}

	public int toInt() {
		return etat;
	}

	public static EtatCommande intToEtatCommande(int i) {
		EtatCommande state;
		switch (i) {
			case -1:
				state = PANIER;
				break;
			case 0:
				state = VALIDEE;
				break;
			default:
				state = ARCHIVEE;
				break;
		}
		return state;
	}
}

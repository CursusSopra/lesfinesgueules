/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.tech;

public enum EtatCommentaire {
	// Les objets énumérés sont forcément au début de la déclaration, ils doivent faire appel à un des constructeurs définis
	REJETE(-1), ENATTENTE(0), VALIDE(1);

	private final int etat;

	/**
	 * Le constructeur ne peut pas être protected ou public
	 * @param text
	 */
	private EtatCommentaire(int etat) {
		this.etat = etat;
	}

	public int toInt() {
		return etat;
	}

	public static EtatCommentaire intToEtatCommentaire(int i) {
		EtatCommentaire state;
		switch (i) {
			case -1:
				state = REJETE;
				break;
			case 0:
				state = ENATTENTE;
				break;
			default:
				state = VALIDE;
				break;
		}
		return state;
	}
}

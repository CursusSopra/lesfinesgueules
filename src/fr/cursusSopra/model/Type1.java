/**
 *  Modified By Julien J
 */

package fr.cursusSopra.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.Type1Dal;

/**
 * 
 * @author Julien J
 *
 */
public class Type1 {

	private long idType1;
	private List<Type2> listeType2;
	private String libelle1;
	private static List<Type1> listeType1;

	/**
	 * Constructeur créant et enregistrant en base de donnée un objet type 1
	 *
	 * @param libelle1
	 *            le nom du type 1 à créer
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Type1(String libelle1) throws SQLException {
		this.libelle1 = libelle1;
		this.save();

	}

	/**
	 * Constructeur récuperant de la Base de Donnée un objet type1 déja existant
	 *
	 * @param idType1
	 *            l'identifiant d'un objet type1 déjà enregistré en base de
	 *            donnée
	 */
	public Type1(long idType1) {
		this.idType1 = idType1;
		Type1Dal t1Dal;
		try {
			t1Dal = new Type1Dal(idType1);

			this.libelle1 = t1Dal.getLibelle1();

			List<Type2> lt2 = t1Dal.getListeType2();
			listeType2 = new ArrayList<Type2>();

			for (int i = 0; i < lt2.size(); i++) {

				String libelle2 = lt2.get(i).getLibelle2();
				long idType2 = lt2.get(i).getIdType2();
				Type2 t2 = new Type2(idType2, libelle2);
				listeType2.add(t2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Constructeur utilisé pour la création de la liste de tous les objets de
	 * types 1 enregistrés en base de donnée
	 *
	 * @param idType1
	 *            l'identifiant d'un objet type1 déjà enregistré en base de
	 *            donnée
	 * @param libelle1
	 *            le nom du type 1 à créer
	 */
	public Type1(long idType1, String libelle1) {
		this.libelle1 = libelle1;
		this.idType1 = idType1;
	}

	// Envoie de l'enregistrement de l'objet type 1 vers la couche d'accès à la
	// base de donnée
	/**
	 * Méthode sauvegardant un objet Type1 à partir de son libellé pour préparer
	 * son enregistrement.
	 *
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void save() throws SQLException {
		Type1Dal t1Dal = new Type1Dal(libelle1);
		idType1 = t1Dal.save();

		List<Type2> lt2 = t1Dal.getListeType2();
		listeType2 = new ArrayList<Type2>();
		for (int i = 0; i < lt2.size(); i++) {

			String libelle2 = lt2.get(i).getLibelle2();
			long idType2 = lt2.get(i).getIdType2();
			Type2 t2 = new Type2(idType2, libelle2);
			listeType2.add(t2);
		}

	}
	
	public void modify() throws SQLException {
		Type1Dal t1Dal = new Type1Dal(idType1);
		
		t1Dal.setLibelle1(libelle1);
		t1Dal.modify();
	}

	/**
	 * Methode getteur retournant la liste de tous les types 1 stockés en base
	 * de donnée
	 *
	 * @return la liste de tous les type1
	 */
	public static List<Type1> getListeType1() {
		listeType1 = new ArrayList<Type1>();
		List<Type1> lt1 = Type1Dal.getListeType1Dal();

		for (int i = 0; i < lt1.size(); i++) {
			String libelle1 = lt1.get(i).getLibelle1();
			long idType1 = lt1.get(i).getIdType1();
			Type1 t1 = new Type1(idType1, libelle1);
			listeType1.add(t1);
		}

		return listeType1;

	}

	// liste des getters de la classe
	public long getIdType1() {
		return idType1;
	}

	public String getLibelle1() {
		return libelle1;
	}

	public List<Type2> getListeType2() {
		return listeType2;
	}

	public void setIdType1(long idType1) {
		this.idType1 = idType1;
	}

	public void setListeType2(List<Type2> listeType2) {
		this.listeType2 = listeType2;
	}

	public void setLibelle1(String libelle1) {
		this.libelle1 = libelle1;
	}

	
}

package fr.cursusSopra.action.contenu;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.dataLayer.contenu.ProducteurDal;
import fr.cursusSopra.dataLayer.contenu.Type1Dal;


public class RechercheAction extends ActionSupport {
	
	static private HashMap<Integer, String> listeTypes1;
	static private HashMap<Integer, String> listeTypes2;
	static private HashMap<Integer, String> listeProducteurs;
	
	private int idType1;
	private int idType2;
	private int idProducteur;
	
	
	public String execute() {
		Type1Dal type1Dal;
//		listeTypes1 = getListeType1(); ;
		
		ProducteurDal rchdal = new ProducteurDal();
		rchdal.getListeProducteur();
				
		
		
		listeProducteurs = rchdal.getListeProducteurs();
		
		return SUCCESS;
	}
	
	

	public HashMap<Integer, String> getListeTypes1() {
		return listeTypes1;
	}


	public HashMap<Integer, String> getListeTypes2() {
		return listeTypes2;
	}

	public int getIdType1() {
		return idType1;
	}

	public void setIdType1(int idType1) {
		this.idType1 = idType1;
	}

	public int getIdType2() {
		return idType2;
	}

	public void setIdType2(int idType2) {
		this.idType2 = idType2;
	}

	public HashMap<Integer, String> getListeProducteurs() {
		return listeProducteurs;
	}

	public void setListeProducteurs(HashMap<Integer, String> listeProducteurs) {
		RechercheAction.listeProducteurs = listeProducteurs;
	}
	
}
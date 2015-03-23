package fr.cursusSopra.action.contenu;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.dataLayer.contenu.RechercheDal;


public class RechercheAction extends ActionSupport {
	
	static private HashMap<Integer, String> listeTypes1;
	static private HashMap<Integer, String> listeTypes2;
	static private HashMap<Integer, String> listeProducteurs;
	
	private int idType1;
	private int idType2;
	private int idProducteur;
	
	public String execute() {
		RechercheDal rchdal = new RechercheDal();
		listeTypes1 = rchdal.getListeTypes();
		return SUCCESS;
	}
	
//	public void getType1(){
//		
//		listeTypes1 = new HashMap<Integer, String>();
//		RechercheDal rchdal = new RechercheDal();
//		rchdal.getType1();
//		this.idType1=rchdal.getIdType1();
//		this.libelle1=rchdal.getLibelle1();
//		
//		listeTypes1.put((int) rchdal.getIdType1(), rchdal.getLibelle1());
//		
//		
//	}

	

	public HashMap<Integer, String> getListeTypes1() {
		return listeTypes1;
	}


	public int getIdType1() {
		return idType1;
	}

	public void setIdType1(int idType1) {
		this.idType1 = idType1;
	}
	
}
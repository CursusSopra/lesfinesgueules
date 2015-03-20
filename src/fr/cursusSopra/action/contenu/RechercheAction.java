package fr.cursusSopra.action.contenu;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.dataLayer.contenu.RechercheDal;

public class RechercheAction extends ActionSupport {
	
	static private HashMap<Integer, String> listeTypes1;
	static private HashMap<Integer, String> listeTypes2;
	static private HashMap<Integer, String> listeProducteurs;
	
	
	private long idType1;
	private String libelle1;
	
	
	
	public String execute() {
		listeTypes1 = new HashMap<Integer, String>();
		RechercheDal rchdal = new RechercheDal();
		rchdal.getType1();
		this.idType1=rchdal.getIdType1();
		this.libelle1=rchdal.getLibelle1();
		
		listeTypes1.put((int) rchdal.getIdType1(), rchdal.getLibelle1());
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

	public long getIdType1() {
		return idType1;
	}

	public String getLibelle1() {
		return libelle1;
	}
	
}
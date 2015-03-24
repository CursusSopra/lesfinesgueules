package fr.cursusSopra.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.admin.Type1Dal;
import fr.cursusSopra.dataLayer.contenu.ProduitDal;

public class Type1 {

	private long idType1;
	private List<Type2> listeType2;
	private String libelle1;
	
	public Type1 (String libelle1){
		this.setLibelle1(libelle1);
	}
	
	public Type1 (long idType1){
		this.idType1=idType1;
		Type1Dal t1dal = new Type1Dal(idType1);
		this.libelle1=t1dal.getLibelle1();
		this.listeType2=t1dal.getListeType2();
	}
	
	public Type1 (long idType1, String libelle1){
		this.setLibelle1(libelle1);
		this.setIdType1(idType1);
	}

	public long getIdType1() {return idType1;}
	public void setIdType1(long idType1) {this.idType1 = idType1;}
	public String getLibelle1() {return libelle1;}
	public void setLibelle1(String libelle1) {this.libelle1 = libelle1;}
	
	public List<Type2> getListeType2(){
		listeType2 = new ArrayList<Type2>();
		listeType2 = new Type1Dal(idType1).getListeType2();
		return listeType2;
	}
	
	
}

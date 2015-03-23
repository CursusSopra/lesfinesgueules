package fr.cursusSopra.model;

public class Type2 {

	private long idType2;
	
	private String libelle2;
	
	public Type2 (String libelle2){
		this.libelle2 = libelle2;
	}
	
	public Type2 (long idType2, String libelle2){
		this.libelle2 = libelle2;
		this.idType2=idType2;
	}
}

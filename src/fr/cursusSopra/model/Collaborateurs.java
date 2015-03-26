package fr.cursusSopra.model;

public class Collaborateurs {

	private String coverPictures;
	private String identityPictures;
	private String completeName;
	private String profession;
	private String location;
	private String email;
	private String motto;
	private String experience;
	private String skills;
	
	
	public String getCoverPictures() {
		return coverPictures;
	}


	public String getIdentityPictures() {
		return identityPictures;
	}


	public String getCompleteName() {
		return completeName;
	}


	public String getProfession() {
		return profession;
	}


	public String getLocation() {
		return location;
	}


	public String getEmail() {
		return email;
	}


	public String getMotto() {
		return motto;
	}


	public String getExperience() {
		return experience;
	}


	public String getSkills() {
		return skills;
	}


	public Collaborateurs(String coverPictures, String identityPictures,
			String completeName, String profession, String location,
			String email, String motto, String experience, String skills) {
		super();
		this.coverPictures = coverPictures;
		this.identityPictures = identityPictures;
		this.completeName = completeName;
		this.profession = profession;
		this.location = location;
		this.email = email;
		this.motto = motto;
		this.experience = experience;
		this.skills = skills;
	}
}

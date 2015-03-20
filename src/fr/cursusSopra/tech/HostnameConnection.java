package fr.cursusSopra.tech;

public class HostnameConnection {
	
	// Static car partagee par toutes les instances de this
	private static HostnameConnection instance = null;
	private String hostName = "";

	// constructeur private, accessible uniquement par la classe elle m�me
	private HostnameConnection() {
	}

	// getInstance(), c'est une methode PUBLIC, c'est par cette methode qu'on instancie l'objet unique "HostnameConnection"
	// C'est une methode STATIC car cette methode doit etre executable sans avoir besoin d'instancier this
	public static HostnameConnection getInstance() {
		if (instance == null) {
			instance = new HostnameConnection();
		}
		return instance;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}

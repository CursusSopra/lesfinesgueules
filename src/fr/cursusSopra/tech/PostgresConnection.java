package fr.cursusSopra.tech;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
	
	// Static car partagee par toutes les instances de this
	private static PostgresConnection instance = null;
	
	private Connection connection = null;
	
	private String server = "localhost";
	private String user = "postgres";
	private String passwd = "postgres";
	
	// constructeur private, accessible uniquement par la classe elle même
	private PostgresConnection() {
	}
	
	// getInstance(), c'est une methode PUBLIC, c'est par cette methode qu'on instancie l'objet unique "PostgresConnection"
	// C'est une methode STATIC car cette methode doit etre executable sans avoir besoin d'instancier this
	public static PostgresConnection getInstance() {
		if (instance == null) {
			instance = new PostgresConnection();
		}
		return instance;
	}
	
	// Set the bdd name to work on, and also defines the connection
	public void setBdd(String bdd) {
		
		if(!HostnameConnection.getInstance().getHostName().equals("localhost")) {
			server = "s2.neggruda.net";
			user = "cursussopra";
			passwd = "cursussopra";			
		}
		
        try {
            Class.forName("org.postgresql.Driver");
            String url = String.format("jdbc:postgresql://%s/%s", server, bdd);
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}

	public Connection getConnection() {
		return connection;
	}
	
}

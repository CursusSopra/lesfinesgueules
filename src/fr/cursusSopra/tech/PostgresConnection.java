package fr.cursusSopra.tech;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
	static String server = "localhost";
	static String user = "postgres";
	static String passwd = "postgres";
	
	static String bdd = "sortiralyon";
	
	public static void setBdd(String bdd) {
		PostgresConnection.bdd = bdd;
	}
	
	public static Connection getConnection() {		
		HostnameConnection hostnameConnection = HostnameConnection.getInstance();
		if(!hostnameConnection.getHostName().equals("localhost")) {
			server = "s2.neggruda.net";
			user = "cursussopra";
			passwd = "cursussopra";			
		}
		
        try {
            Class.forName("org.postgresql.Driver");
            String url = String.format("jdbc:postgresql://%s/%s", server, bdd);
            return DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

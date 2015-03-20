package fr.cursusSopra.tech;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
	public static Connection GetConnexion() {
		String server = "localhost";
		String user = "postgres";
		String passwd = "postgres";
		
		String bdd = "lesfinesgueules";
		
		HostnameConnection hostnameConnexion = HostnameConnection.getInstance();
		if(!hostnameConnexion.getHostName().equals("localhost")) {
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

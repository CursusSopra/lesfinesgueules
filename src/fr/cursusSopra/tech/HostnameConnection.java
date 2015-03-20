package fr.cursusSopra.tech;

public class HostnameConnection {
	private static HostnameConnection instance = null;

	private String hostName = "";

	private HostnameConnection() {
	}

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

package entity;

public class Client {
	private int clientId;
    private String clientName;
    private String contactInfo;
    private String policy;

    // Default constructor
    public Client() {
    }

    // Parametrized constructor
    public Client(int clientId, String clientName, String contactInfo, String policy) {
        this.setClientId(clientId);
        this.setClientName(clientName);
        this.setContactInfo(contactInfo);
        this.setPolicy(policy);
    }

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

}

package entity;

public class Policy {
	private int policyId;
    private String policyName;
    private double coverageAmount;

    // Default constructor
    public Policy() {
    }

    // Parametrized constructor
    public Policy(int policyId, String policyName, double coverageAmount) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.coverageAmount = coverageAmount;
    }

    // Getters and Setters
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    @Override
    public String toString() {
        return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", coverageAmount=" + coverageAmount + "]";
    }
}


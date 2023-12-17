package dao;

import entity.Policy;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class InsuranceServiceImpl implements IPolicyService {
    private static Collection<Policy> policyCollection = new ArrayList<>();

    @Override
    public boolean createPolicy(Policy policy) {
        if (policyExists(policy.getPolicyId())) {
            return false;
        }

        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO Policy (policyId, policyName, coverageAmount) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, policy.getPolicyId());
                preparedStatement.setString(2, policy.getPolicyName());
                preparedStatement.setDouble(3, policy.getCoverageAmount());

                // Execute the insert statement
                int rowsAffected = preparedStatement.executeUpdate();

                // If rowsAffected > 0, the insertion was successful
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean policyExists(int policyId) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM Policy WHERE policyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, policyId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0; // If count is greater than 0, the policy exists
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Policy getPolicy(int policyId) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM Policy WHERE policyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, policyId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Policy policy = new Policy();
                        policy.setPolicyId(resultSet.getInt("policyId"));
                        policy.setPolicyName(resultSet.getString("policyName"));
                        policy.setCoverageAmount(resultSet.getDouble("coverageAmount"));
                        return policy;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null; // Policy not found
    }

    @Override
    public Collection<Policy> getAllPolicies() {
        Collection<Policy> policies = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM Policy";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Policy policy = new Policy();
                        policy.setPolicyId(resultSet.getInt("policyId"));
                        policy.setPolicyName(resultSet.getString("policyName"));
                        policy.setCoverageAmount(resultSet.getDouble("coverageAmount"));
                        // Set other attributes as needed
                        policies.add(policy);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policies;
    }

    @Override
    public boolean updatePolicy(Policy updatedPolicy) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE Policy SET policyName = ?, coverageAmount = ? WHERE policyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, updatedPolicy.getPolicyName());
                preparedStatement.setDouble(2, updatedPolicy.getCoverageAmount());
                preparedStatement.setInt(3, updatedPolicy.getPolicyId());

                int rowsAffected = preparedStatement.executeUpdate();

                // If rowsAffected > 0, the update was successful
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "DELETE FROM Policy WHERE policyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, policyId);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public static Collection<Policy> getPolicyCollection() {
		return policyCollection;
	}

	public static void setPolicyCollection(Collection<Policy> policyCollection) {
		InsuranceServiceImpl.policyCollection = policyCollection;
	}
}

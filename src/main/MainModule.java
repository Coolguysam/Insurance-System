package main;

import dao.InsuranceServiceImpl;
import entity.Policy;
import Exception.PolicyNotFoundException;

import java.util.Collection;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) throws PolicyNotFoundException {
        InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();
		Scanner scanner = new Scanner(System.in);

		int choice;

		do {
		    System.out.println("------ Insurance System Menu ------");
		    System.out.println("1. Create a new policy");
		    System.out.println("2. Retrieve a policy by ID");
		    System.out.println("3. Retrieve all policies");
		    System.out.println("4. Update a policy");
		    System.out.println("5. Delete a policy");
		    System.out.println("0. Exit");
		    System.out.print("Enter your choice: ");

		    choice = scanner.nextInt();
		    scanner.nextLine();

		    switch (choice) {
		        case 1:
		            createPolicy(insuranceService, scanner);
		            break;
		        case 2:
		            retrievePolicy(insuranceService, scanner);
		            break;
		        case 3:
		            retrieveAllPolicies(insuranceService);
		            break;
		        case 4:
		            updatePolicy(insuranceService, scanner);
		            break;
		        case 5:
		            deletePolicy(insuranceService, scanner);
		            break;
		        case 0:
		            System.out.println("Exiting the program. Thank you!");
		            break;
		        default:
		            System.out.println("Invalid choice. Please enter a valid option.");
		    }
		} while (choice != 0);

		scanner.close();
    }

    private static void createPolicy(InsuranceServiceImpl insuranceService, Scanner scanner) {
        Policy newPolicy = new Policy();
        System.out.print("Enter Policy ID: ");
        newPolicy.setPolicyId(scanner.nextInt());
        System.out.print("Enter Policy Name: ");
        scanner.nextLine(); 
        newPolicy.setPolicyName(scanner.nextLine());
        System.out.print("Enter Coverage Amount: ");
        newPolicy.setCoverageAmount(scanner.nextDouble());
        insuranceService.createPolicy(newPolicy);
        System.out.println("Policy created successfully.");
    }

    private static void retrievePolicy(InsuranceServiceImpl insuranceService, Scanner scanner) {
        System.out.print("Enter Policy ID to Retrieve: ");
        int policyIdToRetrieve = scanner.nextInt();
        Policy retrievedPolicy = insuranceService.getPolicy(policyIdToRetrieve);
        if (retrievedPolicy != null) {
            System.out.println("Retrieved Policy: ");
            System.out.println("Policy ID: " + retrievedPolicy.getPolicyId());
            System.out.println("Policy Name: " + retrievedPolicy.getPolicyName());
            System.out.println("Coverage Amount: " + retrievedPolicy.getCoverageAmount());
            System.out.println();
        } else {
            System.out.println("Policy not found with ID: " + policyIdToRetrieve);
        }
    }

    private static void retrieveAllPolicies(InsuranceServiceImpl insuranceService) {
        Collection<Policy> allPolicies = insuranceService.getAllPolicies();
        
        System.out.println("All Policies:");
        for (Policy policy : allPolicies) {
            System.out.println("Policy ID: " + policy.getPolicyId());
            System.out.println("Policy Name: " + policy.getPolicyName());
            System.out.println("Coverage Amount: " + policy.getCoverageAmount());
            System.out.println();
        }
    }


    private static void updatePolicy(InsuranceServiceImpl insuranceService, Scanner scanner) {
        try {
            System.out.print("Enter Policy ID to Update: ");
            int policyIdToUpdate = scanner.nextInt();

            // Checking if the policy exists
            Policy existingPolicy = insuranceService.getPolicy(policyIdToUpdate);

            if (existingPolicy != null) {
                Policy policyToUpdate = new Policy();
                policyToUpdate.setPolicyId(policyIdToUpdate);

                System.out.print("Enter Updated Policy Name: ");
                scanner.nextLine();
                policyToUpdate.setPolicyName(scanner.nextLine());

                System.out.print("Enter Updated Coverage Amount: ");
                policyToUpdate.setCoverageAmount(scanner.nextDouble());

                boolean updateResult = insuranceService.updatePolicy(policyToUpdate);

                if (updateResult) {
                    System.out.println("Policy updated successfully.");
                } else {
                    System.out.println("Failed to update policy. Please try again.");
                }
            } else {
                System.out.println("Policy not found with ID: " + policyIdToUpdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating policy. Please check your input and try again.");
        }
    }


    private static void deletePolicy(InsuranceServiceImpl insuranceService, Scanner scanner) {
        System.out.print("Enter Policy ID to Delete: ");
        int policyIdToDelete = scanner.nextInt();
        boolean deletionResult = insuranceService.deletePolicy(policyIdToDelete);
        if (deletionResult) {
            System.out.println("Policy deleted successfully.");
        } else {
            System.out.println("Policy not found with ID: " + policyIdToDelete);
        }
    }
}

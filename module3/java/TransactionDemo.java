public class TransactionDemo {
    public static void main(String[] args) {
        System.out.println("Bank Transaction Demonstration");
        System.out.println("--------------------------\n");
        
        AccountDAO accountDAO = new AccountDAO();
        
        try {
            // Initialize database
            accountDAO.initializeDatabase();
            
            // Create test accounts
            System.out.println("Creating test accounts:");
            Account account1 = new Account("ACC001", "John Doe", 1000.00);
            Account account2 = new Account("ACC002", "Jane Smith", 500.00);
            
            account1 = accountDAO.createAccount(account1);
            account2 = accountDAO.createAccount(account2);
            
            System.out.println("Created: " + account1);
            System.out.println("Created: " + account2);
            
            // Display initial balances
            System.out.println("\nInitial account balances:");
            displayAccounts(accountDAO);
            
            // Perform successful transaction
            System.out.println("\nTransferring $300 from John to Jane:");
            try {
                accountDAO.transferMoney("ACC001", "ACC002", 300.00);
                System.out.println("Transfer successful!");
                System.out.println("\nBalances after successful transfer:");
                displayAccounts(accountDAO);
            } catch (Exception e) {
                System.out.println("Transfer failed: " + e.getMessage());
            }
            
            // Attempt transfer with insufficient funds
            System.out.println("\nAttempting to transfer $1000 from Jane to John:");
            try {
                accountDAO.transferMoney("ACC002", "ACC001", 1000.00);
                System.out.println("Transfer successful!");
            } catch (Exception e) {
                System.out.println("Transfer failed: " + e.getMessage());
                System.out.println("\nBalances after failed transfer (should be unchanged):");
                displayAccounts(accountDAO);
            }
            
            // Attempt transfer with invalid account
            System.out.println("\nAttempting transfer with invalid account:");
            try {
                accountDAO.transferMoney("ACC003", "ACC001", 100.00);
                System.out.println("Transfer successful!");
            } catch (Exception e) {
                System.out.println("Transfer failed: " + e.getMessage());
                System.out.println("\nFinal account balances (should be unchanged):");
                displayAccounts(accountDAO);
            }
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void displayAccounts(AccountDAO accountDAO) throws Exception {
        for (Account account : accountDAO.getAllAccounts()) {
            System.out.printf("%s: $%.2f%n", 
                account.getAccountHolder(), 
                account.getBalance());
        }
    }
} 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private static final String DB_URL = "jdbc:sqlite:module3/java/banking.db";
    
    // Initialize the database
    public void initializeDatabase() throws SQLException {
        String createTable = """
            CREATE TABLE IF NOT EXISTS accounts (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                account_number TEXT NOT NULL UNIQUE,
                account_holder TEXT NOT NULL,
                balance REAL NOT NULL CHECK (balance >= 0)
            )
            """;
            
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTable);
        }
    }
    
    // Create a new account
    public Account createAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (account_number, account_holder, balance) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, account.getAccountNumber());
            pstmt.setString(2, account.getAccountHolder());
            pstmt.setDouble(3, account.getBalance());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                    return account;
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }
        }
    }
    
    // Get account by account number
    public Account getAccountByNumber(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, accountNumber);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                        rs.getInt("id"),
                        rs.getString("account_number"),
                        rs.getString("account_holder"),
                        rs.getDouble("balance")
                    );
                }
                return null;
            }
        }
    }
    
    // Get all accounts
    public List<Account> getAllAccounts() throws SQLException {
        String sql = "SELECT * FROM accounts ORDER BY account_number";
        List<Account> accounts = new ArrayList<>();
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                accounts.add(new Account(
                    rs.getInt("id"),
                    rs.getString("account_number"),
                    rs.getString("account_holder"),
                    rs.getDouble("balance")
                ));
            }
        }
        
        return accounts;
    }
    
    // Transfer money between accounts using transaction
    public boolean transferMoney(String fromAccountNumber, String toAccountNumber, double amount) 
            throws SQLException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            // Get the accounts
            Account fromAccount = getAccountByNumber(fromAccountNumber);
            Account toAccount = getAccountByNumber(toAccountNumber);
            
            if (fromAccount == null || toAccount == null) {
                throw new SQLException("One or both accounts not found");
            }
            
            if (fromAccount.getBalance() < amount) {
                throw new SQLException("Insufficient funds");
            }
            
            // Perform the transfer
            String debitSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            
            try (PreparedStatement debitStmt = conn.prepareStatement(debitSql);
                 PreparedStatement creditStmt = conn.prepareStatement(creditSql)) {
                
                // Debit from source account
                debitStmt.setDouble(1, amount);
                debitStmt.setString(2, fromAccountNumber);
                debitStmt.executeUpdate();
                
                // Credit to destination account
                creditStmt.setDouble(1, amount);
                creditStmt.setString(2, toAccountNumber);
                creditStmt.executeUpdate();
                
                // If we get here, commit the transaction
                conn.commit();
                return true;
            }
            
        } catch (Exception e) {
            // If anything goes wrong, rollback the transaction
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset auto-commit mode
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Helper method to get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
} 
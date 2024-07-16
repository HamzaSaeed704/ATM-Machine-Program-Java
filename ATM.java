import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    private account account;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ATM() {
        account = new account(123456, 1234, 1000);
        createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        JPanel balancePanel = createBalancePanel();
        JPanel withdrawPanel = createWithdrawPanel();
        JPanel depositPanel = createDepositPanel();
        JPanel pinChangePanel = createPinChangePanel();
        JPanel historyPanel = createHistoryPanel();

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(balancePanel, "Balance");
        mainPanel.add(withdrawPanel, "Withdraw");
        mainPanel.add(depositPanel, "Deposit");
        mainPanel.add(pinChangePanel, "PinChange");
        mainPanel.add(historyPanel, "History");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton balanceButton = new JButton("Balance Inquiry");
        JButton withdrawButton = new JButton("Cash Withdrawal");
        JButton depositButton = new JButton("Cash Deposit");
        JButton pinChangeButton = new JButton("PIN Change");
        JButton historyButton = new JButton("Transaction History");
        JButton exitButton = new JButton("Exit");

        balanceButton.addActionListener(e -> cardLayout.show(mainPanel, "Balance"));
        withdrawButton.addActionListener(e -> cardLayout.show(mainPanel, "Withdraw"));
        depositButton.addActionListener(e -> cardLayout.show(mainPanel, "Deposit"));
        pinChangeButton.addActionListener(e -> cardLayout.show(mainPanel, "PinChange"));
        historyButton.addActionListener(e -> cardLayout.show(mainPanel, "History"));
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(balanceButton);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(pinChangeButton);
        panel.add(historyButton);
        panel.add(exitButton);

        return panel;
    }

    private JPanel createBalancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Your current balance is: $" + account.getBalance(), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(label, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createWithdrawPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Enter amount to withdraw:", SwingConstants.CENTER);
        JTextField amountField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        submitButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                JOptionPane.showMessageDialog(panel, "Withdrawal successful. Please take your cash.");
            } else {
                JOptionPane.showMessageDialog(panel, "Insufficient balance or invalid amount.");
            }
            amountField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(label, BorderLayout.NORTH);
        panel.add(amountField, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createDepositPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Enter amount to deposit:", SwingConstants.CENTER);
        JTextField amountField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        submitButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            JOptionPane.showMessageDialog(panel, "Deposit successful.");
            amountField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(label, BorderLayout.NORTH);
        panel.add(amountField, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPinChangePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Enter your current PIN:", SwingConstants.CENTER);
        JPasswordField currentPinField = new JPasswordField();
        JLabel newPinLabel = new JLabel("Enter your new PIN:", SwingConstants.CENTER);
        JPasswordField newPinField = new JPasswordField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        submitButton.addActionListener(e -> {
            int currentPin = Integer.parseInt(new String(currentPinField.getPassword()));
            if (account.verifyPin(currentPin)) {
                int newPin = Integer.parseInt(new String(newPinField.getPassword()));
                account.setPin(newPin);
                JOptionPane.showMessageDialog(panel, "PIN change successful.");
            } else {
                JOptionPane.showMessageDialog(panel, "Incorrect current PIN.");
            }
            currentPinField.setText("");
            newPinField.setText("");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(label, BorderLayout.NORTH);
        panel.add(currentPinField, BorderLayout.CENTER);
        panel.add(newPinLabel, BorderLayout.WEST);
        panel.add(newPinField, BorderLayout.EAST);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (transaction transaction : account.getTransactionHistory()) {
            textArea.append(transaction + "\n");
        }
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        new ATM();
    }
}
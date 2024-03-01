import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static double balance = 1000; // Solde initial
    private static JPanel mainPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM Program");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        frame.add(mainPanel);

        placeLoginPanel(mainPanel);

        frame.setVisible(true);
    }

    private static void placeLoginPanel(JPanel panel) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel accountLabel = new JLabel("Numéro de compte : 12345");
        accountLabel.setBounds(10, 20, 200, 25);
        loginPanel.add(accountLabel);

        JTextField accountText = new JTextField(20);
        accountText.setBounds(220, 20, 165, 25);
        loginPanel.add(accountText);

        JLabel passwordLabel = new JLabel("Mot de passe : 6789");
        passwordLabel.setBounds(10, 50, 200, 25);
        loginPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(220, 50, 165, 25);
        loginPanel.add(passwordText);

        JButton loginButton = new JButton("Connexion");
        loginButton.setBounds(10, 80, 150, 25);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int accountNumber = Integer.parseInt(accountText.getText());
                int password = Integer.parseInt(new String(passwordText.getPassword()));

                if (authenticate(accountNumber, password)) {
                    placeMenuPanel(mainPanel);
                } else {
                    JOptionPane.showMessageDialog(panel, "Authentification échouée. Numéro de compte ou mot de passe incorrect.");
                }
            }
        });

        panel.add("login", loginPanel);
        cardLayout.show(panel, "login");
    }

    private static void placeMenuPanel(JPanel panel) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1));

        JButton depositButton = new JButton("Déposer de l'argent");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        menuPanel.add(depositButton);

        JButton withdrawButton = new JButton("Retirer de l'argent");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        menuPanel.add(withdrawButton);

        JButton balanceButton = new JButton("Voir la balance");
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBalance();
            }
        });
        menuPanel.add(balanceButton);

        JButton exitButton = new JButton("Sortir");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuPanel.add(exitButton);

        panel.add("menu", menuPanel);
        cardLayout.show(panel, "menu");
    }

    private static boolean authenticate(int accountNumber, int password) {
        // Logique d'authentification simulée
        return (accountNumber == 12345 && password == 6789);
    }

    private static void deposit() {
        String amountString = JOptionPane.showInputDialog("Montant à déposer : ");
        double amount = Double.parseDouble(amountString);
        balance += amount;
        JOptionPane.showMessageDialog(null, "Dépôt réussi. Nouveau solde : " + balance);
    }

    private static void withdraw() {
        String amountString = JOptionPane.showInputDialog("Montant à retirer : ");
        double amount = Double.parseDouble(amountString);

        if (amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Retrait réussi. Nouveau solde : " + balance);
        } else {
            JOptionPane.showMessageDialog(null, "Solde insuffisant. Retrait impossible.");
        }
    }

    private static void displayBalance() {
        JOptionPane.showMessageDialog(null, "Solde actuel : " + balance);
    }
}

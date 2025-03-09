import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PasswordManagerGuide extends JFrame {

    public PasswordManagerGuide() {
        // Frame setup
        setTitle("Password Manager Guide");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);  // Set background color to black

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.BLACK);

        // Title Label
        JLabel titleLabel = new JLabel("Password Manager Guide", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        contentPanel.add(titleLabel);

        // Password Strength Checker Section
        JLabel passwordLabel = new JLabel("Enter a password to check its strength:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        contentPanel.add(passwordLabel);

        // Password Input Box with Line around it
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout());
        passwordPanel.setBackground(Color.BLACK);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));  // Line around input field
        passwordPanel.add(passwordField);

        contentPanel.add(passwordPanel);

        // Show Password Toggle Button
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.setBackground(Color.BLACK);
        showPasswordCheckBox.setFocusPainted(false);
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);  // Show password
            } else {
                passwordField.setEchoChar('*');  // Hide password
            }
        });
        contentPanel.add(showPasswordCheckBox);

        // Password Strength Label and Progress Bar
        JLabel strengthLabel = new JLabel("Password Strength: ");
        strengthLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        strengthLabel.setForeground(Color.WHITE);
        contentPanel.add(strengthLabel);

        JProgressBar strengthBar = new JProgressBar(0, 100);
        strengthBar.setPreferredSize(new Dimension(400, 20));
        strengthBar.setForeground(new Color(0, 204, 0));  // Green color for strong passwords
        strengthBar.setBackground(Color.WHITE);
        contentPanel.add(strengthBar);

        // Check Password Strength Button
        JButton checkPasswordButton = new JButton("Check Password Strength");
        checkPasswordButton.setFont(new Font("Arial", Font.PLAIN, 14));
        checkPasswordButton.setBackground(new Color(0, 102, 204));
        checkPasswordButton.setForeground(Color.WHITE);
        checkPasswordButton.setFocusPainted(false);
        checkPasswordButton.addActionListener(e -> {
            String password = new String(passwordField.getPassword());
            String strength = checkPasswordStrength(password);
            strengthLabel.setText("Password Strength: " + strength);
            strengthBar.setValue(calculatePasswordStrength(password));
        });
        contentPanel.add(checkPasswordButton);

        // Quick Tips Section
        JPanel quickTipsPanel = new JPanel(new GridLayout(3, 1));
        quickTipsPanel.setBackground(Color.BLACK);
        quickTipsPanel.add(new JLabel("Quick Tips for Strong Passwords:", JLabel.LEFT));
        quickTipsPanel.add(new JLabel("1. Avoid common words or predictable patterns."));
        quickTipsPanel.add(new JLabel("2. Use a mix of letters, numbers, and special characters."));
        
        quickTipsPanel.setForeground(Color.WHITE);
        contentPanel.add(quickTipsPanel);

        // Button Panel (Modified)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);

        JButton learnMoreButton = new JButton("Learn More About Password Managers");
        learnMoreButton.setFont(new Font("Arial", Font.PLAIN, 14));
        learnMoreButton.setBackground(new Color(0, 102, 204));
        learnMoreButton.setForeground(Color.WHITE);
        learnMoreButton.setFocusPainted(false);
        learnMoreButton.addActionListener(e -> openWebpage("https://www.google.com/search?q=password+manager"));
        
        JButton youtubeButton = new JButton("Watch Password Manager Tutorial");
        youtubeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        youtubeButton.setBackground(new Color(0, 102, 204));
        youtubeButton.setForeground(Color.WHITE);
        youtubeButton.setFocusPainted(false);
        youtubeButton.addActionListener(e -> openWebpage("https://www.youtube.com/results?search_query=password+manager+tutorial"));
        
        JButton passwordExamplesButton = new JButton("Password Examples & Tips");
        passwordExamplesButton.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordExamplesButton.setBackground(new Color(0, 102, 204));
        passwordExamplesButton.setForeground(Color.WHITE);
        passwordExamplesButton.setFocusPainted(false);
        passwordExamplesButton.addActionListener(e -> openWebpage("https://www.google.com/search?q=strong+password+examples+tips"));
        
        JButton lastPassButton = new JButton("Go to LastPass Setup");
        lastPassButton.setFont(new Font("Arial", Font.PLAIN, 14));
        lastPassButton.setBackground(new Color(0, 102, 204));
        lastPassButton.setForeground(Color.WHITE);
        lastPassButton.setFocusPainted(false);
        lastPassButton.addActionListener(e -> openWebpage("https://www.lastpass.com"));
        
        buttonPanel.add(learnMoreButton);
        buttonPanel.add(youtubeButton);
        buttonPanel.add(passwordExamplesButton);
        buttonPanel.add(lastPassButton);

        // Adding panels to the frame
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showMessageDialog(this, "Error opening webpage: " + ex.getMessage());
        }
    }

    private String checkPasswordStrength(String password) {
        if (password.length() < 8) return "Weak";
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) return "Moderate";
        return "Strong";
    }

    private int calculatePasswordStrength(String password) {
        return Math.min(password.length() * 10, 100); // Strength based on length, capped at 100
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordManagerGuide());
    }
}

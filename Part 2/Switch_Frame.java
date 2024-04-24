import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.UUID;

class Frame1 extends JPanel {

    private ImageIcon scaledIcon;

    public Frame1() {
        setLayout(new BorderLayout());

        // Load the image
        ImageIcon icon = new ImageIcon("res/img_3.png"); // Replace "res/img_2.png" with the actual path to your image
        Image img = icon.getImage();

        // Add listener for size changes
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the size of the panel
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Scale the image to fit the panel size
                Image scaledImg = img.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImg);

                // Repaint the panel
                repaint();
            }
        });

        // Create a JLabel for the image
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Create and add the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton next = new JButton("Next");
        next.addActionListener(e -> {
            String horseName = JOptionPane.showInputDialog(Frame1.this, "Enter name of the horse:");
            // Set the name of the first horse in Frame4
            Frame4.setHorse1Name(horseName);

            Switch_Frame.cardLayout.next(Switch_Frame.cardPanel);
            Switch_Frame.currentCard++;
        });




        JButton close = new JButton("Close");
        close.addActionListener(e -> System.exit(0));

        next.setPreferredSize(new Dimension(125, 38));
        close.setPreferredSize(new Dimension(125, 38));
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(close);
        buttonPanel.add(next);
        buttonPanel.setBorder(null);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the scaled image in the center of the panel
        if (scaledIcon != null) {
            int x = (getWidth() - scaledIcon.getIconWidth()) / 2;
            int y = (getHeight() - scaledIcon.getIconHeight()) / 2;
            scaledIcon.paintIcon(this, g, x, y);
        }

        // Draw the text on top of the image
        String labelText = "Horse Race";
        Font labelFont = new Font("Impact", Font.ITALIC, 50);
        FontMetrics metrics = g.getFontMetrics(labelFont);
        int textWidth = metrics.stringWidth(labelText);
        int x = (getWidth() - textWidth) / 2;
        int y = getHeight() / 2;
        g.setFont(labelFont);
        g.setColor(new Color(252, 195, 22)); // Set the color of the text
        g.drawString(labelText, x, y);
    }
}

class Frame2 extends JPanel implements ActionListener {
    JRadioButton speed;
    JRadioButton strength;
    JRadioButton heal;
    ImageIcon speedIcon;
    ImageIcon strengthIcon;
    ImageIcon healIcon;
    JLabel messageLabel;
    private final Image backgroundImage;

    public Frame2() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set BoxLayout with Y_AXIS alignment
        setBackground(new Color(255, 255, 255, 200));

        JLabel label = new JLabel("Choose Accessories to prepare your horse: ");
        label.setFont(new Font("Arial", Font.PLAIN, 35));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue()); // Add space above the label
        add(label);
        add(Box.createVerticalGlue()); // Add space below the label

        messageLabel = new JLabel();
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(messageLabel);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Flow layout for horizontal alignment

        speed = new JRadioButton("Speed");
        speed.addActionListener(e -> messageLabel.setText("Your horse will run a lot faster!"));

        strength = new JRadioButton("Strength");
        strength.addActionListener(e -> messageLabel.setText("Your horse will use all it's strength to run!"));

        heal = new JRadioButton("Heal");
        heal.addActionListener(e -> messageLabel.setText("Your horse will heal itself during the race!"));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(speed);
        buttonGroup.add(strength);
        buttonGroup.add(heal);

        radioPanel.add(speed);
        radioPanel.add(strength);
        radioPanel.add(heal);

        radioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioPanel.setOpaque(false);
        add(radioPanel);
        //Adding Icons to Radio Buttons speed, strength and heal
        int newWidth = 80;
        int newHeight = 80;

        speedIcon = new ImageIcon("res/loading.png");
        Image imageSpeed = speedIcon.getImage();
        Image scaledImageSpeed = imageSpeed.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledSpeed = new ImageIcon(scaledImageSpeed);
        speed.setOpaque(false);
        speed.setIcon(scaledSpeed);

        strengthIcon = new ImageIcon("res/arm-muscle.png");
        Image imageStrength = strengthIcon.getImage();
        Image scaledImageStrength = imageStrength.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledStrength = new ImageIcon(scaledImageStrength);
        strength.setOpaque(false);
        strength.setIcon(scaledStrength);

        healIcon = new ImageIcon("res/potion.png");
        Image imageHeal = healIcon.getImage();
        Image scaledImageHeal = imageHeal.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledHeal = new ImageIcon(scaledImageHeal);
        heal.setOpaque(false);
        heal.setIcon(scaledHeal);

        // Center the radio panel horizontally
        radioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(radioPanel);

        //BUTTONS NEXT, BACK AND CLOSE
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton next = new JButton("Next");
        buttonPanel.setOpaque(false);
        next.addActionListener(e -> {
            Switch_Frame.cardLayout.next(Switch_Frame.cardPanel);
            Switch_Frame.currentCard++;
        });

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            Switch_Frame.cardLayout.previous(Switch_Frame.cardPanel);
            Switch_Frame.currentCard--;
        });

        JButton close = new JButton("Close");
        close.addActionListener(e -> System.exit(0));

        buttonPanel.add(next);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space between buttons
        buttonPanel.add(back);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space between buttons
        buttonPanel.add(close);

        add(buttonPanel);
        add(Box.createVerticalGlue()); // Add space below the buttons

        // Load the background image
        backgroundImage = new ImageIcon("res/grass.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}

class Frame3 extends JPanel {
    JLabel label;
    JLabel oddsLabel;
    JComboBox<String> horseSelection;
    JTextField betAmountField;
    JButton placeBetButton;

    // Initialize variables to track odds and other data
    double[] odds; // Array to store odds for each horse
    String[] horses; // Array to store names of horses

    private static String selectedHorse;
    private final Image backgroundImageFrame3;

    public Frame3() {


        // Initialize components
        label = new JLabel("Virtual Betting System");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize odds and horse names (replace these with actual data)
        odds = new double[]{2.5, 3.0, 4.0}; // Example odds for three horses
        horses = new String[]{"Horse 1", "Horse 2", "Horse 3"}; // Example horse names


        // Initialize odds label
        oddsLabel = new JLabel();
        updateOddsLabel(); // Update odds label with initial odds

        // Initialize horse selection dropdown
        horseSelection = new JComboBox<>(horses);

        // Initialize text field for entering bet amount
        betAmountField = new JTextField();
        betAmountField.setPreferredSize(new Dimension(100, 30));

        // Initialize place bet button
        placeBetButton = new JButton("Place Bet");
        placeBetButton.setAlignmentX(CENTER_ALIGNMENT);
        placeBetButton.addActionListener(e -> {
            selectedHorse = (String) horseSelection.getSelectedItem();
            String betAmountText = betAmountField.getText();

            try {
                int betAmount = Integer.parseInt(betAmountText);
                if (betAmount >= 1 && betAmount <= 1000000) {
                    // Pass selected horse information to Frame5
                    Frame5.setSelectedHorse(selectedHorse);
                    Frame5.setBetAmount(betAmount);
                    // Proceed to the next frame
                    Switch_Frame.cardLayout.next(Switch_Frame.cardPanel);
                    Switch_Frame.currentCard++;
                    // Process the bet (you'll need to implement this)
                    processBet(selectedHorse, betAmount);
                } else {
                    JOptionPane.showMessageDialog(Frame3.this, "Invalid amount. Number must be between 1-1000000.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Frame3.this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(label, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing
        centerPanel.add(oddsLabel);
        centerPanel.add(new JLabel("Select Horse:"));
        centerPanel.add(horseSelection);
        centerPanel.add(new JLabel("Enter Bet Amount:"));
        centerPanel.add(betAmountField);
        centerPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing
        centerPanel.add(placeBetButton);
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);


        // Initialize and add next, back, and close buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton next = new JButton("Next");
        next.addActionListener(e -> {
            // Get the bet amount text
            String betAmountText = betAmountField.getText();
            try {
                // Parse the bet amount
                int betAmount = Integer.parseInt(betAmountText);
                // Check if the bet amount is within the valid range
                if (betAmount >= 1 && betAmount <= 1000000) {
                    // If valid, proceed to the next frame
                    Switch_Frame.cardLayout.next(Switch_Frame.cardPanel);
                    Switch_Frame.currentCard++;
                } else {
                    // If not valid, display an error message
                    JOptionPane.showMessageDialog(Frame3.this, "Invalid number. Number must be between 1 and 1000000.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // If parsing fails, display an error message
                JOptionPane.showMessageDialog(Frame3.this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            Switch_Frame.cardLayout.previous(Switch_Frame.cardPanel);
            Switch_Frame.currentCard--;
        });

        JButton close = new JButton("Close");
        close.addActionListener(e -> System.exit(0));

        buttonPanel.add(next);
        buttonPanel.add(back);
        buttonPanel.add(close);
        buttonPanel.setOpaque(false);

        add(buttonPanel, BorderLayout.SOUTH);

        // Load background image
        backgroundImageFrame3 = new ImageIcon("res/coin.jpg").getImage();
    }

    // Method to update odds label with current odds
    private void updateOddsLabel() {
        StringBuilder oddsText = new StringBuilder("Current Odds:\n");
        for (int i = 0; i < horses.length; i++) {
            oddsText.append(horses[i]).append(": ").append(odds[i]).append("\n");
        }
        oddsLabel.setText(oddsText.toString());
    }

    // Method to simulate bet processing (replace with actual bet processing logic)
    private void processBet(String selectedHorse, int betAmount) {
        // This is a placeholder method. Replace it with actual bet processing logic.
        System.out.println("Bet placed on " + selectedHorse + " for $" + betAmount);
    }

    public static String getSelectedHorse() {
        return selectedHorse;
    }

    public void setSelectedHorse(String selectedHorse) {
        Frame3.selectedHorse = selectedHorse;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImageFrame3, 0, 0, getWidth(), getHeight(), this);
    }
}
class Frame4 extends JPanel {
    static JLabel horse1Label = new JLabel("1-   Horsey");
    JLabel horse2Label = new JLabel("2-   Bojack");
    JLabel horse3Label = new JLabel("3-   Meow"); // I assume you want to rename this correctly?
    JProgressBar horse1 = new JProgressBar(0, 100);
    JProgressBar horse2 = new JProgressBar(0, 100);
    JProgressBar horse3 = new JProgressBar(0, 100);
    JButton buttonStart = new JButton("Run");
    JButton buttonReset = new JButton("Reset");
    JButton buttonQuit = new JButton("Quit Program");
    JLabel msg = new JLabel("", JLabel.CENTER);

    static boolean runRaceButtonIsPressed = false;
    static boolean resetRaceButtonIsPressed = false;
    static int winningHorse = 0;
    static boolean winner = false;
    static boolean raceCompleted = false;
    public static void setHorse1Name(String name) {
        horse1Label.setText("1-   " + name);
    }
    public Frame4() {
        setLayout(null);
        initialize();

        setSize(750, 500);

        JButton back = new JButton("Back");
        back.setBounds(110, 330, 90, 30);
        back.addActionListener(e -> {
            Switch_Frame.cardLayout.previous(Switch_Frame.cardPanel);
            Switch_Frame.currentCard--;
        });
        add(back);

        JButton next = new JButton("Next");
        next.setAlignmentX(Component.CENTER_ALIGNMENT);
        next.setBounds(300, 330, 90, 30);
        next.addActionListener(e -> {
            Switch_Frame.cardLayout.next(Switch_Frame.cardPanel);
            Switch_Frame.currentCard++;
        });
        add(next);

        JButton close = new JButton("Close");
        close.setBounds(490, 330, 90, 30);
        close.addActionListener(e -> {
            System.exit(0); // Close the entire application
        });
        add(close);
    }

    private void initialize() {
        horse1.setStringPainted(true);
        horse1.setForeground(Color.BLACK);
        horse1.setBounds(150, 129, 259, 14);
        add(horse1);

        horse2.setStringPainted(true);
        horse2.setForeground(Color.LIGHT_GRAY);
        horse2.setBounds(150, 169, 259, 14);
        add(horse2);

        horse3.setStringPainted(true);
        horse3.setForeground(Color.PINK);
        horse3.setBounds(150, 209, 259, 14);
        add(horse3);

        buttonStart.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonStart.addActionListener(new RunRace());
        buttonStart.setBounds(170, 287, 155, 40);
        add(buttonStart);

        buttonReset.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonReset.addActionListener(new ResetRace());
        buttonReset.setBounds(370, 287, 155, 40);
        add(buttonReset);

        buttonQuit.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonQuit.addActionListener(new QuitProgram());
        buttonQuit.setBounds(460, 287, 155, 40);
        //add(buttonQuit);

        horse1Label.setBounds(50, 118, 200, 30);
        horse2Label.setBounds(50, 160, 200, 30);
        horse3Label.setBounds(50, 200, 200, 30);

        add(horse1Label);
        add(horse2Label);
        add(horse3Label);
    }

    // Inside Frame4 class
    class RunRace implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (!runRaceButtonIsPressed) {
                msg.setVisible(false);
                resetRaceButtonIsPressed = false;
                runRaceButtonIsPressed = true;

                HORSE1 horse1Runner = new HORSE1();
                horse1Runner.start();

                HORSE2 horse2Runner = new HORSE2();
                horse2Runner.start();

                HORSE3 horse3Runner = new HORSE3();
                horse3Runner.start();
            }
        }

    }


    class ResetRace implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!resetRaceButtonIsPressed) {
                msg.setVisible(false);
                resetRaceButtonIsPressed = true;
                runRaceButtonIsPressed = false;
                winner = false;

                horse1.setValue(0);
                horse2.setValue(0);
                horse3.setValue(0);

                horse1.repaint();
                horse2.repaint();
                horse3.repaint();
            }
        }
    }

    static class QuitProgram implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class HORSE1 extends Thread {
        public void run() {
            for (int i = 0; i <= 100; i++) {
                if (winner) break;
                horse1.setValue(i);
                horse1.repaint();
                if (i == 100) {
                    winningHorse = 1;
                    finish();
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }

    }

    class HORSE2 extends Thread {
        public void run() {
            for (int i = 0; i <= 100; i++) {
                if (winner) break;
                horse2.setValue(i);
                horse2.repaint();
                if (i == 100) {
                    winningHorse = 2;
                    finish();
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    class HORSE3 extends Thread {
        public void run() {
            for (int i = 0; i <= 100; i++) {
                if (winner) break;
                horse3.setValue(i);
                horse3.repaint();
                if (i == 100) {
                    winningHorse = 3;
                    finish();
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    public synchronized void finish() {
        msg.setVisible(true);
        msg.setText("Horse " + winningHorse + " wins the race");
        winner = true;
        System.out.println("Horse " + winningHorse + " wins the race");
        add(msg);
        msg.setBounds(50, 50, 300, 30);
        raceCompleted = true;
        Frame5.evaluateBet();

    }
    public static int getWinningHorse() {
        return winningHorse;
    }

    // Method to update the winning horse
    public void setWinningHorse(int horse) {
        winningHorse = horse;
    }
}
class Frame5 extends JPanel {
    private static int betAmount;
    private static int winningHorse;
    private static int betm = 0;
    private JLabel betLabel;
    private JLabel betLabelLost;
    // JLabel to display bet message

    public static void setSelectedHorse(String horse) {
    }
    public static void setBetAmount(int amount) {
        betAmount = amount;
    }

    public static void setWinningHorse(int horse) {
        winningHorse = horse;
    }

    public static void evaluateBet() {
        // Check if the race has been completed
        int n;
        if (Frame4.raceCompleted) {
            String selectedHorse = Frame3.getSelectedHorse();
            int winningHorse = Frame4.getWinningHorse();

            if (selectedHorse.equals("Horse 1")) {
                n = 1;
            } else if (selectedHorse.equals("Horse 2")) {
                n = 2;
            } else {
                n = 3;
            }
            if (n == winningHorse) {
                System.out.println("Congratulations! You won the bet!");
                betm = 2;
            } else if(n !=winningHorse){
                System.out.println("Sorry, you lost the bet.");
                betm = 1;
            }
        }
    }
    public Frame5() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel betLabel = new JLabel("Congratulations! You won the bet!"); // Initialize the instance variable
        betLabel.setFont(new Font("Arial", Font.BOLD, 20));
        betLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel betLabelLost = new JLabel("Sorry, you lost the bet.");
        betLabelLost.setFont(new Font("Arial", Font.BOLD, 20));
        betLabelLost.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Determine which label to add based on the bet outcome
        if (betm == 2) {
            add(betLabel);
        } else if (betm == 1) {
            add(betLabelLost);
        }
        add(betLabelLost);

        add(Box.createVerticalGlue()); // Add space above the label
        add(Box.createVerticalGlue()); // Add space below the label

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            Switch_Frame.cardLayout.previous(Switch_Frame.cardPanel);
            Switch_Frame.currentCard--;
        });

        JButton close = new JButton("Close");
        close.addActionListener(e -> System.exit(0));

        JButton next = new JButton("Next");
        next.addActionListener(e -> {
            Switch_Frame.cardLayout.next(Switch_Frame.cardPanel);
            Switch_Frame.currentCard++;
        });

        buttonPanel.add(back);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between buttons
        buttonPanel.add(close);
        buttonPanel.add(next);
        add(buttonPanel);
        add(Box.createVerticalGlue()); // Add space below the button
    }

    public static int getWinningHorse() {
        return winningHorse;
    }
}

class Frame6 extends JPanel {
    public Frame6() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Frame 6");
        JLabel lastWinner = new JLabel(String.valueOf(Frame4.winningHorse));

        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue()); // Add space above the label
        add(lastWinner);
        add(label);
        add(Box.createVerticalGlue()); // Add space below the label

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            Switch_Frame.cardLayout.previous(Switch_Frame.cardPanel);
            Switch_Frame.currentCard--;
        });

        JButton close = new JButton("Close");
        close.addActionListener(e -> System.exit(0));

        buttonPanel.add(back);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between buttons
        buttonPanel.add(close);

        add(buttonPanel);
        add(Box.createVerticalGlue()); // Add space below the buttons
    }
}

public class Switch_Frame {
    static JPanel cardPanel;
    static CardLayout cardLayout;
    static int currentCard = 1;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(new Frame1(), "1");
        cardPanel.add(new Frame2(), "2");
        cardPanel.add(new Frame3(), "3");
        cardPanel.add(new Frame4(), "4");
        cardPanel.add(new Frame5(), "5");
        cardPanel.add(new Frame6(), "6");

        frame.add(cardPanel);
        frame.setSize(750, 500);
        frame.setVisible(true);
    }
}
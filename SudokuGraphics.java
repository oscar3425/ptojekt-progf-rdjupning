package sudoku;


import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuGraphics {
    public SudokuGraphics(Sudoku s){
        SwingUtilities.invokeLater(() -> createWindow(s, "Sudoku",100, 300));
    }
    private void createWindow(Sudoku solver, String title, int height,int width){
        //skapa frame
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());
        //layout
        JPanel titelpanel = new JPanel();
        JPanel knappPanel = new JPanel();
        JPanel gridpanel = new JPanel();
        gridpanel.setLayout(new java.awt.GridLayout(9, 9));
        pane.add(titelpanel, BorderLayout.NORTH);
        pane.add(gridpanel, BorderLayout.CENTER);
        pane.add(knappPanel, BorderLayout.SOUTH);

        JLabel titleLabel = new JLabel("Sudoku Solver");
        titleLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 40));
        titelpanel.add(titleLabel);

        java.awt.Font font = new java.awt.Font("SansSerif", java.awt.Font.BOLD, 40);

        //skapa rutnät
        JTextField[][] board = new JTextField[9][9];
        for (int i=0; i<9;i++){
            for (int j=0; j<9; j++){
                board[i][j] = new JTextField();
                board[i][j].setFont(font);
                board[i][j].setPreferredSize(new java.awt.Dimension(65, 65));
                board[i][j].setHorizontalAlignment(JTextField.CENTER);
                gridpanel.add(board[i][j]);
            }
        }
        //skapa knappar
        JButton solve =new JButton("solve");
        JButton clear = new JButton("clear");

        solve.addActionListener(e-> {
            for (int i=0; i<9;i++){
                for (int j=0; j<9; j++){
                    String s = board[i][j].getText();
                    if (s.isEmpty()) {
                        solver.clear(i,j);
                    } else if (s.length()==1 && Character.isDigit(s.charAt(0))) {
                        int tal = Integer.parseInt(s);
                        if (tal >= 1 && tal <= 9) {
                            solver.set(i, j, tal);
                        } else {
                            JOptionPane.showMessageDialog(gridpanel, "fel insättning - måste vara mellan 1 och 9");
                            throw new IllegalArgumentException("fel insättning");
                        }
                    }else {
                        JOptionPane.showMessageDialog(gridpanel, "fel insättning - måste vara en siffra mellan 1 och 9");

                        throw new IllegalArgumentException("fel insättning");
                    }
                }
            }

            if (solver.solve()){
                for (int i=0; i<9;i++){
                    for (int j=0; j<9; j++){
                        board[i][j].setText(String.valueOf(solver.get(i,j)));
                    }
                }
            }else{
                JOptionPane.showMessageDialog(gridpanel, "Ingen lösning hittades");
            }
        });

        clear.addActionListener(e-> {
                for (int i=0; i<9;i++){
                    for (int j=0; j<9; j++){
                        board[i][j].setText("");
                    }
                }
                solver.clearAll();
        });

        knappPanel.add(solve);
        knappPanel.add(clear);
        frame.pack();
        frame.setVisible(true);
    }
}

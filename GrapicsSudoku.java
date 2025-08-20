package sudoku;


import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuGraphics {
    public SudokuGraphics(Sudoku s){
        SwingUtilities.invokeLater(() -> createWindow(s, "Sudoku",100, 300));
    }
    private void createWindow(Sudoku solver, String title, int height,int width){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel knappPanel = new JPanel();
        JPanel gridpanel = new JPanel();
        gridpanel.setLayout(new java.awt.GridLayout(9, 9));
        pane.add(gridpanel, BorderLayout.CENTER);
        pane.add(knappPanel, BorderLayout.SOUTH);

        java.awt.Font font = new java.awt.Font("SansSerif", java.awt.Font.BOLD, 20);


        JTextField[][] board = new JTextField[9][9];
        for (int i=0; i<9;i++){
            for (int j=0; j<9; j++){
                board[i][j] = new JTextField();
                board[i][j].setFont(font);
                board[i][j].setPreferredSize(new java.awt.Dimension(80, 80));
                board[i][j].setHorizontalAlignment(JTextField.CENTER);
                gridpanel.add(board[i][j]);
            }
        }
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
                            JOptionPane.showMessageDialog(gridpanel, "fel insättning");
                            throw new IllegalArgumentException("fel insättning");
                        }
                    }else {
                        JOptionPane.showMessageDialog(gridpanel, "fel insättning");

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

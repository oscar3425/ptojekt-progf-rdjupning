package sudoku;


import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
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
        //pane.setLayout(new BorderLayout());
        //pane.setLayout(new java.awt.GridLayout(9, 9));
        JPanel[][] panels = new JPanel[9][9];
        for (int i=0; i<9;i++){
            for (int j=0; j<9; j++){
                panels[i][j] = new JPanel();
                pane.add(panels[i][j], BorderLayout.CENTER);
            }
        }

        JTextField[][] board = new JTextField[9][9];
        for (int i=0; i<9;i++){
            for (int j=0; j<9; j++){
                board[i][j]= new JTextField(2);
                panels[i][j].add(board[i][j]);
            }
        }
        JButton solve =new JButton("solve");
        JButton clear = new JButton("clear");
        solve.addActionListener(e-> {
            for (int i=0; i<9;i++){
                for (int j=0; j<9; j++){
                    String s = board[i][j].getText();
                    if (s.length()==1 && Character.isDigit(s.charAt(0))) {

                        int tal = Integer.parseInt(s);
                        solver.set(i,j,tal);
                    }else {
                        throw new IllegalArgumentException("fel insättning");
                    }
                }
            }
            
            solver.solve();
            for (int i=0; i<9;i++){
                for (int j=0; j<9; j++){
                    board[i][j].setText(String.valueOf(solver.get(i,j)));
                }
            }


        });
        clear.addActionListener(e-> {
                for (int i=0; i<9;i++){
                    for (int j=0; j<9; j++){
                        board[i][j].setText("");
                        solver.set(i,j,0);
                    }
                }
            });


        frame.pack();
        frame.setVisible(true);
    }
}

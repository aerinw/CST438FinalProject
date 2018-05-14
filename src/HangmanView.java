import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HangmanView extends JFrame  
{
   public static final int WIDTH = 600;
   public static final int HEIGHT = 800;
   
   //labels for game piles
   private JLabel hangman = new JLabel();
   private JLabel[] wordTiles = new JLabel[15];
   
   private JLabel[] letterTiles = new JLabel[26];
   
   private JFrame gameBoard = new JFrame();
   private JPanel mainPanel = new JPanel();//outer panel
  
   private JPanel top = new JPanel(); //buttons and score
   private JPanel hangmanArea = new JPanel();
   private JPanel playTilesArea = new JPanel();
   private JPanel wordArea = new JPanel();
  
  
   private JLabel playerScore = new JLabel("Score: 0");
   private JLabel usedLetters = new JLabel("Used Letters: ");
   private JLabel logoText = new JLabel("Hangman Game");
   
   private JButton playButton = new JButton("Play Again");
   
   private JLabel winText, wordReveal;
   
   /**
    * constructor
    * @param title - name for the window
    */
   HangmanView(String title)
   {
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
      gameBoard.getContentPane().add(mainPanel);
      mainPanel.setBackground(Color.WHITE);

      
      //create logo and score area ---------------------------------------
      top.setBackground(Color.WHITE);
      top.setPreferredSize(new Dimension(WIDTH, 200));      
      top.setLayout(new GridLayout(2,2));     
      
      Icon logo = new ImageIcon("images/csumbLogo.jpg");
      JLabel logoImage = new JLabel(logo);  
      playerScore.setFont(new Font("SansSerif", Font.PLAIN, 30)); 
      playerScore.setVerticalAlignment(JLabel.BOTTOM);
      usedLetters.setFont(new Font("SansSerif", Font.PLAIN, 30)); 
      usedLetters.setVerticalAlignment(JLabel.TOP);
      logoText.setFont(new Font("SansSerif", Font.PLAIN, 30)); 
      logoText.setHorizontalAlignment(JLabel.RIGHT);
      logoText.setVerticalAlignment(JLabel.TOP);
      logoImage.setHorizontalAlignment(JLabel.RIGHT);
      
      top.add(playerScore);
      top.add(logoImage);
      top.add(usedLetters);
      top.add(logoText);
      mainPanel.add(top);
      
     //create hangman area --------------------------------------------- 
      hangmanArea.setPreferredSize(new Dimension(WIDTH, 250));
      hangmanArea.setLayout(new BoxLayout(hangmanArea, BoxLayout.X_AXIS));
      hangmanArea.setBackground(Color.WHITE);
      hangmanArea.add(hangman);      
      mainPanel.add(hangmanArea);
      
      
      //create word area ---------------------------------------------------
      wordArea.setPreferredSize(new Dimension(WIDTH, 100));
      wordArea.setBackground(Color.WHITE);
      
      for(int i = 0; i < wordTiles.length; i++)
      {
         wordTiles[i] = new JLabel();
         wordArea.add(wordTiles[i]);
      }
      
      mainPanel.add(wordArea);
      
     //create letter tiles area --------------------------------------------- 
      playTilesArea.setPreferredSize(new Dimension(WIDTH, 250));
      playTilesArea.setBackground(Color.WHITE);
      playTilesArea.setLayout(new GridLayout(2, 13));
      
      //set up labels for letters
      for(int i = 0; i < letterTiles.length; i++)
      {
         letterTiles[i] = new JLabel();
         playTilesArea.add(letterTiles[i]);
      }
         
      
      mainPanel.add(playTilesArea);
      
      add(mainPanel);
           //gameBoard.setVisible(true);  
      
   }
   
   //accessors and mutators
   public void setHangmanImage(Icon image) { hangman.setIcon(image); }
   
   public void setLetterImageToPlayArea(Icon image, int num) {letterTiles[num].setIcon(image);}
   
   public void setWordIcon(Icon image, int num) { wordTiles[num].setIcon(image); }
   
   public JLabel getAlphabetLabel(int num) { return letterTiles[num]; }
   
   public void setScore(int score) { playerScore.setText("Score: " + score); }
   
   public void setUsedLetters(String uLetters) { usedLetters.setText(uLetters); }
   
   public String getUsedLetters() { return usedLetters.getText(); }
   
   public JButton getPlayButton() { return playButton; }
   
   /**
    * setWinScreen - removes the necessary labels and adds labels for the screen
    * displayed after the game is compelted.
    * 
    * @param word - the word from the game. Displayed if the game was lost. 
    * @param hasWon - true/false for if the game was won/lost. 
    */
   public void setWinScreen(String word, boolean hasWon)
   {
      for(int i = 0; i < letterTiles.length; i++)
         playTilesArea.remove(letterTiles[i]);
      
      if(hasWon)
         winText = new JLabel("You Win!");
      else
         winText = new JLabel("You Lost!");
      
      winText.setFont(new Font("SansSerif", Font.BOLD, 60));
      winText.setHorizontalAlignment(JLabel.CENTER);
      
      playTilesArea.setLayout(new GridLayout(3, 1));
      playTilesArea.add(winText);
      
      if(!hasWon)
      {
         wordReveal = new JLabel("The word was: " + word);
         wordReveal.setFont(new Font("SansSarif", Font.PLAIN, 30));
         wordReveal.setHorizontalAlignment(JLabel.CENTER);
         playTilesArea.add(wordReveal);
      }

      playButton.setHorizontalAlignment(JLabel.CENTER);
      playButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
      
      playTilesArea.add(playButton);
      playTilesArea.repaint();
      
   }
   
   /**
    * newGame() - resets the window for a new game. 
    * @param hasWon - true/false if the previous game was won/lost 
    */
   public void newGame(boolean hasWon)
   {
      //remove labels added at end of the game
      playTilesArea.remove(playButton);
      playTilesArea.remove(winText);
      
      if(!hasWon)
         playTilesArea.remove(wordReveal); //only added on lose
      
      //update the word tiles
      for(int i = 0; i < wordTiles.length; i++)
      {
         wordArea.remove(wordTiles[i]);
         wordTiles[i] = new JLabel();
         wordArea.add(wordTiles[i]);
      }
      
      //update score info
      usedLetters.setText("Used Letters: ");
      playerScore.setText("Score: 0");
      
      //update layout
      playTilesArea.setLayout(new GridLayout(2, 13));
      
      //set up labels for letters
      for(int i = 0; i < letterTiles.length; i++)
      {
         letterTiles[i] = new JLabel();
         playTilesArea.add(letterTiles[i]);
      }
   
      //display changes
      playTilesArea.repaint();
      wordArea.repaint();
   }
   
   
}//end of hangmanView class ----------------------------------------
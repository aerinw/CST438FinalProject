import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Assign7
{

   public static void main(String[] args)
   {
      HangmanController game = new HangmanController();

   }

}


class HangmanGame
{
   
   private String[] words = { "mozzarella", "quantizing", "smokejacks", "quincunxes", "zygomorphy",
         "mezzanines", "intermezzo", "complexify", "outdazzled", "skyjackers", "quizmaster",
         "epoxidized", "jackplanes", "poppycocks", "euphemized", "squirarchy", "quenchable",
         "liquifying", "vanquished", "zigzagging", "wigwagging", "grogginess", "beekeeping",
         "mummifying", "fluffiness", "fulfilling", "shabbiness","revivified", "hobnobbing",
         "wheeziness", "shagginess", "sleeveless", "parallaxes", "wooliness", "chumminess", 
         "paradoxing", "grubbiness", "wobbliness", "feebleness", "jaywalking", "alkalizing",
         "blabbering", "overjoying", "embezzlement", "crackerjacks", "abracadabra", "absentminded",
         "bighearted", "candyflosses", "chivalrous", "deprogrammed", "epicurean", "fanaticizing",
         "glassblowers", "hardscrabble", "haptoglobins", "idiosyncrasy", "kleptomaniac", "knightliness"};
   
   //icon arrays
   private HangmanIcons hangman;
   private AlphabetIcons alphabet;
   
   private String word; //selected random word
   private char[] usedLetters = new char[26];
   private int usedLetterCount = 0;
   
   private static int[] scores = new int[100];
   private int score;
   
   public static final int MAX_SCORE = 7;
   public static final int MIN_SCORE = 0;
   
   /**
    * Default constructor
    */
   HangmanGame() { init(); }
   
   /**
    * getWord() - returns a random word from the array holding the hangman words
    * @return string holding the word
    */
   private String getWord()
   {
      int rand = (int) (Math.random() * words.length);
      
      return words[rand];
   }
  
  //accessors for icons
  public Icon getHangmanIcon(int num) { return hangman.getIcons(num); }
  public Icon getAlphabetIcon(char letter){ return alphabet.getLetter(letter); }
  public Icon getSpace() { return alphabet.getSpace(); }
  public Icon getLine() { return alphabet.getLine(); }
  
  /**
   * init() - initializes a new game. 
   */
  private void init()
  {
     hangman = new HangmanIcons();
     alphabet = new AlphabetIcons();
     
     //System.out.println("test init");
     
     word = getWord();
     score = 0;
     
  }
  
  /**
   * isInWord() - check if a letter is in the word
   * @param letter - character that is being searched for.
   * @return - boolean, true or false if the letter was found in the word. 
   */
  public boolean isInWord(char letter)
  {
     boolean temp = false;
     //System.out.println(word);
     
     for(int i = 0; i < word.length(); i++)
     {
        if(Character.toUpperCase(word.charAt(i)) == letter)
           temp = true;  
     }
     
     return temp;
  }
  
  /**
   * letterCheck() - checks for a given character at a given position in the word in play
   * @param letter - the character being searched for
   * @param position - the position in the word to be searched.
   * @return - true or false if the letter was found. 
   */
  public boolean letterCheck(char letter, int position)
  {
     boolean temp;
     
     if(position < 0 || position > word.length())
        temp = false;
     else if(letter == Character.toUpperCase(word.charAt(position)))
        temp = true;
     else
        temp = false;
     
     return temp;
  }
  
  /**
   * getWordLength() - returns the length of the word in play. 
   * @return integer for the length of the word. 
   */
  public int getWordLength()
  {
     return word.length();
  }
  
   /**
    * incremementScore() - increments the game score. This method returns the
    * store as opposed to simple retrieval. This is to streamline code by doing
    * two things at once.
    * 
    * @return - returns the new score
    */
   public int incrementScore()
   {
      score++;
      return score;
   }

   /**
    * isUsedLetter() - checks if the letter is already in the used letter array
    * 
    * @param letter
    *           - the letter being searched for
    * @return - true/false if it was found.
    */
   public boolean isUsedLetter(char letter)
   {
      for (int i = 0; i < usedLetters.length; i++)
      {
         if (letter == usedLetters[i])
            return true;
      }
      return false;
   }

   /**
    * addUsedLetter() - adds a letter to the used letter array.
    * 
    * @param letter
    *           - the letter to be added.
    * @return - true/false if the addition was successful.
    */
   public boolean addUsedLetter(char letter)
   {
      if (isUsedLetter(letter))
         return false;

      usedLetters[usedLetterCount] = letter;
      usedLetterCount++;
      return true;
   }

   /**
    * getScore() - gets the current score
    * 
    * @return - integer for the score
    */
   public int getScore()
   {
      return score;
   }

   /**
    * accessor for the word
    * 
    * @return - string for the word
    */
   public String getGameWord()
   {
      return word;
   }

   /**
    * isDone() - checks if the game has been completed
    * 
    * @return - true/false
    */
   public boolean isDone()
   {
      if (score >= MAX_SCORE || allLettersFound())
         return true;
      return false;
   }

   /**
    * isWon() - checks if the game has been won
    * 
    * @return - true/false if the game is won.
    */
   public boolean isWon()
   {
      if (score < MAX_SCORE && allLettersFound())
         return true;
      return false;
   }

   /**
    * allLettersFound() - checks if all the letters in the word have been
    * guessed
    * 
    * @return - true or false for if all letters have been found.
    */
   private boolean allLettersFound()
   {
      int foundCount = 0;

      for (int i = 0; i < usedLetterCount; i++)
      {
         for (int j = 0; j < word.length(); j++)
         {
            if (usedLetters[i] == Character.toUpperCase(word.charAt(j)))
               foundCount++;
         }
      }

      if (foundCount == word.length())
         return true;

      return false;
   }
  
} // end of HangManGame Class --------------------------------------

// ----------------START OF HANGMAN ICONS CLASS---------------------

/**
 * HangmanIcons class - holds image icons for hangman game
 */
class HangmanIcons
{
   private static int MAX_ICONS = 8;
   Icon[] hangman = new Icon[MAX_ICONS];

   /**
    * Default constructor
    */
   HangmanIcons()
   {
      for (int i = 0; i < MAX_ICONS; i++)
      {
         hangman[i] = new ImageIcon("images/Hangman" + i + ".png");
      }
   }

   /**
    * 
    * @param num
    *           - number representing number of errors and which image to return
    * @return Icon object for hangman game that represents the number of
    *         incorrect guesses
    */
   public Icon getIcons(int num)
   {
      if (num < 0 || num >= MAX_ICONS) // if num is invalid
         return hangman[0]; // return 0 as default

      return hangman[num]; // else return icon
   }
}

/**
 * AlphabetIcons class - holds image icons for A-Z, space and underscore
 */
class AlphabetIcons
{

   Icon[] alphabet = new Icon[26];
   Icon space, line;
   static String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

   AlphabetIcons()
   {
      // create letter image icons
      for (int i = 0; i < alphabet.length; i++)
         alphabet[i] = new ImageIcon("images/letter" + i + ".jpg");

      space = new ImageIcon("images/space.jpg");
      line = new ImageIcon("images/line.jpg");

   }

   /**
    * getLetter() - method to get an Icon for the letters
    * 
    * @param letter
    *           - the character that a corresponding image is needed for.
    * @return - the requested icon.
    */
   public Icon getLetter(char letter)
   {
      Icon tempIcon;

      if (letterNum(letter) == -1) // check for invalid input
         tempIcon = alphabet[0];

      tempIcon = alphabet[letterNum(letter)]; // else get the icon

      return tempIcon;

   }

   /**
    * letterNum() - method to take a letter and return the position number the
    * of the letter in the alphabet.
    * 
    * @param letter
    *           - the character to be searched for.
    * @return integer representing the position of the letter in the alphabet.
    */
   private int letterNum(char letter)
   {
      int retNum = -1; // initialize to false

      // correction, just in case
      letter = Character.toUpperCase(letter);

      // check if it is in the string
      for (int i = 0; i < LETTERS.length(); i++)
      {
         if (letter == LETTERS.charAt(i))
         {
            retNum = i; // if valid, update
         }
      }

      return retNum;
   }

   /**
    * getSpace() - method to get the space icon
    * 
    * @return - the icon representing a space
    */
   public Icon getSpace()
   {
      return space;
   }

   /**
    * getLine() - method to get the line Icon
    * 
    * @return - the line Icon
    */
   public Icon getLine()
   {
      return line;
   }

}


class HangmanController
{
   static HangmanView gameView;
   static HangmanGame game = new HangmanGame();
   static final String LETTERS= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
   HangmanController()
   {
      // establish main frame in which program will run
      gameView = new HangmanView("CSUMB Hangman Game");
      gameView.setSize(900, 750);
      gameView.setLocationRelativeTo(null);
      gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setGame(); //update hangman labels and blank word tiles
      
      //listener and action event for play again button
      gameView.getPlayButton().addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent event)
         {
            gameView.newGame(game.isWon());//update window
            game = new HangmanGame();//create new game
            
            setGame(); //update hangman labels and blank word tiles
            
            //set up the letters
            for(int i = 0; i < LETTERS.length(); i++)
            {
               char letter = LETTERS.charAt(i);
               gameView.setLetterImageToPlayArea( game.getAlphabetIcon(letter), i );
               addListener(i, letter);
            }
         }
            
      });
            
      //set up the letters
      for(int i = 0; i < LETTERS.length(); i++)
      {
         char letter = LETTERS.charAt(i);
         
         gameView.setLetterImageToPlayArea( game.getAlphabetIcon(letter), i );
          
         //listener and mouseclick event for the letters 
         addListener(i, letter);

      } //end of for loop ----------------------

      gameView.setVisible(true); //always last
   }
   
   /**
    * addListener() - avoiding duplicating data by putting listener for the alphabet tiles into a method. 
    * @param num - the number of the letter
    * @param letter - the letter for the listener. 
    */
   void addListener(int num, char letter)
   {
      gameView.getAlphabetLabel(num).addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent e)
         {
           if(game.isInWord(letter) && !game.isUsedLetter(letter))
           {
             for(int i = 0; i < game.getWordLength(); i++)
             {
                if( game.letterCheck(letter, i) )
                  gameView.setWordIcon(game.getAlphabetIcon(letter), i);
             }//end for loop
             
             String usedLetters = gameView.getUsedLetters() + letter;
             gameView.setUsedLetters(usedLetters);
             game.addUsedLetter(letter);
             
           }//end if
           else if(!game.isInWord(letter) && !game.isUsedLetter(letter))
           {
              gameView.setScore( game.incrementScore() );
              
              gameView.setHangmanImage(game.getHangmanIcon(game.getScore()));
              
              String usedLetters = gameView.getUsedLetters() + letter;
              gameView.setUsedLetters(usedLetters);
              game.addUsedLetter(letter);
              
           }//end if
            
           if(game.isDone())
              gameView.setWinScreen(game.getGameWord(), game.isWon());
          
         }//end of mouse pressed ------------
         
      }); //end of listener -----------------
   }//end addListner method----------------------------------------------
   
   /**
    * setGame() - updates the hangmanIcon to the 0 point and resets the word tiles. 
    */
   void setGame()
   {
      //set up hangman labels
      gameView.setHangmanImage( game.getHangmanIcon(0) );
      
      //set up the word tiles
      for(int i = 0; i < game.getWordLength(); i++)
         gameView.setWordIcon(game.getLine(), i);
   }
   
}

class HangmanView extends JFrame  
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

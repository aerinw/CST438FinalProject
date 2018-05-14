import javax.swing.Icon;
import javax.swing.ImageIcon;

public class HangmanGame
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

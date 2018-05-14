import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;


public class HangmanController
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary();


	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}
		return words.toArray(new String[0]);
	}



	public static String reverseStringAndRemoveNonAlpha(String text) {
    //Create an empty stack
		Stack<String> phraseStack = new Stack<String>();
    //Now the purpose is to loop through each character
    //For each character, we verify if it's alphabetic
    //If it is, then convert the character into a string and push it onto the stack
    //If it's not, then go to the next iteration (break)
    for (int i = 0; i < text.length(); i++){
      char currentChar = text.charAt(i);
      if (Character.isAlphabetic(currentChar) == true){
        String s = String.valueOf(text.charAt(i));
        phraseStack.push(s);
      }
    }

    //Note: This output is meant to be a test to see if the stack works
    //System.out.println(phraseStack);

    //We will create a string called reverseResult to put the reverse string in
    String reverseResult = "";

    //We make a variable called prePop with the size of the stack
    //We do this because I don't know if the size will have an effect on the for loop.
    int prePop = phraseStack.size();

    //Now, we pop the stack for size times.
    for(int i = 0; i < prePop; i++){
      reverseResult = reverseResult + phraseStack.pop();
    }

    //Return the reversed string
    System.out.println("The reversed result is " + reverseResult);
    return reverseResult;
    
	}



	// Returns true if the text is a palindrome, false if not.
	public static boolean isPalindrome(String text) {
    String lowerText = text.toLowerCase();

    Stack<Character> stackText = new Stack<Character>();
    Queue<Character> queueText = new Queue<Character>();

    for(int i = 0; i < text.length(); i++){
      char currentChar = lowerText.charAt(i);
      if(Character.isAlphabetic(currentChar) == true){
        stackText.push(currentChar);
        queueText.enqueue(currentChar);
      }
      else continue;
    }

    while(queueText.size() != 0){
      char p = stackText.pop();
      char q = queueText.dequeue();
      if(q != p){
        return false;
      } 
    }
    return true;
	}



	public static void explorePalindrome(String text)
   {
        if (text.length() == 0)
        return;

        decomposeText(
            text,
            reverseStringAndRemoveNonAlpha(text.toLowerCase()),
            0,
            new Stack<String>()
        );
	}

	public static void decomposeText( String originalText,
                                    String textToDecompose,
                                    int index,
                                    Stack<String> decomposition) 
    { 
        if (index == textToDecompose.length())
        {
            int sizeOfResultQueue = decomposition.size();
            Stack<String> temp = new Stack<String>();
            String result = "";
            for (int i = 0; i < sizeOfResultQueue; i++)
            {
                String word = decomposition.pop();
                result = word + " " + result;
                temp.push(word);
            }

            while(temp.size() > 0){
                decomposition.push(temp.pop());
            }
            System.out.println(originalText + " = " + result);
        }

        else
        {
            String[] wordsFound = getWords(textToDecompose, index);
            for(int i = 0; i < wordsFound.length; i++){
                if (wordsFound[i].length() > 0){
                    decomposition.push(wordsFound[i]);
            //System.out.println("The one you want " + wordsFound[wordsFound.length - 1]);
                int length = wordsFound[i].length();

            //System.out.println(decomposition);
                decomposeText(originalText, textToDecompose, index + length, decomposition);

                decomposition.pop();
                }
            }
        }
        
	}

	// This function looks at the arguments that are passed
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = {"A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami"};
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);


			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {

					explorePalindrome(testPalindromes[i]);
				}
			}
			else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}

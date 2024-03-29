package wordPlay.handler;

import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

import wordPlay.metrics.MetricsCalculator;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

/**
* WordRotator is a handler class to be used to rotate the words in a sentence.
*
* @author Preeti Priyam
*/

public class WordRotator {
private static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9.]*$";
private int numOfRotations = 0;
private MetricsCalculator metrics;
private Results result;


/**
* Method to send rotated word to the result class.
*
* @return type is object of restult class.
*/
public Results rotator(String Dinput){
 String wordReversed;
 result = new Results();
 metrics = new MetricsCalculator();
 try{
  FileProcessor fileprocessor = new FileProcessor(Dinput);
  String word = fileprocessor.poll();
   if(word == null){
    throw new genericExceptions("EmptyFileException : File is empty");
   }
   while(word != null){
    if(word.isEmpty()){
     throw new genericExceptions("EmptyLineException : Input file has a Empty Line");
   }
   regex(word);
   metrics.addWord(word);
   numOfRotations++;
   wordReversed = WordRotator(word);
   result.addToList(wordReversed);
   word = fileprocessor.poll();
   }
  }
  catch(genericExceptions e){
   System.out.println(e);
   System.exit(0);
  }
  catch(FileNotFoundException e){
   System.out.println("FileNotFoundException : InputFile is missing or input file is in incorrect location");
   System.exit(0);
  }
  catch(Exception e){
   System.out.println(e);
   System.exit(0);
  }
  result.setMetrics(metrics);
  return result;
 }

 /**
 * Method to remove period from the word.
 *
 * @return type is character array.
 */
  public char[] removeperiod(char[] charArray){
  char[] temp = breakString(charArray,0,charArray.length -2).toCharArray();
  return temp;
}

/**
* Method to handle regular expression exception.
*
* @return no return type.
*/
public void regex(String word){
 try{
  if(Pattern.matches(ALPHANUMERIC_PATTERN, word) == false){
  throw new genericExceptions("InvalidCharacterException : Please enter the valid caharcters");
  }
}
  catch(genericExceptions e){
   System.out.println(e);
   System.exit(0);
  }
}

/**
* Medthod to find number of rotations in a word.
*
* @return type is integer.
*/
public int findBreakPoint(int stringLength, int rotations){
 int end = stringLength - rotations;
 while(end < 0){
   end = -end;
   end = stringLength - end;
 }
 return end;
}

/**
* Method to create substring of a string.
*
* @return type is String.
*/
public String breakString(char[] charArray, int start, int end){
String subString = "";
for(int i = start; i <= end; i++){
  subString += charArray[i];
}
return subString;
}

 /**
 * Method to rotate words.
 *
 * @return type is String.
 */
public String WordRotator(String word){
  boolean isPeriod = false;
  char [] wordArray = word.toCharArray();
  if(wordArray[wordArray.length-1] == '.')
  {
    wordArray = removeperiod(wordArray);
    isPeriod=true;
    metrics.endOfLine();
  }
  int end = findBreakPoint(wordArray.length, numOfRotations);
  String subString1 = breakString(wordArray, 0, end-1);
  String subString2 = breakString(wordArray, end, wordArray.length - 1);
   if(isPeriod == true){
    subString1 += ".\n";
    numOfRotations = 0;
    isPeriod = false;
    }
    else{
    subString1 += " ";
    }
  String result = subString2 + subString1;
  return result;
  }
}

/**
* genericExceptions is a class that allows to create coustom exception.
*/
class genericExceptions extends Exception{
  String str;
   genericExceptions(String copy)
    {
      str = copy;
    }
  @Override
  public String toString() {
  return ("Exception: " +str);
   }
}

package week4;

import java.util.ArrayList;

public class NameScore {

    public static int letterPoint(char letter) {

        if(letter >= 'A' && letter <= 'Z')
        return letter - 'A' + 1;

        else
            return 0;
    }


    /**
     Each letter in alphabet,(english ,A-Z) has a score range in 1 and 26 based on their position.
     A = 1 , B = 2 , C = 3 etc.
     If the name is ATA , its score should be 22 (A=1,T=20,A=1)
        @param names  ArrayList of Strings that contains names
        @return total_score Total score of all names that is in the given list
     */
    public static int totalScore(ArrayList<String> names) {

        int total_score = 0;
        int result = 0;
        for (String name : names) {
            for (int index = 0; index < name.length(); index++) {

                if(letterPoint(name.charAt(index)) == 0)
                    return 0;

                else
                    result = result + letterPoint(name.charAt(index));
            }
            total_score = total_score +result;
        }
        return total_score;
    }

}

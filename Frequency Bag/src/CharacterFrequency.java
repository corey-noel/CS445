/**
 * AUTHORED BY COREY NOEL
 */

import java.util.Scanner;
import java.io.*;

public class CharacterFrequency {

    public static void main(String[] args) {
        Scanner letter = null;
        FrequencyBag<Character> letterFrequency = new FrequencyBag<Character>();

        try {
             letter = new Scanner(new File("letter1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (letter.hasNext()) {
            String input = letter.next();
            for (char c : input.toCharArray()) {
                if (Character.isLetter(c))
                    letterFrequency.add(Character.toLowerCase(c));
            }
        }

        System.out.println("Character: Frequency");
        System.out.println("====================");
        for (int i = 0; i < 26; i++)
            System.out.println((char)(i + 97) + ": " + letterFrequency.getFrequencyOf((char)(i + 97)));

    }
}

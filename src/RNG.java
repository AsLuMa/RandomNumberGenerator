import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RNG {

    private Scanner scanner;
    private int randomNumber;
    private int numberOfCodes;
    private String input;
    private ArrayList<String> listOfNumbers;
    private int counter;

    public RNG(Scanner scanner){
        this.scanner = scanner;
        this.listOfNumbers = new ArrayList<>();
        this.counter = 0;
    }

    //opner scanner
    //sjekker ikkje for riktig input
    public void start(){

        System.out.println("Kor mange kodar med 4-siffer vil du lage?");
        input = scanner.nextLine();

        while(!input.equals("end")){

            try {int number = Integer.parseInt(input);

            if (number >= 0 && number <= 10000) {
                //denne må vere int, fordi den angir upper bound i generateNumbers()
                this.numberOfCodes = Integer.parseInt(input);
                generateNumbers();
                printList();
                //For å teste at det faktisk genereres duplikat, som blir tatt bort
                //System.out.println(this.counter + " duplikat tatt bort");
                break;
            }
            else {
                System.out.println("Skriv inn eit tal mellom 0-10000. 'end' for å slutte.");
                input = scanner.nextLine();
            }
            } catch (NumberFormatException ex) {
                System.out.println("Skriv inn eit tal mellom 0-10000. 'end' for å slutte.");
                input = scanner.nextLine();
            }
        }
    }


    //add the randomly generated number to an ArrayList (to keep track of which numbers have been generated)
    public void addNumberToList(String number){
        listOfNumbers.add(number);
    }

    //Method to generate one random number between 0 and 9999
    //Padder venstre side med 0'er, dersom talet er mindre enn 4

    public String generateNumber(){
        Random random = new Random();
        randomNumber = random.nextInt(10000);
        //System.out.println(randomNumber);
        //code to add zero in front if number is less than 4 figures
        String number = padLeftZeros(String.valueOf(randomNumber), 4);
        return number;
    }

    //metode saksa frå internettet for å padde med 0-ar
    public String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    //Metode for å lage ei liste med RGN
    //litt ekstra kode for å sjekke at duplikat blir riktig håndtert
    public void generateNumbers(){
        while(listOfNumbers.size() < numberOfCodes){
            String var = generateNumber();
            if(!listOfNumbers.contains(var)){
                addNumberToList(var);
            }
            //code to check if duplicates happen and are rejected
            else {
                //System.out.println("duplicate");
                this.counter++;
            }
        }
    }


    //printer nummerlista
    public void printList(){
        System.out.println("Kodeliste (" + numberOfCodes + " koder):");
        for(String var : listOfNumbers)
            System.out.println(var);
        System.out.println("Denne lista inneholder " + listOfNumbers.size() + " elementer.");
    }



}

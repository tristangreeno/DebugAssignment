import jodd.json.JsonSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Reads the countries file for each country's abbreviation and full name.
 * Then, the program will write a file based on user input.
 */
class ReadWriteFile extends Country {

    private ArrayList<Country> countries = new ArrayList<>();
    private HashMap<Character, ArrayList<Country>> countryLetters = new HashMap<>();

    void readCountriesFile() throws FileNotFoundException {
        File f = new File("/Users/tristangreeno/WorkSpace/DebugAssignment/src/countries.txt");

        scanCountries(f);
        sortCountriesByLetter();

    }

    void writeCountriesFile() throws IOException {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter the first letter of countries you want added to a list.");
        String countryLetter = userInput.nextLine();
        Character countryChar = countryLetter.charAt(0);

        System.out.printf("List of countries found with %s:\n", countryChar);
        writeToFile(countryChar, countryLetter.toLowerCase());

        System.out.println("Thank you for using the Read/Write system.");
    }

    private void scanCountries(File f) throws FileNotFoundException {

        Scanner countryNameScanner = new Scanner(f);

        while (countryNameScanner.hasNext()) {
            String line = countryNameScanner.nextLine();
            String columns[] = line.split("\\|");
            Country country = new Country(columns[0], columns[1]);
            countries.add(country);
        }
    }

    private void sortCountriesByLetter() {
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            ArrayList<Country> countriesStartWithLetter = new ArrayList<>();
            for (Country c : countries) {

                if (c.countryName.charAt(0) == alphabet) {
                    countriesStartWithLetter.add(c);
                    // loop is fine, problem somewhere else
                }
            }
            countryLetters.put(alphabet, countriesStartWithLetter);
        }
    }

    private void writeToFile(char countryChar, String countryLetter) throws IOException {
        JsonSerializer serializer = new JsonSerializer();

        File countriesFile = new File("src/".concat(countryLetter).concat("_countries.json"));
        FileWriter fw = new FileWriter(countriesFile.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fw);

        if(countryLetters.get(countryChar).isEmpty()){
            System.out.println("Sorry, no countries starting with that letter were found.");
        }

        ArrayList<Country> temp = countryLetters.get(countryChar);
        String countryJson = serializer.include("*").serialize(temp);
        bufferedWriter.write(countryJson);

        System.out.println(countryLetters.get(countryChar).toString());

        System.out.printf("JSON file of countries starting with '%s' has been created.\n", countryLetter.toUpperCase());

        bufferedWriter.close();
    }
}

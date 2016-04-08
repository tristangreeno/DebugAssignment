
/**
 * Stores the information concerning each country from the countries.txt file.
 */
public class Country {
    String countryName;
    String countryAbbreviation;

    Country() {
    }

    Country(String countryAbbreviation, String countryName) {
        this.countryName = countryName;
        this.countryAbbreviation = countryAbbreviation;
    }

    @Override
    public String toString(){
        return countryName;
    }
}

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadWriteFile readWriteFile = new ReadWriteFile();
        readWriteFile.readCountriesFile();
        readWriteFile.writeCountriesFile();
    }
}

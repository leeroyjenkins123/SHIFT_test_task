
public class Main {

    public static void main(String[] args) {
        try {
            CommandLineArgs commandLineArgs = CommandLineParser.parse(args);
            FileFilter fileFilter = new FileFilter(commandLineArgs);
            fileFilter.filter();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}

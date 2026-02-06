
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileFilter {

    private final CommandLineArgs commandLineArgs;
    private final IntStatistics intStatistics = new IntStatistics();
    private final FloatStatistics floatStatistics = new FloatStatistics();
    private final StringStatistics stringStatistics = new StringStatistics();
    private final CreateStatisticsText createStatisticsText = new CreateStatisticsText();

    private PrintWriter intWriter = null;
    private PrintWriter floatWriter = null;
    private PrintWriter stringWriter = null;

    public FileFilter(CommandLineArgs commandLineArgs) {
        this.commandLineArgs = commandLineArgs;
    }

    public void filter() {
        List<String> inputFiles = commandLineArgs.getInputFiles();
        for (String fileName : inputFiles) {
            File file = new File(fileName);
            if (!file.exists() || !file.isFile()) {
                throw new IllegalArgumentException("Error: input file doesn't exists or is not file: " + fileName);
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    processLine(line);
                }
            } catch (Exception e) {
                System.err.println("Error while reading file " + fileName + ": " + e.getMessage());
            }
        }
        closeWriters();
        printStatistics();
    }

    private void processLine(String line) {
        line = line.trim();
        if (line.isEmpty()) {
            return;
        }
        try {
            long intValue = Long.parseLong(line);
            intStatistics.add(intValue);
            writeInt(line);
            return;
        } catch (NumberFormatException ignored) {

        }
        try {
            double doubleValue = Double.parseDouble(line);
            floatStatistics.add(doubleValue);
            writeFloat(line);
            return;
        } catch (NumberFormatException ignored) {

        }
        stringStatistics.add(line);
        writeString(line);
    }

    private void writeInt(String line) {
        if (intWriter == null) {
            intWriter = createWriter("integers.txt");
        }
        if (intWriter != null) {
            intWriter.println(line);
        }
    }

    private void writeFloat(String line) {
        if (floatWriter == null) {
            floatWriter = createWriter("floats.txt");
        }
        if (floatWriter != null) {
            floatWriter.println(line);
        }
    }

    private void writeString(String line) {
        if (stringWriter == null) {
            stringWriter = createWriter("strings.txt");
        }
        if (stringWriter != null) {
            stringWriter.println(line);
        }
    }

    private PrintWriter createWriter(String baseFileName) {
        String fileName = commandLineArgs.getFilePrefix() + baseFileName;
        File dir = new File(commandLineArgs.getOutputPath());
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.println("Error: cannot create directory " + commandLineArgs.getOutputPath());
                return null;
            }
        }
        File outFile = new File(dir, fileName);
        try {
            return new PrintWriter(new java.io.FileWriter(outFile, commandLineArgs.isAppendMode()));
        } catch (IOException e) {
            System.err.println("Error while creating file " + outFile.getAbsolutePath() + ": " + e.getMessage());
            return null;
        }
    }

    private void closeWriters() {
        if (intWriter != null) {
            intWriter.close();
        }
        if (floatWriter != null) {
            floatWriter.close();
        }
        if (stringWriter != null) {
            stringWriter.close();
        }
    }

    private void printStatistics() {
        if (intStatistics.getCount() > 0) {
            System.out.println(createStatisticsText.getIntStatistics(intStatistics, commandLineArgs.getStatMode()));
        }
        if (floatStatistics.getCount() > 0) {
            System.out.println(createStatisticsText.getFloatStatistics(floatStatistics, commandLineArgs.getStatMode()));
        }
        if (stringStatistics.getCount() > 0) {
            System.out.println(createStatisticsText.getStringStatistics(stringStatistics, commandLineArgs.getStatMode()));
        }
        System.out.println();
    }
}

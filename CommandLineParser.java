
import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {

    public static CommandLineArgs parse(String[] args) {
        CommandLineArgs commandLineArgs = new CommandLineArgs();
        List<String> inputFiles = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-o":
                    commandLineArgs.setOutputPath(args[++i]);
                    break;
                case "-p":
                    commandLineArgs.setFilePrefix(args[++i]);
                    break;
                case "-a":
                    commandLineArgs.setAppendMode(true);
                    break;
                case "-s":
                    if (commandLineArgs.getStatMode() == StatisticMode.FULL) {
                        throw new IllegalArgumentException("Cannot use both -s and -f command");
                    }
                    commandLineArgs.setStatMode(StatisticMode.SHORT);
                    break;
                case "-f":
                    if (commandLineArgs.getStatMode() == StatisticMode.SHORT) {
                        throw new IllegalArgumentException("Cannot use both -s and -f command");
                    }
                    commandLineArgs.setStatMode(StatisticMode.FULL);
                    break;
                default:
                    if (arg.startsWith("-")) {
                        throw new IllegalArgumentException("Unknown option: " + arg);
                    }
                    inputFiles.add(arg);
                    break;
            }
        }

        if (inputFiles.isEmpty()) {
            throw new IllegalArgumentException("No input files found");
        }

        commandLineArgs.setInputFiles(inputFiles);
        return commandLineArgs;
    }
}

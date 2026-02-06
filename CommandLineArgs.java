
import java.util.List;

public class CommandLineArgs {

    private String outputPath = ".";
    private String filePrefix = "";
    private boolean appendMode = false;
    private StatisticMode statMode = StatisticMode.NONE;
    private List<String> inputFiles;

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public void setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
    }

    public StatisticMode getStatMode() {
        return statMode;
    }

    public void setStatMode(StatisticMode statMode) {
        this.statMode = statMode;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }

}

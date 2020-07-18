package com.axlsreborn;

public class ProjectPropertiesArgs {
    private final String apiKey;
    private final int appId;
    private final char csvDelimiter;
    private final String csvFilePath;
    private final String debugFlag;

    public ProjectPropertiesArgs(String apiKey, int appId, char csvDelimiter, String csvFilePath, String debugFlag) {
        this.apiKey = apiKey;
        this.appId = appId;
        this.csvDelimiter = csvDelimiter;
        this.csvFilePath = csvFilePath;
        this.debugFlag = debugFlag;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getAppId() {
        return appId;
    }

    public char getCsvDelimiter() {
        return csvDelimiter;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public String getDebugFlag() {
        return debugFlag;
    }
}
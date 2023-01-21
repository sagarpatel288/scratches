fu

public static void logSleepData(String content) {
    Log.d(" :myApp: ", "LogUtil: :logSleepData: logsDir: " + logsDir);
    if (logsDir != null) {
        String fileName = "logsMyApp.txt";
        File logFile = new File(logsDir, fileName);
        try {
            String newLine = "\n\r";
            String currentDateTime = new LocalDateTime().toString();
            content =
                newLine + " Current Local Date Time: " + currentDateTime + " Android OS: " + Build.VERSION.SDK_INT + " : " + content;
            FilesKt.appendText(logFile, content, StandardCharsets.UTF_8);
        } catch (Exception e) {
            L.w(TAG, e.getMessage());
            Log.d(" :myApp: ", "LogUtil: :logData: exception: " + e.getMessage());
            //don't handle to avoid exception loop
        }
    }
}
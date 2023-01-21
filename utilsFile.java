import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import java.io.File;
import java.nio.charset.StandardCharsets;

import kotlin.io.FilesKt;

class Scratch {
    public static void main(String[] args) {
        
    }
    public static void logSleepData(Context context, String content) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "DEBUG_FILES_DIR_NAME" + "DEBUG_LOGS_DIR_NAME");
        file.mkdirs();
        String logsDir = file.getAbsolutePath();
        Log.d(" :myApp: ", "LogUtil: :logData: logsDir: " + logsDir);
        if (logsDir != null) {
            String fileName = "logsMyApp.txt";
            File logFile = new File(logsDir, fileName);
            try {
                String newLine = "\n\r";
                String currentDateTime = new LocalDateTime(DateTimeZone.getDefault()).toString();
                content =
                        newLine + " Current Local Date Time: " + currentDateTime + " Android OS: " + Build.VERSION.SDK_INT + " : " + content;
                FilesKt.appendText(logFile, content, StandardCharsets.UTF_8);
            } catch (Exception e) {
                Log.d(" :myApp: ", "LogUtil: :logData: exception: " + e.getMessage());
                //don't handle to avoid exception loop
            }
        }
    }
}
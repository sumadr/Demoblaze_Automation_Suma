package utils;

import org.testng.*;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;

    public boolean retry(ITestResult r) {
        if (count < 3) {
            count++;
            return true;
        }
        return false;
    }
}
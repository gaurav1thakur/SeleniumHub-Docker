package RemoteTestingDockerHub;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class Docker {

    // pre-requisite steps
    //https://learn.microsoft.com/en-us/windows/wsl/install
    //https://www.browserstack.com/guide/run-selenium-tests-in-docker
    // Docker desktop/engine is running
    public void startDocker() throws IOException, InterruptedException {
        boolean flag = false;
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start dockerUp.bat");
        String f = "output.txt";
        Calendar cal = Calendar.getInstance();//2:44 15th second
        cal.add(Calendar.SECOND, 120);//2:44   45seconds
        long stopnow = cal.getTimeInMillis();
        Thread.sleep(3000);
        while (System.currentTimeMillis() < stopnow) {
            if (flag) break;
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String currentLine = reader.readLine();
            while (currentLine != null && !flag) {
                if (currentLine.contains("Node has been added")) {
                    System.out.println("found my text :: Node has been added in docker up up stage");
                    flag = true;//14th seconds
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }
        Assert.assertTrue(flag);
        runtime.exec("cmd /c start dockerScale.bat");
        Thread.sleep(15000);
    }

    public void stopDocker() throws IOException, InterruptedException {
        boolean flag = false;
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start dockerDown.bat");
        String f = "output.txt";
        Calendar cal = Calendar.getInstance();//2:44 15th second
        cal.add(Calendar.SECOND, 120);//2:44   45seconds
        long stopnow = cal.getTimeInMillis();
        Thread.sleep(3000);
        while (System.currentTimeMillis() < stopnow) {
            if (flag) break;
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String currentLine = reader.readLine();
            while (currentLine != null && !flag) {
                if (currentLine.contains("Shutdown complete")) {
                    System.out.println("found my text :: Shutdown complete for all containers in docker");
                    flag = true;//14th seconds
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }
        Assert.assertTrue(flag);
    }


}



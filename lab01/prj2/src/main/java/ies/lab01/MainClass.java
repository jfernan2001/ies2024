package ies.lab01;

import java.util.Timer;
import java.util.TimerTask;


public class MainClass {
  
  Timer timer;

  public MainClass() {

    timer = new Timer();
    timer.scheduleAtFixedRate(new RemindTask(), 0, //initial delay
        1 * 1000); //subsequent rate
  }

  class RemindTask extends TimerTask {
    int numWarningBeeps = 3;

    public void run() {
      if (numWarningBeeps-- > 0) {
        long time = System.currentTimeMillis();
        if (time - scheduledExecutionTime() > 5) {
          return;
        }

        System.out.println("Beep!");
      } else {
          //toolkit.beep();
        System.out.println("Time's up!");
        System.exit(0);
      }
    }
  }

  public static void main(String args[]) {
    new MainClass();
  }
}

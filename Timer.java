import greenfoot.*;
public class Timer extends Text {
    private boolean running = false;
    public static int millisElapsed=0;
    private long lastTime = 0;
    
    public Timer() {
        super("");
        updateImage();
    }
    
    public void start() {
        millisElapsed = 0;
        lastTime = 0;
    }
    
    public void gamePaused() {
        lastTime = 0;
    }
    
    public void act() {
        long time = System.currentTimeMillis();
        if(lastTime != 0) {
            long diff = time - lastTime;
            millisElapsed += diff;
        }
        lastTime = time;
        
        updateImage();
    } // David Solano
    
    public void updateImage() {
        // Calculate minutes & seconds elapsed
        int secs = (millisElapsed / 1000) % 60;
        int mins = millisElapsed / 60000;
        // Convert these into text
        String secsText = String.format("%02d", secs);
        String minsText = String.format("%02d", mins);
        // Update the image
        this.setText(minsText + ":" + secsText);
    } // David Solano
}

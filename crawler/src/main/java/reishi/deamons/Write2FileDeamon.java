package reishi.deamons;

import reishi.listeners.WriteToFileListener;

/**
 *
 */
public class Write2FileDeamon {
    public static void main(String[] args) {
        WriteToFileListener writeToFileListener = new WriteToFileListener();
        writeToFileListener.start();
    }
}

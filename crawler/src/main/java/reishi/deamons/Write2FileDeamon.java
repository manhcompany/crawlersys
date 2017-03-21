package reishi.deamons;

import reishi.listeners.WriteToFileListener;

/**
 * Created by manhtt on 18/03/2017.
 */
public class Write2FileDeamon {
    public static void main(String[] args) {
        WriteToFileListener writeToFileListener = new WriteToFileListener();
        writeToFileListener.start();
    }
}

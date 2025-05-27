public interface Playable {
    // Method to play the instrument
    void play();
    
    // Method to tune the instrument
    void tune();
    
    // Method to get instrument info
    String getInstrumentInfo();
    
    // Default method that all instruments can use as-is
    default void showStatus() {
        System.out.println("Instrument Status:");
        System.out.println("Type: " + getInstrumentInfo());
        System.out.println("Ready to play!");
    }
} 
public class Guitar implements Playable {
    private String type;
    private int strings;
    private boolean isElectric;
    
    // Constructor
    public Guitar(String type, int strings, boolean isElectric) {
        this.type = type;
        this.strings = strings;
        this.isElectric = isElectric;
    }
    
    // Implement play() method from Playable interface
    @Override
    public void play() {
        System.out.println("Playing guitar: Strum strum strum!");
        if (isElectric) {
            System.out.println("Electric amplification enhances the sound!");
        }
    }
    
    // Implement tune() method from Playable interface
    @Override
    public void tune() {
        System.out.println("Tuning all " + strings + " strings of the guitar");
    }
    
    // Implement getInstrumentInfo() method from Playable interface
    @Override
    public String getInstrumentInfo() {
        return (isElectric ? "Electric" : "Acoustic") + " " + type + " Guitar with " + strings + " strings";
    }
    
    // Guitar-specific method
    public void adjustVolume(int level) {
        if (isElectric) {
            System.out.println("Adjusting amplifier volume to " + level);
        } else {
            System.out.println("This is an acoustic guitar - no volume control available");
        }
    }
} 
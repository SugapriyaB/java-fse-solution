public class Piano implements Playable {
    private String type;
    private int keys;
    private boolean isGrand;
    
    // Constructor
    public Piano(String type, int keys, boolean isGrand) {
        this.type = type;
        this.keys = keys;
        this.isGrand = isGrand;
    }
    
    // Implement play() method from Playable interface
    @Override
    public void play() {
        System.out.println("Playing piano: Plink plink plink!");
        if (isGrand) {
            System.out.println("The grand piano produces a rich, resonant sound!");
        }
    }
    
    // Implement tune() method from Playable interface
    @Override
    public void tune() {
        System.out.println("Tuning all " + keys + " keys of the piano");
    }
    
    // Implement getInstrumentInfo() method from Playable interface
    @Override
    public String getInstrumentInfo() {
        return (isGrand ? "Grand" : "Upright") + " " + type + " Piano with " + keys + " keys";
    }
    
    // Piano-specific method
    public void adjustPedal(String pedalType) {
        System.out.println("Using the " + pedalType + " pedal for effect");
    }
} 
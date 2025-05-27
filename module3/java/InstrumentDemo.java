public class InstrumentDemo {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Musical Instruments Interface Demonstration");
        System.out.println("---------------------------------------\n");
        
        // Create instances of instruments
        Guitar guitar = new Guitar("Classical", 6, false);
        Guitar electricGuitar = new Guitar("Rock", 6, true);
        Piano piano = new Piano("Yamaha", 88, true);
        
        // Demonstrate Guitar
        System.out.println("Testing Acoustic Guitar:");
        System.out.println("----------------------");
        guitar.showStatus();
        guitar.tune();
        guitar.play();
        guitar.adjustVolume(5); // Guitar-specific method
        
        // Demonstrate Electric Guitar
        System.out.println("\nTesting Electric Guitar:");
        System.out.println("----------------------");
        electricGuitar.showStatus();
        electricGuitar.tune();
        electricGuitar.play();
        electricGuitar.adjustVolume(8); // Guitar-specific method
        
        // Demonstrate Piano
        System.out.println("\nTesting Piano:");
        System.out.println("-------------");
        piano.showStatus();
        piano.tune();
        piano.play();
        piano.adjustPedal("sustain"); // Piano-specific method
        
        // Demonstrate polymorphism with interface
        System.out.println("\nDemonstrating Polymorphism:");
        System.out.println("-------------------------");
        Playable[] instruments = {guitar, electricGuitar, piano};
        
        for (Playable instrument : instruments) {
            System.out.println("\nPlaying: " + instrument.getInstrumentInfo());
            instrument.play();
        }
    }
} 
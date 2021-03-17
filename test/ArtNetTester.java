/**
 * ArtNet Tester class
 * @author: Mirco Borella
 */
public class ArtNetTester {

    /**
     * Main loop
     * @param args Arguments
     * @throws Exception if problems
     */
    public static void main(String[] args) throws Exception{
        //Loop
        while (true) {
            try {
                //Create an ArtNet Object
                ArtNet a = new ArtNet("255.255.255.255");
                //Data array
                byte[] data = new byte[512];
                //Dmx Values incremention for the data array (0-255)
                for (int x = 0; x < 255; x++) {
                    //Values Insection on the data array
                    for (int i = 0; i < data.length; i++) {
                        data[i] = (byte)x; 
                    }
                    //Send the packet with the data
                    a.sendArtDmxPacket(data);
                    Thread.sleep(5);
                }
            } catch (Exception e) {
                System.out.println("Exception in Main: " + e);
            }
        }
    }
}

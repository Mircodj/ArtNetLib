import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * ArtNet Class
 * @author: Mirco Borella
 */
public class ArtNetLib {
    
    private DatagramSocket socket;
    private String socketIp;

    /**
     * Constructor with ip
     * @param socketIp Ip address of the socket
     */
    public ArtNetLib(String socketIp){
        this.socketIp = socketIp;
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
            System.out.println("Exception in constructor: " + e);
        }
    }

    /**
     * Send a ArtDmx packet in a universe, subnet and net given.
     * @param dmxChannelData Dmx data array. ArraySize=512, ArrayType=byte, ArrayValue=(0-255)
     * @param universe Universe in which send the packets. (Values from 0 to 15)
     * @param subnet Subnet in which send the packets. (Values from 0 to 15)
     * @param net Net in which send the packets. (Values from 0 to 15)
     */
    public void sendArtDmxPacket(byte[] dmxChannelData, byte universe, byte subnet, byte net){
        //Name:         Size:       Bytes:      Bits:
        //Id[8]         Int8        8           64
        char[] idChars = {'A', 'r', 't', '-', 'N', 'e', 't', 0x00};
        byte[] id = new byte[8];
        for (int i = 0; i < id.length; i++) {
            id[i] = (byte)idChars[i];
        }

        //Name:         Size:       Bytes:      Bits:
        //OpCode        Int16       2           16
        //Choosen OpCode: OpDmx=0x5000;
        //Notation: Little-endian
        byte[] opCode = {(byte)0, (byte)80};

        //Name:         Size:       Bytes:      Bits:
        //ProtVerHi     Int8        1           8
        byte protVerHi = 0;

        //Name:         Size:       Bytes:      Bits:
        //ProtVerLow    Int8        1           8
        byte protVerLow = 14;

        //Name:         Size:       Bytes:      Bits:
        //Sequence      Int8        1           8
        byte sequence = 0;

        //Name:         Size:       Bytes:      Bits:
        //Physical      Int8        1           8
        byte physical = 0;

        //Name:         Size:       Bytes:      Bits:
        //SubUni        Int8        1           8
        //Most significant 4 bit- > Subnet
        //Least significant 4 bit -> Universe
        //Values accepted: 0-15
        subnet = (byte) (subnet << 4);
        byte subUni = (byte) (subnet + universe);

        //Name:         Size:       Bytes:      Bits:
        //Net           Int8        1           8
        //Values accepted: 0-127
        byte pNet = net;

        //Name:         Size:       Bytes:      Bits:
        //LengthHi      Int8        1           8
        byte lengthHi = 2;

        //Name:         Size:       Bytes:      Bits:
        //Lenght        Int8        1           8
        byte length = 0;

        //Name:         Size:       Bytes:      Bits:
        //Data          Int8        512         4096
        byte[] data = dmxChannelData;

        //Preparing the byte array
        byte[] byteToSend = new byte[530];
        for (int i = 0; i < id.length; i++) {
            byteToSend[i] = id[i];
        }
        byteToSend[8] = opCode[0];
        byteToSend[9] = opCode[1]; 
        byteToSend[10] = protVerHi;
        byteToSend[11] = protVerLow;
        byteToSend[12] = sequence;
        byteToSend[13] = physical;
        byteToSend[14] = subUni;
        byteToSend[15] = pNet;
        byteToSend[16] = lengthHi;
        byteToSend[17] = length;
        for (int i = 18; i < byteToSend.length; i++) {
            byteToSend[i] = data[i-18];
        }

        //Sending the packet
        try {
            DatagramPacket dgram = new DatagramPacket(byteToSend, byteToSend.length, InetAddress.getByName(socketIp), 6454);
            socket.send(dgram);
        } catch (Exception e) {
            System.out.println("Exception at packet sending: " + e);
        }
    }

}
## Java ArtNet Dmx Sender

### Constructor:

- `ArtNetLib(String socketIp)`: Constructor with the ip address of the socket.
    ```
    ArtNetLib artnet = new ArtNetLib("xxx.xxx.xxx.xxx");
    ```

### Method:

- `sendArtDmxPacket(byte[] dmxChannelData, byte universe, byte subnet, byte net)`: Send a ArtDmx packet with the dmx data contained on a 512 elements byte array (Elements values from 0 to 255).
  ```
  byte[] data = new byte[512];
  ...
  artnet.sendArtDmxPacket(data, universe, subnet, net);
  ```


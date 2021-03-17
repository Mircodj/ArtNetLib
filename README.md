## Java ArtNet Dmx Sender

### Constructor:

- #### `ArtNet(String socketIp)`: Constructor with the ip address of the socket.
  ##### Code Example:
    ```
    ArtNet artnet = new ArtNet("xxx.xxx.xxx.xxx");
    ```

### Method:

- #### `sendArtDmxPacket(byte[] dmxChannelData)`: Send a ArtDmx packet with the dmx data contained on a 512 elements byte array (Elements values from 0 to 255).

  ##### Code Example:
    ```
    ...
    byte[] data = new byte[512];
    ...
    artnet.sendArtDmxPacket(data);
    ...
    ```


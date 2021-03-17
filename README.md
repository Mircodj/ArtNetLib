## Java ArtNet Dmx Sender

### Methods:

- `ArtNet(String socketIp)`: Constructor with the ip address of the socket.
- `sendArtDmxPacket(byte[] dmxChannelData)`: Send a ArtDmx packet with the dmx data contained on a 512 elements byte array (Elements values from 0 to 255).

### Code Example:

```
ArtNet artnet = new ArtNet("xxx.xxx.xxx.xxx");
...
byte[] data = new byte[512];
...
artnet.sendArtDmxPacket(data);
```


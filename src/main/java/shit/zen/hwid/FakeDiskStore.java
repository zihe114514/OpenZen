package shit.zen.hwid;

import java.util.Random;
import java.util.UUID;

public class FakeDiskStore {
    private String fakeSerial;
    private final Random random;

    public FakeDiskStore(Random random) {
        this.random = random;
    }

    public String getSerial() {
        if (this.fakeSerial == null) {
            byte[] randomBytes = new byte[16];
            random.nextBytes(randomBytes);
            randomBytes[6] = (byte)(randomBytes[6] & 15);
            randomBytes[6] = (byte)(randomBytes[6] | 64);
            randomBytes[8] = (byte)(randomBytes[8] & 63);
            randomBytes[8] = (byte)(randomBytes[8] | 128);
            long msb = 0L;
            long lsb = 0L;
            for (int i = 0; i < 8; i++) msb = msb << 8 | (long)(randomBytes[i] & 255);
            for (int i = 8; i < 16; i++) lsb = lsb << 8 | (long)(randomBytes[i] & 255);
            UUID uuid = new UUID(msb, lsb);
            this.fakeSerial = "{" + uuid.toString() + "}";
        }
        return this.fakeSerial;
    }

    public String getName() {
        return "\\\\\\.\\PHYSICALDRIVE3";
    }

    public String getModel() {
        return "Microsoft Storage Space Device";
    }
}

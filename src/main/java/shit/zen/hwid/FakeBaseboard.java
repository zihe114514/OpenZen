package shit.zen.hwid;

import java.util.Random;

public class FakeBaseboard {
    private String fakeSerial;
    private final Random random;

    public FakeBaseboard(Random random) {
        this.random = random;
    }

    public String getManufacturer() {
        return "LENOVO";
    }

    public String getModel() {
        return "unknown";
    }

    public String getVersion() {
        return "SDK0T" + (int)(random.nextDouble() * 60.0 + 760.0) * 100 + " WIN";
    }

    public String getSerialNumber() {
        if (this.fakeSerial == null) {
            StringBuilder sb = new StringBuilder(8);
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            for (int i = 0; i < 8; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            this.fakeSerial = sb.toString();
        }
        return this.fakeSerial;
    }
}

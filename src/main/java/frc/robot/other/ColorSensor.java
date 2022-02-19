package frc.robot.other;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.I2C;

public class ColorSensor {
    I2C sensor;

    protected final static int COMMAND_REGISTER_BIT = 0x80;
    protected final static int MULTI_BYTE_BIT = 0x20;

    protected final static int ENABLE_REGISTER = 0x00;
    protected final static int ATIME_REGISTER = 0x01;
    protected final static int PPULSE_REGISTER = 0x0E;

    protected final static int ID_REGISTER = 0x12;
    protected final static int CDATA_REGISTER = 0x14;
    protected final static int RDATA_REGISTER = 0x16;
    protected final static int GDATA_REGISTER = 0x18;
    protected final static int BDATA_REGISTER = 0x1A;
    protected final static int PDATA_REGISTER = 0x1C;

    public ColorSensor() {
        sensor = new I2C(I2C.Port.kOnboard, 0x39); // port, I2c address

        sensor.write(COMMAND_REGISTER_BIT | 0x00, 0b00000011); // power on, color sensor on
    }

    protected int readWordRegister(int address) {
        ByteBuffer buf = ByteBuffer.allocate(2);
        sensor.read(COMMAND_REGISTER_BIT | MULTI_BYTE_BIT | address, 2, buf);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        return buf.getShort(0);
    }

    public int red() {
        return readWordRegister(RDATA_REGISTER);
    }

    public int green() {
        return readWordRegister(GDATA_REGISTER);
    }

    public int blue() {
        return readWordRegister(BDATA_REGISTER);
    }

    public int clear() {
        return readWordRegister(CDATA_REGISTER);
    }

    public int proximity() {
        return readWordRegister(PDATA_REGISTER);
    }

    public boolean ping() {
        return sensor.addressOnly();
    }

    /**
     * This function returns whether the color is a red in the system
     * @return True if red, false if blue
     */
    public boolean isRedBall() {
        return red() > blue();
    }

    /**
     * This function returns whether the color is a blue ball in the system
     * @return True if blue, false if red
     */
    public boolean isBlueBall() {
        return blue() > red();
    }

    /**
     * This function determines if the ball is in the system using proximity
     * @return True if within proximity, false otherwise
     */
    public boolean hasBall() {
        //todo this proximity really needs playing with to get this working
        return proximity() < 100;
    }
}
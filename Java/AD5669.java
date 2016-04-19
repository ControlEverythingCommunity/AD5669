// Distributed with a free-will license.
// Use it any way you want, profit or free, provided it fits in the licenses of its associated works.
// AD5669
// This code is designed to work with the AD5669_I2CDAC I2C Mini Module available from ControlEverything.com.
// https://www.controleverything.com/content/Digital-Analog?sku=AD5669_I2CDAC#tabs-0-product_tabset-2

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class AD5669
{
	public static void main(String args[]) throws Exception
	{
		// Create I2C bus
		I2CBus Bus = I2CFactory.getInstance(I2CBus.BUS_1);
		// Get I2C device, AD5669 I2C address is 0x56(86)
		I2CDevice device = Bus.getDevice(0x56);

		// Select DAC and input register, 0x2F(47)
		// Select all channels, with input value 0x8000(32768)
		byte[] config = new byte[3];
		config[0] = 0x2F;
		config[1] = (byte)0x80;
		config[2] = 0x00;
		device.write(config, 0, 3);

		// Convert the data
		double voltage = (((config[1] & 0xFF) * 256) + (config[2] & 0xFF)) / 65536.0 * 5.0;
		
		// Output data to screen
		System.out.printf("Voltage : %.2f V %n", voltage);
	}
}
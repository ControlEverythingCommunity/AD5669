# Distributed with a free-will license.
# Use it any way you want, profit or free, provided it fits in the licenses of its associated works.
# AD5669
# This code is designed to work with the AD5669_I2CDAC I2C Mini Module available from ControlEverything.com.
# https://www.controleverything.com/content/Digital-Analog?sku=AD5669_I2CDAC#tabs-0-product_tabset-2

from OmegaExpansion import onionI2C
import time

# Get I2C bus
i2c = onionI2C.OnionI2C()

# AD5669 address, 0x56(86)
# Select DAC and input register, 0x2F(47)
#		0x8000(32768)	Selected all DAC channels
data = [0x80, 0x00]
i2c.writeBytes(0x56, 0x2F, data)

time.sleep(0.5)

# Convert the data
voltage = ((data[0] * 256 + data[1]) / 65536.0) * 5.0

# Output data to screen
print "Voltage : %.2f V" %voltage

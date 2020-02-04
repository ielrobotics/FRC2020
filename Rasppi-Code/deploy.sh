#!/bin/bash
cd ..
tar -ca Rasppi-Code -f rasppi-code-image.tar.gz
scp rasppi-code-image.tar.gz pi@10.80.58.254:/home/pi/rasppi-code-image.tar.gz
ssh pi@10.80.58.254 "rm -rf /home/pi/Rasppi-Code && tar -xf rasppi-code-image.tar.gz"
#!/bin/bash
cd ..
#PI_IP=10.80.58.254
PI_IP=raspberrypi
tar -ca Rasppi-Code -f rasppi-code-image.tar.gz
scp rasppi-code-image.tar.gz pi@${PI_IP}:/home/pi/rasppi-code-image.tar.gz
ssh pi@${PI_IP} "systemctl --user stop image-recognition && rm -rf /home/pi/Rasppi-Code && tar -xf rasppi-code-image.tar.gz && rm rasppi-code-image.tar.gz && sudo mkfifo /home/pi/Rasppi-Code/logfile && sudo chown pi:pi /home/pi/Rasppi-Code/logfile && systemctl --user start image-recognition"
rm rasppi-code-image.tar.gz
ssh pi@${PI_IP} "systemctl --user status image-recognition && journalctl -u image-recognition && cat /home/pi/Rasppi-Code/logfile"
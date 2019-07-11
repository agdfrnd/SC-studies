#!/bin/sh
# these pins use WiringPi's numbering scheme
# they correspond to pins 3,5,7,9,11,13 of PiSound's breakout header
# (resp. orange, yellow, green, blue, purple, grey wires)
for wPin in 11 21 3 2 16 8
do
	gpio mode $wPin out
	gpio write $wPin 0
	[ `gpio read $wPin` != "0" ] && \
		echo "$wPin failed write 0"
	gpio write $wPin 1
	[ `gpio read $wPin` != "1" ] && \
		echo "$wPin failed write 1"
	gpio write $wPin 0
done
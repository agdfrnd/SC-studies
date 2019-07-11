#!/bin/sh
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
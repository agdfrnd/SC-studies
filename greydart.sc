s.quit;
s.options.memSize_(65536 * 4);
s.boot;

"weh".postln;

// ???
(
var freqLag = 1.0, slopeLag = 4.0, greymix = 0.8;

w = SynthDef(\whackapitch, {
	var trig1, trig2, in, freq, hasFreq, laggedFreq, notched, grey, out;
	var freqSlope, slowSlope=0, rq;

	//in = SoundIn.ar(0);
	trig1 = Dust.kr(0.4);
	trig2 = Dust.kr(0.4);
	in = LFCub.ar(TIRand.kr(1,16,trig1)*27.5 + LFPar.kr(10,0,2), 0, 0.25) + LFCub.ar(TIRand.kr(1,16,trig2)*27.5*(6/5) + LFCub.kr(10,0,2), 0, 0.25);

	# freq, hasFreq = Tartini.kr(in);

	laggedFreq = Lag.kr(freq, freqLag);
	freqSlope = abs(Slope.kr(laggedFreq));
	slowSlope = Slew.kr(Lag.kr(freqSlope, slopeLag)*1e-3, 1e3, 1);

	rq = 16*(1-min(1,slowSlope));

	notched = BRF.ar(in, laggedFreq, rq);

	laggedFreq.poll;
	slowSlope.poll;

	grey = Greyhole.ar(RHPF.ar(notched, 80, 2), sqrt(2), 0.05, 2.4, 0.5, 0.95, 0.2, 4);
	out = (1-greymix)*notched + greymix*grey;
//	Out.ar(0, [in,out]);
	Out.ar(0, out);

}).play(s);
)
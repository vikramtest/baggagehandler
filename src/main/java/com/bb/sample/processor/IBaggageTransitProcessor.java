package com.bb.sample.processor;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IBaggageTransitProcessor {

	public void processPrintBaggageInfo()
		throws IOException,FileNotFoundException;
		
}

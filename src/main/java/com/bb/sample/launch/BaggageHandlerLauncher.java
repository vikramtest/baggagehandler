package com.bb.sample.launch;
 
import java.io.IOException;
 
import com.bb.sample.exception.InCorrectFileException;
import com.bb.sample.processor.BaggageTransitProcessor;
import com.bb.sample.processor.IBaggageTransitProcessor;
 
/**
*
 * @author VMittal
* This is the main class.
* This class will trigger the Load of the Config Data and the Baggage Processor.
*/
public class BaggageHandlerLauncher {
 
                public static void main(String[] args)
                {
                               
                                BaggageHandlerMaster handlerFramework = new BaggageHandlerMaster();
                               
                                try
                                {
                                                handlerFramework.loadBaggageProperties();
                                                handlerFramework.loadConfigData();
                                }
                                catch(IOException ioException)
                                {
                                                System.out.println("Exception "+ioException.getMessage());
                                                System.exit(-1);
                                }
                                catch(InCorrectFileException fileException)
                                {
                                                System.out.println("Exception"+fileException.getMessage());
                                                System.exit(-1);
                                }
                               
                               
                                IBaggageTransitProcessor processor = new BaggageTransitProcessor(handlerFramework);
 
                                try
                                {
                                               
                                                processor.processPrintBaggageInfo();
                                               
                                }catch(IOException ioException)
                                {
                                                System.out.println("Exception "+ioException.getMessage());
                                                System.exit(-1);
                                }
                }
 
}
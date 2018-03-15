This program solves the Problem 1 in the document.
The build tool which is used is maven.

The main method is in class BaggageHandlerLauncher.java.
This loads the baggageproperties which has names for the 3 CSV Files.
conveyorsystem.csv contains the connection information from One Belt to another.
departureinfo.csv contains the flight departure info alongith the belt that feeds it.
bagsInfo.csv is the route each bag needs to take from One Point to another.

The program first loads the conveyorsystem.csv and departureInfo.csv and builds a HashMap
in its memory which has the connection details for every Node to every Other Node.
The logic for this code is in BaggageHandlerMaster.java.

Once this is complete then the bagsInfo.csv is read to see which bags needs to move and from 
which node to which node. This is done by BaggageTransitProcessor.java.
This methods also prints the route information and time taken.

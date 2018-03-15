package com.bb.sample.launch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bb.sample.constant.BaggageHandlerConstant;
import com.bb.sample.dao.CSVFileLoader;
import com.bb.sample.exception.InCorrectFileException;
import com.bb.sample.model.BaggageConnection;
import com.bb.sample.model.BaggageNode;
import com.bb.sample.model.DepartureNode;

/**
 * This class does the work of Loading the Data and building Node to Node paths.
 * 
 * @author VMittal
 *
 */
public class BaggageHandlerMaster {

	/**
	 * This attribute contains the conveyer File Name.
	 */
	String conveyerFileName;

	/**
	 * This attribute contains the departureFileName.
	 */
	String departureFileName;

	/**
	 * This attribute contains the baggageFileName.
	 */
	String baggageFileName;

	/**
	 * This Field contains the initial connection List.After the complete node to
	 * node list is populated in newCollectionLink this is merged into
	 * collectionLink.
	 */
	Map<BaggageConnection, BaggageConnection> collectionLink = new HashMap<BaggageConnection, BaggageConnection>();

	/**
	 * This field contains the Node->Node path which was created based on Config
	 * data.
	 */
	Map<BaggageConnection, BaggageConnection> newCollectionLink = new HashMap<BaggageConnection, BaggageConnection>();

	/**
	 * This field contains the Departure Node List.
	 */
	Map<DepartureNode, DepartureNode> departureNodeList = new HashMap<DepartureNode, DepartureNode>();

	/**
	 * This field contain unique List of all the BaggageNodes.
	 */
	Map<BaggageNode, BaggageNode> setOfNodes = new HashMap<BaggageNode, BaggageNode>();

	public Map<BaggageNode, BaggageNode> getSetOfNodes() {
		return setOfNodes;
	}

	public void setSetOfNodes(Map<BaggageNode, BaggageNode> setOfNodes) {
		this.setOfNodes = setOfNodes;
	}

	public Map<BaggageConnection, BaggageConnection> getCollectionLink() {
		return collectionLink;
	}

	public void setCollectionLink(HashMap<BaggageConnection, BaggageConnection> collectionLink) {
		this.collectionLink = collectionLink;
	}

	public Map<DepartureNode, DepartureNode> getDepartureNodeList() {
		return departureNodeList;
	}

	public void setDepartureNodeList(Map<DepartureNode, DepartureNode> departureNodeList) {
		this.departureNodeList = departureNodeList;
	}

	public void loadBaggageProperties() throws IOException {

		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader()
				.getResourceAsStream(BaggageHandlerConstant.PROPERTIES_FILE_NAME);

		// load a properties file
		prop.load(input);
		conveyerFileName = prop.getProperty(BaggageHandlerConstant.CONVEYOR_SYSTEM_FILE);
		departureFileName = prop.getProperty(BaggageHandlerConstant.DEPARTURE_SYSTEM_FILE);
		baggageFileName = prop.getProperty(BaggageHandlerConstant.BAGGAGE_SYSTEM_FILE);
	}

	public void loadConfigData() throws InCorrectFileException, IOException, FileNotFoundException {
		CSVFileLoader csvFileLoader = new CSVFileLoader();
		List<String[]> conveyorFileData = csvFileLoader.readCSVFile(conveyerFileName);
		buildInitialTransitList(conveyorFileData);
		buildBaggageLink();
		// Build a Single Map
		collectionLink.putAll(newCollectionLink);

		List<String[]> departureFileData = csvFileLoader.readCSVFile(departureFileName);
		loadDepartureData(departureFileData);

	}

	public List<String[]> loadBaggageInfo() throws IOException, FileNotFoundException {
		CSVFileLoader csvFileLoader = new CSVFileLoader();
		List<String[]> baggageFileData = csvFileLoader.readCSVFile(baggageFileName);

		return baggageFileData;
	}

	/**
	 * This method loads the Departure Information
	 * 
	 * @param departureFileData
	 */
	public void loadDepartureData(List<String[]> departureFileData) throws InCorrectFileException {
		for (String[] rowData : departureFileData) {
			if (rowData.length != 4)
				throw new InCorrectFileException("Incorrect Data in File DepartureInfo:Record" + rowData);

			DepartureNode deptNode = new DepartureNode();
			deptNode.setFlightNumber(rowData[0]);

			BaggageNode flightNode = new BaggageNode(rowData[1]);

			deptNode.setFlightNode(flightNode);
			deptNode.setFlightCode(rowData[2]);
			deptNode.setDepartureTime(rowData[3]);

			departureNodeList.put(deptNode, deptNode);
		}
	}

	/**
	 * This method builds the initial bidirectional transit List. This is used
	 * further to derive all the Hops from One Node to Another.
	 * 
	 * @param conveyorFileData
	 */

	public void buildInitialTransitList(List<String[]> conveyorFileData) throws InCorrectFileException {

		for (String[] rowData : conveyorFileData) {
			if (rowData.length != 3)
				throw new InCorrectFileException("Incorrect Data in File DepartureInfo:Record ID" + rowData[0]);

			BaggageNode baggageNodeA1 = new BaggageNode(rowData[0]);
			BaggageNode baggageNodeA2 = new BaggageNode(rowData[1]);

			BaggageNode firstNode = setOfNodes.put(baggageNodeA1, baggageNodeA1);
			if (firstNode == null)
				firstNode = baggageNodeA1;

			BaggageNode secondNode = setOfNodes.put(baggageNodeA2, baggageNodeA2);
			if (secondNode == null)
				secondNode = baggageNodeA2;

			BaggageConnection bgConnection = new BaggageConnection(firstNode, secondNode, Integer.parseInt(rowData[2]));
			collectionLink.put(bgConnection, bgConnection);

			// Reverse Connection also since it is BiDirectional
			BaggageConnection bgConnection1 = new BaggageConnection(secondNode, firstNode,
					Integer.parseInt(rowData[2]));
			// BaggageConnectionLink link1 = new BaggageConnectionLink(firstNode,null);
			collectionLink.put(bgConnection1, bgConnection1);
		}
	}

	/**
	 * This is method which runs through the initial list of build the Node -> Node
	 * path.
	 * 
	 * @return
	 */
	BaggageNode buildBaggageLink() {

		for (BaggageConnection gbConn : collectionLink.keySet()) {
			checkForLinks(gbConn);
		}

		return null;
	}

	/**
	 * This is a recursive function to derive Node-> Node hops.
	 * 
	 * @param inputConnection
	 */
	@SuppressWarnings("unchecked")
	void checkForLinks(BaggageConnection inputConnection) {
		for (BaggageConnection innerConn : collectionLink.keySet()) {
			if (inputConnection == innerConn || (inputConnection.getStartNode().equals(innerConn.getEndNode())
					&& inputConnection.getEndNode().equals(innerConn.getStartNode())))
				continue;
			if (!inputConnection.getEndNode().getNodeNumber().equals(innerConn.getStartNode().getNodeNumber()))
				continue;
			if (inputConnection.getEndNode().equals(innerConn.getStartNode())
					&& !inputConnection.getIntermediateNodes().contains(innerConn.getEndNode())) {
				// Create new Node.
				BaggageConnection newConnection = new BaggageConnection(inputConnection.getStartNode(),
						innerConn.getEndNode(),
						inputConnection.getTotalTransitTime() + innerConn.getTotalTransitTime());
				List<BaggageNode> copy = (List<BaggageNode>) ((LinkedList<BaggageNode>) inputConnection
						.getIntermediateNodes()).clone();

				copy.add(inputConnection.getEndNode());
				newConnection.setIntermediateNodes(copy);

				newCollectionLink.put(newConnection, newConnection);
				//System.out.println(newConnection);
				checkForLinks(newConnection);
			}

		}
	}

}
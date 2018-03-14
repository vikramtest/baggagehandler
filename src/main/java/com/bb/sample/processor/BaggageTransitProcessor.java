package com.bb.sample.processor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.bb.sample.constant.BaggageHandlerConstant;
import com.bb.sample.launch.BaggageHandlerMaster;
import com.bb.sample.model.BaggageConnection;
import com.bb.sample.model.BaggageNode;
import com.bb.sample.model.DepartureNode;
import com.bb.sample.utils.BaggageHandlerUtils;

public class BaggageTransitProcessor implements IBaggageTransitProcessor {

	/**
	 * This stores the BaggageHandler Object.
	 */
	BaggageHandlerMaster handlerFramework;

	public BaggageTransitProcessor(BaggageHandlerMaster inpHandlerFramework) {
		handlerFramework = inpHandlerFramework;
	}

	/**
	 *
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void processPrintBaggageInfo() throws IOException, FileNotFoundException {
		List<String[]> baggageMovementInfo = handlerFramework.loadBaggageInfo();

		for (String[] rowData : baggageMovementInfo) {
			String baggageTagNum = rowData[0];
			BaggageNode startNode = new BaggageNode(rowData[1]);
			DepartureNode endNode = new DepartureNode(rowData[2]);

			// Get the BaggageNode for Departure Node
			BaggageNode departureNode = getDepartureBaggageNode(endNode);

			BaggageConnection baggageTransitInfo = new BaggageConnection();
			baggageTransitInfo.setStartNode(startNode);
			baggageTransitInfo.setEndNode(departureNode);

			// Get the Route Between Nodes
			getRouteBetweenNodes(baggageTagNum, baggageTransitInfo);
		}

	}

	BaggageNode getDepartureBaggageNode(DepartureNode endNode) {
		BaggageNode departureNode = null;
		// get the BaggageNode for the Departure Node
		DepartureNode endBaggageNode = handlerFramework.getDepartureNodeList().get(endNode);
		if (endBaggageNode == null) {
			// Check if it is an Arrival. Arrival is not a Fligh Id . So if treated
			// separately.
			if (endNode.getFlightNumber().equals(BaggageHandlerConstant.ARRIVAL_CONSTANT)) {
				departureNode = new BaggageNode(BaggageHandlerConstant.BAGGAGE_CONSTANT);
			} else
				System.out.println("No End Node Info Found for " + endNode.getFlightNumber());
		} else
			departureNode = endBaggageNode.getFlightNode();

		return departureNode;
	}

	BaggageConnection getRouteBetweenNodes(String baggageTagNum, BaggageConnection baggageTransitInfo) {
		Map<BaggageConnection, BaggageConnection> connectionList = handlerFramework.getCollectionLink();
		BaggageConnection transitPath = connectionList.get(baggageTransitInfo);
		if (transitPath == null) {
			System.out.println("No Route Found For Bag" + baggageTagNum);
			return null;
		} else {
			BaggageHandlerUtils.printTransitInfo(baggageTagNum, transitPath);
			return transitPath;
		}

	}

	public BaggageHandlerMaster getHandlerFramework() {
		return handlerFramework;
	}

	public void setHandlerFramework(BaggageHandlerMaster handlerFramework) {
		this.handlerFramework = handlerFramework;
	}

}

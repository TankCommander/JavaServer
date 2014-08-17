package gameManagement.gameObjects.implementations;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sharedObjects.gameObjects.interfaces.FlightPath;
import sharedObjects.gameObjects.interfaces.Hit;
import sharedObjects.gameObjects.interfaces.Player;
import sharedObjects.gameObjects.interfaces.Point;
import sharedObjects.gameObjects.interfaces.TimePoint;

public class FlightPathImpl implements FlightPath {

	private static final long serialVersionUID = -1834458441451973315L;
	private ArrayList<TimePoint> timePoints = new ArrayList<TimePoint>();
	private ArrayList<Hit> hits = new ArrayList<Hit>();
	private Player origin;

	public FlightPathImpl(Player origin) {
		this.origin = origin;
	}

	public FlightPathImpl(Player origin, TimePoint startPoint) {
		this(origin);
		this.timePoints.add(startPoint);
	}

	@Override
	public ArrayList<TimePoint> getTimePoints() {
		return timePoints;
	}
	@Override
	public void setTimePoints(ArrayList<TimePoint> timePoints) {
		this.timePoints = timePoints;
	}

	@Override
	public ArrayList<Hit> getHits() {
		return hits;
	}
	@Override
	public void setHits(ArrayList<Hit> hits) {
		this.hits = hits;
	}

	@Override
	public Player getOrigin() {
		return origin;
	}
	@Override
	public void setOrigin(Player origin) {
		this.origin = origin;
	}

	@Override
	public Point getStartPoint() throws RemoteException {
		if (this.origin != null)
			return this.origin.getPosition();
		else
			return null;
	}

	@Override
	public void setHit(Hit hit) throws RemoteException {
		boolean add = false;
		
	    for (Hit lHit: hits) {
			if (lHit.getT() > hit.getT()){
				add = true;
	            this.hits.remove(lHit);
			} else if (lHit.getT() == hit.getT()) {
				add = true;
			};
	    }
	    
	    if (add || this.hits.isEmpty())
	    	this.hits.add(hit);		
	}

}

package sharedObjects.gameObjects.implementations;

import gameManagement.Calculation;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sharedObjects.gameObjects.interfaces.GameMap;
import sharedObjects.gameObjects.interfaces.Point;

public class GameMapImpl implements GameMap {

	private static final long serialVersionUID = 3548478649677504938L;
	private ArrayList<Point> horizonSkeleton;
	private ArrayList<Point> horizonLine;
	
	public GameMapImpl(ArrayList<Point> horizonSkeleton) throws RemoteException {
		this.horizonSkeleton = horizonSkeleton;
		this.horizonLine = getNewHorizonLine(horizonSkeleton);
	}
       		
	private ArrayList<Point> getNewHorizonLine(ArrayList<Point> horizonSkeleton) throws RemoteException{

		ArrayList<Point> result = new ArrayList<Point>();
		
        Point start_point = horizonSkeleton.get(0);
        
        for (int i=1; i<horizonSkeleton.size(); i++){
        	Point point = horizonSkeleton.get(i);
        
            for (int x=(int)start_point.getX(); x<=point.getX(); x++){
                result.add(Calculation.interpolate_point(x, start_point, point));
            }
            start_point = point;
            }
            
        result.add(start_point); // letzten Punkt noch extra einfügen

        return result;
        }
	
	@Override
	public ArrayList<Point> getHorizonLine() throws RemoteException {
		return this.horizonLine;
	}

	@Override
	public ArrayList<Point> getHorizonSkeleton() throws RemoteException {
		return this.horizonSkeleton;
	}

	@Override
	public double getHorizonY_Value(int x) throws RemoteException {
		return horizonLine.get(x).getY();
	}

}

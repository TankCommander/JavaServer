package gameManagement;

import gameManagement.gameObjects.implementations.GameMapImpl;
import gameManagement.gameObjects.implementations.PointImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

import sharedObjects.gameObjects.interfaces.GameMap;
import sharedObjects.gameObjects.interfaces.Player;
import sharedObjects.gameObjects.interfaces.Point;

class PointComparator implements Comparator<Point>{

	@Override
	public int compare(Point arg0, Point arg1) {
		int result = 0;
		try {
			
			result = (int)Math.signum(arg0.getX() - arg1.getX());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}

class MatchBuilder {
	static Random random = new Random();

	public static ArrayList<Point> getNewHorizonSkeleton() throws RemoteException{
		
		// TODO: random.seed() ?

		int world_width = Consts.WORLD_WIDTH;
		
		ArrayList<Point> points = new ArrayList<Point>();
        int MAX_LOOPS = 9000;

        int points_count = Consts.MIN_SAMPLING_POINTS + 
        		random.nextInt(Consts.MAX_SAMPLING_POINTS + 1 - Consts.MIN_SAMPLING_POINTS); //Ergebnis von nextInt ist excl. ob. Grenze
        int num_loops = 0;

//        # Anfang und Ende setzen - inkonsistent, bad smell!
        points.add(new PointImpl(0, 
        		Consts.MIN_HORIZON_HEIGHT + random.nextInt(Consts.MAX_HORIZON_HEIGHT + 1 - Consts.MIN_HORIZON_HEIGHT)));
        points.add(new PointImpl(world_width - 1,
        		Consts.MIN_HORIZON_HEIGHT + random.nextInt(Consts.MAX_HORIZON_HEIGHT + 1 - Consts.MIN_HORIZON_HEIGHT)));

        int max_x = world_width - 2;

        while (points.size() < points_count && num_loops < MAX_LOOPS){
        	
	        num_loops += 1;
	        points.add(new PointImpl(random.nextInt(max_x-1)+1,
	                      random.nextInt(Consts.MAX_HORIZON_HEIGHT-Consts.MIN_HORIZON_HEIGHT)+Consts.MIN_HORIZON_HEIGHT));
	
	        Collections.sort(points, new PointComparator());
	
	        Point start_point = null;

	        Point point;
	        
	        for (int i = points.size()-1; i>=0; i--){
	        	if (start_point == null){
	        		start_point = points.get(i);
	        	} else {
	        		point = points.get(i);
		            if (point.getX() == start_point.getX()){
		                points.remove(point);
		            } else {
		                double c = Math.abs(Calculation.derivation(start_point, point));
		                
		                if (c > Consts.MAX_DERIVATION){
		                	
		                    if (point.getX() == 0) //: #Startpunkt nicht l�schen, sondern den vorherigen
		                        points.remove(start_point);
		                    else
		                        points.remove(point);
		                    
		                } else {
		                    start_point = point;
		                }
		                
		            }
	        	}
	        }	
        }
        
////        #Testmap / Random wieder aktivieren
//        points.addAll(Arrays.asList(new PointImpl(0,0), new PointImpl(100,100), new PointImpl(500,100), new PointImpl(world_width-1, 0)));

        return points;
	}        		

	public static GameMap getNewGameMap() throws RemoteException{
		return new GameMapImpl(MatchBuilder.getNewHorizonSkeleton());		
	}
	
	public static Dictionary<Player,Integer> get_new_player_x_positions(ArrayList<Player> players){
		
		int world_width = Consts.WORLD_WIDTH;
		Dictionary<Player,Integer>  result = new Hashtable<Player,Integer>(players.size());

//        # etwas �bertrieben kompliziert, evtl. k�nnen mehr als 2 Player spielen?
        double parts_width = (double)world_width / (players.size() + 1);
        for (int i=0; i<players.size(); i++){
            if (i < players.size() / 2) {
            	
            result.put(players.get(i), (int) Math.max(Math.round(i * parts_width + Consts.PLAYER_RADIUS +
                    ((parts_width-2* Consts.PLAYER_RADIUS) * random.nextDouble()))-1,0));
            } else {
                result.put(players.get(i), (int) Math.max(Math.round((i + 1) * parts_width + Consts.PLAYER_RADIUS +
                    ((parts_width-2* Consts.PLAYER_RADIUS ) * random.nextDouble()))-1,0));
            };
        };
        
//        TODO: testen
        return result;
	}

	public static ArrayList<Player> getShuffledPlayers(ArrayList<Player> players){
//	    player_order = range(len(players))
		ArrayList<Player> result = new ArrayList<Player>(players.size());
		
		ArrayList<Player> tempPlayers = new ArrayList<Player>(players.size());
		tempPlayers.addAll(players);
		
		for (int i=tempPlayers.size(); i>0; i--){
			// [0..i) wird ausgelost
			int index = random.nextInt(i);
			result.add(tempPlayers.remove(index));
		}
		return result;
	}

	public static Dictionary<Player, Point> getNewPlayerPositions(GameMap map, ArrayList<Player> players) throws RemoteException {
		
		Dictionary<Player, Point> result = new Hashtable<Player, Point>(players.size());
		Dictionary<Player,Integer> xPositions = get_new_player_x_positions(players);
		
		for (Player player : players) {
			int x = xPositions.get(player);
			result.put(player, new PointImpl(x, map.getHorizonY_Value(x)+Consts.PLAYER_RADIUS));
		};
		return result;
	}

	
}

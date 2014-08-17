package gameManagement;

import gameManagement.gameObjects.implementations.GameMapImpl;
import gameManagement.gameObjects.implementations.PointImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

import sharedObjects.gameObjects.interfaces.GameMap;
import sharedObjects.gameObjects.interfaces.Player;
import sharedObjects.gameObjects.interfaces.Point;

class MatchBuilder {
	static Random random = new Random();

	public static ArrayList<Point> getNewHorizonSkeleton(){
		
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

//        int max_x = world_width - 2;

        while (points.size() < points_count && num_loops < MAX_LOOPS){
        	/// TODO: Horizont erstellen
        	break;
        	
//            num_loops += 1;
//            points.append(Point(MatchBuilder.f_random.randint(1, max_x),
//                          MatchBuilder.f_random.randint(Consts.MIN_HORIZON_HEIGHT, Consts.MAX_HORIZON_HEIGHT)))
//
//            points.sort(key= lambda point: point.x)
//
//            start_point = points[0]
//            is_valid = True
//            for point in points[1::]:
//                if point.x == start_point.x:
//                    points.remove(point)
//                else:
//                    c = abs(Calculation.derivation(start_point, point))
//                    if c > Consts.MAX_DERIVATION:
//                        if point.x > (max_x): #Endpunkt nicht l�schen ?!
//                            points.remove(start_point)
//                        else:
//                            points.remove(point)
//                        # is_valid = False
//                        # MatchBuilder.f_random.seed()
//                        # break
//                    else:
//                        start_point = point
//
        }
        
//        #TODO Random wieder aktivieren
        points.addAll(Arrays.asList(new PointImpl(0,0), new PointImpl(100,100), new PointImpl(500,100), new PointImpl(world_width-1, 0)));

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

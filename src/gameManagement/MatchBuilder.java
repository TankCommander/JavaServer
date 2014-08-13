package gameManagement;

import sharedObjects.gameObjects.implementations.PointImplementation;
import sharedObjects.gameObjects.interfaces.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
        points.add(new PointImplementation(0, 
        		Consts.MIN_HORIZON_HEIGHT + random.nextInt(Consts.MAX_HORIZON_HEIGHT + 1 - Consts.MIN_HORIZON_HEIGHT)));
        points.add(new PointImplementation(world_width - 1,
        		Consts.MIN_HORIZON_HEIGHT + random.nextInt(Consts.MAX_HORIZON_HEIGHT + 1 - Consts.MIN_HORIZON_HEIGHT)));

        int max_x = world_width - 2;

        while (points.size() < points_count && num_loops < MAX_LOOPS){
        	/// TODO...
            num_loops += 1;
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
        points.addAll(Arrays.asList(new PointImplementation(0,0), new PointImplementation(100,100), new PointImplementation(500,100), new PointImplementation(world_width-1, 0)));

        return points;
	}        		

}
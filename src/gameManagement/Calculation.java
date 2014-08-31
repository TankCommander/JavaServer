package gameManagement;

import gameManagement.gameObjects.implementations.FlightPathImpl;
import gameManagement.gameObjects.implementations.HitImpl;
import gameManagement.gameObjects.implementations.PointImpl;
import gameManagement.gameObjects.implementations.TimePointImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;




import sharedObjects.gameObjects.interfaces.FlightPath;
import sharedObjects.gameObjects.interfaces.Hit;
import sharedObjects.gameObjects.interfaces.Player;
import sharedObjects.gameObjects.interfaces.Point;
import sharedObjects.gameObjects.interfaces.TimePoint;

public class Calculation {

	private ArrayList<Point> horizonLine;
	private int worldWidth;
	private ArrayList<Player> players;
	private Dictionary<Player, Point> playerPositions;
	
	public Calculation(int worldWidth, ArrayList<Point> horizonLine,
			ArrayList<Player> players, Dictionary<Player, Point> playerPositions) {

		this.horizonLine = horizonLine;
		this.worldWidth = worldWidth;
		this.players = players;
		this.playerPositions = playerPositions; 
	}
  
	public static double DegreeToRadiant(double degree){
		return degree / 180 * Math.PI;
	}
	
	public static double derivation(Point point_0, Point point_1) throws RemoteException{
    		
    	double result = 0;
        double diff_x = point_1.getX() - point_0.getX();
        if (diff_x != 0)
            result = (double)(point_1.getY() - point_0.getY()) / diff_x;

        return result;
    }
    
    public static Point interpolate_point(double x, Point point_0, Point point_1) throws RemoteException{
        return new PointImpl(x, (point_0.getY() + (x - point_0.getX()) * Calculation.derivation(point_0, point_1)));
    }
    
    public FlightPath calc_flugbahn(Player source, double angle, double speed) throws RemoteException{

        source.setAngle(angle); // f�r Client zur Anzeige des Gesch�tzwinkels

        FlightPath flugbahn = this.__calc_flugbahn(source, angle, speed);

        for (Player player: this.players) {
            if (!player.equalsPlayer(source)){
                // Selbstabschuss nicht zulassen, macht einige Probleme
                Hit hit = __calc_target_hit(flugbahn, player);
                if (hit != null)
                	flugbahn.setHit(hit);
            };
        };
        
        for (Hit hit : flugbahn.getHits())
                hit.getTarget().addDamage(hit.getPercent());

		//redu
		ArrayList<TimePoint> reducedPoints = new ArrayList<TimePoint>(flugbahn.getTimePoints().size()/10);
		for (int i=0; i<flugbahn.getTimePoints().size(); i += 10){
			reducedPoints.add(flugbahn.getTimePoints().get(i));
		}
		flugbahn.setTimePoints(reducedPoints);

        // Flugbahn k�rzen, endet beim ersten Treffer!
        if (!flugbahn.getHits().isEmpty()){
            for (int i=0; i<flugbahn.getTimePoints().size()-1; i++){
            	// einzeln durchgehen, zwischendurch k�nnen welche fehlen...
                if (flugbahn.getTimePoints().get(i).getT() > flugbahn.getHits().get(0).getT()){
                    flugbahn.setTimePoints(new ArrayList<TimePoint>(flugbahn.getTimePoints().subList(0, i-1)));
                    break;
                }
            }
        }
        		
        return flugbahn;
    };
    

    private boolean __point_is_in_world(Point point) throws RemoteException{
        return point.getY() > 0 && __x_is_in_world((int)(Math.round(point.getX())));
    };

    private boolean __x_is_in_world(int x_pos){
        return 0 <= x_pos && x_pos < worldWidth;
    };
    
    private boolean __is_horizon_hit(TimePoint bullet_pos) throws RemoteException{
//        # Horizonttreffer am Rand des Geschosses oder in doch erst in der Mitte ?
//        # TODO: Horizonth�he berechnen / interpolieren?
    	
        int pos_x = (int)(Math.round(bullet_pos.getX()));
        
//        # �berpr�fung ist eigentlich redundant
        if (__x_is_in_world(pos_x))
            return bullet_pos.getY() <= horizonLine.get(pos_x).getY();
        else
            return false;

//        oder bereits, wenn der Rand auftrifft?
//        # return bullet_pos.y - Consts.BULLET_RADIUS < self.__horizon[int(round(bullet_pos.x))]
    }
    
    private boolean __is_out_of_radius(Point origin_pos, Point point) throws RemoteException{
        return Consts.PLAYER_RADIUS < Math.hypot(origin_pos.getX() - point.getX(), origin_pos.getY() - point.getY());
    }

    private double __calc_target_hit_percent(Point target_pos, Point bullet_pos) throws RemoteException{
//        # k�rzesten Abstand zwischen Ziel und Geschossmittelpunkt mit Pytagoras ermitteln
        double distanceValue = Math.hypot(target_pos.getX() - bullet_pos.getX(), target_pos.getY() - bullet_pos.getY());

        int distanceRadii = Consts.PLAYER_RADIUS + Consts.BULLET_RADIUS;
        double overlap = distanceRadii - distanceValue;

        // �berlappung > 0 = Treffer
        if (overlap > 0){
//            # �berdeckung der Kreisradien als Ma� f�r Treffer-% ermitteln
//            # ggf. Fl�cheninhalt der �berdeckung als genaueres Trefferma� berechnen
//            # R�ckgabe %-genau
            return (double) Math.round(100*(double)(overlap) / distanceRadii) / 100;
        } else {
            return 0;
        }
    }

    private Hit __calc_target_hit(FlightPath flugbahn, Player target) throws RemoteException{
        Hit result = new HitImpl(0,0,0,0,target);

        Point temp_target_pos = playerPositions.get(target);
        Point target_pos = new PointImpl(temp_target_pos.getX(), temp_target_pos.getY());

        // Rechteck um Ziel festlegen
        Point topLeft = new PointImpl(target_pos.getX() - Consts.PLAYER_RADIUS, target_pos.getY() + Consts.PLAYER_RADIUS);
        Point bottomRight = new PointImpl(target_pos.getX() + Consts.PLAYER_RADIUS, target_pos.getY() - Consts.PLAYER_RADIUS);
        
        for (TimePoint point : flugbahn.getTimePoints()){
            // zuerst grob pr�fen, ob Zielrechteck getroffen wurde
            if (topLeft.getX() < point.getX() && point.getX() <= bottomRight.getX() &&
                point.getY() <= topLeft.getY() && point.getY() >= bottomRight.getY()){
                // X und Y des Geschosses im Zielrechteck, Treffer anhand der Umkreise genauer pr�fen
                // Maximalwert zur�ckgeben
                double hitPercent = __calc_target_hit_percent(target_pos, point);
                if (result.getPercent() < hitPercent){
                    result.setX(point.getX());
                    result.setY(point.getY());
                    result.setT(point.getT());
                    result.setPercent(hitPercent);
                }
            }
        }
        
        if (result.getPercent() > 0)
            return result;
        else
            return null;
    }
    
    private FlightPath __calc_flugbahn(Player source, double angle, double speed) throws RemoteException{

        Point temp_source_pos = source.getPosition();
        Point source_pos = new PointImpl(temp_source_pos.getX(), temp_source_pos.getY());
    	FlightPath result = new FlightPathImpl(source, new TimePointImpl(source_pos.getX(), source_pos.getY(), 0));
        
        double t = Consts.TIME_RESOLUTION;
        TimePoint point = __calc_pos(t, source_pos, angle, speed);
        result.getTimePoints().add(point);

        while (__point_is_in_world(point) && !__is_horizon_hit(point)){
                t += Consts.TIME_RESOLUTION;
                point = __calc_pos(t, source_pos, angle, speed);
                if (point.getY() <= Consts.WORLD_HEIGHT + Consts.BULLET_RADIUS &&
                    __is_out_of_radius(source_pos, point)){ // darf / muss drüber gehen
                    result.getTimePoints().add(point);
                }
        }

        return result;
    }

    TimePoint __calc_pos(double t, Point source_pos, double angle, double speed) throws RemoteException{
//        # ohne Ber�cksichtigung Luftwiderstand
//        #v_x0=cos(phi)*v_0
//        #v_y0=sin(phi)*v_0
//        #s_x=v_x0 * t
//        #s_y=v_y0*t-0.5*g*t^2
//        #->
//        #s_x = cos(phi)*v_0*t
//        #s_y = sin(phi) * v_0 * t -0.5* g * t^2
        return new TimePointImpl(source_pos.getX() + Math.cos(angle) * speed * t,
                     source_pos.getY() + Math.sin(angle) * speed * t -0.5 * Consts.g * Math.pow(t,2), t);
    }
}

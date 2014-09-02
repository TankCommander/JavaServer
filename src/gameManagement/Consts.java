package gameManagement;

public class Consts {
	public static final int POINTS_PER_REFRESH = 8;
    public static final long REFRESH_RATE_MS = 40;
    
	public static final int PLAYER_RADIUS = 10;
    public static final int BULLET_RADIUS = 3;
    public static final int  WORLD_WIDTH = 600;
    public static final int WORLD_HEIGHT = 400;

    public static final int MIN_HORIZON_HEIGHT = 0;
    public static final int MAX_HORIZON_HEIGHT = 380;
    public static final int MIN_SAMPLING_POINTS = 10; // # minimale Anzahl Stuetzstellen f. Horizont
    public static final int MAX_SAMPLING_POINTS = 30; // # maximale Anzahl Stuetzstellen f. Horizont
    public static final int MAX_DERIVATION = 1; 		 // max. H�he der Ableitung bzw. Steigung des Horizonts

    public static final double TIME_RESOLUTION = 0.0009;
    public static final int DIVIDER_CLIENT_DATA = 10;
//    # 0.001  reicht fuer 99,47% Treffer bei senkrechtem Schuss
//    # 0.0009 reicht fuer 99,63% Treffer bei senkrechtem Schuss

    public static final double g = 9.81;

//    #Constants for Client State handling
    public static final int CONNECTED = 0;
    public static final int WAITFORPLAYER = 1;
    public static final int GAMERUNNING = 2;
    public static final int GAMEFINSIHED = 3;
	public static final Long TIME_OUT_TIME = 5000l;
    
//    #Constants for Server commands
//    ##############################
    
//    #incoming
//    LOGON = "Logon"
//    FIRE = "Fire"
//    PLAYERNAME = "name"
//    ANGLE = "angle"
//    POWER = "power"
//    
//    #outgoing
//    WAITFORPLAYERMESSAGE = "WaitForPlayer"
//    PLAYERAVAILABLE = "PlayerAvailable"
//    GAMEDATA = "GameData"
//    PLAYER = "Player"
//    PLAYER1 = "Player1"
//    PLAYER2 = "Player2"
//    PLAYERBEGINS = "PlayerBegins"
//    FIRED = "Fired"
//    MAPHORIZON = "MapHorizon"
//    WRONGTURN = "WrongTurn"
//    CONNECTIONLOST = "PlayerLostConnection"
}

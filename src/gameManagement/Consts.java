package gameManagement;

public class Consts {
    public static int PLAYER_RADIUS = 10;
    public static int BULLET_RADIUS = 3;
    public static int  WORLD_WIDTH = 600;
    public static int WORLD_HEIGHT = 400;

    public static int MIN_HORIZON_HEIGHT = 0;
    public static int MAX_HORIZON_HEIGHT = 380;
    public static int MIN_SAMPLING_POINTS = 10; // # minimale Anzahl Stuetzstellen f. Horizont
    public static int MAX_SAMPLING_POINTS = 30; // # maximale Anzahl Stuetzstellen f. Horizont
    public static int MAX_DERIVATION = 1; 		 // max. Höhe der Ableitung bzw. Steigung des Horizonts

    public static double TIME_RESOLUTION = 0.0009;
    public static int DIVIDER_CLIENT_DATA = 10;
//    # 0.001  reicht fuer 99,47% Treffer bei senkrechtem Schuss
//    # 0.0009 reicht fuer 99,63% Treffer bei senkrechtem Schuss

    public static double g = 9.81;

//    #Constants for Client State handling
    public static int CONNECTED = 0;
    public static int WAITFORPLAYER = 1;
    public static int GAMERUNNING = 2;
    public static int GAMEFINSIHED = 3;
    
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

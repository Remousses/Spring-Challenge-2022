package coding.game;

class Opponent extends Entity {
    
    public Opponent(int id, int type, int x, int y, int shieldLife, int isControlled, int health, int vx, int vy,
			int nearBase, int threatFor) {
		super(id, type, x, y, shieldLife, isControlled, health, vx, vy, nearBase, threatFor);
	}

}
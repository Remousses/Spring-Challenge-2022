package coding.game;

class Monster extends Entity {
    
    public Monster(int id, int type, int x, int y, int shieldLife, int isControlled, int health, int vx, int vy,
			int nearBase, int threatFor) {
		super(id, type, x, y, shieldLife, isControlled, health, vx, vy, nearBase, threatFor);
	}

}

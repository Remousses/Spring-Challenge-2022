package coding.game;

class Heroe extends Entity {
    int number;

    public Heroe() {
        super();
    }

	public Heroe(int id, int type, int x, int y, int shieldLife, int isControlled, int health, int vx, int vy,
			int nearBase, int threatFor) {
		super(id, type, x, y, shieldLife, isControlled, health, vx, vy, nearBase, threatFor);
	}

	public Heroe(Entity entity) {
        super(entity.id, entity.type, entity.x, entity.y, entity.shieldLife, entity.isControlled, entity.health, entity.vx, entity.vy, entity.nearBase, entity.threatFor);
	}

	public Heroe(Entity entity, int number) {
        super(entity.id, entity.type, entity.x, entity.y, entity.shieldLife, entity.isControlled, entity.health, entity.vx, entity.vy, entity.nearBase, entity.threatFor);
        this.number = number;
    }
    
    @Override
    public String toString() {
        return super.toString() + " number " + number;
    }
}
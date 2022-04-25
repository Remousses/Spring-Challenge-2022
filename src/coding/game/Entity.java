package coding.game;

class Entity {
    int id; // Unique identifier
    int type; // 0=monster, 1=your hero, 2=opponent hero
    public int x; // Position of this entity
    public int y;
    int shieldLife; // Ignore for this league; Count down until shield spell fades
    int isControlled; // Ignore for this league; Equals 1 when this entity is under a control spell
    int health; // Remaining health of this monster
    int vx; // Trajectory of this monster
    int vy;
    int nearBase; // 0=monster with no target yet, 1=monster targeting a base
    int threatFor;

    public Entity() {
        super();
    }

    public Entity(int id, int x, int y) {
    	this.id = id;
        this.x = x;
        this.y = y;
    }

    public Entity(int id, int type, int x, int y, int shieldLife, int isControlled,
                     int health, int vx, int vy, int nearBase, int threatFor) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.shieldLife = shieldLife;
        this.isControlled = isControlled;
        this.health = health;
        this.vx = vx;
        this.vy = vy;
        this.nearBase = nearBase;
        this.threatFor = threatFor;
    }

    @Override
    public String toString() {
        return "{\n" +
        "id : " + id + "\n" +
        "type : " + type + "\n" +
        "x : " + x + "\n" +
        "y : " + y + "\n" +
        "shieldLife : " + shieldLife + "\n" +
        "isControlled : " + isControlled + "\n" +
        "health : " + health + "\n" +
        "vx : " + vx + "\n" +
        "vy : " + vy + "\n" +
        "nearBase : " + nearBase + "\n" +
        "threatFor : " + threatFor +
        "\n}";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
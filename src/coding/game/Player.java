package coding.game;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    static final int TYPE_MONSTER = 0;
    static final int TYPE_MY_HERO = 1;
    static final int TYPE_OP_HERO = 2;
    static final int initMapX = 17630;
    static final int initMapY = 9000;
    static final double maxMapBaseRight = initMapX / 1.8;
    static final double maxMapBaseLeft = initMapX / 2.5;
    static final double halfMapY = initMapY / 2;
    static boolean baseAtRight;
    static int baseX; 
    static int baseY;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        baseX = in.nextInt(); // The corner of the map representing your base
        baseY = in.nextInt();
        baseAtRight = baseX == initMapX;
        logErr(baseX);
        logErr(baseY);
        int heroesPerPlayer = in.nextInt(); // Always 3

        // game loop
        while (true) {
            logErr("maxRight " + maxMapBaseRight);
            logErr("maxLeft " + maxMapBaseLeft);
            int myHealth = in.nextInt(); // Your base health
            int myMana = in.nextInt(); // Ignore in the first league; Spend ten mana to cast a spell
            int oppHealth = in.nextInt();
            int oppMana = in.nextInt();
            int entityCount = in.nextInt(); // Amount of heros and monsters you can see
            
            List<Entity> heroes = new ArrayList<>();
            List<Entity> oppHeroes = new ArrayList<>();
            List<Entity> monsters = new ArrayList<>();
            List<Entity> threats = new ArrayList<>();
            //Map<Monster, Heroe> threats = new HashMap<>();
            AtomicInteger currentHeroe = new AtomicInteger(0);
            for (int i = 0; i < entityCount; i++) {
                int id = in.nextInt(); // Unique identifier
                int type = in.nextInt(); // 0=monster, 1=your hero, 2=opponent hero
                int x = in.nextInt(); // Position of this entity
                int y = in.nextInt();
                int shieldLife = in.nextInt(); // Ignore for this league; Count down until shield spell fades
                int isControlled = in.nextInt(); // Ignore for this league; Equals 1 when this entity is under a control spell
                int health = in.nextInt(); // Remaining health of this monster
                int vx = in.nextInt(); // Trajectory of this monster
                int vy = in.nextInt();
                int nearBase = in.nextInt(); // 0=monster with no target yet, 1=monster targeting a base
                int threatFor = in.nextInt(); // Given this monster's trajectory, is it a threat to 1=your base, 2=your opponent's base, 0=neither

                Entity entity = new Entity(id, type, x, y, shieldLife, isControlled,
                                           health, vx, vy, nearBase, threatFor);
        
                addEntitites(entity, heroes, oppHeroes, monsters, threats, currentHeroe);
            }

            handleThreats(threats, heroes);
            
            handleMove(heroes, monsters);
        }
    }

    private static void addEntitites(Entity entity, List<Entity> heroes, List<Entity> oppHeroes,
            List<Entity> monsters, List<Entity> threats, AtomicInteger currentHeroe) {
        switch (entity.type) {
            case TYPE_MONSTER:
                if (baseAtRight) {
//                logErr("monster id " + entity.id);
//                logErr("monster x " + entity.x);
//                logErr("monster y " + entity.y);
                    if (entity.x >= maxMapBaseRight) {
                        monsters.add(entity);
                    }
                } else {
                    if (entity.x <= maxMapBaseLeft) {
                        monsters.add(entity);
                    }
                }
                
                if (entity.nearBase == 1 && entity.threatFor == 1) {
                    threats.add(entity);
                }
                break;
            case TYPE_MY_HERO:
                heroes.add(new Heroe(entity, currentHeroe.getAndAdd(1)));
                break;
            case TYPE_OP_HERO:
                oppHeroes.add(entity);
                break;
        }
	}

	private static void handleThreats(List<Entity> threats, List<Entity> heroes) {
        // find the enemy nearest to the base
		// if new monster autofocus ... should keep the nearest monster
		// sort by x y
		threats.sort((o1, o2) -> {
//			double tempDistance = Math.sqrt(Math.pow(o1.x - baseX, 2) + Math.pow(o1.y - baseY, 2));
            Integer value1 = (Integer )o1.x + o1.y;
            Integer value2 = (Integer )o2.x + o2.y;
            return value1.compareTo(value2);
//            if (tempDistance > 0) {
//    			System.out.println("if");
//                return -1;
////                nearEntity = entity;
//            } else {
//    			System.out.println("else");
//            	return 0;
//            }
		});
        for (Entity threat : threats) {
            logErr("threat id " + threat.id);
            logErr("threat x " + threat.x);
            logErr("threat y " + threat.y);
            Entity nearestHeroe = findNearestEntity(baseX, baseY, heroes);
            if (nearestHeroe != null) {
                move(threat.x, threat.y);
                heroes.remove(nearestHeroe);
            }
        }
	}

	private static void handleMove(List<Entity> heroes, List<Entity> monsters) {
        for (Entity heroe : heroes) {
            Entity nearestMonster = findNearestEntity(heroe.x, heroe.y, monsters);
            // In the first league: MOVE <x> <y> | WAIT; In later leagues: | SPELL <spellParams>;
            
            if (nearestMonster == null) {
                if (baseAtRight) {
                    firstMoveBaseRight((Heroe) heroe);
                } else {
                    firstMoveBaseLeft((Heroe) heroe);
                }
            } else {
                move(nearestMonster.x, nearestMonster.y);
            }
            // Prendre des decisions en fonction de la direction des monstres vx et vy
        }
    }

    private static Entity findNearestEntity(int x, int y, List<Entity> entities) {
        double minDistance = Integer.MAX_VALUE;
        Entity nearEntity = null;
        for (Entity entity : entities) {
            double tempDistance = Math.sqrt(Math.pow(x - entity.x, 2) + Math.pow(y - entity.y, 2));
            if (tempDistance < minDistance) {
                minDistance = tempDistance;
                nearEntity = entity;
            }
        }
        return nearEntity;
	}

	private static void firstMoveBaseRight(Heroe heroe) {
        if (heroe.number == 0) {
            move(14000,2500);
        } else if (heroe.number == 1) {
            move(12000, 5500);
        } else if (heroe.number ==  2) {
            move(11000, 7800);
        }
	}

	private static void firstMoveBaseLeft(Heroe heroe) {
        if (heroe.number == 0) {
            move(1100, 6300);
        } else if (heroe.number == 1) {
            move(4000, 3500);
        } else if (heroe.number ==  2) {
            move(5600, 1000);
        }
	}

	private static void logErr(Object msg) {
        System.err.println(msg);
    }

    private static void move(int x, int y) {
        System.out.println("MOVE " + x + " " + y);
    }
}

import java.util.List;

public class Pokemon {
	private int id;
	private String name;
	private String type1;
	private String type2;
	private int attack;
	private int defense;
	private int spAttack;
	private int spDefense;
	private List<Move> moves;
		
	public Pokemon(int id, String name, String type1, String type2, int attack, int defense, int spAttack, int spDefense, List<Move> moves) {
		this.id = id;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.attack = attack;
		this.defense = defense;
		this.spAttack = spAttack;
		this.spDefense = spDefense;
		this.moves = moves;
	}

	public double attack(Move move, Pokemon opponent) {
		int attackStat = move.isPhysical() ? this.attack : this.spAttack;
        int defenseStat = move.isPhysical() ? opponent.defense() : opponent.spDefense();

        double stab = 1.0;
        if (move.moveType().equalsIgnoreCase(this.type1) || move.moveType().equalsIgnoreCase(this.type2)) {
            stab = 1.5;
        }

		double effectiveness = 1.0;
		for (int i = 0; i < move.effectivenessList().size(); i++) {
			if (move.effectivenessList().get(i).equalsIgnoreCase(opponent.type1()) || move.effectivenessList().get(i).equalsIgnoreCase(opponent.type2())) {
				effectiveness = 2;
				break;
			}
		}
		for (int i = 0; i < move.uneffectivenessList().size(); i++) {
			if (move.uneffectivenessList().get(i).equalsIgnoreCase(opponent.type1()) || move.uneffectivenessList().get(i).equalsIgnoreCase(opponent.type2())) {
				effectiveness = 0.5;
				break;
			}
		}	

        double rand = 0.60 + Math.random() * 0.40;

        double base = (((2 / 5.0 + 2) * move.movePower() * attackStat / (double) defenseStat) / 50) + 2;
        double totalDamage = base * stab * effectiveness * rand;

        System.out.printf(this.name + " hit " + opponent.name() + " for " + totalDamage + " damage");
		
		return totalDamage;
	}
	
	public int id() {
		return this.id;
	}

	public String name() {
		return this.name;
	}

	public String type1() {
		return this.type1;
	}

	public String type2() {
		return this.type2;
	}

	public int attack() {
		return this.attack;
	}

	public int defense() {
		return this.defense;
	}

	public int spAttack() {
		return this.spAttack;
	}

	public int spDefense() {
		return this.spDefense;
	}

	public List<Move> moves() {
		return this.moves;
	}
}

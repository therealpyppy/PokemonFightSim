import java.util.List;

public class Move {
	private String name;
	private int id;
	private int movePower;
	private String moveType;
	private boolean isPhysical;
	private List<String> effectivenessList;
	private List<String> uneffectivenessList;

	public Move(int movePower, String moveType, boolean isPhysical, List<String> effectivenessList, List<String> uneffectivenessList, String name, int id) {
		this.movePower = movePower;
		this.moveType = moveType;
		this.isPhysical = isPhysical;
		this.effectivenessList = effectivenessList;
		this.uneffectivenessList = uneffectivenessList;
		this.name = name;
		this.id = id;
	}

	public boolean isPhysical() {
		return this.isPhysical;
	}

	public String moveType() {
		return this.moveType;
	}

	public int movePower() {
		return this.movePower;
	}
	
	public List<String> effectivenessList() {
		return this.effectivenessList;
	}

	public List<String> uneffectivenessList() {
		return this.uneffectivenessList;
	}

	public String name() {
		return this.name;
	}
	
	public int id() {
		return this.id;
	}
}

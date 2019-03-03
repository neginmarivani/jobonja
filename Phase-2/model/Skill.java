public class Skill {
	String name;
	int point;

	public Skill(String name, int point) {
		this.name = name;
		this.point = point;
	}

	public Skill() {
	}

	@Override
	public String toString() {
		return "Skill{" + "name='" + name + '\'' + ", point=" + point + '}';
	}

	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}

	public void addPoint() {
		point++;
	}

}
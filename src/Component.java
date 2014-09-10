public enum Component {
	FUNCTION_1("function1"), FUNCTION_2("function2");

	/**
	 * LAST_SEEN_ENEMY_X(0), LAST_SEEN_ENEMY_Y(0), LAST_SEEN_ENEMY_HEADING(0),
	 * LAST_SEEN_ENEMY_DISTANCE(0), ROBOT_HEADING(0), ROBOT_HEALTH(0),
	 * CURRENT_TIME(0),
	 * 
	 * AHEAD(1), BACK(1), TURN_RIGHT(1), TURN_LEFT(1), STOP(1), RESUME(1),
	 * EXECUTE(1);
	 */

	private final String name;

	Component(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	};
}
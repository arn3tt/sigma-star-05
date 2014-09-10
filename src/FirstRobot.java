
public class FirstRobot extends BaseRobot {

	@Override
	public void run() {
		super.run();
		while (true) {
			setTurnLeft(360);
			execute();
		}
	}

}

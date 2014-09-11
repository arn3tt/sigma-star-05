package robot;

import robocode.ScannedRobotEvent;

public class FirstRobot extends BaseRobot {

	@Override
	public void run() {
		super.run();
		while (true) {
			setTurnLeft(360);
			execute();
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		setAhead(fireIfPositive(ifGreater(acos(cos(subtract(fireIfPositive(ifPositive(subtract(getEnemyVelocity(e), sin(opposite(getEnemyEnergy(e)))), abs(acos(sin(getRandomNumber()))), getEnemyDistance(e))), acos(fireIfPositive(getEnemyHeading(e)))))), acos(sin(asin(opposite(add(divide(ifGreater(multiply(getEnemyHeading(e), getEnemyDistance(e)), sin(getEnemyEnergy(e)), add(getEnemyVelocity(e), getEnemyEnergy(e)), getEnemyVelocity(e)), ifGreater(divide(getEnemyHeading(e), getEnemyEnergy(e)), getEnemyBearing(e), getEnemyEnergy(e), sin(getEnemyHeading(e)))), opposite(getEnemyHeading(e))))))), sin(ifPositive(getEnemyDistance(e), ifPositive(subtract(multiply(opposite(ifPositive(getEnemyVelocity(e), acos(getEnemyDistance(e)), getRandomNumber())), ifPositive(getEnemyHeading(e), getEnemyHeading(e), asin(ifPositive(getRandomNumber(), getEnemyDistance(e), getEnemyEnergy(e))))), ifPositive(acos(getRandomNumber()), getEnemyEnergy(e), subtract(sin(cos(getEnemyHeading(e))), getEnemyDistance(e)))), subtract(getEnemyHeading(e), abs(multiply(subtract(ifGreater(getEnemyBearing(e), getRandomNumber(), getEnemyVelocity(e), getEnemyVelocity(e)), acos(getEnemyBearing(e))), acos(ifPositive(getRandomNumber(), getRandomNumber(), getEnemyDistance(e)))))), getEnemyVelocity(e)), acos(getEnemyBearing(e)))), ifGreater(abs(getEnemyVelocity(e)), add(add(ifGreater(acos(ifPositive(ifPositive(sin(getRandomNumber()), subtract(getEnemyVelocity(e), getEnemyEnergy(e)), ifGreater(getEnemyDistance(e), getEnemyHeading(e), getEnemyVelocity(e), getEnemyDistance(e))), getEnemyBearing(e), ifGreater(divide(getEnemyDistance(e), getEnemyEnergy(e)), sin(getEnemyEnergy(e)), divide(getEnemyVelocity(e), getEnemyVelocity(e)), getEnemyHeading(e)))), opposite(getEnemyVelocity(e)), cos(opposite(asin(sin(getEnemyDistance(e))))), getEnemyBearing(e)), subtract(abs(abs(abs(ifPositive(getRandomNumber(), getEnemyBearing(e), getRandomNumber())))), getEnemyBearing(e))), getEnemyHeading(e)), subtract(getEnemyBearing(e), ifGreater(cos(ifPositive(getEnemyHeading(e), multiply(acos(getEnemyBearing(e)), getEnemyDistance(e)), opposite(sin(opposite(getEnemyDistance(e)))))), getEnemyBearing(e), cos(ifGreater(cos(getEnemyDistance(e)), getEnemyBearing(e), getEnemyVelocity(e), abs(sin(divide(getEnemyHeading(e), getEnemyEnergy(e)))))), add(acos(asin(cos(ifPositive(getEnemyEnergy(e), getEnemyVelocity(e), getEnemyEnergy(e))))), getEnemyEnergy(e)))), asin(divide(opposite(cos(abs(getEnemyBearing(e)))), fireIfPositive(fireIfPositive(acos(getEnemyBearing(e))))))))));
	}
}

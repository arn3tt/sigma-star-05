package robot;
import java.util.Random;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class BaseRobot extends AdvancedRobot {

	private Random generator;

	/**
	 * Returns a random number between -1 and 1.
	 * 
	 * @return
	 */
	protected double getRandomNumber() {
		return generator.nextDouble() * 2 - 1;
	}

	/**
	 * If x is positive, executes a fire command with x being the power and
	 * returns 1; otherwise, does nothing and returns 0
	 */
	protected double fireIfPositive(double x) {
		if (x < 0) {
			return 0;
		} else {
			setFire(x);
			execute();
			return 1;
		}
	}

	// TERMINAL
	protected double getEnemyBearing(ScannedRobotEvent e) {
		return e.getBearing();
	}

	// TERMINAL
	protected double getEnemyDistance(ScannedRobotEvent e) {
		return e.getDistance();
	}

	// TERMINAL
	protected double getEnemyVelocity(ScannedRobotEvent e) {
		return e.getVelocity();
	}

	// TERMINAL
	protected double getEnemyHeading(ScannedRobotEvent e) {
		return e.getHeading();
	}

	// TERMINAL
	protected double getEnemyEnergy(ScannedRobotEvent e) {
		return e.getEnergy();
	}

	protected double abs(double argument) {
		return Math.abs(argument);
	}

	protected double opposite(double argument) {
		return -argument;
	}

	protected double sin(double argument) {
		return Math.sin(argument);
	}

	protected double cos(double argument) {
		return Math.cos(argument);
	}

	protected double asin(double argument) {
		return Math.asin(argument);
	}

	protected double acos(double argument) {
		return Math.acos(argument);
	}

	protected double add(double firstArgument, double secondArgument) {
		return firstArgument + secondArgument;
	}

	protected double subtract(double firstArgument, double secondArgument) {
		return firstArgument - secondArgument;
	}

	protected double multiply(double firstArgument, double secondArgument) {
		return firstArgument * secondArgument;
	}

	protected double divide(double firstArgument, double secondArgument) {
		return firstArgument / secondArgument;
	}
	
	// TODO Change to ifNegative (makes more sense)
	protected double ifPositive(double x, double ifTrue, double ifFalse) {
		return x > 0 ? ifTrue : ifFalse;
	}

	protected double ifGreater(double x, double y, double ifTrue, double ifFalse) {
		return x > y ? ifTrue : ifFalse;
	}

}

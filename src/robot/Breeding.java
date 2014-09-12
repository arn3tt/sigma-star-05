package robot;

import java.util.Arrays;
import java.util.Random;

public class Breeding {

	private static final int ELITISM = 2;
	private static final int MUTATION = (int) Math
			.round(GeneticProgram.POPULATION * 0.01);
	private static final int REPRODUCTION = (int) Math
			.round(GeneticProgram.POPULATION * 0.09);
	private static final int CROSSOVER = GeneticProgram.POPULATION
			- (ELITISM + MUTATION + REPRODUCTION);

	private static Random robotRandomChooser = new Random();

	public static void evolute(GeneticRobot[] robots) {
		boolean[] evoluted = new boolean[robots.length];
		Arrays.sort(robots);
		GeneticRobot robot;
		GeneticRobot robot2;
		int index;
		int index2;

		for (int i = 1; i <= ELITISM; i++) {
			evoluted[robots.length - i] = true;
		}

		for (int i = 0; i <= REPRODUCTION; i++) {
			index = getUnmodifiedRobotIndex(evoluted);
			evoluted[index] = true;
		}

		for (int i = 0; i < MUTATION; i++) {
			index = getUnmodifiedRobotIndex(evoluted);
			robot = robots[index];
			TreeManipulatorTabajara.mutation(robot.getTrees()[0]);
			TreeManipulatorTabajara.mutation(robot.getTrees()[1]);
			TreeManipulatorTabajara.mutation(robot.getTrees()[2]);
			evoluted[index] = true;
		}

		for (int i = 0; i < CROSSOVER; i++) {
			index = getUnmodifiedRobotIndex(evoluted);
			index2 = getUnmodifiedRobotIndex(evoluted);
			while (index == index2) {
				index2 = getUnmodifiedRobotIndex(evoluted);
			}
			robot = robots[index];
			robot2 = robots[index2];
			TreeManipulatorTabajara.crossover(robot.getTrees()[0], robot2.getTrees()[0]);
			TreeManipulatorTabajara.crossover(robot.getTrees()[1], robot2.getTrees()[1]);
			TreeManipulatorTabajara.crossover(robot.getTrees()[2], robot2.getTrees()[2]);
			evoluted[index] = true;
			evoluted[index2] = true;
		}

	}

	private static int getUnmodifiedRobotIndex(boolean[] evoluted) {
		int index = robotRandomChooser.nextInt(evoluted.length);
		while (evoluted[index] == true) {
			index = robotRandomChooser.nextInt(evoluted.length);
		}
		return index;
	}

	
	public static void main(String[] args) {
		GeneticRobot r1 = new GeneticRobot("1", 10, null);
		GeneticRobot r2 = new GeneticRobot("2", 50, null);
		GeneticRobot r3 = new GeneticRobot("3", 70, null);
		GeneticRobot r4 = new GeneticRobot("4", 90, null);
		GeneticRobot r5 = new GeneticRobot("5", 20, null);
		GeneticRobot r6 = new GeneticRobot("6", 40, null);
		GeneticRobot r7 = new GeneticRobot("7", 60, null);
		GeneticRobot r8 = new GeneticRobot("8", 70, null);
		GeneticRobot r9 = new GeneticRobot("9", 30, null);
		GeneticRobot r10 = new GeneticRobot("10", 10, null);
		
		GeneticRobot[] robots = {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10};
		for (GeneticRobot geneticRobot : robots) {
			System.out.print(geneticRobot.getFitness() + " ");
		}
		System.out.println();
		Arrays.sort(robots);
		for (GeneticRobot geneticRobot : robots) {
			System.out.print(geneticRobot.getFitness() + " ");
		}
		
	}
}

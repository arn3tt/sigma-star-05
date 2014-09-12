package battle;

import robocode.BattleResults;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;

/**
 * 
 * @author toxicblu
 * 
 */
public class BattleRunner {

	private RobocodeEngine engine;
	private BattlefieldSpecification battlefield;
	private GeneticBattleObserver battleObserver;

	private final static int BATTLE_HANDICAP = 20; // TODO Adapt this value

	public static final String ROBOCODE_HOME = "/home/arnett/robocode";

	public BattleRunner() {
		RobocodeEngine.setLogMessagesEnabled(false);
		engine = new RobocodeEngine(new java.io.File(ROBOCODE_HOME));
		battleObserver = new GeneticBattleObserver();
		engine.addBattleListener(battleObserver);
		engine.setVisible(true);
		battlefield = new BattlefieldSpecification(800, 600);
	}

	public double[] runRobocode(String bots[], String enemies[], int rounds) {
		double fitnesses[] = new double[bots.length];
		String bot, opponent;
		BattleResults[] results;

		System.out.println("Running battles against sample batch");
		for (int i = 0; i < bots.length; i++) {
			double fitnessScore = 0;
			for (int j = 0; j < enemies.length; j++) {
				bot = bots[i];
				opponent = enemies[j];

				RobotSpecification[] selectedBots = engine
						.getLocalRepository(bot + ", " + opponent);
				BattleSpecification battleSpec = new BattleSpecification(
						rounds, battlefield, selectedBots);
				engine.runBattle(battleSpec, true);

				results = battleObserver.getResults();
				int myBot = (results[0].getTeamLeaderName().equals(bots[i]) ? 0
						: 1);
				int opBot = (myBot == 1 ? 0 : 1);
				int botScore = results[myBot].getScore();

				double totalScore = botScore + results[opBot].getScore();
				double roundFitness = (botScore + BATTLE_HANDICAP)
						/ (totalScore + BATTLE_HANDICAP);

				fitnessScore += roundFitness;
			}
			fitnesses[i] = fitnessScore / enemies.length; // take average of
															// each round score

		}

		return fitnesses;
	}

	public double[] runBatchWithCoevolution(String bots[], int rounds) {
		double fitnesses[] = new double[bots.length];
		return fitnesses;
	}

	public static void main(String[] args) {
		System.setProperty("robocode.home", ROBOCODE_HOME);
		BattleRunner runner = new BattleRunner();
		for (int i = 0; i < 3; i++) {
			double[] results = runner.runRobocode(
					new String[] { "gen.GeneticRobot0*" },
					new String[] { "sample.Crazy" }, 10);
			for (int j = 0; j < results.length; j++) {
				System.out.println(results[j]);
			}
		}
	}
}

class GeneticBattleObserver extends BattleAdaptor {

	robocode.BattleResults[] results;

	public void onBattleCompleted(BattleCompletedEvent e) {
		results = e.getIndexedResults();
	}

	public void onBattleError(BattleErrorEvent e) {
		System.out.println("Error running battle: " + e.getError());
	}

	public BattleResults[] getResults() {
		return results;
	}

}
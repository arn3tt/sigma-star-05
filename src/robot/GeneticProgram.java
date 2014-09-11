package robot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class GeneticProgram {

	private final static String LINE_SEPARATOR = System
			.getProperty("line.separator");
	private static final String GENERATED_ROBOTS_PATH = "src/gen";

	private final static String ROBOTS_NAME_TEMPLATE = GENERATED_ROBOTS_PATH
			+ "/%s.java";
	private static final String ROBOTS_COMMON_NAME = "GeneticRobot";
	private static final String CODE_TEMPLATE_PATH = "template/TemplateRobot.java";
	private static final String CODE_TEMPLATE = generateCommonTemplate();

	private final static int GENERATIONS = 100;
	private final static int POPULATION = 128;

	private static GeneticRobot[] robots = new GeneticRobot[POPULATION];

	public static void main(String[] args) {
		populate();
		clearRobots();
		createRobotJavaClasses();
		compileRobotJavaClasses();
	}

	private static void compileRobotJavaClasses() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnostics, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjectsFromStrings(getAllRobotJavaClassesLocation());

		new File("bin/gen").mkdir();
		Iterable<String> options = Arrays.asList(new String[] { "-d", "bin" });
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
				diagnostics, options, null, compilationUnits);
		boolean success = task.call();
		try {
			fileManager.close();
		} catch (IOException e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}

		if (!success) {
			System.err.println("Robot classes were not compiled successfully");
		}
	}

	private static List<String> getAllRobotJavaClassesLocation() {
		List<String> robotClasses = new ArrayList<String>();
		File genDir = new File(GENERATED_ROBOTS_PATH);
		if (genDir.exists()) {
			for (File geneticRobotClass : genDir.listFiles()) {
				robotClasses.add(GENERATED_ROBOTS_PATH + "/"
						+ geneticRobotClass.getName());
			}
		}
		System.out.println(robotClasses.toString());
		return robotClasses;
	}

	private static void createRobotJavaClasses() {
		new File(GENERATED_ROBOTS_PATH).mkdir();
		for (int i = 0; i < robots.length; i++) {
			createRobotJavaClass(robots[i]);
		}
	}

	private static void createRobotJavaClass(GeneticRobot robot) {
		String path = String.format(ROBOTS_NAME_TEMPLATE, robot.getName());
		FileWriter robotFile;
		try {
			robotFile = new FileWriter(path);
			PrintWriter gravarArq = new PrintWriter(robotFile);

			gravarArq.printf(applyCodeTemplate(robot));
			robotFile.close();
		} catch (IOException e) {
			System.err.println("Unexpected Error: " + e.getMessage());
		}
	}

	private static String applyCodeTemplate(GeneticRobot robot) {
		String[] splittedTemplate = CODE_TEMPLATE.split("#");
		return splittedTemplate[0] + robot.getName() + splittedTemplate[1]
				+ robot.getTrees()[0].composite() + splittedTemplate[2]
				+ robot.getTrees()[1].composite() + splittedTemplate[3]
				+ robot.getTrees()[2].composite() + splittedTemplate[4];
	}

	private static void clearRobots() {
		File genDir = new File(GENERATED_ROBOTS_PATH);
		if (genDir.exists()) {
			for (File geneticRobotClass : genDir.listFiles()) {
				geneticRobotClass.delete();
			}
			genDir.delete();
		}
	}

	private static String generateCommonTemplate() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					CODE_TEMPLATE_PATH));
			String fileContent = "";
			String line = null;
			while ((line = reader.readLine()) != null) {
				fileContent += line;
				fileContent += LINE_SEPARATOR;
			}
			reader.close();
			return fileContent;
		} catch (FileNotFoundException e) {
			System.err.println("File " + CODE_TEMPLATE_PATH + " not found.");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		System.exit(1);
		return null;
	}

	private static void populate() {
		for (int i = 0; i < robots.length; i++) {
			Node[] trees = new Node[GeneticRobot.TREES];
			for (int j = 0; j < trees.length; j++) {
				trees[j] = TreeManipulatorTabajara.generateRandomTree();
			}
			robots[i] = new GeneticRobot(ROBOTS_COMMON_NAME + i, -1, trees);
		}
	}

}

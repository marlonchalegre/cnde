package br.upe.dsc.de;

import br.upe.dsc.de.problem.IProblem;
import br.upe.dsc.de.problem.LayoutProblem;
import br.upe.dsc.de.util.FileManager;
import br.upe.dsc.de.view.PopulationObserver;
import br.upe.dsc.de.view.ViewManager;

public class Main {

	public static void main(String[] args) {
		int populationSize = 300;
		int maximumIterations = 30000;
		double standardDeviation = 0.1;
		double scaleFactor = 0.5;
		double recombinationProbability = 0.8;
		IProblem problem = new LayoutProblem();
		PopulationObserver observer = new PopulationObserver(populationSize, new FileManager(problem.getName()));

		// ViewManager.runText(populationSize, maximumIterations,
		// standardDeviation, scaleFactor,
		// recombinationProbability, problem, observer);

		// ViewManager.runChart(populationSize, maximumIterations,
		// standardDeviation, scaleFactor,
		// recombinationProbability, problem, observer);

		// new ChartLayout(null);

		ViewManager.runChartLayout(populationSize, maximumIterations, standardDeviation, scaleFactor,
			recombinationProbability, problem, observer);
	}
}
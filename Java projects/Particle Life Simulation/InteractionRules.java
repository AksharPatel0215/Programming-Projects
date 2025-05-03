package main;

public class InteractionRules {
private double[][] rules;

public InteractionRules(int numTypes) {
	rules = new double[numTypes][numTypes];
}

void setRule(int typeOne, int typeTwo, double force) {
	rules[typeOne][typeTwo] = force;
}

double getRule(int typeOne, int typeTwo) {
	return rules[typeOne][typeTwo];
}
}

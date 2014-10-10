public class FunctionalityMult {

	protected static int counter;

	public int run(int number) {
		counter++;
		return number * number;
	}

	public int getStaticCounter() {
		return counter;
	}
}

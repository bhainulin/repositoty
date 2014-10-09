import com.epam.classloading.functionality.IFunctionality;


public class Functionality11 extends IFunctionality {
	
	protected static int counter;
	
	@Override
	public int run(int number) {
		counter ++;
		return number + number;
	}
	
	@Override
	public int getStaticCounter() {
		return counter;
	}

}

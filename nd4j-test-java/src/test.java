import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class test {
	public static void main(String args[]){
		int nRows = 3;
		int nColumns = 5;
		INDArray allZeros = Nd4j.zeros(nRows, nColumns);
		System.out.println("Nd4j.zeros(nRows, nColumns)");
		System.out.println(allZeros);
	}
}

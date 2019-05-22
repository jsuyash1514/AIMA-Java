import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class test {
	public static void main(String args[]){

//Here, we'll see how to create INDArrays with different scalar value initializations
		int nRows = 3;
		int nColumns = 5;
		INDArray allZeros = Nd4j.zeros(nRows, nColumns);
		System.out.println("Nd4j.zeros(nRows, nColumns)");
		System.out.println(allZeros);
		
		INDArray allOnes = Nd4j.ones(nRows, nColumns);
		System.out.println("\nNd4j.ones(nRows, nColumns)");
		System.out.println(allOnes);
		
		INDArray allTens = Nd4j.valueArrayOf(nRows, nColumns, 10.0);
		System.out.println("\nNd4j.valueArrayOf(nRows, nColumns, 10.0)");
		System.out.println(allTens);

//We can also create INDArrays from double[] and double[][] (or, float/int etc Java arrays)
		
		double[][] matrixDouble = new double[][]{
				{1.0, 2.0, 3.0},
				{4.0, 5.0, 6.0}};
		INDArray matrix = Nd4j.create(matrixDouble);
		System.out.println("\nINDArray defined from double[][]:");
		System.out.println(matrix);

//It is also possible to create random INDArrays:
		
		int[] shape = new int[]{nRows, nColumns};
		INDArray uniformRandom = Nd4j.rand(shape);
		System.out.println("\n\n\nUniform random array:");
		System.out.println(uniformRandom);

//We can create INDArrays by combining other INDArrays, too:
		
		
		INDArray vStack = Nd4j.vstack(allZeros, allOnes);//Vertical stack:   [1,3]+[1,3] to [2,3]
		INDArray hStack = Nd4j.hstack(allZeros, allOnes);//Horizontal stack: [1,3]+[1,3] to [1,6]
		System.out.println("\n\n\nCreating INDArrays from other INDArrays, using hstack and vstack:");
		System.out.println("vStack:\n" + vStack);
		System.out.println("hStack:\n" + hStack);


//There's some other miscellaneous methods, too:
		
		INDArray identityMatrix = Nd4j.eye(3);
		System.out.println("\n\n\nNd4j.eye(3):\n" + identityMatrix);
		INDArray linspace = Nd4j.linspace(1,10,10);         //Values 1 to 10, in 10 steps
		System.out.println("Nd4j.linspace(1,10,10):\n" + linspace);
	}
}

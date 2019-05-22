import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.split.FileSplit;
import org.datavec.image.loader.NativeImageLoader;
import org.datavec.image.recordreader.ImageRecordReader;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class deepLearning {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Logger log = LoggerFactory.getLogger(deepLearning.class);
//		INDArray array = Nd4j.linspace(-5, +5, 200);
//		INDArray sigmoid = Transforms.sigmoid(array);
//		INDArray relu = Transforms.relu(array);
//		INDArray leakyRelu = Transforms.leakyRelu(array);
//		INDArray elu = Transforms.elu(array);
		
		int seed = 123;
		double learningRate = 0.01;
		int batchSize = 100;
		int numEpochs = 3;
		
		int height = 28;
		int width = 28;
		int numInput = height * width;
		int numHidden = 1000;
		int numOutput = 10;
		
		System.out.println("Building model...");
		MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
				.seed(seed)
				.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
				.updater(Updater.ADAM)
				.list()
				.layer(0, new DenseLayer.Builder()
						.nIn(numInput)
						.nOut(numHidden)
						.activation(Activation.RELU)
						.weightInit(WeightInit.XAVIER)
						.build())
				.layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
						.nIn(numHidden)
						.nOut(numOutput)
						.activation(Activation.SOFTMAX)
						.weightInit(WeightInit.XAVIER)
						.build())
				.setInputType(InputType.convolutional(28, 28, 1))
				.build();
		
		MultiLayerNetwork model = new MultiLayerNetwork(conf);
		model.init();
		model.setListeners(new ScoreIterationListener(10));

		DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, seed);

		DataSetIterator mnistTest = new MnistDataSetIterator(batchSize, false, seed);


//		System.out.println("Training model...");
//
//		model.fit(trainIter, numEpochs);
//
//		System.out.println("Evaluating model...");
//		Evaluation eval = model.evaluate(testIter);
//		System.out.println(eval.stats());
		
		File trainData = new File("../../../mnist_png/training");
		FileSplit trainSplit = new FileSplit(trainData, NativeImageLoader.ALLOWED_FORMATS, new Random(seed));
		ParentPathLabelGenerator labelMaker = new ParentPathLabelGenerator(); // use parent directory name as the image label
		ImageRecordReader trainRR = new ImageRecordReader(height, width, 1, labelMaker);
		trainRR.initialize(trainSplit);
		DataSetIterator trainIter = new RecordReaderDataSetIterator(trainRR, batchSize, 1, numOutput);
		DataNormalization imageScaler = new ImagePreProcessingScaler();
		imageScaler.fit(trainIter);
		trainIter.setPreProcessor(imageScaler);
		
		
		File testData = new File("../../../mnist_png/testing");
		FileSplit testSplit = new FileSplit(testData, NativeImageLoader.ALLOWED_FORMATS, new Random(seed));
		ImageRecordReader testRR = new ImageRecordReader(height, width, 1, labelMaker);
		testRR.initialize(testSplit);
		DataSetIterator testIter = new RecordReaderDataSetIterator(testRR, batchSize, 1, numOutput);
		testIter.setPreProcessor(imageScaler);

		
		for (int i = 0; i < numEpochs; i++) {
			model.fit(trainIter);
			System.out.println("Completed epoch " + i);
			Evaluation eval = model.evaluate(testIter);
			System.out.println(eval.stats(true, false));
			
			trainIter.reset();
			testIter.reset();
		}
		
		System.out.println("********Example finished*********");
		
	}
}

package network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;

import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;


public class NeuralNetworkBuilder {

    private int seed;
    private int numRows, numColumns;

    private static Logger logger;

    private MultiLayerNetwork model;


    public NeuralNetworkBuilder(int seed, int numRows, int numColumns)
    {
        this.seed = seed;
        this.numRows = numRows;
        this.numColumns = numColumns;

        this.logger = LogManager.getLogger(this.getClass().getName());
    }

    public void create()
    {
        int numInputs = numRows * numColumns;
        int numOutputs = numInputs;

        logger.info("Creating the neural network with " + numInputs + " inputs and " + numOutputs + " outputs");

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed) //include a random seed for reproducibility
                // use stochastic gradient descent as an optimization algorithm
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .iterations(1)
                .learningRate(0.006) //specify the learning rate
                .updater(Updater.NESTEROVS)
                .regularization(true).l2(1e-4)
                .list()
                .layer(0, new DenseLayer.Builder() //create the first, input layer with xavier initialization
                        .nIn(numInputs)
                        .nOut(1000)
                        .activation(Activation.RELU)
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD) //create hidden layer
                        .nIn(1000)
                        .nOut(numOutputs)
                        .activation(Activation.SOFTMAX)
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .pretrain(false).backprop(true) //use backpropagation to adjust weights
                .build();

        model = new MultiLayerNetwork(conf);
        model.init();

        logger.info("Model is initiated");
    }

    public INDArray output (double[][] input)
    {
        return model.output(Nd4j.create(input));
    }

}

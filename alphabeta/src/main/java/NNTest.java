import java.util.Vector;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.nnet.learning.CompetitiveLearning;
import org.neuroph.nnet.learning.SigmoidDeltaRule;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;

public class NNTest {
  public static void main(String[] args) {
    // create new perceptron network
    NeuralNetwork neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 2, 3, 1);
    SigmoidDeltaRule rule = new SigmoidDeltaRule();
    rule.setMaxError(0.03);
    rule.setMaxIterations(10000);
    rule.setLearningRate(0.7);
    
    neuralNetwork.setLearningRule(rule);
    neuralNetwork.setInput(new double[] { 1, 1 });
    // calculate network
    neuralNetwork.calculate();
    // get network output
    Vector<Double> networkOutput = neuralNetwork.getOutput();
    System.out.println(networkOutput);

    // create training set
    TrainingSet trainingSet = new TrainingSet();
    // add training data to training set (logical OR function)
    trainingSet.addElement(new SupervisedTrainingElement(new double[] { 0, 0 }, new double[] { 0 }));
    trainingSet.addElement(new SupervisedTrainingElement(new double[] { 0, 1 }, new double[] { 1 }));
    trainingSet.addElement(new SupervisedTrainingElement(new double[] { 1, 0 }, new double[] { 1 }));
    trainingSet.addElement(new SupervisedTrainingElement(new double[] { 1, 1 }, new double[] { 1 }));
    // learn the training set
    neuralNetwork.learnInSameThread(trainingSet);

    neuralNetwork.setInput(new double[] { 1, 1 });
    // calculate network
    neuralNetwork.calculate();
    // get network output
    networkOutput = neuralNetwork.getOutput();
    System.out.println(networkOutput);
    
    neuralNetwork.setInput(new double[] { 0, 0 });
    // calculate network
    neuralNetwork.calculate();
    // get network output
    networkOutput = neuralNetwork.getOutput();
    System.out.println(networkOutput);
  }
}

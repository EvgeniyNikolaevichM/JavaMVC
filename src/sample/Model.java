package sample;

import java.util.Arrays;

public class Model {

    private double[] valuesX;
    private double[] valuesY;


    private double[] calculateFunctionValues(double[] xCoords) {
        double x, y;
        for (int i = 0; i < xCoords.length; i++) {
            x = xCoords[i];
            y = 2 * (x * x) + 3;
            if (valuesY == null) {
                valuesY = new double[1];
                valuesY[i] = y;
            } else {
                valuesY = Arrays.copyOf(valuesY, valuesY.length + 1);
                valuesY[i] = y;
            }

        }
        return valuesY;
    }


    public double calculateFunctionValue(double xCoords) {
        double y;
        y = 2 * (xCoords * xCoords) + 3;
        return y;
    }

    public void addValueToArray(double x) {

        if (valuesX == null) {
            valuesX = new double[1];
            valuesX[0] = x;
            calculateFunctionValues(valuesX);
        } else {
            valuesX = Arrays.copyOf(valuesX, valuesX.length + 1);
            valuesX[valuesX.length - 1] = x;
            calculateFunctionValues(valuesX);
        }

    }

    public void delValueInArray(int index) {
        double[] copyX = new double[valuesX.length - 1];
        System.arraycopy(valuesX, 0, copyX, 0, index);
        System.arraycopy(valuesX, index + 1, copyX, index, valuesX.length - index - 1);
        valuesX = copyX;

        double[] copyY = new double[valuesY.length - 1];
        System.arraycopy(valuesY, 0, copyY, 0, index);
        System.arraycopy(valuesY, index + 1, copyY, index, valuesY.length - index - 1);
        valuesY = copyY;

    }

    public void updValueInArray(int index, double newValue) {
        valuesX[index] = newValue;
        valuesY[index] = calculateFunctionValue(newValue);
    }


    public double[] getValuesX() {
        return valuesX;
    }

    public double[] getValuesY() {
        return valuesY;
    }

    public double getXByIndex(int index) {
        return valuesX[index];
    }

    public double getYByIndex(int index) {
        return valuesY[index];
    }
}
    
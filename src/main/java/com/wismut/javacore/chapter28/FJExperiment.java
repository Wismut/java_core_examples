package main.java.com.wismut.javacore.chapter28;

import java.util.concurrent.RecursiveAction;

class Transform extends RecursiveAction {
    int seqThreshold;
    double[] data;
    int start, end;

    Transform(double[] vals, int s, int e, int t) {
        data = vals;
        start = s;
        end = e;
        seqThreshold = t;
    }

    @Override
    protected void compute() {
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                if ((data[i] % 2) == 0) {
                    data[i] = Math.sqrt(data[i]);
                } else {
                    data[i] = Math.cbrt(data[i]);
                }
            }
        } else {
            int middle = (start + end) / 2;
            invokeAll(new Transform(data, start, middle, seqThreshold),
                    new Transform(data, middle, end, seqThreshold));
        }
    }
}

public class FJExperiment {
    public static void main(String[] args) {
        int pLevel;
        int threshold;
        if (args.length != 2) {
            System.out.println("Usage: FJExperiment parallelism threshold");
            return;
        }
        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);
        long beginT, endT;
    }
}

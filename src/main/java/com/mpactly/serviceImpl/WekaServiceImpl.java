package com.mpactly.serviceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.mpactly.service.WekaService;
import com.mpactly.util.FilePath;
import com.mpactly.vo.GraphVO;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.J48;

import weka.core.Instances;

@Service
public class WekaServiceImpl implements WekaService {
	
	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(FilePath.QOC_TRAIN_DATA_SET));
		Instances dataSet = new Instances(reader);
		System.out.println(dataSet.toSummaryString());
		BufferedReader reader1 = new BufferedReader(new FileReader(FilePath.BREAST_CANCER_TEST_DATA_SET));
		Instances testDataSet = new Instances(reader1);
		String[] options = new String[2];
		options[0]="-additional-stats";
		options[1]="true";
		
		
		
		dataSet.setClassIndex(dataSet.numAttributes()-1);
		testDataSet.setClassIndex(testDataSet.numAttributes()-1);
		
		LinearRegression linearReg = new LinearRegression();
		linearReg.setOptions(new String[]{"-additional-stat","true"});
		int pos = getOptionPos("additional-stats", options);
		System.err.println("POS: "+pos );
		linearReg.getOptions();
		//linearReg.
		for (int i = 0; i < linearReg.getOptions().length; i++) {
			System.err.println(i+" "+linearReg.getOptions()[i]);
			
		}
		linearReg.buildClassifier(dataSet);
		System.err.println("LINEAR REGRESSION-to String");
		//System.out.println(linearReg.toString());
		
		
		
		Evaluation eval =new Evaluation(dataSet);
		eval.evaluateModel(linearReg, dataSet);
		System.err.println("EVALUATIONNNN");
		/*
		 * MEAN
		 */
		System.err.println("MEAN: "+dataSet.meanOrMode(0));
		/*
		 * 
		 * 
		 */
		
		System.err.println(dataSet.attributeStats(0));
		
		/*
		 * stats
		 */
		//System.err.println(dataSet.);
		
		System.out.println(eval.toSummaryString());
		
		GraphVO grvo = new GraphVO();
		grvo.setMean(String.valueOf(dataSet.meanOrMode(0)));
		grvo.setCrc(String.valueOf(eval.correlationCoefficient()));
		grvo.setRqe(String.valueOf(eval.rootMeanSquaredError()));
		
	//	J48 j48=new J48();
		//j48.buildClassifier(dataSet);
//		//System.err.println(j48.classifyInstance(testDataSet.instance(dataSet.numAttributes()-1)));
//		System.err.println(j48.toString());
//		eval.evaluateModel(j48, testDataSet);
//		System.err.println(eval.toSummaryString());
//		System.err.println("RABERR: "+eval.relativeAbsoluteError());
//		
	}

    public static int getOptionPos(String flag, String[] options) {
    	System.err.println("Flag: "+flag);
    	System.err.println("Options: "+options);
        if (options == null) {
            return -1;
        }

        for (int i = 0; i < options.length; i++) {
            if ((options[i].length() > 0) && (options[i].charAt(0) == '-')) {
                // Check if it is a negative number
                try {
                	System.err.println("Checking: "+options[i]);
                    Double.valueOf(options[i]);
                } catch (NumberFormatException e) {
                    // found?
                	System.err.println("Options[i] is: "+options[i] +"flag is "+flag+"Result is :"+options[i].equals("-" + flag));
                    if (options[i].equals("-" + flag)) {
                        return i;
                    }
                    // did we reach "--"?
                    if (options[i].charAt(1) == '-') {
                        return -1;
                    }
                }
            }
        }

        return -1;
    }
	@Override
	public GraphVO analyseData(String filePath) {
		BufferedReader reader;
		Instances dataSet;
		GraphVO grvo = null;
		try {
			//reader = new BufferedReader(new FileReader(FilePath.BREAST_CANCER_TRAIN_DATA_SET));
			reader = new BufferedReader(new FileReader(filePath));
			dataSet = new Instances(reader);
			System.out.println(dataSet.toSummaryString());
			BufferedReader reader1 = new BufferedReader(new FileReader(FilePath.BREAST_CANCER_TEST_DATA_SET));
			Instances testDataSet = new Instances(reader1);
			
			dataSet.setClassIndex(dataSet.numAttributes()-1);
			testDataSet.setClassIndex(testDataSet.numAttributes()-1);
			LinearRegression linearReg = new LinearRegression();
			linearReg.setOptions(new String[]{"-additional-stat","true"});
			linearReg.buildClassifier(dataSet);
			
			Evaluation eval =new Evaluation(dataSet);
			eval.evaluateModel(linearReg, dataSet);
		
			
			System.out.println(eval.toSummaryString());
			grvo = new GraphVO();
			grvo.setMean(String.valueOf(dataSet.meanOrMode(0)));
			grvo.setCrc(String.valueOf(eval.correlationCoefficient()));
			grvo.setRqe(String.valueOf(eval.rootMeanSquaredError()));
			
		
			//System.err.println(j48.classifyInstance(testDataSet.instance(dataSet.numAttributes()-1)));
			
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return grvo;
	}

}

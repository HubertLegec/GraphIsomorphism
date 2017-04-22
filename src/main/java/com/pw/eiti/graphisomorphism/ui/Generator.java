package com.pw.eiti.graphisomorphism.ui;

import com.pw.eiti.graphisomorphism.generator.TestCaseDTO;
import com.pw.eiti.graphisomorphism.generator.TestGraphGenerator;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Generator {
    private static final Logger log = Logger.getLogger(Generator.class);

    public static void main(String[] args) {
        if(args.length < 3) {
            log.error("Specify name of file where test case should be saved, test case size and edge probability.\n" +
                    "Eg. 'test.json 20 0.5' - save to file test.json test case of size 20 with edge probability equals to 0.5");
            return;
        }
        final String path = args[0];
        final int size = Integer.parseInt(args[1]);
        final double probability = Double.parseDouble(args[2]);
        TestCaseDTO dto = TestGraphGenerator.generateTestCase(size, probability);
        try {
            TestGraphGenerator.saveTestCaseToFile(dto, path);
        } catch (IOException e) {
            log.error("Error occurred during test case file saving!");
        }
    }
}

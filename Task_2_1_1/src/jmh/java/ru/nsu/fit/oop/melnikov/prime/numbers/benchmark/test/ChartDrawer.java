package ru.nsu.fit.oop.melnikov.prime.numbers.benchmark.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchart.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartDrawer {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNodeRoot;
        try {
            jsonNodeRoot = mapper.readTree(
                    new File("results.json")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Map<Integer, Double>> charts = new HashMap<>();

        for(JsonNode node : jsonNodeRoot) {
            String test = node.get("benchmark").asText();
            test = test.substring(test.lastIndexOf(".") + 1);
            if(!charts.containsKey(test)) {
                charts.put(test, new HashMap<>());
            }
            charts.get(test).put(node.path("params").get("size").asInt(), node.path("primaryMetric").get("score").asDouble());
        }

        XYChart chart = new XYChartBuilder().title("Benchmark tests").xAxisTitle("array size").yAxisTitle("score, ops/s").build();
        chart.getStyler().setXAxisLogarithmic(true);
        chart.getStyler().setYAxisLogarithmic(true);

        for(String testName : charts.keySet()) {
            List<Integer> sizeData = charts.get(testName).keySet().stream().sorted().toList();
            List<Double> scoreData = sizeData.stream().map(x -> charts.get(testName).get(x)).toList();
            chart.addSeries(testName, sizeData, scoreData);
        }

        try {
            BitmapEncoder.saveBitmapWithDPI(
                    chart,
                    "./Benchmark_tests_result.png",
                    BitmapEncoder.BitmapFormat.PNG,
                    300
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

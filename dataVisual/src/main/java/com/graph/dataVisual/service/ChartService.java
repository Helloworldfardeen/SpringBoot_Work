package com.graph.dataVisual.service;

import com.graph.dataVisual.util.CsvUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import javax.imageio.ImageIO;

@Service
public class ChartService {

    private List<Double> data;

    public void processCsvFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty.");
        }

        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            data = CsvUtils.parseCsv(reader);
        } catch (Exception e) {
            throw new Exception("Error processing CSV file", e);
        }
    }

    public byte[] getChartImage() throws Exception {
        if (data == null || data.isEmpty()) {
            throw new IllegalStateException("No data available to generate the chart.");
        }

        BufferedImage chartImage = createLogGraph(data);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(chartImage, "png", baos);
            baos.flush();
            return baos.toByteArray();
        }
    }

    private BufferedImage createLogGraph(List<Double> data) {
        XYSeries series = new XYSeries("Log Data");
        for (int i = 0; i < data.size(); i++) {
            series.add(i, Math.log(data.get(i)));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Log Graph",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        return chart.createBufferedImage(800, 600);
    }
}

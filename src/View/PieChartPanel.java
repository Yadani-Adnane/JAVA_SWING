package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PieChartPanel extends JPanel {
    private int[] counts;
    private String[] labels;
    private Color[] colors;

    public PieChartPanel(int[] counts, String[] labels, Color[] colors) {
        this.counts = counts;
        this.labels = labels;
        this.colors = colors;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int total = 0;
        for (int count : counts) {
            total += count;
        }

        int startAngle = 0;
        int arcAngle;
        for (int i = 0; i < counts.length; i++) {
            arcAngle = (int) Math.round((double) counts[i] / total * 360);
            g2d.setColor(colors[i]);
            g2d.fillArc(50, 50, 200, 200, startAngle, arcAngle);
            startAngle += arcAngle;
        }

        // Draw legend
        int x = 300;
        int y = 50;
        int lineHeight = 20;
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i < labels.length; i++) {
            g2d.setColor(colors[i]);
            g2d.fillRect(x, y + i * lineHeight, 10, 10);
            g2d.setColor(Color.WHITE);
            g2d.drawString(labels[i] + " (" + counts[i] + ")", x + 20, y + i * lineHeight + 10);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }
}
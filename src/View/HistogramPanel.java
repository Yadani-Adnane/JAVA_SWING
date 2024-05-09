package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class HistogramPanel extends JPanel {
    private Map<String, Integer> universityDistribution;

    public HistogramPanel(Map<String, Integer> universityDistribution) {
        this.universityDistribution = universityDistribution;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int maxCount = 0;
        for (int count : universityDistribution.values()) {
            maxCount = Math.max(maxCount, count);
        }

        int barWidth = 20;
        int barSpacing = 10;
        int x = 50;
        int y = getHeight() - 50;
        int maxHeight = getHeight() - 100;
        g2d.setColor(Color.BLUE);
        for (Map.Entry<String, Integer> entry : universityDistribution.entrySet()) {
            String university = entry.getKey();
            int count = entry.getValue();
            int barHeight = (int) ((double) count / maxCount * maxHeight);
            g2d.fillRect(x, y - barHeight, barWidth, barHeight);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            g2d.drawString(university, x - 10, y + 12);
            x += barWidth + barSpacing;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 400);
    }
}

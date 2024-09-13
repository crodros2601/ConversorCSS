package Aplicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class ConversorCSS {

    private static double anchoPx = 0;
    private static double alturaPx = 0;
    private static final ArrayList<String> historial = new ArrayList<>();

    public static void main(String[] args) {
        // Configuración de Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Conversor de Unidades CSS");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());
            
            frame.setPreferredSize(new Dimension(800, 400));
            frame.setPreferredSize(new Dimension(800, 400));
            frame.setMaximumSize(new Dimension(800, 400));	
            
            frame.setResizable(false);

            // Panel de entrada
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 15, 15, 15);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.5;

            JLabel anchoLabel = new JLabel("Ancho de la pantalla (px):");
            anchoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            anchoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            anchoLabel.setForeground(new Color(60, 60, 60));
            JTextField anchoField = new JTextField(15);
            anchoField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            JLabel alturaLabel = new JLabel("Altura de la pantalla (px):");
            alturaLabel.setHorizontalAlignment(SwingConstants.CENTER);
            alturaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            alturaLabel.setForeground(new Color(60, 60, 60));
            JTextField alturaField = new JTextField(15);
            alturaField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            JButton setDimensionsButton = new JButton("Establecer Dimensiones");
            setDimensionsButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            setDimensionsButton.setBackground(new Color(33, 150, 243));
            setDimensionsButton.setForeground(Color.WHITE);
            setDimensionsButton.setFocusPainted(false);
            setDimensionsButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            inputPanel.add(anchoLabel, gbc);
            gbc.gridx = 1;
            inputPanel.add(anchoField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            inputPanel.add(alturaLabel, gbc);
            gbc.gridx = 1;
            inputPanel.add(alturaField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.CENTER;
            inputPanel.add(setDimensionsButton, gbc);

            // Área de resultados
            JPanel resultPanel = new JPanel(new BorderLayout());
            JLabel resultLabel = new JLabel("Resultado de la conversión", SwingConstants.CENTER);
            resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            resultLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
            resultLabel.setForeground(new Color(33, 150, 243));
            resultPanel.setPreferredSize(new Dimension(resultPanel.getPreferredSize().width, 80));

            // Crear JTextPane y JScrollPane
            JTextPane outputPane = new JTextPane();
            outputPane.setEditable(false);
            outputPane.setContentType("text/html");
            outputPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            JScrollPane outputScrollPane = new JScrollPane(outputPane);
            outputScrollPane.setPreferredSize(new Dimension(750, 150));

            // Panel para centrar el JTextPane
            JPanel centeredOutputPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbcOutput = new GridBagConstraints();
            gbcOutput.insets = new Insets(10, 10, 10, 10);
            gbcOutput.gridx = 0;
            gbcOutput.gridy = 0;
            gbcOutput.fill = GridBagConstraints.BOTH;
            gbcOutput.weightx = 1.0;
            gbcOutput.weighty = 1.0;
            centeredOutputPanel.add(outputScrollPane, gbcOutput);

            resultPanel.add(resultLabel, BorderLayout.NORTH);
            resultPanel.add(centeredOutputPanel, BorderLayout.CENTER);

            // Pestañas
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));

            // Panel de conversión
            JPanel convertPanel = new JPanel();
            convertPanel.setLayout(new GridLayout(0, 2, 10, 10));
            JButton vwToPxButton = new JButton("Convertir vw a px");
            JButton vhToPxButton = new JButton("Convertir vh a px");
            JButton pxToVWButton = new JButton("Convertir px a vw");
            JButton pxToVHButton = new JButton("Convertir px a vh");
            vwToPxButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            vhToPxButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            pxToVWButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            pxToVHButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            vwToPxButton.setBackground(new Color(33, 150, 243));
            vhToPxButton.setBackground(new Color(33, 150, 243));
            pxToVWButton.setBackground(new Color(33, 150, 243));
            pxToVHButton.setBackground(new Color(33, 150, 243));
            vwToPxButton.setForeground(Color.WHITE);
            vhToPxButton.setForeground(Color.WHITE);
            pxToVWButton.setForeground(Color.WHITE);
            pxToVHButton.setForeground(Color.WHITE);
            vwToPxButton.setFocusPainted(false);
            vhToPxButton.setFocusPainted(false);
            pxToVWButton.setFocusPainted(false);
            pxToVHButton.setFocusPainted(false);
            convertPanel.add(vwToPxButton);
            convertPanel.add(vhToPxButton);
            convertPanel.add(pxToVWButton);
            convertPanel.add(pxToVHButton);

            // Panel de porcentajes
            JPanel percentPanel = new JPanel();
            percentPanel.setLayout(new GridLayout(0, 2, 10, 10));
            JButton pxToPercentButton = new JButton("Convertir px a % (2 contenedores)");
            JButton percentToPxButton = new JButton("Convertir % a px (2 contenedores)");
            JButton pxToPercentWidthButton = new JButton("Convertir px a % (ancho pantalla)");
            JButton percentToPxWidthButton = new JButton("Convertir % a px (ancho pantalla)");
            JButton pxToPercentHeightButton = new JButton("Convertir px a % (alto pantalla)");
            JButton percentToPxHeightButton = new JButton("Convertir % a px (alto pantalla)");
            pxToPercentButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            percentToPxButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            pxToPercentWidthButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            percentToPxWidthButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            pxToPercentHeightButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            percentToPxHeightButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            pxToPercentButton.setBackground(new Color(33, 150, 243));
            percentToPxButton.setBackground(new Color(33, 150, 243));
            pxToPercentWidthButton.setBackground(new Color(33, 150, 243));
            percentToPxWidthButton.setBackground(new Color(33, 150, 243));
            pxToPercentHeightButton.setBackground(new Color(33, 150, 243));
            percentToPxHeightButton.setBackground(new Color(33, 150, 243));
            pxToPercentButton.setForeground(Color.WHITE);
            percentToPxButton.setForeground(Color.WHITE);
            pxToPercentWidthButton.setForeground(Color.WHITE);
            percentToPxWidthButton.setForeground(Color.WHITE);
            pxToPercentHeightButton.setForeground(Color.WHITE);
            percentToPxHeightButton.setForeground(Color.WHITE);
            pxToPercentButton.setFocusPainted(false);
            percentToPxButton.setFocusPainted(false);
            pxToPercentWidthButton.setFocusPainted(false);
            percentToPxWidthButton.setFocusPainted(false);
            pxToPercentHeightButton.setFocusPainted(false);
            percentToPxHeightButton.setFocusPainted(false);
            percentPanel.add(pxToPercentButton);
            percentPanel.add(percentToPxButton);
            percentPanel.add(pxToPercentWidthButton);
            percentPanel.add(percentToPxWidthButton);
            percentPanel.add(pxToPercentHeightButton);
            percentPanel.add(percentToPxHeightButton);
            
            // Panel de comprobación de resolución
            JPanel checkResolutionPanel = new JPanel();
            checkResolutionPanel.setLayout(new GridLayout(2, 1));
            JLabel anchoLabelCurrent = new JLabel("Ancho actual: " + anchoPx + " px");
            anchoLabelCurrent.setFont(new Font("Segoe UI", Font.BOLD, 16));
            anchoLabelCurrent.setHorizontalAlignment(SwingConstants.CENTER);
            anchoLabelCurrent.setForeground(new Color(60, 60, 60));
            JLabel alturaLabelCurrent = new JLabel("Altura actual: " + alturaPx + " px");
            alturaLabelCurrent.setFont(new Font("Segoe UI", Font.BOLD, 16));
            alturaLabelCurrent.setHorizontalAlignment(SwingConstants.CENTER);
            alturaLabelCurrent.setForeground(new Color(60, 60, 60));
            checkResolutionPanel.add(anchoLabelCurrent);
            checkResolutionPanel.add(alturaLabelCurrent);

            // Panel de historial
            JPanel historyPanel = new JPanel(new BorderLayout());
            JLabel historyLabel = new JLabel("Historial de Conversiones", SwingConstants.CENTER);
            historyLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            historyLabel.setForeground(new Color(33, 150, 243));
            JTextPane historyPane = new JTextPane();
            historyPane.setEditable(false);
            historyPane.setContentType("text/html");
            historyPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            JScrollPane historyScrollPane = new JScrollPane(historyPane);
            historyPanel.add(historyLabel, BorderLayout.NORTH);
            historyPanel.add(historyScrollPane, BorderLayout.CENTER);

            // Agregar pestañas al panel de pestañas
            tabbedPane.addTab("Convertir", convertPanel);
            tabbedPane.addTab("Porcentaje", percentPanel);
            tabbedPane.addTab("Comprobar Resolución", checkResolutionPanel);
            tabbedPane.addTab("Historial", historyPanel);

            // Agregar componentes al marco
            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.add(resultPanel, BorderLayout.SOUTH);

            // Acciones de los botones
            setDimensionsButton.addActionListener(e -> {
                try {
                    anchoPx = Double.parseDouble(anchoField.getText());
                    alturaPx = Double.parseDouble(alturaField.getText());
                    resultLabel.setText("Dimensiones establecidas.");
                    checkResolutionPanel.removeAll();
                    checkResolutionPanel.add(new JLabel("Ancho actual: " + anchoPx + " px", SwingConstants.CENTER));
                    checkResolutionPanel.add(new JLabel("Altura actual: " + alturaPx + " px", SwingConstants.CENTER));
                    checkResolutionPanel.revalidate();
                    checkResolutionPanel.repaint();
                } catch (NumberFormatException ex) {
                    resultLabel.setText("<html>Por favor, introduzca valores válidos para el ancho y la altura.</html>");
                }
            });

            vwToPxButton.addActionListener(e -> {
                try {
                    double vw = Double.parseDouble(JOptionPane.showInputDialog("Introduce los vw a convertir"));
                    double pxResultado = (anchoPx / 100) * vw;
                    resultLabel.setText("Conversión realizada.");
                    String result = vw + " vw son " + new DecimalFormat("#.##").format(pxResultado) + " px.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca un valor válido para los vw.</div></html>");
                }
            });

            vhToPxButton.addActionListener(e -> {
                try {
                    double vh = Double.parseDouble(JOptionPane.showInputDialog("Introduce los vh a convertir"));
                    double pxResultado = (alturaPx / 100) * vh;
                    resultLabel.setText("Conversión realizada.");
                    String result = vh + " vh son " + new DecimalFormat("#.##").format(pxResultado) + " px.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca un valor válido para los vh.</div></html>");
                }
            });

            pxToVWButton.addActionListener(e -> {
                try {
                    double px = Double.parseDouble(JOptionPane.showInputDialog("Introduce los px a convertir"));
                    double vwResultado = (px / anchoPx) * 100;
                    resultLabel.setText("Conversión realizada.");
                    String result = px + " px son " + new DecimalFormat("#.##").format(vwResultado) + " vw.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca un valor válido para los px.</div></html>");
                }
            });

            pxToVHButton.addActionListener(e -> {
                try {
                    double px = Double.parseDouble(JOptionPane.showInputDialog("Introduce los px a convertir"));
                    double vhResultado = (px / alturaPx) * 100;
                    resultLabel.setText("Conversión realizada.");
                    String result = px + " px son " + new DecimalFormat("#.##").format(vhResultado) + " vh.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca un valor válido para los px.</div></html>");
                }
            });

            pxToPercentButton.addActionListener(e -> {
                try {
                    double elemento = Double.parseDouble(JOptionPane.showInputDialog("Introduce el ancho del elemento en px"));
                    double contenedor = Double.parseDouble(JOptionPane.showInputDialog("Introduce el ancho del contenedor en px"));
                    double pxResultado = (elemento / contenedor) * 100;
                    resultLabel.setText("Conversión realizada.");
                    String result = "El " + elemento + "px representa el " + new DecimalFormat("#.##").format(pxResultado) + "% del contenedor de " + contenedor + "px.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca valores válidos.</div></html>");
                }
            });

            percentToPxButton.addActionListener(e -> {
                try {
                    double porcentaje = Double.parseDouble(JOptionPane.showInputDialog("Introduce el porcentaje"));
                    double contenedor = Double.parseDouble(JOptionPane.showInputDialog("Introduce el ancho del contenedor en px"));
                    double pxResultado = (porcentaje / 100) * contenedor;
                    resultLabel.setText("Conversión realizada.");
                    String result = "El " + porcentaje + "% del contenedor de " + contenedor + "px representa " + new DecimalFormat("#.##").format(pxResultado) + "px.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca valores válidos.</div></html>");
                }
            });

            pxToPercentWidthButton.addActionListener(e -> {
                try {
                    double elemento = Double.parseDouble(JOptionPane.showInputDialog("Introduce el ancho del elemento en px"));
                    double pxResultado = (elemento / anchoPx) * 100;
                    resultLabel.setText("Conversión realizada.");
                    String result = "El ancho de " + elemento + "px representa el " + new DecimalFormat("#.##").format(pxResultado) + "% del ancho actual de " + anchoPx + "px.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca un valor válido para los px.</div></html>");
                }
            });

            percentToPxWidthButton.addActionListener(e -> {
                try {
                    double porcentaje = Double.parseDouble(JOptionPane.showInputDialog("Introduce el porcentaje"));
                    double pxResultado = (porcentaje / 100) * anchoPx;
                    resultLabel.setText("Conversión realizada.");
                    String result = "El " + porcentaje + "% del ancho actual de " + anchoPx + "px representa " + new DecimalFormat("#.##").format(pxResultado) + "px.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca un valor válido.</div></html>");
                }
            });

            pxToPercentHeightButton.addActionListener(e -> {
                try {
                    double elemento = Double.parseDouble(JOptionPane.showInputDialog("Introduce la altura del elemento en px"));
                    double pxResultado = (elemento / alturaPx) * 100;
                    resultLabel.setText("Conversión realizada.");
                    String result = "La altura de " + elemento + "px representa el " + new DecimalFormat("#.##").format(pxResultado) + "% de la altura actual de " + alturaPx + "px.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca un valor válido para los px.</div></html>");
                }
            });

            percentToPxHeightButton.addActionListener(e -> {
                try {
                    double porcentaje = Double.parseDouble(JOptionPane.showInputDialog("Introduce el porcentaje"));
                    double pxResultado = (porcentaje / 100) * alturaPx;
                    resultLabel.setText("Conversión realizada.");
                    String result = "El " + porcentaje + "% de la altura actual de " + alturaPx + "px representa " + new DecimalFormat("#.##").format(pxResultado) + "px.";
                    outputPane.setText("<html><div style='text-align: center;'>" + result + "</div></html>");
                    historial.add(result);
                    historyPane.setText("<html><div style='text-align: center;'>" + String.join("<br>", historial) + "</div></html>");
                } catch (NumberFormatException ex) {
                    outputPane.setText("<html><div style='text-align: center;'>Por favor, introduzca un valor válido.</div></html>");
                }
            });

            // Mostrar el marco
            frame.setVisible(true);
        });
    }
}

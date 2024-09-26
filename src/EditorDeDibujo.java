import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class EditorDeDibujo extends JFrame {
    private LinkedList<Trazo> trazos = new LinkedList<>();
    private String figuraActual = "Linea";
    private Trazo trazoSeleccionado = null;

    private JPanel panelDibujo = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Trazo trazo : trazos) {
                if (trazo.equals(trazoSeleccionado)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                trazo.dibujar(g);
            }
        }
    };

    public EditorDeDibujo() {
        setTitle("Editor de Dibujos Vectoriales");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelOpciones = new JPanel();
        String[] figuras = {"Linea", "Rectangulo", "Circulo"};
        JComboBox<String> comboFiguras = new JComboBox<>(figuras);
        comboFiguras.addActionListener(e -> figuraActual = (String) comboFiguras.getSelectedItem());
        panelOpciones.add(comboFiguras);

        JButton botonGuardar = new JButton("Guardar Dibujo");
        botonGuardar.addActionListener(e -> GestorArchivos.guardarDibujo(trazos));
        panelOpciones.add(botonGuardar);

        JButton botonCargar = new JButton("Cargar Dibujo");
        botonCargar.addActionListener(e -> {
            LinkedList<Trazo> cargados = GestorArchivos.cargarDibujo();
            if (cargados != null) {
                trazos = cargados;
                repaint();
            }
        });
        panelOpciones.add(botonCargar);

        JButton botonSeleccionar = new JButton("Seleccionar Trazo");
        botonSeleccionar.addActionListener(e -> seleccionarTrazo());
        panelOpciones.add(botonSeleccionar);

        add(panelOpciones, BorderLayout.NORTH);

        panelDibujo.setBackground(Color.WHITE);
        panelDibujo.addMouseListener(new MouseAdapter() {
            private int x1, y1;

            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x2 = e.getX();
                int y2 = e.getY();
                Trazo trazo = null;
                switch (figuraActual) {
                    case "Linea":
                        trazo = new Linea(x1, y1, x2, y2);
                        break;
                    case "Rectangulo":
                        trazo = new Rectangulo(x1, y1, x2, y2);
                        break;
                    case "Circulo":
                        trazo = new Circulo(x1, y1, x2, y2);
                        break;
                }
                trazos.add(trazo);
                repaint();
            }
        });
        add(panelDibujo, BorderLayout.CENTER);
    }

    private void seleccionarTrazo() {
        if (trazos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay trazos para seleccionar.");
            return;
        }

        trazoSeleccionado = trazos.getFirst();
        JOptionPane.showMessageDialog(this, "Trazo seleccionado.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorDeDibujo().setVisible(true));
    }
}
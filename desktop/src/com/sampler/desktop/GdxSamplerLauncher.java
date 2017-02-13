package com.sampler.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.sampler.common.SampleFactory;
import com.sampler.common.SampleInfos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by jarek on 03-Jan-17.
 */
public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private LwjglAWTCanvas lwjglAWTCanvas;
    private JList sampleList;
    private JPanel controlPanel;

    private static final int CELL_WIDTH = 200;
    private static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH;


    public GdxSamplerLauncher() throws HeadlessException {

        init();

        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //tells jframe to resize and layout our components

        pack();
        setVisible(true);

    }

    private void init() {

        createControlPanel();

        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (lwjglAWTCanvas != null) {

                    //will call dispose and close java application
                    lwjglAWTCanvas.stop();
                }
            }
        });

        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //tells jframe to resize and layout our components

        pack();
        setVisible(true);


    }

    public static void main(String[] args) {


        //used to run our jFrame properly
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GdxSamplerLauncher();

            }
        });
    }

    private void createControlPanel() {

        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        //constraints for a scroll pane
        c.gridx = 0; // column
        c.gridy = 0; // row
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1; // weight used when fillin empty space

        sampleList = new JList(SampleInfos.getSampleNames().toArray());

        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    launchSelectedSample();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(sampleList);
        controlPanel.add(scrollPane, c);

        //constraints for a button
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;

        JButton button = new JButton("Launch");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchSelectedSample();
            }

        });

        controlPanel.add(button, c);


    }

    private void launchSelectedSample() {
        String sampleName = (String) sampleList.getSelectedValue();

        if (sampleName == null || sampleName.isEmpty()) {
            System.out.println("Sample name is empty cannot launch...");
            return;
        }

        launchSample(sampleName);

    }

    private void launchSample(String sampleName) {

        System.out.println("launching sample = " + sampleName);

        Container container = getContentPane();

        if (lwjglAWTCanvas != null) {

            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());

        }

        ApplicationListener sample = SampleFactory.newSample(sampleName);



        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        pack();


    }


}

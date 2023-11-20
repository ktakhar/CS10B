import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StereoMP3PlayerGUI {
    private Map<String, String> songs;
    private AdvancedPlayer player;
    private boolean playing;
    private JFrame frame;
    private JComboBox<String> songList;

    public StereoMP3PlayerGUI() {
        this.songs = new HashMap<>();
        this.playing = false;
        initializeSongs();
        createGUI();
    }

    private void initializeSongs() {
        // Add your songs to the map with a user-friendly name and the corresponding MP3 file path.
        songs.put("Song 1", "/path/to/song1.mp3");
        songs.put("Song 2", "/path/to/song2.mp3");
        songs.put("Song 3", "/path/to/song3.mp3");
        // Add more songs as needed.
    }

    public void play(String songName) {
        String mp3FilePath = songs.get(songName);
        if (mp3FilePath == null) {
            System.out.println("Song not found.");
            return;
        }

        try {
            InputStream inputStream = getClass().getResourceAsStream(mp3FilePath);
            Bitstream bitstream = new Bitstream(inputStream);
            playing = true;

            new Thread(() -> {
                try {
                    while (playing) {
                        com.mindprod.common11.Bitstream.readFrame();
                        player = new AdvancedPlayer(bitstream.readFrame());
                        if (player.play(1) == -1) {
                            playing = false;
                        }
                    }
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (playing) {
            playing = false;
            player.close();
        }
    }

    public void createGUI() {
        frame = new JFrame("Stereo MP3 Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create a custom StereoPanel for the stereo-like design.
        StereoPanel stereoPanel = new StereoPanel();
        frame.add(stereoPanel, BorderLayout.CENTER);

        songList = new JComboBox<>(songs.keySet().toArray(new String[0]));
        songList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSong = (String) songList.getSelectedItem();
                stop();
                play(selectedSong);
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select a song: "));
        controlPanel.add(songList);

        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StereoMP3PlayerGUI();
        });
    }
}

class StereoPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw a stereo-like design with speakers, buttons, and volume controls.
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Draw left speaker
        int leftSpeakerX = panelWidth / 4 - 50;
        int speakerY = panelHeight / 2 - 50;
        g.setColor(Color.GRAY);
        g.fillRect(leftSpeakerX, speakerY, 50, 100);
        g.setColor(Color.BLACK);
        g.drawRect(leftSpeakerX, speakerY, 50, 100);

        // Draw right speaker
        int rightSpeakerX = panelWidth * 3 / 4;
        g.setColor(Color.GRAY);
        g.fillRect(rightSpeakerX, speakerY, 50, 100);
        g.setColor(Color.BLACK);
        g.drawRect(rightSpeakerX, speakerY, 50, 100);

        // Draw play/pause button
        int buttonSize = 50;
        int buttonX = panelWidth / 2 - buttonSize / 2;
        int buttonY = panelHeight / 2 - buttonSize / 2;
        g.setColor(Color.GREEN);
        g.fillOval(buttonX, buttonY, buttonSize, buttonSize);
        g.setColor(Color.BLACK);
        g.drawOval(buttonX, buttonY, buttonSize, buttonSize);

        // Draw volume controls
        int volumeX = 20;
        int volumeY = 20;
        int volumeWidth = 20;
        int volumeHeight = 120;
        g.setColor(Color.BLACK);
        g.fillRect(volumeX, volumeY, volumeWidth, volumeHeight);

        // Volume bars
        g.setColor(Color.GREEN);
        int barSpacing = 10;
        int barHeight = 20;
        for (int i = 0; i < 5; i++) {
            int barY = volumeY + i * (barHeight + barSpacing);
            g.fillRect(volumeX + 2, barY, volumeWidth - 4, barHeight);
        }
    }
}

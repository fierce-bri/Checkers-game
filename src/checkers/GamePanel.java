package checkers;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel{
	
	Clip clip;
    AudioInputStream sound;
    JTextField jf;
    JButton button;
    final Icon icon1,icon2;
    static String soundFileName;
    
	public GamePanel () {
        try {
			soundFileName = "sound/Pokemon.wav";
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            AudioFormat format = sound.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);
            clip.start();
            loop();
        }catch (Exception e) {
        	e.printStackTrace();
          } 
        
		jf = new JTextField("");
		Board board = new Board(jf);
		board.setAlignmentY(LEFT_ALIGNMENT);
		
		icon1 = new ImageIcon("images/sound_on.jpg");
    	icon2 = new ImageIcon("images/sound_off.jpg");
    	
    	button = new JButton();
    	button.setText("Sound on");
    	button.setIcon(icon1);
    	
    	button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String op = e.getActionCommand();
				
				if (op.equals("Sound on")) {
					button.setText("Sound off");
					button.setIcon(icon2);
					try {
						stop();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}if (op.equals("Sound off")) {
					button.setText("Sound on");
					button.setIcon(icon1);
					play();
				}
			}
    	});

    	button.setBackground(Color.CYAN);
    	button.setPreferredSize(new Dimension((int) (Square.SIZE * 0.5),(int) (Square.SIZE * 0.7)));
    	
		this.setLayout(new BorderLayout());
		this.requestFocusInWindow(true);
		this.setFocusable(true);
		setPreferredSize(new Dimension(Square.SIZE * 8, Square.SIZE * 10));
	
		this.add(board);
		this.add(button, BorderLayout.NORTH);
	}

    public void stop() throws IOException { 
        clip.close();
        sound.close();
    }
    
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void play() {
        clip.setFramePosition(0);  // Must always rewind!
        //clip.open(sound);
        clip.start();
        loop();
    }
}

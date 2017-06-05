package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class LetUsProunceView extends JFrame {
	
	private static final int HEIGHT = 300, WIDTH = 450;

	private JPanel contentPane;
	private JMenuBar menuBar;
	public JMenu mnFile, mnWordRepo, mnDictate;
	public JMenuItem mntmFile;

	public DictatePane dictatePane;
	public WordRepoPane wordRepoPane;
	/**
	 * Launch the view.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					LetUsProunceView frame = new LetUsProunceView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LetUsProunceView() {
		// add window closing listener
		// pop up a confirm dialog when user is trying to exit
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int option= JOptionPane.showConfirmDialog( 
						LetUsProunceView.this, "Exit system? ", "Confirm Exit", JOptionPane.YES_NO_OPTION); 
				if(option == JOptionPane.YES_OPTION){
					if(e.getWindow() == LetUsProunceView.this){ 
						System.exit(0); 
			        }
				} 
			}
		});
		setBounds(100, 100, 450, 300);
		setResizable(false);
		// create menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmFile = new JMenuItem("Open File...");
		mnFile.add(mntmFile);
		
		mnWordRepo = new JMenu("Word Repo");
		menuBar.add(mnWordRepo);
		
		mnDictate = new JMenu("Dictate");
		menuBar.add(mnDictate);
		
		// create content pane
		contentPane = new JPanel();
		// clear the default layout for the content pane
		contentPane.setLayout(null);
		setContentPane(contentPane);
		addPanels();
	}
	
	private void addPanels() {
		// add word repo pane
		wordRepoPane = new WordRepoPane();
		wordRepoPane.setBounds(0, 0, WIDTH, HEIGHT);
		contentPane.add(wordRepoPane);
		// add dictation pane
		dictatePane = new DictatePane();
		dictatePane.setBounds(0, 0, WIDTH, HEIGHT);
		contentPane.add(dictatePane);
	}

}
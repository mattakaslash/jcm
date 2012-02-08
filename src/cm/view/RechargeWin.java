package cm.view;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.GroupLayout;

import cm.model.Power;
import cm.util.DiceBag;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RechargeWin extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String PREFERRED_LOOK_AND_FEEL = null;

	public RechargeWin() {
		initComponents();
	}

	public RechargeWin(Frame parent) {
		super(parent);
		initComponents();
	}

	public RechargeWin(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public RechargeWin(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public RechargeWin(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public RechargeWin(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public RechargeWin(Dialog parent) {
		super(parent);
		initComponents();
	}

	public RechargeWin(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public RechargeWin(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public RechargeWin(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public RechargeWin(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public RechargeWin(Window parent) {
		super(parent);
		initComponents();
	}

	public RechargeWin(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public RechargeWin(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public RechargeWin(Window parent, String title, ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public RechargeWin(Window parent, String title, ModalityType modalityType,
			GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public RechargeWin(String combatHandle, List<Power> list,
			DiceBag encounterDice) {
		// TODO Auto-generated constructor stub
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(320, 240);
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				RechargeWin dialog = new RechargeWin();
				dialog.setDefaultCloseOperation(RechargeWin.DISPOSE_ON_CLOSE);
				dialog.setTitle("RechargeWin");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

	public List<String> getRecharged() {
		// TODO Auto-generated method stub
		return null;
	}

}

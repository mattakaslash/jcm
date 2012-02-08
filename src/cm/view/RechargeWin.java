package cm.view;

import java.awt.Frame;
import java.util.List;

import javax.swing.JDialog;
import org.dyno.visual.swing.layouts.GroupLayout;

import cm.model.Power;
import cm.util.DiceBag;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RechargeWin extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public RechargeWin() {
		initComponents();
	}

	public RechargeWin(String combatHandle, List<Power> powerList, DiceBag diceBag, Frame parent) {
		super(parent);
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(320, 240);
		setTitle("Recharge Powers");
	}

	public List<String> getRecharged() {
		return null;
	}
}

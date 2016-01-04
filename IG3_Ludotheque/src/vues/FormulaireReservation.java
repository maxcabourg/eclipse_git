package vues;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

public class FormulaireReservation extends JPanel implements ActionListener{
	private JComboBox idR;
	private JComboBox idU;
	private JComboBox idJeuReserve;
	private JTextField tableauIdExtensionsReservees;
	private JDateChooser dateReservation;
	private JDateChooser dateRendu;
	private JCheckBox venuChercher;
	
	FormulaireReservation(){
	//-------------MASK FORMATTER-----------------------------------------------------	
			try {
				MaskFormatter date = new MaskFormatter("##/##/####");
				//-------------TEXT FIELD-----------------------------------------------------
				idU = new JComboBox();
				idU.setPreferredSize(new Dimension(100, 25));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

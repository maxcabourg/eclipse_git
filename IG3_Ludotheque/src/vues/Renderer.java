package vues;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
 
public class Renderer extends DefaultTableCellRenderer{
 
	
	public Renderer(){
		setOpaque(true);
	}
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
    boolean hasFocus, int row, int column)
    {
        return (JButton)value;
    }
}
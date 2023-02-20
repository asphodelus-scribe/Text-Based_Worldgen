import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;


public class window extends JFrame implements MouseMotionListener  {
	private static final long serialVersionUID = 1L;

window(String s){
	super(s);
}

@Override
public void mouseDragged(MouseEvent e) {
	System.out.println("dtut");
		Generate.refresh(e);
		
	}
	


@Override
public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}
package net.sourceforge.plantuml.eclipse.views.actions;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;

import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ImageControlAction;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.DiagramImageControl;
import net.sourceforge.plantuml.util.DiagramModus;
import net.sourceforge.plantuml.util.DiagramModusProvider;

public class ChangeModusMenuAction extends Action implements Observer {

	private DiagramModusProvider modusProvider;


	    public ChangeModusMenuAction() {
			modusProvider = DiagramModusProvider.getModusProvider();
			this.modusProvider.addObserver(this);
			this.setText(PlantumlConstants.CHANGE_MODUS_MENU_CLASS);
		}
	    
	    private void changeModus() {
	    	System.out.println("got notified in Menu: " + modusProvider.getModus());
	    	if(modusProvider.getModus() == DiagramModus.CLASSDIAGRAMM) {
				this.setText(PlantumlConstants.CHANGE_MODUS_MENU_SEQ);
				modusProvider.setModus(DiagramModus.SEQUENZDIAGRAMM);
			} else {
				this.setText(PlantumlConstants.CHANGE_MODUS_MENU_CLASS);
				modusProvider.setModus(DiagramModus.CLASSDIAGRAMM);
			}
	    }
	    
		private void updateModus() {
			if(modusProvider.getModus() == DiagramModus.CLASSDIAGRAMM) {
				this.setText(PlantumlConstants.CHANGE_MODUS_MENU_CLASS);
			} else {
				this.setText(PlantumlConstants.CHANGE_MODUS_MENU_SEQ);
			}
		}

	    public void run() {
	    	this.changeModus();
	    }

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("got notify in Menu Modus:" + arg.toString());
			this.updateModus();
		}

}

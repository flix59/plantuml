package net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.resource.ImageDescriptor;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;
import net.sourceforge.plantuml.util.DiagramModus;
import net.sourceforge.plantuml.util.DiagramModusProvider;

public abstract class ChangeModi extends ImageControlAction implements Observer {

	private DiagramModusProvider modusProvider;
	
	public ChangeModi(ImageControl imageControl) {
		super(imageControl);
		modusProvider = DiagramModusProvider.getModusProvider();
		modusProvider.addObserver(this);
		setImageDescriptor(ImageDescriptor.createFromFile(ImageControlAction.class, ( "/icons/ModusClass.png")));
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("got notify in Button Modus:" + arg.toString());
		this.updateModus();
	}
    
	private void updateModus() {
		if(modusProvider.getModus() == DiagramModus.CLASSDIAGRAMM) {
			setImageDescriptor(ImageDescriptor.createFromFile(ImageControlAction.class, ( "/icons/ModusClass.png")));
		} else {
			setImageDescriptor(ImageDescriptor.createFromFile(ImageControlAction.class, ( "/icons/ModusSeq.png")));
		}
	}
	
	private void changeModus() {
		if(modusProvider.getModus() == DiagramModus.CLASSDIAGRAMM) {
			setImageDescriptor(ImageDescriptor.createFromFile(ImageControlAction.class, ( "/icons/ModusSeq.png")));
			modusProvider.setModus(DiagramModus.SEQUENZDIAGRAMM);
		} else {
			setImageDescriptor(ImageDescriptor.createFromFile(ImageControlAction.class, ( "/icons/ModusClass.png")));
			modusProvider.setModus(DiagramModus.CLASSDIAGRAMM);
		}
	}

	public void run() {
		this.changeModus();
	}
}

package net.sourceforge.plantuml.util;

import java.util.Observable;

public class DiagramModusProvider extends Observable {
	
	private DiagramModus modus;
	
	public DiagramModusProvider() {
		this.modus = DiagramModus.SMALL;
	}
	
	public void setModus(DiagramModus modus) {
	      if(!this.modus.equals(modus)) {
	  			this.modus = modus;

	          setChanged();
	          // trigger notification
	          notifyObservers(modus);
	       }
	}
	
	public DiagramModus getModus() {
		return this.modus;
	}
	
	

}

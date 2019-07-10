package net.sourceforge.plantuml.util;

import java.util.Observable;

public class DiagramModusProvider extends Observable {
	
	private DiagramModus modus;
	private static DiagramModusProvider modusProvider;
	
	private DiagramModusProvider() {
		this.modus = DiagramModus.CLASSDIAGRAMM;
		this.notifyObservers();
	}
	
	public static DiagramModusProvider getModusProvider() {
		if(modusProvider == null) {
			modusProvider = new DiagramModusProvider();
		}
		return modusProvider;
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

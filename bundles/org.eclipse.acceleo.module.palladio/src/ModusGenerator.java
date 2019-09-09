import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;

import net.sourceforge.plantuml.util.DiagramModusProvider;

public abstract class ModusGenerator extends AbstractAcceleoGenerator{
	
	private DiagramModusProvider modus;

	public DiagramModusProvider getModus() {
		return modus;
	}

	public void setModus(DiagramModusProvider modus) {
		this.modus = modus;
	}
}

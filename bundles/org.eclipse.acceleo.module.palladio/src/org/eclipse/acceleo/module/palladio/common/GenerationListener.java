package org.eclipse.acceleo.module.palladio.common;

import org.eclipse.acceleo.engine.event.AcceleoTextGenerationEvent;
import org.eclipse.acceleo.engine.event.IAcceleoTextGenerationListener;

public class GenerationListener implements IAcceleoTextGenerationListener{

	@Override
	public void textGenerated(AcceleoTextGenerationEvent event) {
		// TODO Auto-generated method stub
		System.out.print(event.getText());
	}

	@Override
	public void filePathComputed(AcceleoTextGenerationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileGenerated(AcceleoTextGenerationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generationEnd(AcceleoTextGenerationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean listensToGenerationEnd() {
		// TODO Auto-generated method stub
		return false;
	}

}

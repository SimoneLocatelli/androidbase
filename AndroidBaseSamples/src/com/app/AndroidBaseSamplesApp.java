package com.app;

import com.androidbase.app.ABApplication;
import com.androidbase.app.ApplicationModes;

public class AndroidBaseSamplesApp extends ABApplication {

	@Override
	public ApplicationModes getApplicationMode() {
		return ApplicationModes.Debug;
	}

}

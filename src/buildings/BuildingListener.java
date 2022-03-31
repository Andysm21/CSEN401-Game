package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public interface BuildingListener {
	void onUpgrade(Building b)throws BuildingInCoolDownException, MaxLevelException;
	
}

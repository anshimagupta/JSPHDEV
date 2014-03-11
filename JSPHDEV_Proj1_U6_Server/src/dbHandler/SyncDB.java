package dbHandler;

import model.Automobile;

public interface SyncDB {
	void createAutoDB(Automobile auto);
	void deleteAutoDB(String modelName);
	void updateOptPriceDB(String modelName, String opsetName, String optName, float newPrice);
}

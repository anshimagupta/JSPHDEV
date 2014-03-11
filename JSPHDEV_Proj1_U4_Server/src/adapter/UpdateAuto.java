package adapter;

public interface UpdateAuto {
	void updateOptionSetName(String modelName, String opsetName, String newName);
	void updateOptionPrice(String modelName, String opsetName, String optName, float newPrice);
}

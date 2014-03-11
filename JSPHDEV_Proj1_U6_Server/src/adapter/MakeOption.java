package adapter;

public interface MakeOption {
	void setOptionChoice(String modelName, String opsetName, String optName);
	String getOptionChoice(String modelName, String opsetName);
	float getOptionChoicePrice(String modelName, String opsetName);
}

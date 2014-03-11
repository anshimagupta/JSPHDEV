package server;
import adapter.ProxyAutoMobile;
import model.Automobile;

public class BuildCarModelOptions extends ProxyAutoMobile implements AutoServer{
	public void buildCarModelOptions(String modelName, Automobile auto) {
		buildCarModelOptions(modelName, auto);
	}

	public String printAllModelNames() {
		return printAllModelNames();
	}

	public Automobile returnSelectedAuto(String modelName) {
		return returnSelectedAuto(modelName);
	}
}

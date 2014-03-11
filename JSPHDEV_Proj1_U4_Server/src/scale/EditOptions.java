package scale;


public class EditOptions extends Thread {
	private String modelName;
	private int op; 
	private EditThread et;
	
	public EditOptions(String model_name, EditThread edit_threat, int operation){
		modelName = model_name;
		et = edit_threat;
		op = operation;
	}
	
	public void run(){
		//synchronized(System.out){
			switch (op){
			case 1: 
				et.editOpsetName(modelName);
				break;
			case 2:
				et.editOption(modelName);
				break;
			}
		//}
	}
}

package test;

import scale.EditOptions;
import adapter.BuildAuto;
import adapter.EditThread;

public class Driver_U3 {
	public void startTest(){
		System.out.println("**TEST FOR MULTITHREADING**");
		System.out.println("========================");
		BuildAuto testAutoShop = new BuildAuto();
		testAutoShop.buildAuto("Focus Wagon ZTW", "Focus_Wagon_ZTW.txt", "textFile");
		testAutoShop.printAuto("Focus Wagon ZTW");
		
	   /**
	    *  Here we create 2 threads to modify the same object.
		*  E.g. If we are modifying the option set name (operation no. 0), 
		*  current option set name will first be displayed.
		*  <p>
		*  Case 1: with synchronization statement:
		*  Assume thread 0 enters first, it will lock the auto object,
		*  and changes the thread name, say from 'Color' to 'Color0'.
		*  When thread 1 enters and tries to edit the option set name, 
		*  the displayed option set names will reflect the changes made in thread 0
		*  that is, one won't see option set 'Color', but see 'Color0'.
		*  <p>
		*  Case 2: without synchronization statement:
		*  Both threads can reach the object during the process. 
		*  The "faster" thread finishes the update correctly (say from "Color" to "Color0").
		*  The "slower" thread fails to update the value (say from "Color" to "Color1").
		*  This is because the option set name has already been edited to "Color0" by faster thread.
		*  When the slower thread tries to update the "Color" option set name, 
		*  it finds that the "Color" option set name is no longer there (the value is now "Color0"),
		*  thus an exception will be thrown.
		*  <p>
		*  With this, we show that removing synchronization will cause data corruption.
		*/
		
		EditThread et = new BuildAuto();
		EditOptions eo1 = new EditOptions("Focus Wagon ZTW", et, 1);
		EditOptions eo2 = new EditOptions("Focus Wagon ZTW", et, 1);
		
		eo1.start();
		eo2.start();
	}
}

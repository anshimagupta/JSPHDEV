package adapter;

import dbHandler.SyncDB;
import scale.EditThread;
import server.AutoServer;

public class BuildAuto 
		extends ProxyAutoMobile 
		implements CreateAuto, UpdateAuto, DeleteAuto, MakeOption, EditThread, AutoServer, SyncDB {

	/**
	 * Add generated serial version ID.
	 */
	private static final long serialVersionUID = 7868022980306317394L;
}

package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IRemote extends Remote{
	String iremote() throws RemoteException;
}

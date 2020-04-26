import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISensorServerRMI extends Remote {

	public void insertSensor() throws RemoteException;

	public void updateSensor() throws RemoteException;

	public void deleteSensor() throws RemoteException;

	public void getSensor() throws RemoteException;

	public void getAllSensors() throws RemoteException;

	public void getActiveSensors() throws RemoteException;

	public void increment() throws RemoteException;
}

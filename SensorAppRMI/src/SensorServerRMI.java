import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SensorServerRMI extends UnicastRemoteObject implements ISensorServerRMI {

	private static final long serialVersionUID = 1L;

	private int count = 0;

	protected SensorServerRMI() throws RemoteException {
		super();
		increment();
		System.out.println("Clients: " + count);
	}

	@Override
	public void insertSensor() throws RemoteException {

	}

	@Override
	public void updateSensor() throws RemoteException {

	}

	@Override
	public void deleteSensor() throws RemoteException {

	}

	@Override
	public void getSensor() throws RemoteException {

	}

	@Override
	public void getAllSensors() throws RemoteException {

	}

	@Override
	public void getActiveSensors() throws RemoteException {

	}

	@Override
	public synchronized void increment() throws RemoteException {
		count++;
	}

	public static void main(String[] args) {
		System.setProperty("java.security.policy", "file:allowall.policy");
		try {
			SensorServerRMI sensorServerRMI = new SensorServerRMI();
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("SensorService", sensorServerRMI);
			System.out.println("Sensor server started...");
		} catch (RemoteException remoteException) {
			System.err.println(remoteException.getMessage());
			remoteException.printStackTrace();
		} catch (AlreadyBoundException alreadyBoundException) {
			System.err.println(alreadyBoundException.getMessage());
			alreadyBoundException.printStackTrace();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}
	}
}

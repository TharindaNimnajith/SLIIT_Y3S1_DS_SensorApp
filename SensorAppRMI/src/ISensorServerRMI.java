import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.models.Sensor;

public interface ISensorServerRMI extends Remote {

	public void addSensor(Sensor sensor) throws RemoteException, IOException;

	public void updateSensor(String sensorId, Sensor sensor) throws RemoteException, IOException;

	public void removeSensor(String sensorId) throws RemoteException, IOException;

	public Sensor getSensor(String sensorId) throws RemoteException, IOException;

	public ArrayList<Sensor> getSensorsList() throws RemoteException, IOException;

	public ArrayList<Sensor> getActiveSensorsList() throws RemoteException, IOException;

	public void increment() throws RemoteException;
}

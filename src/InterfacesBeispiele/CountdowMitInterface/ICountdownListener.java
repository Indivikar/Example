package InterfacesBeispiele.CountdowMitInterface;

public interface ICountdownListener {
	public void onTick(int current);
	public void onStart();
	public void onStop();
	public void onPause();
	public void onUnPause();
	public void onMaximum(int maximum);
}

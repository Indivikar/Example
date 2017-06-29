package InterfacesBeispiele.CountdowMitInterface;

public class Main {
	public static void main(String[] args) {
		Countdown c = new Countdown();

		c.setMinimum(0);
		c.setMaximum(5);

		c.addListener(new ICountdownListener() {

			@Override
			public void onUnPause() {
				System.out.println("Unpaused");
			}

			@Override
			public void onTick(int current) {
				System.out.println("Tick: " + current);
			}

			@Override
			public void onStop() {
				System.out.println("Stopped");
			}

			@Override
			public void onStart() {
				System.out.println("Started");
			}

			@Override
			public void onPause() {
				System.out.println("Paused");
			}

			@Override
			public void onMaximum(int maximum) {
				System.out.println("Maximum reached: " + maximum);
			}
		});

		c.startCountdown();
	}
}

package org.somenonprofit.pack1;

/**
 * Demonstrate behavior of two sync block and in between some simple statement in a method.
 * Output::
 *  <br>
in run method two <br>
in run method three <br>
in run method four <br>
in run method one <br>
Called by Threadtwo <br>
Called by Threadthree <br>
in sync block two <br>
Called by Threadfour <br>
Called by Threadone <br>
Outer two <br>
in sync block one <br>
in instance sync blocktwo <br>
in sync block four <br>
Outer one <br>
in instance sync blockone <br>
in sync block three <br>
Outer four <br>
in instance sync blockfour <br>
Outer three <br>
in instance sync blockthree <br>

**/

public class SynchronizedExampleV2 {

	public static void main(String[] args) {
		new Thread(new MyThread(), "one").start();
		new Thread(new MyThread(), "two").start();
		new Thread(new MyThread(), "three").start();
		new Thread(new MyThread(), "four").start();
	}

	private static class A {
		static int a = 1;
		int b = 0;
	}

	private static class B {
		void m() {
			A a = new A();
			System.out.println("Called by Thread"
					+ Thread.currentThread().getName());
			synchronized (A.class) {
				System.out.println("in sync block "
						+ Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println("Outer " + Thread.currentThread().getName());
			synchronized (a) {
				System.out.println("in instance sync block"
						+ Thread.currentThread().getName());
			}
		}
	}

	private static class MyThread implements Runnable {

		@Override
		public void run() {
			System.out.println("in run method "
					+ Thread.currentThread().getName());
			new B().m();
		}

	}
}

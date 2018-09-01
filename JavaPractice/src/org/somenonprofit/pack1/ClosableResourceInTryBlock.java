package org.somenonprofit.pack1;

/**
 * program that demonstrates use of the closable resource which calls resource's (object's) 
 * close method on completion of try block irrespective of exception been thrown from try or not.
 * <br>
 * date : 25-Aug-2018
 * <br>
 * Program Output :
 * <br>
 * closing test obj 2
 * <br>
 * closing test obj 1
 * <br>
 * 
 * <code>Summary : closable resoure's close method called first and then finally got executed.
 * 
 * @author VISHAL
 */
public class ClosableResourceInTryBlock implements AutoCloseable {

	int id;
	public ClosableResourceInTryBlock(int id) { this.id = id; }
	public static void main(String[] args) {
		m();
	}
	
	private static void m() {
		ClosableResourceInTryBlock t = new ClosableResourceInTryBlock(1);
		try (ClosableResourceInTryBlock t2 = new ClosableResourceInTryBlock(2);) {
			// doing nothing
			int a = 1;
		} catch (Exception e) {
			System.out.println("error");
			System.out.println(e.getMessage());
			if (e.getSuppressed().length > 0)
				System.out.println(e.getSuppressed()[0].getMessage());
		} finally {
			try {
				t.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void close() throws Exception {
		System.err.println("closing test obj " + this.id);
	}
}

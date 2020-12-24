/**
 * @author Mackchao.Sun
 * @description:
 * @date:2020/12/23
 **/
public class IncTest {
	private volatile static int i = 0;

	public static void main(String[] args) throws InterruptedException {
//		Thread a = new Thread() {
//			@Override
//			public void run() {
//				for( int j = 0 ; j < 1000000 ; j++ ) i++;
//			}
//		};
//		a.start();
//
//		Thread b = new Thread() {
//			@Override
//			public void run() {
//				for( int j = 0 ; j < 1000000 ; j++ ) i++;
////				for( int j = 0 ; j < 1000000 ; j++ ) i--;
//			}
//		};
//		b.start();
//		a.join();
//		b.join();


		for( int j = 0 ; j < 100 ; j++ ) {
			Thread b2 = new Thread() {
				@Override
				public void run() {
					 i++;
					 try{
						 Thread.sleep(100);
					 }catch (Exception e){

					 }

				}
			};
			b2.start();

		}

//		Thread.sleep(100000);

		System.out.println(i);
	}
}
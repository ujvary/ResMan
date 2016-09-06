import java.text.SimpleDateFormat;

import org.obc.resmgmt.Engagement;
import org.obc.resmgmt.exceptions.ResourcesExhaustedException;
import org.obc.resmgmt.managers.FirstTypeResourceManager;
import org.obc.resmgmt.resources.Engagements;
import org.obc.resmgmt.resources.Queues;
import org.obc.resmgmt.resources.Resource;
import org.obc.resmgmt.resources.ResourcePool;
import org.obc.resmgmt.utils.QueueStatistics;
import org.obc.resmgmt.utils.ResMgmtHelper;

public class Main {
	private ResourcePool pool;
	private Engagements engagements;

	public static void main(String[] args) throws ResourcesExhaustedException {

		new Main().run();

	}

	private void run() throws ResourcesExhaustedException {
		try {

			pool = ResMgmtHelper.fillResourcePool();
			engagements = ResMgmtHelper.fillEngagements();
			System.out.println("KIMENET");
			Queues queues = new FirstTypeResourceManager(engagements, pool).process();
			// for (Engagements es : queues.values()) {
			// for (Engagement e : es)
			// System.out.println(e.toString());
			// }
			System.out.println("STATISZTIKA");

			QueueStatistics qs = new QueueStatistics(queues);

			System.out.println("Erõforrás szükséglet: " + qs.getResourceCount());

			// System.out.println("KIÁLLÁSI IDÕ SZERINT");
			//
			// for(Engagement e : qs.getListByStartTime().values())
			// System.out.println(e);

			System.out.println("JÁRAT ÉS KIÁLLÁSI IDÕ SZERINT");

			for (Engagement e : qs.getListByResourceAndStartTime().values())
				System.out.println(e);
			// for(String s : qs.getListByResourceAndStartTime().keySet())
			// System.out.println(s);

			System.out.println("Busz szükséglet perces bontásban");
			for (Object[] o : qs.getResourceRequirementsProMinutes(engagements)) {
				System.out.println(o[0] + " - " + o[1]);
			}
			;

			System.out.println("Busz futási idõk órában");
			for (Object[] o : qs.countBusRunningTime(queues)) {
				System.out.println(((Resource) o[0]).getName() + " - " + o[1]);
			}
			;

			System.out.println("Busz futások calendarba");
			SimpleDateFormat hour = new SimpleDateFormat("HH");
			SimpleDateFormat min = new SimpleDateFormat("mm");
			int i = 1;
			for (Engagements engs : queues.values()) {
				for (Engagement e : engs) {

					System.out.println("{");
					System.out.println("eventId: " + i + ",");
					System.out.println("startDate: new Date(y, m, d, " + hour.format(e.getDateFrom()) + ","
							+ min.format(e.getDateFrom()) + "),");
					System.out.println("endDate: new Date(y, m, d, " + hour.format(e.getDateTo()) + ","
							+ min.format(e.getDateTo()) + "),");
					System.out.println("name: \"" + e.getLabel() + "\",");
					System.out.println("lane: \"" + e.getResource().getName() + "\"");
					System.out.println("},");

					i++;
				}
			}
		} catch (Exception e) {
			System.err.println("Az adott erõforrásokkal a munka nem osztható be");			
		}

	}

}

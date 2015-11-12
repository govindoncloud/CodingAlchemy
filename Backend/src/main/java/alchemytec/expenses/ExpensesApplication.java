package alchemytec.expenses;

import net.vz.mongodb.jackson.JacksonDBCollection;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.DB;
import com.mongodb.Mongo;

import alchemytec.expenses.api.Expense;
import alchemytec.expenses.healthcheck.MongoHealthCheck;
import alchemytec.expenses.managed.MongoManaged;
import alchemytec.expenses.resources.ExpensesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ExpensesApplication extends Application<ExpensesConfiguration> {

	public static void main(String[] args) throws Exception {
		new ExpensesApplication().run(args);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "AchemyTec";
	}

	@Override
	public void initialize(Bootstrap<ExpensesConfiguration> bootstrap) {
		// TODO Auto-generated method stub
		super.initialize(bootstrap);
		// bootstrap.getObjectMapper().setDateFormat(dateFormat)
	}

	@Override
	public void run(ExpensesConfiguration configuration, Environment environment)
			throws Exception {

		Mongo mongo = new Mongo(configuration.mongohost,
				configuration.mongoport);
		MongoManaged mongoManaged = new MongoManaged(mongo);
		
		environment.lifecycle().manage(mongoManaged);
		
		environment.healthChecks().register("mongo",
				new MongoHealthCheck(mongo));
		
		
		 DB db = mongo.getDB( "alchemytec" );
//        JacksonDBCollection<Expense, String> expenses = JacksonDBCollection.wrap(db.getCollection("expense"), Expense.class, String.class);

		final ExpensesResource expensesResource = new ExpensesResource(db);
		environment.jersey().register(expensesResource);
		environment.getObjectMapper().configure(
				SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

	}

}

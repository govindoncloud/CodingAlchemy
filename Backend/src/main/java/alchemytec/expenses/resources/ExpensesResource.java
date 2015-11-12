package alchemytec.expenses.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;
import alchemytec.expenses.api.Expense;
import alchemytec.expenses.api.Response;

import com.codahale.metrics.annotation.Timed;
import com.mongodb.DB;

@Path("/expenses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class ExpensesResource {

	
	DB db;
	JacksonDBCollection<Expense, String> collection;
	


	public ExpensesResource(DB db) {
		this.db = db;
		collection = JacksonDBCollection.wrap(db.getCollection("expense"), Expense.class, String.class);
	}

	@GET
	@Timed
	public Collection<Expense> getExpenses() {
		
		
		DBCursor<Expense> dbCursor = collection.find();

		List<Expense> expenses = new ArrayList<Expense>();
		while (dbCursor.hasNext()) {
			Expense expense = dbCursor.next();
			expenses.add(expense);
		}

		return expenses;

	}

	@POST
	@Timed
	public Response addExpense(Expense expense) {
		WriteResult result=collection.insert(expense);		
//        return "{\"id\":"+"\""+result.getSavedId().toString()+"\"}";
		return new Response(result.getSavedId().toString());

	}

}

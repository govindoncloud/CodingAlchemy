package alchemytec.expenses.test;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.IOException;

import alchemytec.expenses.api.Expense;
import alchemytec.expenses.ExpensesConfiguration;
import alchemytec.expenses.ExpensesApplication;
import static org.assertj.core.api.Assertions.assertThat;
import alchemytec.expenses.api.Response;

import java.util.*;

public class IntegrationTest {

	private static final String TMP_FILE = createTempFile();
	private static final String CONFIG_PATH = ResourceHelpers
			.resourceFilePath("alchemytec.yaml");

	Expense expense = new Expense(new Double(20.0), "2015-03-29T23:00:00.000Z",
			"some reason", new Double(4.0));
	

	@ClassRule
	public static final DropwizardAppRule<ExpensesConfiguration> RULE = new DropwizardAppRule<ExpensesConfiguration>(
			ExpensesApplication.class, CONFIG_PATH);

	private Client client;

	
	@Before
	public void setUp() throws Exception {
		client = ClientBuilder.newClient();
	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

	private static String createTempFile() {
		try {
			return File.createTempFile("alchemytec", null).getAbsolutePath();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Test
	public void testPostExpense() throws Exception {

		Response response = postExpense();
		assertThat(response).isNotNull();

	}

	@Test
	public void testGetExpenses() throws Exception {

		Response response = postExpense();
		List<Expense> collection = client
				.target("http://localhost:" + RULE.getLocalPort() + "/expenses")
				.request().get(ArrayList.class);
		assertThat(collection).isNotNull();

		assertThat(collection).extracting("_id", String.class).contains(
				response.getId());

	}

	private Response postExpense() {

		Response response = client
				.target("http://localhost:" + RULE.getLocalPort() + "/expenses")
				.request()
				.post(Entity.entity(expense, MediaType.APPLICATION_JSON_TYPE))
				.readEntity(Response.class);
		return response;
	}

}

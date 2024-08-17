package io.fardeen.ipldashboard.data;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import io.fardeen.ipldashboard.model.Match;

@Configuration
@EnableBatchProcessing
//@BatchConfiguration
public class BatchConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Autowired
	public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	private final String[] FIELD_NAME = new String[] { "id", "season", "city", "date", "match_type", "player_of_match",
			"venue", "team1", "team2", "toss_winner", "toss_decision", "winner", "result", "result_margin",
			"target_runs", "target_overs", "super_over", "method", "umpire1", "umpire2" };

	/*
	 * @Bean public FlatFileItemReader<MatchInput> reader() { return new
	 * FlatFileItemReaderBuilder<MatchInput>().name("MatchItemReader") .resource(new
	 * ClassPathResource("matches.csv")).delimited().names(FIELD_NAME)
	 * .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() { {
	 * setTargetType(MatchInput.class); } }).build(); }
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public FlatFileItemReader<MatchInput> reader() {
		return new FlatFileItemReaderBuilder<MatchInput>().name("MatchItemReader")
				.resource(new ClassPathResource("matches.csv")).delimited().names(FIELD_NAME)
				.targetType(MatchInput.class).build();
	}

	@Bean
	MatchDataProcessor processor() {
		return new MatchDataProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Match>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO Match (id,city,date,player_of_match,venue,team1,team2,toss_decision,toss_winner,match_winner,result,result_margin,method,umpire1,umpire2) "
						+ "VALUES (:id,:city,:date,:playerOfMatch,:venue,:team1,:team2,:tossDecision,:tossWinner,:matchWinner,:result,:resultMargin,:method,:umpire1,:umpire2)")
				.dataSource(dataSource).beanMapped().build();
	}

	/*
	 * @Bean public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
	 * return new JdbcBatchItemWriterBuilder<Match>().sql(
	 * "INSERT INTO Match (id, city, date, player_of_match, venue, team1, team2, toss_decision, toss_winner, match_winner, result, result_margin, method, umpire1, umpire2) "
	 * +
	 * "VALUES (:id, :city, :date, :playerOfMatch, :venue, :team1, :team2, :tossDecision, :tossWinner, :matchWinner, :result, :resultMargin, :method, :umpire1, :umpire2)"
	 * ) .dataSource(dataSource).beanMapped().build(); }
	 */

	/*
	 * @Bean public Job importUserJob(JobCompletionNotificationListener listener,
	 * Step step1) { return JobBuilderFactory.get("importUserJob").incrementer(new
	 * RunIdIncrementer()).listener(listener).flow(step1) .end().build(); }
	 */

	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
		return new JobBuilder("importUserJob", jobRepository).listener(listener).start(step1).build();
	}

/*
 * @Bean public Step step1(JdbcBatchItemWriter<Match> writer) { return
 * stepBuilderFactory.get("step1") .<MatchInput, Match>chunk(10) // Specify
 * chunk size, e.g., 10 .reader(reader()) .processor(processor())
 * .writer(writer) .build(); }
 */


  @Bean public Step step1(JobRepository jobRepository,
  DataSourceTransactionManager transactionManager,
  FlatFileItemReader<MatchInput> reader, ItemProcessor<? super MatchInput, ?
  extends Match> processor, JdbcBatchItemWriter<Match> writer) { return new
  StepBuilder("step1", jobRepository) .<MatchInput, Match>chunk(3,
  transactionManager) .reader(reader) .processor(processor) .writer(writer)
  .build(); } }
 */

/*
 * Put Together a Batch Job Now you need to put together the actual batch job.
 * Spring Batch provides many utility classes that reduce the need to write
 * custom code. Instead, you can focus on the business logic.
 * 
 * To configure your job, you must first create a Spring @Configuration class
 * like the following example in
 * src/main/java/com/example/batchprocessing/BatchConfiguration.java. This
 * example uses a memory-based database, meaning that, when it is done, the data
 * is gone. Now add the following beans to your BatchConfiguration class to
 * define a reader, a processor, and a writer:
 * 
 * @Bean public FlatFileItemReader<Person> reader() { return new
 * FlatFileItemReaderBuilder<Person>() .name("personItemReader") .resource(new
 * ClassPathResource("sample-data.csv")) .delimited() .names("firstName",
 * "lastName") .targetType(Person.class) .build(); }
 * 
 * @Bean public PersonItemProcessor processor() { return new
 * PersonItemProcessor(); }
 * 
 * @Bean public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
 * return new JdbcBatchItemWriterBuilder<Person>()
 * .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)"
 * ) .dataSource(dataSource) .beanMapped() .build(); }
 * 
 * @Bean public Job importUserJob(JobRepository jobRepository,Step step1,
 * JobCompletionNotificationListener listener) { return new
 * JobBuilder("importUserJob", jobRepository) .listener(listener) .start(step1)
 * .build(); }
 * 
 * @Bean public Step step1(JobRepository jobRepository,
 * DataSourceTransactionManager transactionManager, FlatFileItemReader<Person>
 * reader, PersonItemProcessor processor, JdbcBatchItemWriter<Person> writer) {
 * return new StepBuilder("step1", jobRepository) .<Person, Person> chunk(3,
 * transactionManager) .reader(reader) .processor(processor) .writer(writer)
 * .build(); }
 */

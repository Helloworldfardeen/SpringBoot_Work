
package com.demo.batch.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.batch.entity.Customer;
import com.demo.batch.repository.CustomerRepository;

@Configuration
@AllArgsConstructor
public class SpringBatchConfig {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager platformTransactionManager;
	private CustomerRepository customerRepository;

	@Bean
	public FlatFileItemReader<Customer> reader() {
		FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<Customer> lineMapper() {
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");

		BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Customer.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;

	}

	@Bean
	public CustomerProcessor processor() {
		return new CustomerProcessor();
	}

	@Bean
	public RepositoryItemWriter<Customer> writer() {
		RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
		writer.setRepository(customerRepository);
		writer.setMethodName("save");
		return writer;
	}

    @Bean
    public Step step1() {
        return new StepBuilder("csvImport", jobRepository)
                .<Customer, Customer>chunk(1000, platformTransactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

	   @Bean
	    public Job runJob() {
	        return new JobBuilder("importStudents", jobRepository)
	                .start(step1())
	                .build();

	    }

	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
	}
}
/*
 * import javax.sql.DataSource;
 * 
 * import org.springframework.batch.core.Job; import
 * org.springframework.batch.core.Step; import
 * org.springframework.batch.core.job.builder.JobBuilder; import
 * org.springframework.batch.core.repository.JobRepository; import
 * org.springframework.batch.core.step.builder.StepBuilder; import
 * org.springframework.batch.item.database.JdbcBatchItemWriter; import
 * org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
 * import org.springframework.batch.item.file.FlatFileItemReader; import
 * org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder; import
 * org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.io.ClassPathResource; import
 * org.springframework.core.task.SimpleAsyncTaskExecutor; import
 * org.springframework.core.task.TaskExecutor; import
 * org.springframework.jdbc.datasource.DataSourceTransactionManager; import
 * com.demo.batch.entity.Customer;
 * 
 * 
 * @Configuration public class SpringBatchConfig {
 * 
 * @Bean public DataSourceTransactionManager transactionManager(DataSource
 * dataSource) { return new DataSourceTransactionManager(dataSource); }
 * 
 * // CSV file ko read karne ke liye FlatFileItemReader bean banaya ja raha hai
 * 
 * @Bean public FlatFileItemReader<Customer> reader() { return new
 * FlatFileItemReaderBuilder<Customer>() .name("csvReader") .resource(new
 * ClassPathResource("customers.csv")) .delimited() .names("id", "firstName",
 * "lastName", "email", "gender", "contactNo", "country", "dob")
 * .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
 * setTargetType(Customer.class); }}) .build(); }
 * 
 * // Processor bean jo Customer object ko process karega
 * 
 * @Bean public CustomerProcessor processor() { return new CustomerProcessor();
 * }
 * 
 * // Writer bean jo processed data ko database mein save karega
 * 
 * @Bean public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
 * return new JdbcBatchItemWriterBuilder<Customer>()
 * .sql("INSERT INTO customer_data (CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, CONTRACT_NO, COUNTRY, DOB) "
 * +
 * "VALUES (:id, :firstName, :lastName, :email, :gender, :contactNo, :country, :dob)"
 * ) .dataSource(dataSource) .beanMapped() .build(); }
 * 
 * 
 * // Ek Job define kar rahe hain jo step1 ko execute karega
 * 
 * // Ek Step define kar rahe hain jo CSV se data read karega, process karega
 * aur phir database mein save karega
 * 
 * @Bean public Step step1(JobRepository jobRepository,
 * DataSourceTransactionManager transactionManager, FlatFileItemReader<Customer>
 * reader, CustomerProcessor processor, JdbcBatchItemWriter<Customer> writer) {
 * return new StepBuilder("step1", jobRepository) .<Customer, Customer>chunk(10,
 * transactionManager) // Chunk size 10 set kar rahe hain .reader(reader) //
 * Reader set kar rahe hain .processor(processor) // Processor set kar rahe hain
 * .writer(writer) // Writer set kar rahe hain .taskExecutor(taskExecutor()) //
 * Async execution ke liye TaskExecutor set kar rahe hain .build(); }
 * 
 * @Bean public Job importUserJob(JobRepository jobRepository, Step step1,
 * JobCompletionNotificationListener listener) { return new
 * JobBuilder("importUserJob", jobRepository) .listener(listener) .start(step1)
 * .build(); }
 * 
 * // TaskExecutor bean jo concurrency limit set karta hai taaki async task
 * execution ho sake
 * 
 * @Bean public TaskExecutor taskExecutor() { SimpleAsyncTaskExecutor
 * asyncTaskExecutor = new SimpleAsyncTaskExecutor();
 * asyncTaskExecutor.setConcurrencyLimit(10); // Concurrency limit 10 set kar
 * rahe hain return asyncTaskExecutor; } }
 */

/*
 * package com.demo.batch.config;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.batch.core.Job; import
 * org.springframework.batch.core.Step; import
 * org.springframework.batch.core.configuration.annotation.
 * EnableBatchProcessing; import
 * org.springframework.batch.core.job.builder.JobBuilder; import
 * org.springframework.batch.core.repository.JobRepository; import
 * org.springframework.batch.core.step.builder.StepBuilder; import
 * org.springframework.batch.item.data.RepositoryItemWriter; import
 * org.springframework.batch.item.database.JdbcBatchItemWriter; import
 * org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
 * import org.springframework.batch.item.file.FlatFileItemReader; import
 * org.springframework.batch.item.file.LineMapper; import
 * org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder; import
 * org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper; import
 * org.springframework.batch.item.file.mapping.DefaultLineMapper; import
 * org.springframework.batch.item.file.transform.DelimitedLineTokenizer; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.io.ClassPathResource; import
 * org.springframework.core.io.FileSystemResource; import
 * org.springframework.core.task.SimpleAsyncTaskExecutor; import
 * org.springframework.core.task.TaskExecutor; import
 * org.springframework.jdbc.datasource.DataSourceTransactionManager; import
 * org.springframework.transaction.PlatformTransactionManager;
 * 
 * import com.demo.batch.entity.Customer; import
 * com.demo.batch.repository.CustomerRepository;
 * 
 * @Configuration public class SpringBatchConfig {
 * 
 * // CSV file ko read karne ke liye FlatFileItemReader bean banaya ja raha hai
 * 
 * @Bean public FlatFileItemReader<Customer> reader() { return new
 * FlatFileItemReaderBuilder()<Customer>() .name("csvReader") .resource(new
 * ClassPathResource("customers.csv")) .delimited() .names("id","firstName",
 * "lastName", "email", " gender", "contractNo", "country", "dob")
 * .targetType(Customer.class) .build();
 * 
 * }
 * 
 * // Processor bean jo Customer object ko process karega
 * 
 * @Bean public CustomerProcessor processor() { return new CustomerProcessor();
 * }
 * 
 * // Writer bean jo processed data ko database mein save karega
 * 
 * @Bean public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
 * return new JdbcBatchItemWriterBuilder<Customer>().sql(
 * "INSERT INTO customer_data (FIRST_NAME, LAST_NAME,EMAIL,GENDER,CONTRACT_NO,COUNTRY,DOB) VALUES (:FIRST_NAME, :LAST_NAME,:EMAIL,:GENDER,:CONTRACT_NO,:COUNTRY,:DOB)"
 * ) .dataSource(dataSource).beanMapped().build();
 * 
 * }
 * 
 * // Ek Step define kar rahe hain jo CSV se data read karega, process karega
 * aur // phir database mein save karega
 * 
 * @Bean
 * 
 * public Step step1(JobRepository jobRepository, DataSourceTransactionManager
 * transactionManager, FlatFileItemReader<Customer> reader, CustomerProcessor
 * processor, JdbcBatchItemWriter<Customer> writer) { return new
 * StepBuilder("csv-step", jobRepository).<Customer, Customer>chunk(10,
 * transactionManager) // Chunk .reader(reader) // Reader set kar rahe hain
 * .processor(processor) // Processor set kar rahe hain .writer(writer) //
 * Writer set kar rahe hain .taskExecutor(taskExecutor()) // Async execution ke
 * liye TaskExecutor set kar rahe hain .build(); }
 * 
 * // Ek Job define kar rahe hain jo step1 ko execute karega
 * 
 * @Bean public Job importUserJob(JobRepository jobRepository, Step step1,
 * JobCompletionNotificationListener listener) { return new
 * JobBuilder("importUserJob",
 * jobRepository).listener(listener).start(step1).build(); }
 * 
 * // TaskExecutor bean jo concurrency limit set karta hai taaki async task //
 * execution ho sake
 * 
 * @Bean public TaskExecutor taskExecutor() { SimpleAsyncTaskExecutor
 * asyncTaskExecutor = new SimpleAsyncTaskExecutor();
 * asyncTaskExecutor.setConcurrencyLimit(10); // Concurrency limit 10 set kar
 * rahe hain return asyncTaskExecutor; } }
 */
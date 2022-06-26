package airport.app.dao.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import airport.app.dao.batch.model.FlightFromBatch;
import airport.app.dao.batch.processor.CsvFlightItemProcessor;
import airport.app.dao.batch.writer.FlightWritter;
import airport.app.dao.model.Flight;
import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfiguration {

	public JobBuilderFactory jobBuilderFactory;
	public StepBuilderFactory stepBuilderFactory;

	private static final int CHUNK_SIZE = 5;

	/**
	 * Job to import flights
	 * 
	 * @param step1
	 * @return
	 */
	@Bean
	public Job importFlightsJob(Step stepImportFlightsFromCsv) {
		return jobBuilderFactory.get("importFlightsJob").incrementer(new RunIdIncrementer())
				.start(stepImportFlightsFromCsv).build();
	}

	/**
	 * Step to read and write in the chunks from csv files
	 * 
	 * @param writer
	 * @param multiCSVResourceItemReader
	 * @param csvFlightItemProcessor
	 * @return
	 */
	@Bean
	public Step stepImportFlightsFromCsv(FlightWritter writer, MultiResourceItemReader<FlightFromBatch> multiCSVResourceItemReader, CsvFlightItemProcessor csvFlightItemProcessor) {
		return stepBuilderFactory.get("stepImportFlightsFromCsv").<FlightFromBatch, Flight>chunk(CHUNK_SIZE)
				.reader(multiCSVResourceItemReader).processor(csvFlightItemProcessor).writer(writer).build();
	}

}
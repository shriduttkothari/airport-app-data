package airport.app.dao.batch.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import airport.app.dao.batch.model.FlightFromBatch;

@Configuration
public class CsvBatchConfiguration {

	@Value("classpath*:/flights/*.csv")
	public Resource[] csvInputResources;

	private static final String[] CSV_TOKEN_NAMES = new String[] { "Departure Time", "Destination",
			"Destination Airport IATA", "Flight No", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	@Bean
	public MultiResourceItemReader<FlightFromBatch> multiCSVResourceItemReader(
			FlatFileItemReader<FlightFromBatch> csvReader) {
		MultiResourceItemReader<FlightFromBatch> resourceItemReader = new MultiResourceItemReader<FlightFromBatch>();
		resourceItemReader.setResources(csvInputResources);
		resourceItemReader.setDelegate(csvReader);
		return resourceItemReader;
	}

	@Bean
	public FlatFileItemReader<FlightFromBatch> csvReader(DefaultLineMapper<FlightFromBatch> csvDefaultLineMapper) {
		FlatFileItemReader<FlightFromBatch> reader = new FlatFileItemReader<FlightFromBatch>();
		reader.setLinesToSkip(1);
		reader.setLineMapper(csvDefaultLineMapper);
		return reader;
	}

	@Bean
	public DefaultLineMapper<FlightFromBatch> csvDefaultLineMapper(LineTokenizer csvLineTokenizer,
			BeanWrapperFieldSetMapper<FlightFromBatch> csvBeanWrapperFieldSetMapper) {
		DefaultLineMapper<FlightFromBatch> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(csvLineTokenizer);
		defaultLineMapper.setFieldSetMapper(csvBeanWrapperFieldSetMapper);
		return defaultLineMapper;
	}

	@Bean
	public BeanWrapperFieldSetMapper<FlightFromBatch> csvBeanWrapperFieldSetMapper() {
		BeanWrapperFieldSetMapper<FlightFromBatch> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(FlightFromBatch.class);
		return beanWrapperFieldSetMapper;
	}

	@Bean
	public DelimitedLineTokenizer csvLineTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		tokenizer.setNames(CSV_TOKEN_NAMES);
		return tokenizer;
	}
}

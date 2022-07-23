package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class DemoApplication {
	//Autowire place on the outside of the function
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// instead of new, use IOC AppConfig to Autowire
	// pro: only need to modify config to make change in everywhere
	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//@Value load application.properties from anywhere
	@Value("${urlTemplate}")
	public String urlTemplate;

	// should not use commandLineRunner, TODO: use controller(Check e.g. symbol; exsitence of starting Time) -> call Service -> call DAO, TODO:
	// the service need to call rather than run everytime
//	@Transactional
//	@Bean
//	CommandLineRunner runner(KlineService klineService) {
//		return args -> {
//			//
//			// multiple api
//			// clientLoad, for loop call load
//
//			// Make url dynamic
//			// symbol, starttime, endtime need to have parameter, check
//			// IOC, put url into properties to make it universal
//			// how: TODO: String.format
//
//			String symbol = "BTC"; // TODO: Enum, use enum, and check
//			String startingTime = "1502942400000";
//			String url = String.format(urlTemplate, symbol, startingTime);
//
//			Object[][] res = restTemplate.getForObject(url, Object[][].class);
//			// instead of bi_raw bi_trans, Use express_type column and put all data in the same table
//// 			For loop insert raw data to bi_raw
////			TODO: Batch Update, test performance (better), compare with for loop currentOut
////			TODO: Test mock 3个
//			for (Object[] kline : res) {
//				jdbcTemplate.update("INSERT INTO bi_raw (open_time, open, high, low, close, volume, close_time,quote_asset_volume, num_of_trades) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
//						kline[0], kline[1], kline[2], kline[3], kline[4], kline[5], kline[6], kline[7], kline[8]);
//
////				Date openTime = new Date(TimeUnit.MILLISECONDS.convert((Long) kline[0], TimeUnit.SECONDS));
////				Date closeTime = new Date(TimeUnit.MILLISECONDS.convert( (Long) kline[6], TimeUnit.SECONDS));
////				double open = Double.parseDouble((String) kline[1]);
////				double high = Double.parseDouble((String) kline[2]);
////				double low = Double.parseDouble((String) kline[3]);
////				double close = Double.parseDouble((String) kline[4]);
////				double volume = Double.parseDouble((String) kline[5]);
////				double quoteAV = Double.parseDouble((String) kline[7]);
////				int numTrades = (Integer) kline[8];
////				jdbcTemplate.update("INSERT INTO bi_trans (open_time, open, high, low, close, volume, close_time,quote_asset_volume, num_of_trades) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", openTime, open, high, low, close, volume, closeTime,quoteAV, numTrades);
//			}
//
//		};
//	}

}

package com.cognizant.stockMarket.Repository;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

//import javax.transaction.Transactional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.stockMarket.Model.StockDetail;


@Repository
//@Transactional
public interface StockDetailRepository extends MongoRepository<StockDetail, Integer>{

	Integer deleteByCompCode(int companycode);

	List<StockDetail> findByCompCodeAndSpCreatedatBetween(int companycode,LocalDateTime spCreatedat, LocalDateTime spUpdateddat);

	boolean existsByCompCode(int compcode);
	
	StockDetail findFirstByCompCodeOrderBySpCreatedatDesc(int compCode);
	
	List<StockDetail> findByCompCode(int compCode);

}

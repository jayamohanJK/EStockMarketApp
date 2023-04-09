/**
 * 
 */
package com.cognizant.stockMarket.Repository;

import java.time.LocalDateTime;
import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.stockMarket.Model.Company;
import com.cognizant.stockMarket.Model.StockDetail;

/**
 * @author 2162702
 *
 */
@Repository

public interface CompanyRepository extends MongoRepository<Company, Integer> {


		public Company findByCompCode(int compCode);
}

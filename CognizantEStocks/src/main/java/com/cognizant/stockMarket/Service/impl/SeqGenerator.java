package com.cognizant.stockMarket.Service.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


import com.cognizant.stockMarket.Model.DatabaseSequence;

@Service
public class SeqGenerator{

	@Autowired
	private MongoOperations mongo;
	
	public int generateSequence(String seqName) {
		DatabaseSequence counter = mongo.findAndModify(query(where("_id").is(seqName)),
	      new Update().inc("seq",1), options().returnNew(true).upsert(true),
	      DatabaseSequence.class);
	    return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}
}

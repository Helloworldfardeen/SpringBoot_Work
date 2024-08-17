package io.fardeen.ipldashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import io.fardeen.ipldashboard.model.Match;
/*  Create an Intermediate Processor A common paradigm in batch processing
 *  is to ingest data,transform it,and then pipe it out somewhere else.
 *  Here,you need to write a simple transformer that converts the names to uppercase.
 *  The following listing(from src/main/java/com/example/batchprocessing/PersonItemProcessor.java)shows how to do so:
 *  */

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

//this is process Method
	@Override
	public Match process(final MatchInput matchInput) throws Exception {

		Match match = new Match();
		match.setId(Long.parseLong(matchInput.getId()));
		match.setCity(matchInput.getCity());
		match.setDate(LocalDate.parse(matchInput.getDate()));
		match.setVenue(matchInput.getPlayer_of_match());
		// Set Team 1 and Team 2 depending on the Innings Order

		String firstInningsTeam, secondInningsTeam;
		if ("bat".equals(matchInput.getToss_winner())) {
			firstInningsTeam = matchInput.getToss_decision();
			secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
					: matchInput.getTeam1();
		} else {
			secondInningsTeam = matchInput.getToss_winner();
			firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
					: matchInput.getTeam1();

		}
		match.setTeam1(firstInningsTeam);
		match.setTeam2(secondInningsTeam);
		match.setTossWinner(matchInput.getToss_winner());
		match.setTossDecision(matchInput.getToss_decision());
		match.setResult(matchInput.getResult_margin());
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());
		return match;

	}

}

/* https://spring.io/guides/gs/batch-processing */
/*
 * 
 * package com.example.batchprocessing;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory;
 * 
 * import org.springframework.batch.item.ItemProcessor;
 * 
 * public class PersonItemProcessor implements ItemProcessor<Person, Person> {
 * 
 * private static final Logger log =
 * LoggerFactory.getLogger(PersonItemProcessor.class);
 * 
 * @Override public Person process(final Person person) { final String firstName
 * = person.firstName().toUpperCase(); final String lastName =
 * person.lastName().toUpperCase();
 * 
 * final Person transformedPerson = new Person(firstName, lastName);
 * 
 * log.info("Converting (" + person + ") into (" + transformedPerson + ")");
 * 
 * return transformedPerson; }
 * 
 * }
 */

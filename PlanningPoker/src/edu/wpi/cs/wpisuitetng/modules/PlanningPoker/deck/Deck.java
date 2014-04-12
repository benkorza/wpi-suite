/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: team struct-by-lightning
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.DeckDeserializer;
import edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models.DeckSerializer;

/**
 * A deck of planning poker cards.
 * 
 * @author Alec Thompson - ajthompson
 * @version Apr 10, 2014
 */
public class Deck {
	/** The name of the deck */
	private String deckName;
	/** The numbers for the cards in the decks */
	private List<Integer> cards;

	/**
	 * Constructor for a deck, in which all the cards are added later.
	 * 
	 * @param deckName
	 *            the name of the Deck
	 */
	public Deck(String deckName) {
		this.deckName = deckName;
		this.cards = new ArrayList<Integer>();
	}

	/**
	 * Constructor for a deck, in which the cards are added at creation in the
	 * form of an ArrayList. The list is then sorted using the static
	 * Collections sort method, to prevent the user from entering a potentially
	 * confusing unordered deck.
	 * 
	 * @param deckName
	 * @param cards
	 */
	public Deck(String deckName, List<Integer> cards) {
		this.deckName = deckName;
		this.cards = cards;
		Collections.sort(this.cards);
	}

	public void addCard(Integer card) {
		this.cards.add(card);
		this.sortDeck();
	}

	public void removeCard(Integer card) {
		this.cards.remove((Integer) card);
	}

	public void sortDeck() {
		Collections.sort(this.cards);
	}

	/**
	 * @return the deckName
	 */
	public String getDeckName() {
		return deckName;
	}

	/**
	 * @return the list of cards
	 */
	public List<Integer> getCards() {
		return cards;
	}

	/**
	 * @param deckName
	 *            the deckName to set
	 */
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	/**
	 * @param cards
	 *            the list of cards to set
	 */
	public void setCards(List<Integer> cards) {
		this.cards = cards;
	}

	// Serializing

	/**
	 * Serializes this Deck into a JSON string.
	 * 
	 * @return the JSON representation of this Deck
	 */
	public String toJSON() {
		String json;

		Gson gson = new GsonBuilder().registerTypeAdapter(Deck.class,
				new DeckSerializer()).create();

		json = gson.toJson(this, Deck.class);

		return json;
	}

	/**
	 * Static method offering comma-delimited JSON serialization of Deck lists
	 * 
	 * @param d
	 *            an array of Decks
	 * @return the serialized array of Decks
	 */
	public static String toJSON(Deck[] d) {
		String json = "[";

		for (Deck a : d) {
			json += a.toJSON() + ", ";
		}

		json += "]";

		return json;
	}

	/**
	 * Reconstruct a Deck from its JSON representation
	 * 
	 * @param json
	 *            the JSON string to user
	 * @return the reconstructed Deck
	 */
	public static Deck fromJSON(String json) {
		DeckDeserializer dd = new DeckDeserializer();
		return dd.deserialize(new JsonParser().parse(json), null, null);
	}

	/**
	 * Reconstruct an array of Decks from a JSON array
	 * 
	 * @param jsonArr
	 *            the JSON array to deserialize
	 * @return an array of reconstructed decks
	 */
	public static Deck[] fromJsonArray(String jsonArr) {
		DeckDeserializer dd = new DeckDeserializer();
		JsonArray array = new JsonParser().parse(jsonArr).getAsJsonArray();
		List<Deck> decks = new ArrayList<Deck>();

		for (JsonElement json : array) {
			decks.add(dd.deserialize(json, null, null));
		}

		return decks.toArray(new Deck[0]);
	}
}

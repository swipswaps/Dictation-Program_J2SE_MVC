package model;

import java.util.Iterator;
import java.util.List;
import controller.DictatePaneController;
import util.Word;
import util.WordRepo;
import util.mvc.controller.MessageHandler;

public class DictationManager implements MessageHandler {
	private WordRepo repo;
	private Iterator<Word> iterator;
	private DictatePaneController controller;

	public DictationManager(WordRepo repo) {
		this.setRepo(repo);
		iterator = repo.getIterator();
	}
	
	public WordRepo getRepo() {
		return repo;
	}

	public void setRepo(WordRepo repo) {
		this.repo = repo;
	}

	public void setController(DictatePaneController controller) {
		this.controller = controller;
	}
	
	public void addWords(List<Word> words) {
		for (Word w: words) {
			repo.put(w);
		}
	}
	
	public Word getNext() {
		if (iterator.hasNext()) {
			return iterator.next();
		} else {
			// if this iterator reaches the end,
			// then continue with a new one
			iterator = repo.getIterator();
			if (iterator.hasNext()) {
				return getNext();
			} else {
				// if the new iterator has no words,
				// then dictation is over!
				return new Word("Congrats!");
			}
		}
	}
	
	public void removeWord() {
		// remove the last word provided by iterator
		try {
			iterator.remove();
		} catch (IllegalStateException e) {
			// next() should be called first!
		}
	}
	
	@Override
	public boolean achieveMessage(String source, String command) {
		switch (source + command) {
			case "btnYesremove_word_and_next":
				removeWord();
			case "btnNokeep_word_and_next":
			case "btnGostart_dictate":
				controller.setWordField(getNext());
				return true;
			default:
				break;
		}
		return false;
	}
}
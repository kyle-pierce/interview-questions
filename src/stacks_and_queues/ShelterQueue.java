package stacks_and_queues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A ShelterQueue represents a Queue of dogs and cats in a shelter
 * where a visitor can dequeue the animal which has been in the 
 * shelter longest or can specify a dog or cat to dequeue.
 * 
 * @author Kyle
 */
public class ShelterQueue {
	
	private Queue<ShelterAnimal> dogs;	// queue of dogs at shelter
	private Queue<ShelterAnimal> cats;	// queue of cats at shelter
	
	/**
	 * Constructs a new ShelterQueue.
	 */
	public ShelterQueue() {
		dogs = new LinkedList<ShelterAnimal>();
		cats = new LinkedList<ShelterAnimal>();
	}
	
	/**
	 * Enqueues the animal with the given isDog classification
	 * into the animals stored by this ShelterQueue.
	 * 
	 * @param isDog true is animal is a dog, false otherwise
	 */
	public void enqueue(boolean isDog) {
		ShelterAnimal animal = new ShelterAnimal(isDog);
		if (isDog) {
			dogs.add(animal);
		} else {
			cats.add(animal);
		}
	}
	
	/** 
	 * Dequeues the animal who has been at the shelter longest.
	 * 
	 * @throws NoSuchElementException if there are not animals to dequeue
	 * @return the dequeued animal
	 */
	public ShelterAnimal dequeueAny() {
		if (dogs.isEmpty() || cats.peek().enqueuedTime < dogs.peek().enqueuedTime) {
			return cats.remove();
		} else {
			return dogs.remove();
		}
	}
	
	/** 
	 * Dequeues the dog who has been at the shelter longest.
	 * 
	 * @throws NoSuchElementException if there are no dogs to dequeue
	 * @return the dequeued dog
	 */
	public ShelterAnimal dequeueDog() {
		return dogs.remove();
	}
	
	/** 
	 * Dequeues the cat who has been at the shelter longest.
	 * 
	 * @throws NoSuchElementException if there are no cats to dequeue
	 * @return the dequeued cat.
	 */
	public ShelterAnimal dequeueCat() {
		return cats.remove();
	}

}

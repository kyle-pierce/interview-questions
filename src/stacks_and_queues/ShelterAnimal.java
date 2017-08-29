package stacks_and_queues;

/* This class represents a ShelterAnimal for use in the
 * ShelterQueue, where animals are either dogs or cats. */
public class ShelterAnimal {
	
	public long enqueuedTime;
	
	/* Constructs a ShelterAnimal that is either a dog
	 * or cat based on the given boolean. */
	public ShelterAnimal(boolean isDog) {
		enqueuedTime = System.currentTimeMillis();
	}
	
}

package com.stream_work.ch03.api;

import java.io.Serializable;
import java.util.List;

/**
 * This Operator class is the base class for all user defined operators.
 */
public abstract class Operator extends Component implements Serializable {
  private static final long serialVersionUID = -1972993710318354151L;

  // Grouping strategy for the incoming data
  private final IGroupingStrategy grouping;

  public Operator(String name, int parallelism) {
    super(name, parallelism);
    this.grouping = new ShuffleGrouping();  // Default
  }

  public Operator(String name, int parallelism, IGroupingStrategy grouping) {
    super(name, parallelism);
    this.grouping = grouping;
  }

  /**
   * Set up instance.
   * @param instance The instance id (an index starting from 0) of this source instance.
   */
  public abstract void setupInstance(int instance);

  /**
   * Apply logic to the incoming event and generate results.
   * The function is abstract and needs to be implemented by users.
   * @param event The incoming event
   * @param eventCollector The outgoing event collector
   */
  public abstract void apply(Event event, List<Event> eventCollector);

  /**
   * Get the grouping key of an event.
   * @return The grouping strategy of this operator
   */
  public IGroupingStrategy getGroupingStrategy() {
    return grouping;
  }
}

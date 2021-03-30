package com.naxanria.mods.fortissimum.util.sidedstate;

import net.minecraft.util.Direction;

import java.util.*;

/*
  @author: Naxanria
*/
public class SidedStateContainer<T extends SidedState>
{
  public final Set<Direction> availableDirections;
  private final Map<Direction, T> stateMap = new HashMap<>();
  
  public SidedStateContainer(Direction[] availableDirections, T defaultState)
  {
    (this.availableDirections = new HashSet<>(Arrays.asList(availableDirections)))
      .forEach(d -> stateMap.put(d, defaultState));
  }
  
  public SidedStateContainer<T> setState(Direction direction, T state)
  {
    assertValid(direction);
    
    stateMap.put(direction, state);
    
    return this;
  }
  
  public T getState(Direction direction)
  {
    assertValid(direction);
    
    return stateMap.get(direction);
  }
  
  private void assertValid(Direction direction)
  {
    assert isValidSide(direction) : "Invalid direction! Direction: " + direction.getName();
  }
  
  public boolean isValidSide(Direction direction)
  {
    return availableDirections.contains(direction);
  }
  
  public static <T extends SidedState<?>> SidedStateContainer<T> full(T defaultState)
  {
    return new SidedStateContainer<>(Direction.values(), defaultState);
  }
  
  public static <T extends SidedState<?>> SidedStateContainer<T> horizontal(T defaultState)
  {
    return new SidedStateContainer<>(new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST}, defaultState);
  }
  
  public static <T extends SidedState<?>> SidedStateContainer<T> vertical(T defaultState)
  {
    return new SidedStateContainer<>(new Direction[]{Direction.UP, Direction.DOWN}, defaultState);
  }
}

package com.naxanria.mods.fortissimum.util.sidedstate;

/*
  @author: Naxanria
*/
public class SidedState<T>
{
  protected T state;
  
  public SidedState(T state)
  {
    this.state = state;
  }
  
  public T getState()
  {
    return state;
  }
}

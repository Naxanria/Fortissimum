package com.naxanria.mods.fortissimum.util.sidedstate;

/*
  @author: Naxanria
*/
public class BooleanSidedState extends SidedState<Boolean>
{
  public static final BooleanSidedState TRUE = new BooleanSidedState(true);
  public static final BooleanSidedState FALSE = new BooleanSidedState(false);
  
  private BooleanSidedState(Boolean state)
  {
    super(state);
  }
}

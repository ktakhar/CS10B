// StateMachineUsingSwitches.java

import java.util.Scanner;

/**
* This class demonstrates a simple example of a state machine.
* A state machine is always in one of a set of "states."
* When a user provides input to a state machine, the machine's
* response depends on the machine's current state and the input.
*
* The big idea is that this is a good way to manage complexity
* when a program's response to input depends on what the machine
* is currently doing.
*
* This implementation of a state machine uses nested switch statements
* to organize processing for the (state, command) combinations.
*
* @author David Habermehl
* @version Last modified 2016_03_22
**/

class StateMachineUsingSwitches {
    /**
    * The machine's current state is represented by a member of
    * the State enum.
    **/
    private enum State {NORTHWEST, NORTH,   NORTHEAST,
                        WEST,      CENTRAL, EAST,
                        SOUTHWEST, SOUTH,   SOUTHEAST}

    /**
    * Valid command are represented by a member of the Command enum.
    **/
    private enum Command {NW, N, NE,
                          W,     E,
                          SW, S, SE,

                             QUIT}

    // The machine is initially in the state stored in INITIALSTATE
    private static final State INITIALSTATE = State.CENTRAL;



    /**
    * This method repeats a simple loop that
    *     1. Displays the current state
    *     2. Get the user's command
    *     3. Updates the machine's state according to the machine's
    *        current state and the user's command.
    *
    * @param args String array filled with command line arguments. This program ignores command line arguments.
    */
    public static void main(String [] args) {
        Scanner keyboard = new Scanner(System.in);
        State state = INITIALSTATE;
        Command command;

        do {
            displayState(state);
            command = getCommand(keyboard);
            state = respondToCommand(state, command);
        } while (command != Command.QUIT);
    }



    /**
    * This method displays a state
    *
    * @param state The state to be displayed.
    */
    private static void displayState(State state) {
        System.out.printf("You are in the %s region\n", state);
    }



    /**
    * This method gets the user's next command.
    *
    * @param keyboard Scanner object used to get user's input.
    */
    private static Command getCommand(Scanner keyboard) {
        Command command = null;
        do {
            System.out.print("\nEnter command (NW, N, NE, W, E, SW, S, SE, QUIT); ");
            String str = keyboard.nextLine().trim().toUpperCase();
            command = switch( str ) {
                case "NW"   -> { yield Command.NW;   }
                case "N"    -> { yield Command.N;    }
                case "NE"   -> { yield Command.NE;   }
                case "W"    -> { yield Command.W;    }
                case "E"    -> { yield Command.E;    }
                case "SW"   -> { yield Command.SW;   }
                case "S"    -> { yield Command.S;    }
                case "SE"   -> { yield Command.SE;   }
                case "QUIT" -> { yield Command.QUIT; }
                default     -> { yield null;         }
            };
        } while( command == null );
        return command;
    }



    /**
    * This method responds to the user's command.
    *
    * @param command The user's command.
    * @param state   The machine's current state.
    * @return The state that results from executing <command> when the machine is in <state>
    */
    private static State respondToCommand(State state, Command command) {
        return switch (state) {
            case NORTHWEST -> {
                yield switch (command) {
                    case E  -> { yield State.NORTH;     }
                    case S  -> { yield State.WEST;      }
                    case SE -> { yield State.CENTRAL;   }
                    default -> { yield state;           }
                };
            }
            case NORTH -> {
                yield switch (command) {
                    case W  -> { yield State.NORTHWEST; }
                    case E  -> { yield State.NORTHEAST; }
                    case SW -> { yield State.WEST;      }
                    case S  -> { yield State.CENTRAL;   }
                    case SE -> { yield State.EAST;      }
                    default -> { yield state;           }
                };
            }
            case NORTHEAST -> {
                yield switch (command) {
                    case W  -> { yield State.NORTH;     }
                    case SW -> { yield State.CENTRAL;   }
                    case S  -> { yield State.EAST;      }
                    default -> { yield state;           }
                };
            }
            case WEST -> {
                yield switch (command) {
                    case N  -> { yield State.NORTHWEST; }
                    case NE -> { yield State.NORTH;     }
                    case E  -> { yield State.CENTRAL;   }
                    case S  -> { yield State.SOUTHWEST; }
                    case SE -> { yield State.SOUTH;     }
                    default -> { yield state;           }
                };
            }
            case CENTRAL -> {
                yield switch (command) {
                    case NW -> { yield State.NORTHWEST; }
                    case N  -> { yield State.NORTH;     }
                    case NE -> { yield State.NORTHEAST; }
                    case W  -> { yield State.WEST;      }
                    case E  -> { yield State.EAST;      }
                    case SW -> { yield State.SOUTHWEST; }
                    case S  -> { yield State.SOUTH;     }
                    case SE -> { yield State.SOUTHEAST; }
                    default -> { yield state;           }
                };
            }
            case EAST -> {
                yield switch (command) {
                    case NW -> { yield State.NORTH;     }
                    case N  -> { yield State.NORTHEAST; }
                    case W  -> { yield State.CENTRAL;   }
                    case SW -> { yield State.SOUTH;     }
                    case S  -> { yield State.SOUTHEAST; }
                    default -> { yield state;           }
                };
            }
            case SOUTHWEST -> {
                yield switch (command) {
                    case N  -> { yield State.WEST;      }
                    case NE -> { yield State.CENTRAL;   }
                    case E  -> { yield State.SOUTH;     }
                    default -> { yield state;           }
                };
            }
            case SOUTH -> {
                yield switch (command) {
                    case NW -> { yield State.WEST;      }
                    case N  -> { yield State.CENTRAL;   }
                    case NE -> { yield State.EAST;      }
                    case W  -> { yield State.SOUTHWEST; }
                    case E  -> { yield State.SOUTHEAST; }
                    default -> { yield state;           }
                };
            }
            case SOUTHEAST -> {
                yield switch (command) {
                    case NW -> { yield State.CENTRAL;   }
                    case N  -> { yield State.EAST;      }
                    case W  -> { yield State.SOUTH;     }
                    default -> { yield state;           }
                };
            }
        };
    }
}

package tictactoe.utils;


public enum Symbol {

    CIRCLE {
        public String toString() {
            return "O";
        }
    },

    CROSS {
        public String toString() {
            return "X";
        }
    },

    EMPTY {
        public String toString() {
            return "?";
        }
    };

    public static int getValue(Symbol symbol) {
        switch (symbol) {
            case EMPTY : {
                return 0;
            }
            case CIRCLE : {
                return -10;
            }
            case CROSS : {
                return +10;
            }
        }
        return 0;
    }

    public boolean isEmpty() {
        return (this == Symbol.EMPTY);
    }

    public boolean isCircle() {
        return (this == Symbol.CIRCLE);
    }

    public boolean isCross() {
        return (this == Symbol.CROSS);
    }
}
